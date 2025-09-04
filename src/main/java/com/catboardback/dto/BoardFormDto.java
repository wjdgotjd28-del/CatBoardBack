package com.catboardback.dto;

import com.catboardback.constant.Category;
import com.catboardback.entity.Board;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardFormDto {

    private Long id;

    @NotNull(message = "카테고리는 필수 입력 값입니다.")
    private Category category;

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String content;

    private LocalDateTime regTime;


    private List<BoardImgDto> boardImgDtoList = new ArrayList<>();

    private List<Long> boardImgIds = new ArrayList<>();

    // Entity -> DTO 변환
    public static BoardFormDto fromEntity(Board board) {
        return BoardFormDto.builder()
                .id(board.getId())
                .category(board.getCategory())
                .title(board.getTitle())
                .content(board.getContent())
                .regTime(board.getRegTime())
                .build();
    }

    // DTO -> Entity 변환
    public Board toEntity(Board board) {
        return Board.builder()
                .id(this.id)
                .category(this.category)
                .title(this.title)
                .content(this.content)
                .regTime(LocalDateTime.now())
                .member(board.getMember())
                .build();
    }
}