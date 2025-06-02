package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private int LastID = 0;
    private final List<WiseSaying> wiseSayings = new ArrayList<>();

    public void save(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(++LastID, content, author);
        wiseSayings.add(wiseSaying);
        System.out.println(wiseSaying.getId() + "번 명언이 등록되었습니다.");
    }

    public List<WiseSaying> getForList() {
        return wiseSayings.reversed();
    }
}
