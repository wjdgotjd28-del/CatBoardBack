package com.catboardback.dto;

import com.catboardback.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto
{
    private Long id;

    private String cartegory;

    private String title;

    private String content;

    private LocalDateTime regTime;

    private String imgUrl;
}
