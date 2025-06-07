package com.back.domain.wiseSaying.service;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;

import java.util.Optional;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingService(){
        wiseSayingRepository = AppContext.wiseSayingRepository;
    }

    public void write (WiseSaying wiseSaying) {
        wiseSayingRepository.save(wiseSaying);
    }

    public Page<WiseSaying> getForList(String keywordType, String keyword, Pageable pageable) {
        if(keyword.isBlank()) return wiseSayingRepository.getForList(pageable);
        return switch (keywordType) {
            case "content" -> wiseSayingRepository.findForListByContent(keyword, pageable);
            case "author" -> wiseSayingRepository.findForListByAuthor(keyword, pageable);
            default -> wiseSayingRepository.findForListByContentOrAuthor(keyword, keyword, pageable);
        };
    }

    public Optional<WiseSaying> findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public boolean delete(int id) {
        Optional<WiseSaying> opWiseSaying = wiseSayingRepository.findById(id);
        if (opWiseSaying.isEmpty()) return false;
        wiseSayingRepository.delete(opWiseSaying.get());
        return true;
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);

        wiseSayingRepository.save(wiseSaying);
    }
}
