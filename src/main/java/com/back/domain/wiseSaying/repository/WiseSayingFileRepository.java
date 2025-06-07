package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.global.app.AppConfig;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;
import com.back.standard.util.Util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class WiseSayingFileRepository implements WiseSayingRepository {
    public String getEntityFilePath(WiseSaying wiseSaying) {
        return getEntityFilePath(wiseSaying.getId());
    }

    public String getEntityFilePath(int id) {
        return getTableDirPath() + "/%d.json".formatted(id);
    }

    public String getLastIdFilePath() {
        return getTableDirPath() + "/lastId.txt";
    }

    public String getTableDirPath() {
        return AppConfig.getMode() + "Db/wiseSaying";
    }


    public void save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            int newId = getLastId() + 1;
            wiseSaying.setId(newId);
            setLastId(newId);
        }
        Map<String, Object> wiseSayingMap = wiseSaying.toMap();
        String wiseSayingJsonStr = Util.json.toString(wiseSayingMap);
        Util.file.set(getEntityFilePath(wiseSaying), wiseSayingJsonStr);
    }

    public Optional<WiseSaying> findById(int id) {
        String wiseSayingJsonStr = Util.file.get(getEntityFilePath(id), "");

        if (wiseSayingJsonStr.isEmpty()) {
            return Optional.empty();
        }

        Map<String, Object> wiseSayingMap = Util.json.toMap(wiseSayingJsonStr);

        return Optional.of(new WiseSaying(wiseSayingMap));
    }

    private int getLastId() {
        return Util.file.getAsInt(getLastIdFilePath(), 0);
    }

    private void setLastId(int newId) {
        Util.file.set(getLastIdFilePath(), newId);
    }

    public boolean delete(WiseSaying wiseSaying) {
        String filePath = getEntityFilePath(wiseSaying);

        return Util.file.delete(filePath);
    }

    public void clear() {
        Util.file.rmdir(getTableDirPath());
    }

    private Page<WiseSaying> createPage(List<WiseSaying> filterd, Pageable pageable) {
        int totalCount = filterd.size();

        List<WiseSaying> content = filterd
                .stream()
                .skip(pageable.getSkipCount())
                .limit(pageable.getPageSize())
                .toList();

        return new Page<>(totalCount, pageable.getPageNum(), pageable.getPageSize(), content);
    }

    public Page<WiseSaying> findForList(Pageable pageable) {
        List<WiseSaying> filterd = findAll().toList();
        return createPage(filterd, pageable);
    }

    private Stream<WiseSaying> findAll() {
        return Util.file.walkRegularFiles(getTableDirPath(), "\\d+\\.json")
                .map(path -> Util.file.get(path.toString(), ""))
                .map(Util.json::toMap)
                .map(WiseSaying::new)
                .sorted(Comparator.comparingInt(WiseSaying::getId).reversed());
    }

    public Page<WiseSaying> findForListByContent(String keyword, Pageable pageable) {
        List<WiseSaying> filtered = findAll()
                .filter(wiseSaying -> wiseSaying.getContent().contains(keyword))
                .toList();

        return createPage(filtered, pageable);
    }

    public Page<WiseSaying> findForListByAuthor(String keyword, Pageable pageable) {
        List<WiseSaying> filtered = findAll()
                .filter(wiseSaying -> wiseSaying.getAuthor().contains(keyword))
                .toList();

        return createPage(filtered, pageable);
    }

    public Page<WiseSaying> findForListByContentOrAuthor(String contentKeyword, String authorKeyword, Pageable pageable) {
        List<WiseSaying> filtered = findAll()
                .filter(wiseSaying -> wiseSaying.getContent().contains(contentKeyword) || wiseSaying.getAuthor().contains(authorKeyword))
                .toList();

        return createPage(filtered, pageable);
    }
}
