package Babyak.babyak_backend.common;

import Babyak.babyak_backend.chat.handler.WebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
    }

    /*
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // 웹소켓 서버의 엔드포인트
                .setAllowedOrigins("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // '/sub'가 prefix로 붙은 destination의 클라이언트에게 메세지 보낼 수 있도록
        registry.enableSimpleBroker("/sub");
        //registry.setPathMatcher(new AntPathMatcher(".")); // url을 chat/room/3 -> chat.room.3로 참조
        // '/pub'가 prefix로 붙은 메세지들은 @MessageMapping이 붙은 method로 바운드
        registry.setApplicationDestinationPrefixes("/pub");

        //registry.enableStompBrokerRelay("/queue", "/topic", "/exchange", "/amq/queue");
    }
    */
}
