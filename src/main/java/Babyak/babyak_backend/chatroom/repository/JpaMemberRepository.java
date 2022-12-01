package Babyak.babyak_backend.chatroom.repository;

import Babyak.babyak_backend.chatroom.domain.Member;
import Babyak.babyak_backend.user.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) { this.em=em; }

    @Override
    public Member save(Member m) {
        em.persist(m);
        return m;
    }

    @Override
    public Member add(Member member, User user) {
        return null;
    }

    @Override
    public Member reduce(Member member, User user) {
        return null;
    }


}
