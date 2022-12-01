package Babyak.babyak_backend.chatroom.repository;

import Babyak.babyak_backend.chatroom.domain.Member;
import Babyak.babyak_backend.user.entity.User;

public interface MemberRepository {

    public Member save(Member member);

    public Member add(Member member, User user);

    public Member reduce(Member member, User user);
}
