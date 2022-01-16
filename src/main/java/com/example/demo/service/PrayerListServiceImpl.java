package com.example.demo.service;


import com.example.demo.domain.entity.PrayerList;
import com.example.demo.domain.repository.PrayerListRepository;
import com.example.demo.dto.PrayerListRequestDto;
import com.example.demo.dto.PrayerListResponseDto;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.exception.PrayerListNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PrayerListServiceImpl implements PrayerListService {

    private final PrayerListRepository prayerListRepository;
    private static Logger logger = LoggerFactory.getLogger(PrayerListService.class);

    /*
    1(1). 작성받은 PrayerList 저장하기
     */
    public boolean savePrayerList(PrayerListRequestDto prayerListRequestDto){
        prayerListRepository.save(prayerListRequestDto.toEntity(prayerListRequestDto));
        return true;
    }

    /*
    1(2). 작성받은 PrayerList 저장하기
     */
    @Override
    public String createPrayerList(PrayerListRequestDto prayerListRequestDto) {
        System.out.printf(prayerListRequestDto.getContent());
        logger.info(new StringBuilder()
                .append("Content 잘 나오는지 테스트 :")
                .append(prayerListRequestDto.getContent())
                .toString()
        );
        prayerListRepository.save(PrayerList
                .builder()
                .content(prayerListRequestDto.getContent())
                .name(prayerListRequestDto.getName())
                .build()
        );
        return "생성 완료";
    }


    /*
    2. 전체 조회하기 (without paging)
     */
//    @Override
//    public List<PrayerListResponseDto> getPrayerLists(){
//        List<PrayerListResponseDto> prayerLists = prayerListRepository.findAllByCreatedTimeDesc();
//
//        return PrayerListResponseDto.of(prayerLists);
//    }


    /**
    2(1). Paging 처리된 PrayerList 갖고오기
     */
    @Override
    public Page<PrayerListResponseDto> getPagedPrayerLists(Pageable pageable){
        return prayerListRepository.findAllByCreatedTimeDesc(pageable);
    }

    /**
    3. id로 조회하기
        수정할 때 사용하게 될 메소드
     */
    @Override
    public PrayerList getPrayerListById(Long id){
        validateIdExist(id);
//        PrayerList prayerList = prayerListRepository.findById(id).orElseThrow(() -> {
//            throw new IllegalArgumentException("기도제 존재하지 않습니다.");
//        });

        Optional<PrayerList> optionalTarget = prayerListRepository.findById(id);
        PrayerList target = optionalTarget.get();

        return target;
    }

    /**
    4. target 글 삭제하기
     */
    public boolean deletePrayerList(Long id){
        validateIdExist(id);

        prayerListRepository.deleteById(id);
        return true;
    }

    /**
    5. target 기도제목 update 하기

     @param id target Entity 의 id 값
     @param prayerListRequestDto target Entity 에 새롭게 입혀줄 데이터
     @return prayerList
     */
    public boolean updatePrayerListById(Long id, PrayerListRequestDto prayerListRequestDto){
        Optional<PrayerList> optionalTraget = prayerListRepository.findById(id);
        PrayerList target = optionalTraget.get();

        target.update(prayerListRequestDto.getName(), prayerListRequestDto.getContent());
        prayerListRepository.save(target);

        return true;
    }

    /*
    6. dateCollection 받아오기
    */
    public List<PrayerListResponseDto> getPrayerListsBetweenTwoDates(LocalDateTime startDate, LocalDateTime endDate){
        return prayerListRepository.findAllByCreatedTimeBetween(startDate, endDate);
    }


    /*
    6. 이름으로 조회하기
     */
//    @Override
//    public List<PrayerListRequestDto> findAllByName(String name){
//        List<PrayerListRequestDto> prayerLists = PrayerListRepository.findAllByName(name);
//        if(prayerLists.size()==0){
//            throw new PrayerListNotFoundException("none exists!");
//        }
//        return prayerLists;

    /**
        해당 id 값이 존재하는지 확인해주는 메소드
     */
    @Override
    public void validateIdExist(Long id) {
        if (!prayerListRepository.existsById(id))
            throw new IdNotFoundException("존재하지 않는 id입니다.");
    }
}


