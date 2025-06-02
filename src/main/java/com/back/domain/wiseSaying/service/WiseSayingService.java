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

    public void write (String content, String author) {
        wiseSayingRepository.save(content, author);
    }

    public List<WiseSaying> getforList(){
        return wiseSayingRepository.getForList();
    }

    public boolean delete(int id) {
        WiseSaying wiseSaying = wiseSayingRepository.findById(id);
        if (wiseSaying == null) return false;
        wiseSayingRepository.delete(wiseSaying);
        return true;
    }
}
