package Babyak.babyak_backend.chat.repository;

import Babyak.babyak_backend.chat.dto.ChatRoomDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static Babyak.babyak_backend.chat.entity.QChatRoom.chatRoom;

@Repository
@RequiredArgsConstructor
public class ChatRoomQuerydslRepository {

    @PersistenceContext
    private EntityManager em;

    /* 채팅방 생성 순서 최신 순으로 반환 */
    @Transactional(readOnly = true)
    public List<ChatRoomDto> findAllRooms() {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        List<ChatRoomDto> chatRoomDtoList = jpaQueryFactory
                .select(Projections.fields(ChatRoomDto.class,
                        chatRoom.id,
                        chatRoom.title))
                .from(chatRoom)
                .groupBy(chatRoom.id)
                .orderBy(chatRoom.id.desc())
                .fetch();

        return chatRoomDtoList;

    }

    /* findRoomById */

    /* createChatRoom */

}
