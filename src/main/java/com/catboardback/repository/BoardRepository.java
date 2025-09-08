package com.catboardback.repository;

import com.catboardback.constant.Category;
import com.catboardback.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByCategory(Category category);

    Board findById(Board boardId);
}
