package com.example.demo.service;

import com.example.demo.domain.repository.NoticeRepository;
import com.example.demo.domain.entity.Notice;
import com.example.demo.dto.NoticeRequestDto;
import com.example.demo.dto.NoticeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    /**
     * 작성한 Notice 를 저장한다
     */
    @Override
    public boolean createNotice(NoticeRequestDto noticeRequestDto){
        noticeRepository.save(noticeRequestDto.toEntity());
//        noticeRepository.save(noticeRequestDto.toEntity(noticeRequestDto));
        return true;
    }

    /**
     * 모든 Notice를 Paging 처리하여 return 한다
     */
    @Override
    public Page<NoticeResponseDto> getPagedNotices(Pageable pageable){
        return noticeRepository.findAllByCreatedTimeDesc(pageable);
    }

    /**
     * id로 Notice 조회하기
     */
    @Override
    public Notice getNoticeById(Long id){
        Optional<Notice> optionalTarget = noticeRepository.findById(id);
        Notice target = optionalTarget.get();

        return target;
    }

    /**
     * Notice 하나 삭제하기
     */
    public boolean deleteNotice(Long id){
        noticeRepository.deleteById(id);
        return true;
    }

    /**
     * Notice 하나 update 하기
     *
     * @param id target Notice 의 id 값
     * @param noticeRequestDto target Notice 에 덮어 쓸 데이터
     * @return notice
     */
    public boolean updateNoticeById(Long id, NoticeRequestDto noticeRequestDto){
        Optional<Notice> optionalTarget = noticeRepository.findById(id);
        Notice target = optionalTarget.get();

        target.update(noticeRequestDto.getName(), noticeRequestDto.getContent());
        noticeRepository.save(target);

        return true;
    }
}
