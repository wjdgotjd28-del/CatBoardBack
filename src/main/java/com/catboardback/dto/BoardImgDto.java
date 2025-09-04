package com.catboardback.dto;


import com.catboardback.entity.BoardImg;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardImgDto {
    private Long id;
    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private String repimgYn;

    public static BoardImgDto of(BoardImg boardImg) {
        return BoardImgDto.builder()
                .id(boardImg.getId())
                .imgName(boardImg.getImgName())
                .oriImgName(boardImg.getOriImgName())
                .imgUrl(boardImg.getImgUrl())
                .repimgYn(boardImg.getRepImgYn())
                .build();
    }
}