package Babyak.babyak_backend.chat.repository;

import Babyak.babyak_backend.chat.dto.ChatRoomDto;
import Babyak.babyak_backend.common.service.RedisSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
@Repository
public class ChattingRoomRepository {

    // 채팅방(topic)에 발행되는 메세지를 처리할 Listener
    private final RedisMessageListenerContainer redisMessageListener;
    // 구독 처리 서비스
    private final RedisSubscriber redisSubscriber;
    // Redis
    private static final String CHAT_ROOMS = "CHAT_ROOM";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoomDto> opsHashChatRoom;
    // 채팅방의 대화 메세지를 발행하기 위한 Redis topic 정보. 서버별로 채팅방에 매치되는 topic 정보를 Map에 넣어 roomId로 찾을 수 있도록 한다.
    private Map<String, ChannelTopic> topics;
   // private Map<String, ChatRoomDto> chatRoomMap; // 서버에 생성된 모든 채팅방의 정보를 모아둠

    // 채팅방 정보 저장은 일단 외부 저장소 이용 안 하고 HashMap에 저장

    @PostConstruct
    private void init() {
        opsHashChatRoom = redisTemplate.opsForHash();
        topics = new HashMap<>();
    }

    /* 채팅방 조회: 채팅방 Map에 저장된 정보 조회 */
    public List<ChatRoomDto> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        //List chatRooms = new ArrayList<>(chatRoomMap.values());
        //Collections.reverse(chatRooms);
        //return chatRooms;
        return opsHashChatRoom.values(CHAT_ROOMS);
    }

    public ChatRoomDto findRoomById (String id) {
        //return chatRoomMap.get(id);
        return opsHashChatRoom.get(CHAT_ROOMS, id);
    }

    /* 채팅방 생성: random UUID로 구별 ID를 가진 채팅방 객체 생성 후 채팅방 Map에 추가
    * -> 변경: 서버간 채팅방 공유를 위해 Redis Hash에 저장한다.
    * */
    public ChatRoomDto createChatRoom (String name) {
        ChatRoomDto chatRoom = ChatRoomDto.create(name);
        //chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        opsHashChatRoom.put(CHAT_ROOMS, chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    /*
    채팅방 입장: Redis에 topic을 만들고 pub/sub 통신을 하기 위해 Listener 설정
     */
    public void enterChatRoom(String roomId) {
        ChannelTopic topic = topics.get(roomId);
        if (topic == null) {
            topic = new ChannelTopic(roomId);
            redisMessageListener.addMessageListener(redisSubscriber, topic);
            topics.put(roomId, topic);
        }
    }

    public ChannelTopic getTopic(String roomId) {
        return topics.get(roomId);
    }

    /* 메세지 발송: 지정한 WebSocket 세션에 메세지 발송
    public <T> void sendMessage (WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
     */
}
