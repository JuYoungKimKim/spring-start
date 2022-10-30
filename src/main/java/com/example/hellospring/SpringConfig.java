package com.example.hellospring;

import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;


// @Service, @Repository를 쓰지 않고 우리가
// 자바코드로  SpringContainer에 Bean을 올리는 방법이다.
// @Bean을 보고 memberService와 memberRepository를 Container를 넣어둔다.
// @Controller는 이렇게 못한다. 스프링에서 관리하기 때문이라는데 이것은 좀 더 찾아봐야겠다.

@Configuration
public class SpringConfig {
//    private DataSource dataSource;
//
//    // resources/application.properties 를 보고 만든 dataSource를 spring boot 가
//    // 자동으로 DataSource 를 DI 해준다.
//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }

//    private EntityManager em;
//
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    // 원래는 MemoryMemberRepository 지만 JdbcMemberRepository를 만들고
    // 그냥 Bean 생성을 바꾸기 위해 코드 return 줄만 바꿨는데도 불구하고 정상적으로 작동한다
    // Interface를 이용하여 구현하였기 때문이다. 스프링의 장점이라고 설명하지만 사실 자바의 장점이 아닌가 싶다.
//    @Bean
//    public MemberRepository memberRepository(){
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//  }
}
