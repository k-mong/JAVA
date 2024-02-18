package com.til.dto;

import com.til.entity.BaseEntity;
import com.til.entity.Board;
import com.til.entity.BoardImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private String memberName;
    private String email;
    private List<String> imageUrl;

    @Builder
    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.memberName = board.getMember().getName();
        this.email = board.getMember().getEmail();
        this.imageUrl = board.getBoardImages().stream().map(BoardImage::getUrl).collect(Collectors.toUnmodifiableList());
    }
}
