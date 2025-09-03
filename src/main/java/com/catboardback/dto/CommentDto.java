package com.catboardback.dto;

import com.catboardback.entity.Board;
import com.catboardback.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto
{
    private Long id;

    private Board board;

    private String content;

    private LocalDateTime regTime;

}