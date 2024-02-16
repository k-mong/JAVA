package com.til.service;

import com.til.dto.InsertBoardRequestDto;
import com.til.entity.Board;
import com.til.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public String insertBoard(InsertBoardRequestDto insertBoardRequestDto) {
        boardRepository.save(
                Board.builder()
                        .title(insertBoardRequestDto.getTitle())
                        .content(insertBoardRequestDto.getContent())
                        .build());
        return "게시글 등록 완료";
    }

    public List<Board> boardList() {
        return boardRepository.findAll();
    }
}
