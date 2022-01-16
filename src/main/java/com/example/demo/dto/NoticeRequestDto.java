package com.example.demo.dto;

import com.example.demo.domain.entity.Notice;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoticeRequestDto {
    private String name;
    private String content;

    public Notice toEntity(){
        return new Notice(this.name, this.content);
    }

    public Notice toEntity(NoticeRequestDto noticeRequestDto){
        return new Notice(noticeRequestDto.name, noticeRequestDto.content);
    }
}
