package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // JPA는 EntityManager로 모두 동작한다.
    // Spring 이 알아서 만들어주고 DI 해줌, DataSource 같은 것도 다 알아서 처리해줌.
    // 즉 JPA 를 쓰려면 EntityManager를 만들어야한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // pk 기반이 아닌 나머지는 jpql로 작성하는게 좋다.
        List<Member> result =  em.createQuery("select m from Member as m where m.name =: name", Member.class)
                .setParameter("name",name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // jpql 이라는 쿼리언어 객체를 대상으로 쿼리를 날림? 그리고 Member entity를 조회해!
        // 그리고 mapping 된 녀석을 return 해주는 거 같음.
        List<Member> result = em.createQuery("select m from Member as m", Member.class)
                .getResultList();

        return result;
    }
}
