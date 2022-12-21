package Babyak.babyak_backend.chat.repository;

import Babyak.babyak_backend.chat.dto.ChatRoomDto;
import Babyak.babyak_backend.common.service.RedisSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
@Repository
public class ChattingRoomRepository {

    // 채팅방(topic)에 발행되는 메세지를 처리할 Listener
    //private final RedisMessageListenerContainer redisMessageListener;
    // 구독 처리 서비스
    //private final RedisSubscriber redisSubscriber;

    // Redis CacheKeys
    private static final String CHAT_ROOMS = "CHAT_ROOM"; // 채팅방 저장
    public static final String USER_COUNT = "USER_COUNT"; // 채팅방에 입장한 클라이언트 수 저장
    public static final String ENTER_INFO = "ENTER_INFO"; // 채팅방에 입장한 클라이언트의 sessionId와 채팅방 id를 맵핑한 정보 저장

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, ChatRoomDto> hashOpsChatRoom;
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> hashOpsEnterInfo;
    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOps;

    //private final RedisTemplate<String, Object> redisTemplate;
    //private HashOperations<String, String, ChatRoomDto> opsHashChatRoom;

    // 채팅방의 대화 메세지를 발행하기 위한 Redis topic 정보. 서버별로 채팅방에 매치되는 topic 정보를 Map에 넣어 roomId로 찾을 수 있도록 한다.
    //private Map<String, ChannelTopic> topics;
    // private Map<String, ChatRoomDto> chatRoomMap; // 서버에 생성된 모든 채팅방의 정보를 모아둠
    // 채팅방 정보 저장은 일단 외부 저장소 이용 안 하고 HashMap에 저장

//    @PostConstruct
//    private void init() {
//        opsHashChatRoom = redisTemplate.opsForHash();
//    }

    /**
     *  모든 채팅방 조회
     */
    public List<ChatRoomDto> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        //List chatRooms = new ArrayList<>(chatRoomMap.values());
        //Collections.reverse(chatRooms);
        //return chatRooms;
        //return opsHashChatRoom.values(CHAT_ROOMS);
        return hashOpsChatRoom.values(CHAT_ROOMS);
    }

    /**
     *  특정 채팅방 조회
     */
    public ChatRoomDto findRoomById (String id) {
        //return chatRoomMap.get(id);
        //return opsHashChatRoom.get(CHAT_ROOMS, id);
        return hashOpsChatRoom.get(CHAT_ROOMS, id);
    }

    /**
     *  채팅방 생성
     *  : random UUID로 구별 ID를 가진 채팅방 객체 생성 후 채팅방 Map에 추가
     * -> 변경: 서버간 채팅방 공유를 위해 Redis Hash에 저장한다.
    */
    public ChatRoomDto createChatRoom (String name) {
        ChatRoomDto chatRoom = ChatRoomDto.create(name);
        //chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        //opsHashChatRoom.put(CHAT_ROOMS, chatRoom.getRoomId(), chatRoom);
        hashOpsChatRoom.put(CHAT_ROOMS, chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    /**
     * 유저가 입장한 채팅방 id와 유저의 sessionId 맵핑 정보 저장
     */
    public void setUserEnterInfo (String sessionId, String roomId) {
        hashOpsEnterInfo.put(ENTER_INFO, sessionId, roomId);
    }

    /**
     * 유저 세션으로 입장해 있는 채팅방 id 조회
     */
    public String getUserEnterRoomId (String sessionId) {
        return hashOpsEnterInfo.get(ENTER_INFO, sessionId);
    }

    /**
     * 유저 세션 정보와 맵핑된 채팅방 id 삭제
     */
    public void removeUserEnterInfo (String sessionId) {
        hashOpsEnterInfo.delete(ENTER_INFO, sessionId);
    }

    /**
     * 채팅방 유저 수 조회
     */
    public Long getUserCount (String roomId) {
        return Long.valueOf(Optional.ofNullable(valueOps.get(USER_COUNT + "_" + roomId)).orElse("0"));
    }

    /**
     * 채팅방에 입장한 유저 수 +1
     */
    public Long plusUserCount (String roomId) {
        return Optional.ofNullable(valueOps.increment(USER_COUNT + "_" + roomId)).orElse(0L);
    }

    /**
     * 채팅방에 입장한 유저 수 -1
     */
    public Long minusUserCount (String roomId) {
        return Optional.ofNullable(valueOps.decrement(USER_COUNT + "_" + roomId))
                .filter(count -> count > 0)
                .orElse(0L);
    }
    /*
    채팅방 입장: Redis에 topic을 만들고 pub/sub 통신을 하기 위해 Listener 설정
    public void enterChatRoom(String roomId) {
        ChannelTopic topic = topics.get(roomId);
        if (topic == null) {
            topic = new ChannelTopic(roomId);
            redisMessageListener.addMessageListener(redisSubscriber, topic);
            topics.put(roomId, topic);
        }
    }
     */

    /*
    public ChannelTopic getTopic(String roomId) {
        return topics.get(roomId);
    }
     */

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
