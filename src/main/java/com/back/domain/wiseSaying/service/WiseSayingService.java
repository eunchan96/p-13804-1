package com.back.domain.wiseSaying.service;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingService(){
        wiseSayingRepository = AppContext.wiseSayingRepository;
    }

    public void write (WiseSaying wiseSaying) {
        wiseSayingRepository.save(wiseSaying);
    }

    public List<WiseSaying> getforList(String keywordType, String keyword) {
        if(keyword.isBlank()) return wiseSayingRepository.getForList();
        return switch (keywordType) {
            case "content" -> wiseSayingRepository.findForListByContent(keyword);
            case "author" -> wiseSayingRepository.findForListByAuthor(keyword);
            default -> throw new IllegalArgumentException("알 수 없는 검색 타입입니다: " + keywordType);
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
