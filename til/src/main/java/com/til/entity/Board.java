package com.til.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)    // 글자 수 20 글자
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    // N:1 관계(null 값 허용안됨, 연관된 엔티티에 접근할 때 데이터베이스에서 가져온다)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @OrderBy("id asc")
    private List<BoardImage> boardImages;

    public void setMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }
}

