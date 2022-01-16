package com.example.demo.controller;

import com.example.demo.dto.BaseResponseDto;
import com.example.demo.dto.NoticeRequestDto;
import com.example.demo.service.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path="/api/v1/notice")
@AllArgsConstructor
@RestController
public class NoticeController {
    private final NoticeService noticeService;

    /**
     * Paging 된 Notice 들을 불러온다
     */
    @GetMapping
    public ResponseEntity<BaseResponseDto> getPagedNotices(
            @PageableDefault(size=5) Pageable pageable){
        return new ResponseEntity<>(new BaseResponseDto(HttpStatus.OK.value(), "paging success", noticeService.getPagedNotices(pageable)), HttpStatus.OK);
    }

    /**
     * Notice 하나를 생성한다
     */
    @PostMapping
    public ResponseEntity<BaseResponseDto> postNotice(@RequestBody NoticeRequestDto noticeRequestDto){
        return new ResponseEntity<>(new BaseResponseDto(HttpStatus.CREATED.value(), "post success", noticeService.createNotice(noticeRequestDto)), HttpStatus.CREATED);
    }

    /**
     * Notice 하나를 삭제한다
     */
    @DeleteMapping(path="/{id}")
    public ResponseEntity<BaseResponseDto> deleteNotice(@PathVariable("id") long id) throws Exception {
        return new ResponseEntity<>(new BaseResponseDto(HttpStatus.NO_CONTENT.value(), "delete success", noticeService.deleteNotice(id)), HttpStatus.OK);
    }

    /**
     * Notice 하나를 update한다
     */
    @PutMapping(path="/{id}")
    public ResponseEntity<BaseResponseDto> updateNotice(@PathVariable("id") long id, @RequestBody NoticeRequestDto noticeRequestDto){
        return new ResponseEntity<>(new BaseResponseDto(HttpStatus.OK.value(), "update success", noticeService.updateNoticeById(id, noticeRequestDto)), HttpStatus.OK);
    }
}
