package project.swithme.notification.common.configuration.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import project.swithme.notification.core.handler.NotificationWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSockConfiguration implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler;

    public WebSockConfiguration(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(
                new NotificationWebSocketHandler(),
                "/api/notifications"
            )
            .setAllowedOrigins("*");
    }
}
