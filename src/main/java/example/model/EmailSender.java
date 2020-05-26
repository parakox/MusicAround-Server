package example.model;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class EmailSender {

    private JavaMailSender javaMailSender;

    private SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

    public EmailSender(JavaMailSender javaMailSender,String[] emails,String subject,String text){
        this.javaMailSender = javaMailSender;
        simpleMailMessage.setTo(emails);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
    }
    public void send(){
        javaMailSender.send(simpleMailMessage);
    }
}
