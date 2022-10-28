package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service를 보고 Spring은 이녀석을 container에 등록한다.
// 그리고 이녀석을 사용하는 곧에 @Autowired를 사용하면 이 service를 사용하는 곳에서 요녀석을 사용할 수 있게 되는것이다.
//@Service

public class MemberService {
    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        // 중복회원 검증
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 만약 optional 로 받아온 객체에 null이 아니면 이미 존재하는 회원이다.
        memberRepository.findByName(member.getName())
                        .ifPresent(m->{
                             throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     *
     * @return
     * 전체 회원조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
