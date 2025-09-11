package com.catboardback.entity;

import com.catboardback.constant.Category;
import com.catboardback.dto.BoardFormDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Board
{
    @Id
    @Column(name="board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String title;

    private String content;

    private LocalDateTime regTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void updateBoard(BoardFormDto boardFormDto) {
        this.category = boardFormDto.getCategory();
        this.title = boardFormDto.getTitle();
        this.content = boardFormDto.getContent();
        this.regTime = LocalDateTime.now();
    }
}
