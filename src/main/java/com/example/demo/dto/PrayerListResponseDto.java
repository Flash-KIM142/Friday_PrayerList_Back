package com.example.demo.dto;


import com.example.demo.domain.entity.PrayerList;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrayerListResponseDto {
    private Long id;
    private String name;
    private String content;
    private String simpleCreatedTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd | HH시 mm분 ss초")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd | HH시 mm분 ss초", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;



    public PrayerListResponseDto(PrayerList prayerList) {
        this.id = prayerList.getId();
        this.name = prayerList.getName();
        this.content = prayerList.getContent();
        this.createdTime = prayerList.getCreatedTime();
        this.simpleCreatedTime = createSimpleCreatedTime(prayerList.getCreatedTime().toString());
    }


    // 하나 바꿔주는 것.
    public static PrayerListResponseDto of(PrayerList prayerList){
        return PrayerListResponseDto.builder()
                .id(prayerList.getId())
                .name(prayerList.getName())
                .content(prayerList.getContent())
                .createdTime(prayerList.getCreatedTime())
                .simpleCreatedTime(createSimpleCreatedTime(prayerList.getCreatedTime().toString()))
                .build();
    }


    // list 변환
    public static List<PrayerListResponseDto> of(List<PrayerList> prayerLists){
        return prayerLists.stream()
                .map(PrayerListResponseDto::of)
                .collect(Collectors.toList());
    }

    public static String createSimpleCreatedTime(String createdTime){
        StringTokenizer stk = new StringTokenizer(createdTime, "T");
        return stk.nextToken();
    }


}
