package com.back.domain.wiseSaying.service;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import com.back.standard.dto.Pageable;

import java.util.List;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingService(){
        wiseSayingRepository = AppContext.wiseSayingRepository;
    }

    public void write (WiseSaying wiseSaying) {
        wiseSayingRepository.save(wiseSaying);
    }

    public List<WiseSaying> getforList(String keywordType, String keyword, Pageable pageable) {
        if(keyword.isBlank()) return wiseSayingRepository.getForList(pageable);
        return switch (keywordType) {
            case "content" -> wiseSayingRepository.findForListByContent(keyword, pageable);
            case "author" -> wiseSayingRepository.findForListByAuthor(keyword, pageable);
            default -> wiseSayingRepository.findForListByContentOrAuthor(keyword, keyword, pageable);
        };
    }

    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public boolean delete(int id) {
        WiseSaying wiseSaying = wiseSayingRepository.findById(id);
        if (wiseSaying == null) return false;
        wiseSayingRepository.delete(wiseSaying);
        return true;
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);

        wiseSayingRepository.save(wiseSaying);
    }
}
