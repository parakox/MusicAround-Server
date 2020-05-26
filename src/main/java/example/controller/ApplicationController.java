package example.controller;

import example.model.EmailSender;
import example.model.Constants;
import example.model.entity.Chat;
import example.model.entity.Message;
import example.model.entity.User;
import example.model.entity.VerificationToken;
import example.service.ChatService;
import example.service.UserService;
import example.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Controller
public class ApplicationController {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;
    @Autowired
    private VerificationTokenService verificationTokenService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/chat/{id}")
    public void send(@DestinationVariable("id") Long id,
                        @RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Message message) {
        message.setDate(new Date());
        Chat chat = chatService.findById(id);
        User user = userService.findByEmailAndPassword(email,password);
        if(chat!=null && user!=null && user.getChats().contains(chat)){
            simpMessagingTemplate.convertAndSend("/currentChat/"+id,message);
            chat.getMessages().add(message);
            chatService.save(chat);
        }
    }
    @MessageMapping("/updateForMap")
    public void updateForMap(@RequestBody User user){
        if(userService.findByEmailAndPassword(user.getEmail(),user.getPassword())!=null){
            simpMessagingTemplate.convertAndSend("/map",user);
            userService.save(user);
        }
    }

    @GetMapping("/confirmRegistration")
    @ResponseBody
    public String confirmRegistration(@RequestParam("token") String token){
        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        if(verificationToken!= null && verificationToken.getExpiryDate().after(new Date())){
            User user = verificationToken.getUser();
            user.setConfirmed(true);
            userService.save(user);
            verificationTokenService.delete(verificationToken);
            return "success";
        }
        return "failure";
    }
    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestParam("email") String email,
                           @RequestParam("nickname") String nickname,
                           @RequestParam("password") String password){
        if(userService.findByEmail(email)==null && userService.findByNickname(nickname)==null) {
            userService.save(new User(email, nickname, password, false,false, new ArrayList<>(),0d,0d));
            VerificationToken verificationToken = new VerificationToken(UUID.randomUUID().toString(),
                    userService.findByEmail(email));
            verificationTokenService.save(verificationToken);
            new EmailSender(javaMailSender, new String[]{email},
                    "Confirm your email",
                    Constants.ADDRESS + "confirmRegistration?token=" + verificationToken.getToken()).send();
            return "success";
        }
        return "failure";
    }
    @PostMapping("/logIn")
    @ResponseBody
    public String logIn(@RequestParam("email") String email,
                      @RequestParam("password") String password){
        User user = userService.findByEmailAndPassword(email,password);
        if(user!=null && user.getConfirmed()){
            return "success";
        }
        return "failure";
    }
    @GetMapping("/user")
    @ResponseBody
    public User getUserByEmailAndPassword(@RequestParam("email") String email,
                            @RequestParam("password") String password){
        return userService.findByEmailAndPassword(email,password);
    }
    @GetMapping("/user/{id}")
    @ResponseBody
    public User getUserById(@PathParam("id") Long id){
        User user = userService.findById(id);
        if(user!=null) {
            user.setEmail("");
            user.setPassword("");
        }
        return user;
    }
    @GetMapping("/chat")
    @ResponseBody
    public Chat getChatById(@RequestParam("chatId") Long chatId,
                            @RequestParam("email") String email,
                            @RequestParam("password") String password){
        User user = userService.findByEmailAndPassword(email,password);
        Chat chat = chatService.findById(chatId);
        if(user!=null && chat!=null && user.getChats().contains(chat)){
            return chat;
        }
        return null;
    }
}
