package example.model;

import example.service.SessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SubProtocolWebSocketHandler;

public class CustomSubProtocolWebSocketHandler extends SubProtocolWebSocketHandler {

    @Autowired
    private SessionHandler sessionHandler;

    public CustomSubProtocolWebSocketHandler(MessageChannel clientInboundChannel, SubscribableChannel clientOutboundChannel) {
        super(clientInboundChannel, clientOutboundChannel);
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionHandler.register(session);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        sessionHandler.unregister(session);
        super.afterConnectionClosed(session, closeStatus);
    }
}
