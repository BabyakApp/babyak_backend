package Babyak.babyak_backend.post.repository;

import Babyak.babyak_backend.post.dto.PostResponse;

import Babyak.babyak_backend.post.entity.Post;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static Babyak.babyak_backend.post.entity.QPost.post;

@Repository
@RequiredArgsConstructor
public class PostQuerydslRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<PostResponse> findAllPost() {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        List<PostResponse> postList = jpaQueryFactory
                .select(Projections.fields(PostResponse.class,
                        post.title,
                        post.detail,
                        post.numOfPeople.as("maxPeople"),
                        post.meetingSite,
                        post.preferredFood,
                        post.meetingDate,
                        post.meetingTime,
                        post.nickname,
                        post.major
                ))
                .from(post)
                .groupBy((post.postId))
                .orderBy(post.postId.desc())
                .fetch();

        return postList;
    }

}
