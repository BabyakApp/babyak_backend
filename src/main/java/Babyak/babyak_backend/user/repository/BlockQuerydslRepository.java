package Babyak.babyak_backend.user.repository;

import Babyak.babyak_backend.user.entity.Block;
import Babyak.babyak_backend.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static Babyak.babyak_backend.user.entity.QBlock.block;
import static Babyak.babyak_backend.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class BlockQuerydslRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public Block findByEmail(String email) {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        return jpaQueryFactory
                .selectFrom(block)
                .where(user.email.eq(email))
                .from(user)
                .fetchOne();
    }
}
