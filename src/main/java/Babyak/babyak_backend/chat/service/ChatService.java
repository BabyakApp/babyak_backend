package Babyak.babyak_backend.chat.service;

import Babyak.babyak_backend.chat.dto.ChatMessageDto;
import Babyak.babyak_backend.chat.dto.ChatRoomDto;
import Babyak.babyak_backend.chat.repository.ChatRoomRepository;
import Babyak.babyak_backend.chat.repository.ChattingRoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChannelTopic channelTopic;
    private final RedisTemplate redisTemplate;
    private final ChattingRoomRepository chattingRoomRepository;

    /**
     * destination 정보에서 roomId 추출
     */
    public String getRoomId (String destination) {
        int lastIndex = destination.lastIndexOf('/');
        if (lastIndex != -1)
            return destination.substring(lastIndex + 1);
        else
            return "";
    }

    /**
     * 채팅방에 메세지 발송
     */
    public void sendChatMessage (ChatMessageDto chatMessage) {
        chatMessage.setUserCount(chattingRoomRepository.getUserCount(chatMessage.getRoomId()));

        if (ChatMessageDto.MessageType.ENTER.equals(chatMessage.getType())) {
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
            chatMessage.setSender("[알림]");
        }
        else if (ChatMessageDto.MessageType.QUIT.equals(chatMessage.getType())) {
            chatMessage.setMessage(chatMessage.getSender()  + "님이 퇴장했습니다.");
            chatMessage.setSender("[알림]");
        }
        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);
    }

}
