package com.example.hellospring.service;


import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


// @Transactional 은 테스트가 끝나면 롤백을 해준다. 그러니까 들어갔던 데이터들은 오로지 테스트만
// 이거 아니라면 또 메소드 만들고 jdbc 연결해서 truncate 이런거 해주고 아주 귀찮을거다.
// 하지만 dml 은 autocommit 이 아니지만 다른 명령어는 어떨지 모르겠다.
// 동작원리는 테스트 직전 트랜잭션을 시작하고 완료 후 롤백하는 형식
// 테스트 1개 1개마다 작동함

// @SpringBootTest 는 스프링 컨테이너와 테스틀 함께 실행

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() {
        // given : 뭔가 주어졌는데
        Member member = new Member();
        member.setName("spring");
        // when : 이걸로 실행했을 때
        Long saveId = memberService.join(member);

        // then : 이렇게 나와야 해!
        Member findMember = memberService.findOne(member.getId()).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void duplicateMemberCheck(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then
    }
}
