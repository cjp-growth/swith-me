package project.swithme.notification.core.handler;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
public class NotificationWebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(
        WebSocketSession session,
        TextMessage message
    ) throws Exception {
    }

    @Override
    public void afterConnectionClosed(
        WebSocketSession webSocketSession,
        CloseStatus status
    ) throws IOException {
        webSocketSession.close();
    }
}
