package Babyak.babyak_backend.user.repository;

import Babyak.babyak_backend.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static Babyak.babyak_backend.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserQuerydslRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public User findByEmail(String email) {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        return jpaQueryFactory
                .selectFrom(user)
                .where(user.email.eq(email))
                .fetchOne();

    }

    @Transactional(readOnly = true)
    public User findByNickname(String nickname) {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        return jpaQueryFactory
                .selectFrom(user)
                .where(user.nickname.eq(nickname))
                .fetchOne();

    }

    @Transactional
    public void updateNoShow(String email) {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        jpaQueryFactory
                .update(user)
                .set(user.noShows, user.noShows.add(1))
                .where(user.email.eq(email))
                .execute();

        em.flush();
        em.clear();

    }


    @Transactional(readOnly = true)
    public int getNoShow(String email) {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        return jpaQueryFactory
                .select(user.noShows)
                .where(user.email.eq(email))
                .from(user)
                .fetchOne();
    }
}
