package com.example.demo.controller;

import com.example.demo.domain.entity.PrayerList;
import com.example.demo.dto.BaseResponseDto;
import com.example.demo.dto.PrayerListRequestDto;
import com.example.demo.service.PrayerListService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequestMapping(path="/api/v1/prayerlist")
@AllArgsConstructor
@RestController
public class PrayerListController {


    private final PrayerListService prayerListService;
    // TODO : GET, POST, DELETE ,UPDATE

    // paging 된 data 받아올 거니까 어차피 얘 더이상 안쓴다
//    @GetMapping(path="/home")
//    public ResponseEntity<BaseResponseDto> getPrayerLists(){
//        return new ResponseEntity<>(new BaseResponseDto(HttpStatus.OK.value(),"okay",prayerListService.getPrayerLists()), HttpStatus.OK);
//    }

    /*
    Paging 된 PrayerLsts 를 보여준다
     */
    @GetMapping
    public ResponseEntity<BaseResponseDto> getPagedPrayerLists(
            @PageableDefault(size=5) Pageable pageable){
        return new ResponseEntity<>(new BaseResponseDto(HttpStatus.OK.value(), "paging success", prayerListService.getPagedPrayerLists(pageable)), HttpStatus.OK);
    }


    /*
     두 Dates 사이의 PrayerList 를 갖고온다
     */
    @GetMapping(path="/dateCollection/{startDateString}/{endDateString}")
    public ResponseEntity<BaseResponseDto> getPrayerListsBetweenTwoDates(
            @PathVariable("startDateString") String startDateString,
             @PathVariable("endDateString") String endDateString){
        StringBuilder startBuilder = new StringBuilder(startDateString);
        StringBuilder endBuilder = new StringBuilder(endDateString);

        startBuilder.append("_00:00:00");
        endBuilder.append("_23:59:59");

        DateTimeFormatter startDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
        DateTimeFormatter endDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");

        LocalDateTime startDate = LocalDateTime.parse(startBuilder.toString(), startDateFormatter);
        LocalDateTime endDate = LocalDateTime.parse(endBuilder.toString(), endDateFormatter);
        System.out.println(startDate);
        System.out.println(endDate);

        return new ResponseEntity<>(new BaseResponseDto(HttpStatus.OK.value(), "dateCollection success", prayerListService.getPrayerListsBetweenTwoDates(startDate, endDate)), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<BaseResponseDto>  postPrayerList(@RequestBody PrayerListRequestDto prayerListRequestDto){
//        return new ResponseEntity<>(new BaseResponseDto(HttpStatus.CREATED.value(),"okay",prayerListService.createPrayerList(prayerListRequestDto)), HttpStatus.CREATED);
        return new ResponseEntity<>(new BaseResponseDto(HttpStatus.CREATED.value(),"okay",prayerListService.savePrayerList(prayerListRequestDto)), HttpStatus.CREATED);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<BaseResponseDto> deletePrayerList(@PathVariable("id") long id) throws Exception{
//        return ResponseEntity.ok()
//                .body(new BaseResponseDto(HttpStatus.CREATED.value(), "okay", prayerListService.deletePrayerList(id) ));
        return new ResponseEntity<>(new BaseResponseDto(HttpStatus.NO_CONTENT.value(), "deleted!", prayerListService.deletePrayerList(id)), HttpStatus.OK);
    }

    @GetMapping(path="/edit/{id}")
    public ResponseEntity<BaseResponseDto> getPrayerListById(@PathVariable("id") long id) throws Exception{
        return new ResponseEntity<>(new BaseResponseDto(HttpStatus.OK.value(), "okay", prayerListService.getPrayerListById(id)), HttpStatus.OK);
    }

    @PutMapping(path="/edit/{id}")
    public ResponseEntity<BaseResponseDto> updatePrayerList(@PathVariable("id") long id, @RequestBody PrayerListRequestDto prayerListRequestDto){
        return new ResponseEntity<>(new BaseResponseDto(HttpStatus.OK.value(), "successfully updated", prayerListService.updatePrayerListById(id, prayerListRequestDto)), HttpStatus.OK);
    }
}
