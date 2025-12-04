package poly.edu.lab8.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.*;
import poly.edu.lab8.entity.ChatMessage;

@Component
public class WebSocketListener {
    @Autowired
    private SimpMessageSendingOperations client;

    @EventListener
    public void connect(SessionConnectEvent event) {
        System.out.println("connect()");
    }
    @EventListener
    public void clientConnected(SessionConnectedEvent event) {
        System.out.println("connected()");
    }

    @EventListener
    public void disconnected(SessionDisconnectEvent event) {
        System.out.println("disconnected()");
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setType(ChatMessage.MessageType.LEAVE);
        chatMessage.setSender("poly");
        chatMessage.setContent("Poly left room");
        client.convertAndSend("/topic/simple-chat", chatMessage);
    }

    @EventListener
    public void subscribe(SessionSubscribeEvent event) {
        System.out.println("subscribe()");
    }

    @EventListener
    public void unsubscribe(SessionUnsubscribeEvent event) {
        System.out.println("unsubscribe()");
    }
}
