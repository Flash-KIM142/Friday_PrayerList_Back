package com.example.demo.domain.repository;
import com.example.demo.domain.entity.Notice;

import com.example.demo.dto.NoticeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    /**
     * Service 에서 getPagedNotices() 에 사용할 내림차순 메소드
     */
    @Query("Select n FROM Notice n ORDER BY n.createdTime DESC")
    Page<NoticeResponseDto> findAllByCreatedTimeDesc(Pageable pageable);


}
