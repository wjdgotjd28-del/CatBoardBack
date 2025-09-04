package com.catboardback.service;

import com.catboardback.entity.BoardImg;
import com.catboardback.repository.BoardImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardImgService {
    @Value("${boardImgLocation}")
    private String boardImgLocation;
    private final BoardImgRepository boardImgRepository;
    private final FileService fileService;

    // 1. MultipartFile에 들어있는 itemImgFile을 파일시스템에 저장 -> FileService 호출.
    // 2. DB에 저장할 ItemImg 정보 ==> DB에 저장 ==> ItemImgRepository
    public void saveBoardImg(BoardImg boardImg, MultipartFile boardImgFile) throws Exception {
        String oriImgName = boardImgFile.getOriginalFilename(); // 업로드한 파일의 원본 이름 (사용자가 업로드한 파일명)
        String imgName = "";  // 서버에 저장할 파일명 (UUID + 확장자 등으로 저장됨)
        String imgUrl = "";   // 클라이언트가 접근할 이미지 URL 경로 (웹에서 이미지 보여줄 때 사용)

        // 원본 파일명이 비어있지 않은 경우에만 파일 업로드 수행
        if (!StringUtils.isEmpty(oriImgName)) {
            // FileService의 uploadFile 메서드를 호출해 파일 시스템에 저장하고, 저장된 파일명을 반환받음 (예: "uuid.png")
            imgName = fileService.uploadFile(boardImgLocation, oriImgName, boardImgFile.getBytes());
            // 웹에서 이미지를 요청할 수 있도록 URL 경로 생성 (예: "/images/board/uuid.png")
            imgUrl = "/images/board/" + imgName;
        }

        // ItemImg 엔티티 객체에 원본 이름, 저장된 파일명, 이미지 URL 정보를 세팅
        boardImg.updateItemImg(oriImgName, imgName, imgUrl);
        //DB에 저장된 파일 정보 저장 . . .2
        boardImgRepository.save(boardImg);
    }
}
