package com.example.demo.domain.repository;
import com.example.demo.domain.entity.PrayerList;


import com.example.demo.dto.PrayerListRequestDto;

import com.example.demo.dto.PrayerListResponseDto;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

public interface PrayerListRepository extends JpaRepository<PrayerList, Long>{

    PrayerListResponseDto findAllById(String id);

    /*
    1. Service 에서 getPrayerLists() 에 사용할 시간 내림차순 Logic
     */
    @Query("SELECT p FROM PrayerList p ORDER BY p.createdTime DESC")
    List<PrayerListResponseDto> findAllByCreatedTimeDesc();

    /*
    2. paging 을 위한 findAll 메소드
     */
    @Query("SELECT p FROM PrayerList p ORDER BY p.createdTime DESC")
    Page<PrayerListResponseDto> findAllByCreatedTimeDesc(Pageable pageable);

    /*
    3. 두 날짜 between data들을 paging 처리 없이 받아올 findAll 메소드
     */
//    @Query("SELECT p FROM PrayerList p WHERE p.createdTime BETWEEN :startDate AND :endDate")
    List<PrayerListResponseDto> findAllByCreatedTimeBetween(LocalDateTime start, LocalDateTime end);

}


