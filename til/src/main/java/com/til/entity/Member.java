package com.til.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자 만들어준다
@NoArgsConstructor  // 기본 생성자 만들어준다.
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    @Column(unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    // 한 명의 사용자는 여러 게시글을 올릴 수 있다. 1:N 관계
    // cascade = CascadeType.ALL = 연관관계가 때문에 User 를 삭제하면 해당 사용자의 게시글들도 함께 삭제하기 위해
    // mappedBy = "user" 가 owner
    // fetch = FetchType.LAZY = 연관된 엔티티에 접근할 때 데이터베이스에서 가져온다
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member", fetch = FetchType.LAZY)
    private List<Board> boards = new ArrayList<>();

}

