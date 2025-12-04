package poly.edu.lab8.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ChatMessage {
    @Builder.Default
    private MessageType type = MessageType.CHAT;
    private String content;
    private String sender;
    public enum MessageType {
        CHAT, JOIN, LEAVE
    }
}
