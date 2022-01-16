package com.example.demo.dto;

import com.example.demo.domain.entity.Notice;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.StringTokenizer;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeResponseDto {
    private Long id;
    private String name;
    private String content;
    private String simpleCreatedTime;

    @DateTimeFormat(pattern = "yyyy년 MM월 dd일 HH시 mm분 ss초")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy년 MM월 dd일 HH시 mm분 ss초", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    public NoticeResponseDto(Notice notice){
        this.id = notice.getId();
        this.name = notice.getName();
        this.content = notice.getContent();
        this.createdTime = notice.getCreatedTime();
        this.simpleCreatedTime = createSimpleCreatedTime(notice.getCreatedTime().toString());
    }

    public static String createSimpleCreatedTime(String createdTime){
        StringTokenizer stk1 = new StringTokenizer(createdTime, "T");
        String uncompletedSimpleCreatedTime = stk1.nextToken();

        StringTokenizer stk2 = new StringTokenizer(uncompletedSimpleCreatedTime, "-");
        StringBuilder sb = new StringBuilder();
        sb.append(stk2.nextToken() + "년 ");
        sb.append(stk2.nextToken() + "월 ");
        sb.append(stk2.nextToken() + "일");

        return sb.toString();
    }
}
