package com.example.hellospring;

import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.MemoryMemberRepository;
import com.example.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// @Service, @Repository를 쓰지 않고 우리가
// 자바코드로  SpringContainer에 Bean을 올리는 방법이다.
// @Bean을 보고 memberService와 memberRepository를 Container를 넣어둔다.
// @Controller는 이렇게 못한다. 스프링에서 관리하기 때문이라는데 이것은 좀 더 찾아봐야겠다.

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
