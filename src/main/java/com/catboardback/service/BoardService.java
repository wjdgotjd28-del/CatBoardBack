package com.catboardback.service;

import com.catboardback.constant.Category;
import com.catboardback.dto.BoardDto;
import com.catboardback.dto.BoardFormDto;
import com.catboardback.entity.Board;
import com.catboardback.entity.BoardImg;
import com.catboardback.entity.Member;
import com.catboardback.repository.BoardRepository;
import com.catboardback.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardImgService boardImgService;
    private final MemberRepository memberRepository;


    public BoardDto addBoard(BoardDto boardDto) {
        Board board = Board.builder()
                .category(boardDto.getCategory())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())

                .build();
        Board savedBoard = boardRepository.save(board);

        boardDto.setId(savedBoard.getId());
        return boardDto;
    }
/// 멤버 리포지토리 만들고 이메ㅇㄹ로 멤버 찾아서 toEntity에 멤버 넘겨서 board 엔티티에 member를 채우기.
    public Long saveBoard(@Valid BoardFormDto boardFormDto, List<MultipartFile> boardImgFileList
            , String email) throws Exception {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));

        Board board = boardFormDto.toEntity(member); // Dto -> Entity

        boardRepository.save(board);
        if (boardImgFileList != null) {
            for (int i = 0; i < boardImgFileList.size(); i++) {
                BoardImg boardImg = new BoardImg();
                boardImg.setBoard(board);
                if (i == 0) { //첫번째 이미지일 경우 대표 이미지로.
                    boardImg.setRepImgYn("Y");
                } else {
                    boardImg.setRepImgYn("N");
                }
                boardImgService.saveBoardImg(boardImg, boardImgFileList.get(i));
            }
            //저장된 item ID를 반환
        }
        return board.getId();
        // 2. 이미지 여러 개 저장 (반복문 돌면서 처리)

    }

    public List<BoardDto> getBoardList(Category category) {
        List<Board> boardList = boardRepository.findByCategory(category);

        List<BoardDto> boardDtoList = new ArrayList<>();
        for (Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .category(board.getCategory())
                    .title(board.getTitle())
                    .regTime(board.getRegTime())
                    .nickname(board.getMember().getNickName())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    public BoardDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);
        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .category(board.getCategory())
                .title(board.getTitle())
                .content(board.getContent())
                .regTime(board.getRegTime())
                .nickname(board.getMember().getNickName())
                .build();
    return boardDto;
    }

}