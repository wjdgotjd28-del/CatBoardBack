package com.catboardback.repository;

import com.catboardback.entity.BoardImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardImgRepository extends JpaRepository<BoardImg, Long> {
    List<BoardImg> findByBoardIdOrderByIdAsc(String boardId); //그냥 FindById하면 PK을 말하기때문에 xx 우린 FK가필요.
    BoardImg findByBoardIdAndRepImgYn(Long boardId, String repImgYn);
}
