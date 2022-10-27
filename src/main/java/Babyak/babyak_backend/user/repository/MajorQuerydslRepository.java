package Babyak.babyak_backend.user.repository;


import Babyak.babyak_backend.user.entity.Major;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static Babyak.babyak_backend.user.entity.QMajor.major1;

@Repository
@RequiredArgsConstructor
public class MajorQuerydslRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public Major findByMajor(String major) {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        return jpaQueryFactory
                .selectFrom(major1)
                .where(major1.major.eq(major))
                .fetchOne();
    }
}
