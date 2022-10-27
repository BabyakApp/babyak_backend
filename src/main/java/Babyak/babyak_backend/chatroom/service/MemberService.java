package Babyak.babyak_backend.chatroom.service;

import Babyak.babyak_backend.chatroom.domain.Member;
import Babyak.babyak_backend.chatroom.repository.MemberRepository;
import Babyak.babyak_backend.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { this.memberRepository = memberRepository; }

    public Long create(Member member){
        memberRepository.save(member);

        return member.getMemberid();
    }

    public void addUser(Member member, User user){
        memberRepository.add(member, user);
    }
}
