package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;




public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();


    // 테스트는 순서를 보장하지 않고 랜덤으로 실행된다.
    // 순서에 의존적이면 안된다. 당연히
    // 그러므로 findAll 하고 findByName 을 하면 id 값이 서로 맞지 않아 test에 실패한다.
    // @AfterEach 는 test 하나 끝날때마다 어떤 동작을 하게 하는 callback method이다.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    // 반드시 이 어노테이션을 사용해야한다.
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // optional 에서 값을 꺼내는 방법 get
        Member result =  repository.findById(member.getId()).get();

        // Assertions 는 우리가 test 할때마다 sysout 을 하기 힘드므로 도와주는 녀석이다
        // assertions.arrsetEqulas 는 첫번째에 우리가 기대하는 값 두번 째로 실제로 받아온 값을 넣어서
        // 만약 실패하면 test가 실패하는 것이고 성공이면 test가 성공하는 것이다.
        assertEquals(member, result);

        // 이녀석도 위와 비슷하다. 하지만 assertThat 은 static class
        // import static org.assertj.core.api.Assertions.assertThat; 해야한다.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
