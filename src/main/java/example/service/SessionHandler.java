package example.service;

import org.springframework.web.socket.WebSocketSession;

public interface SessionHandler {

    void register(WebSocketSession session);
    void unregister(WebSocketSession session);
}
