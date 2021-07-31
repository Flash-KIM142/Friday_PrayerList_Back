package com.example.demo.service;

import java.util.List;
import java.time.LocalDateTime;

import com.example.demo.domain.entity.PrayerList;
import com.example.demo.domain.repository.PrayerListRepository;
import com.example.demo.dto.PrayerListRequestDto;
import com.example.demo.dto.PrayerListResponseDto;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

public interface PrayerListService {

    /**
     * 작성된 PrayerList 를 저장합니다. - 방법 1
     */
    boolean savePrayerList(PrayerListRequestDto prayerListRequestDto);

    /**
     * 작성된 PrayerList 를 저장합니다. - 방법 2
     *
     * @param prayerListRequestDto
     * @return
     */
    String createPrayerList(PrayerListRequestDto prayerListRequestDto);


    /**
     * 모든 글을 다 return 합니다.
     *
     */
//    List<PrayerListResponseDto> getPrayerLists();

    /**
     * id로 특정 PrayerList 를 return 합니다.
     *
     * @param id
     * @return 찾은 PrayerList 를 return 합니다.
     */
    PrayerList getPrayerListById(Long id);

    /**
     * paging 을 처리해 prayerList 를 담은 Page 객체를 return 합니다.
     *
     * @param pageable Pageable 객체를 넘겨준다
     */
//    Page<PrayerList> getPagedPrayerLists(Integer pageNo, Integer pageSize);
    Page<PrayerListResponseDto> getPagedPrayerLists(Pageable pageable);

    /**
     * target 글을 삭제합니다.
     */
    boolean deletePrayerList(Long id);

    /**
     * target 글을 update 합니다.
     */
    boolean updatePrayerListById(Long id, PrayerListRequestDto prayerListRequestDto);


    /**
     * startDate 와 endDate 를 받아오면 그 사이의 PrayerLists 를 return 한다
     * @param startDate 시작 날짜
     * @param endDate 끝나는 날짜
     * @return 그 사이의 모든 PrayerList
     */
    List<PrayerListResponseDto> getPrayerListsBetweenTwoDates(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * createdTime 에 따라 PrayerList 를 return 합니다.
     *
     * @param createdTime 생성된 시간
     * @return 작성된 시간에 따른 PrayerList 를 return 합니다.
     */
//    List<PrayerListRequestDto> getPrayerListsByTime(LocalDateTime createdTime);

    /**
     * 특정 name 을 찾아 해당되는 PrayerList 를 return 합니다.
     *
     * @param name 작성자의 이름
     * @return 해당 name 의 모든 PrayerList 를 return 합니다.
     */
//    List<PrayerListRequestDto> findAllByName(String name);

    /**
     * 유저 정보가 존재하는지 확인합니다.
     *
     * @param id 유저의 고유 아이디 값
     */
    void validateIdExist(Long id);
}
