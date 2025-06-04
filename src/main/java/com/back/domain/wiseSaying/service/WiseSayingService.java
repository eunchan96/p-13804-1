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

    public List<WiseSaying> getforList(String keywordType, String keyword, int pageSize, int pageNum) {
        if(keyword.isBlank()) return wiseSayingRepository.getForList(pageSize, pageNum);
        return switch (keywordType) {
            case "content" -> wiseSayingRepository.findForListByContent(keyword, pageSize, pageNum);
            case "author" -> wiseSayingRepository.findForListByAuthor(keyword, pageSize, pageNum);
            default -> wiseSayingRepository.findForListByContentOrAuthor(keyword, keyword, pageSize, pageNum);
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
