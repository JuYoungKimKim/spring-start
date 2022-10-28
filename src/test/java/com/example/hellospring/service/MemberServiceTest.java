package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemoryMemberRepository;
import com.example.hellospring.repository.MemoryMemberRepositoryTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    // 만약 우리가 MemoryMemberRepository를 계속 생성한다면
    // 지금은 store, sequence가 static 이여서 상관없지만 아니라면? 아니면 다른 필드를 사용한다면 서로 다른 인스턴스가 만들어지고
    // 마치 서로 다른 DB를 이용하거나 Test할 때에도 서로 다른 인스턴스로 test를 진행하기 때문에 문제가 될 수 있다.
    // 그러므로 DI패턴?을 사용해서 그걸 방지해주자.
     MemberService memberService;
     MemoryMemberRepository memberRepository;

     // test를 실행하기 전에
     // 같은 메모리의 memberRepositry를 사용하기 위해 DI를 해주는 것이다.
     // MemoryMemberRepository의 필드들은 static 이기 때문에 마치 같은 db를 사용하는 것 같은, 혹은 같은 db로 test하는 효과를 가져온다.
     // 오 굿
     @BeforeEach
     public void beforeEach(){
         memberRepository = new MemoryMemberRepository();
         memberService = new MemberService(memberRepository);
     }

     @AfterEach
     public void afterEach(){
         memberRepository.clearStore();
     }

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
    void duplicataMemberCheck(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when

        memberService.join(member1);

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. ㅂㅈㄷ");
//        }

        // 이 (2번째 파라미터)로직을 실행한건데 (1번째 파라미터)illegalstateException 이 나와야해
        // 라고 try catch 문을 쓰지 않고 assertThrows를 사용하는 방법이 있다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then
    }

    @Test
    void findMembers() {
        // given

        // when

        // then
    }

    @Test
    void findOne() {
    }
}