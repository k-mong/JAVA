package com.til.service;

import com.til.dto.InsertBoardRequestDto;
import com.til.dto.UploadImageDto;
import com.til.entity.Board;
import com.til.entity.BoardImage;
import com.til.repository.BoardImageRepository;
import com.til.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;

    @Value("${file.boardImagePath}")
    private String uploadFolder;

    public String insertBoard(InsertBoardRequestDto insertBoardRequestDto, UploadImageDto uploadImageDto) {
        Board result = boardRepository.save(
                Board.builder()
                        .title(insertBoardRequestDto.getTitle())
                        .content(insertBoardRequestDto.getContent())
                        .build());

        if(uploadImageDto.getFiles() != null && !uploadImageDto.getFiles().isEmpty()) {
            //만약 이미지파일이 있고, 이미지파일이 비어있지 않다면
            for(MultipartFile file : uploadImageDto.getFiles()) {
                // MultipartFile file 안에 인자값으로 들어온 이미지 파일을 하나씩 너어줘
                UUID uuid = UUID.randomUUID();
                // 서버 내부에서 관리하는 파일명은 유일한 이름을 생성하는 UUID를 사용해서 충돌하지 않도록 한다.
                // 각 이미지에대한 식별값 생성
                String imageFileName = uuid + "_" + file.getOriginalFilename();
                // 이미지파일 이름 규칙지정

                File destinationFile = new File(uploadFolder + imageFileName);
                try {
                    file.transferTo(destinationFile);
                    // 인자값으로 들어온 이미지파일의 저장경로 지정
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                BoardImage image = BoardImage.builder()
                        .url("/boardImages/" + imageFileName)
                        .board(result)
                        .build();

                boardImageRepository.save(image);
            }
        }
        return "게시글 등록 완료";
    }

    public List<Board> boardList() {
        return boardRepository.findAll();
    }
}
