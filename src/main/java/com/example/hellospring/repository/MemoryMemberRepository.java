package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


// @Repository를 보고 Spring은 이녀석을 container에 등록한다.
// 그리고 이녀석을 사용하는 곧에 @Autowired를 사용하면 이 Repository를 사용하는 곳에서 요녀석을 사용할 수 있게 되는것이다.
//@Repository
public class MemoryMemberRepository implements MemberRepository{

    // 만약 우리가 MemoryMemberRepository를 계속 생성한다면
    // 지금은 store, sequence가 static 이여서 상관없지만 아니라면? 아니면 다른 필드를 사용한다면 서로 다른 인스턴스가 만들어지고
    // 마치 서로 다른 DB를 이용하거나 Test할 때에도 서로 다른 인스턴스로 test를 진행하기 때문에 문제가 될 수 있다.

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // optional 을 사용하는 경우는 반환하려는 녀석이 null이 될 수 있는 녀석들이라면
        // 감싸서 null 예외처리를 좀 더 편하게 하도록 도와준다.
        // null일 경우를 생각해서 optional.ofnullable 로 감싸준다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // stream 은 iterator을 편하기 쓰기 위한 객체이다.

        // findAny 는 filter로 찾은 값을 optional로 반환하기 위해 사용하는데
        // findAny()는 Stream에서 가장 먼저 탐색되는 요소를 리턴하고,
        // findFirst()는 조건에 일치하는 요소들 중에 Stream에서 순서가 가장 앞에 있는 요소를 리턴.
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
