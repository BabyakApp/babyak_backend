package Babyak.babyak_backend.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //private final WebSocketHandler webSocketHandler;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 메세지를 발생하는 요청의 prefix: /pub
        config.enableSimpleBroker("/sub");
        // 메세지를 구독하는 요청의 prefix: /sub
        config.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // stomp websocket의 연결 endpoint: /ws-stomp
        registry.addEndpoint("/ws-stomp").setAllowedOriginPatterns("*")
                .withSockJS();
    }

    /*
    STOMP(X). 그냥 Server-Client의 WebSocket 구조
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
    }
     */

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
