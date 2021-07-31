package com.example.demo.dto;

import com.example.demo.domain.entity.PrayerList;
import lombok.*;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PrayerListRequestDto {
    private String name;
    private String content;

    public PrayerList toEntity(PrayerListRequestDto prayerListRequestDto){
        return new PrayerList(prayerListRequestDto.name, prayerListRequestDto.content);
    }


}
