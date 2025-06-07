package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.global.app.AppConfig;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;
import com.back.standard.util.Util;

import java.util.List;
import java.util.Optional;

public interface WiseSayingRepository {
    void save(WiseSaying wiseSaying);

    Optional<WiseSaying> findById(int id);

    boolean delete(WiseSaying wiseSaying);

    Page<WiseSaying> findForList(Pageable pageable);

    Page<WiseSaying> findForListByContent(String content, Pageable pageable);

    Page<WiseSaying> findForListByAuthor(String author, Pageable pageable);

    Page<WiseSaying> findForListByContentOrAuthor(String content, String author, Pageable pageable);

    default String archive(){
        List<WiseSaying> all = findAll();

        String json = Util.json.toString(all.stream()
                .map(WiseSaying::toMap)
                .toList()
        );

        String filePath = getArchiveFilePath();

        Util.file.set(filePath, json);
        return filePath;
    }

    default String getTableDirPath() {
        return AppConfig.getMode() + "Db/wiseSaying";
    }

    List<WiseSaying> findAll();

    default String getArchiveFilePath(){
        return getTableDirPath() + "/data.json";
    }
}
