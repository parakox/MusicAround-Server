package example.service.impl;

import example.model.entity.User;
import example.service.SessionHandler;
import example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class SessionHandlerImpl implements SessionHandler {
    @Autowired
    private UserService userService;
    @Override
    public void register(WebSocketSession session) {
        Long userId = Long.valueOf((String) session.getAttributes().get("id"));
        User user = userService.findById(userId);
        user.setOnline(true);
        userService.save(user);
    }

    @Override
    public void unregister(WebSocketSession session) {
        Long userId = Long.valueOf((String) session.getAttributes().get("id"));
        User user = userService.findById(userId);
        user.setOnline(false);
        userService.save(user);
    }
}
