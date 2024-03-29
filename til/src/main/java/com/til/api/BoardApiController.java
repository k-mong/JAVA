package com.til.api;

import com.til.dto.BoardResponseDto;
import com.til.dto.InsertBoardRequestDto;
import com.til.dto.UploadImageDto;
import com.til.entity.Board;
import com.til.entity.BoardImage;
import com.til.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping("/insert")
    public ResponseEntity<String> insertBoard (InsertBoardRequestDto insertBoardRequestDto, @ModelAttribute UploadImageDto uploadImageDto) {
        log.info("boardImage = ", uploadImageDto);
        String result = boardService.insertBoard(insertBoardRequestDto, uploadImageDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list")
    public List<BoardResponseDto> boardList () {
        List<Board> boards = boardService.boardList();
        return boards.stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }
}
