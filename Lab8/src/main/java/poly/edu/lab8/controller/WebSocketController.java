package poly.edu.lab8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import poly.edu.lab8.entity.ChatMessage;

@Controller
public class WebSocketController {
    @Autowired
    private SimpMessageSendingOperations client;

    @ResponseBody
    @RequestMapping("/test")
    public void name() {
        var msg = new ChatMessage();
        msg.setType(ChatMessage.MessageType.LEAVE);
        msg.setSender("Hello");
        client.convertAndSend("/topic/simple-chat", msg);
    }

    @MessageMapping("/chat.sendMessage") // [/app/chat.sendMessage]
    @SendTo("/topic/publicChatRoom")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        System.out.println("WebSocketController.sendMessage()");
        return chatMessage;
    }

    @MessageMapping("/chat.addUser") // [/app/chat.addUser]
    @SendTo("/topic/publicChatRoom")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("WebSocketController.addUser()");
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
