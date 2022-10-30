package com.example.hellospring.domain;

import lombok.Data;

import javax.persistence.*;

// Entity 는 JPA가 관리하는 Entity라고 명시
@Data
@Entity
public class Member {
    
    // @Id 는 Pk를 맵핑
    // GeneratedValue 는 pk 전략을 명시하는 부분인데 이건 좀 찾아보자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column 은 Column 이름과 class의 attribute를 맵핑
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
