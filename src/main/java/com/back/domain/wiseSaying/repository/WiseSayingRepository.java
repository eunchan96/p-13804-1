package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;

import java.util.Optional;

public interface WiseSayingRepository {
    void save(WiseSaying wiseSaying);

    Optional<WiseSaying> findById(int id);

    boolean delete(WiseSaying wiseSaying);

    Page<WiseSaying> findForList(Pageable pageable);

    Page<WiseSaying> findForListByContent(String content, Pageable pageable);

    Page<WiseSaying> findForListByAuthor(String author, Pageable pageable);

    Page<WiseSaying> findForListByContentOrAuthor(String content, String author, Pageable pageable);
}
