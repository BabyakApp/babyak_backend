package Babyak.babyak_backend.like.repository;

import Babyak.babyak_backend.like.dto.LikeListResponse;
import Babyak.babyak_backend.post.entity.Post;
import Babyak.babyak_backend.user.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static Babyak.babyak_backend.like.entity.QLike.like;
@Repository
@RequiredArgsConstructor
public class LikeQuerydslRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<LikeListResponse> findLikeListByUserId (Long userId) {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        List<LikeListResponse> likeList = jpaQueryFactory
                .select(Projections.fields(LikeListResponse.class,
                        like.post.title,
                        like.post.detail,
                        like.post.numOfPeople.as("maxPeople"),
                        like.post.meetingSite,
                        like.post.preferredFood,
                        like.post.meetingDate,
                        like.post.meetingTime,
                        like.post.nickname,
                        like.post.major))
                .from(like)
                .where(like.user.id.eq(userId))
                .groupBy(like.likeId)
                .orderBy(like.likeId.desc())
                .fetch();

        return likeList;

    }
}
