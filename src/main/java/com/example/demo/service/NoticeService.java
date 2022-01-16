package com.example.demo.service;

import com.example.demo.domain.entity.Notice;
import com.example.demo.dto.NoticeRequestDto;
import com.example.demo.dto.NoticeResponseDto;
import org.springframework.data.domain.*;

public interface NoticeService {
    /**
     * 작성한 Notice 를 저장한다
     */
    boolean createNotice(NoticeRequestDto noticeRequestDto);

    /**
     * 모든 Notice를 Paging 처리하여 return 한다
     */
    Page<NoticeResponseDto> getPagedNotices(Pageable pageable);

    /**
     * id로 특정 Notice 를 return 한다
     * @param id
     * @return 타겟 Notice 를 return 한다
     */
    Notice getNoticeById(Long id);

    /**
     * Noice 하나를 삭제한다
     */
    boolean deleteNotice(Long id);

    /**
     * Notice 하나를 update 한다
     */
    boolean updateNoticeById(Long id, NoticeRequestDto noticeRequestDto);
}
