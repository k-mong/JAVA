package com.til.api;

import com.til.dto.InsertBoardRequestDto;
import com.til.entity.Board;
import com.til.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping("/insert")
    public ResponseEntity<String> insertBoard (InsertBoardRequestDto insertBoardRequestDto) {
        String result = boardService.insertBoard(insertBoardRequestDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list")
    public List<Board> boardList () {
        return boardService.boardList();
    }
}
