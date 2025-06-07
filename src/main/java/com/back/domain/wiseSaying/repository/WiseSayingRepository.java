package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class WiseSayingRepository {
    private int LastID = 0;
    private final List<WiseSaying> wiseSayings = new ArrayList<>();

    public void save(WiseSaying wiseSaying) {
        if(wiseSaying.isNew()) {
            wiseSaying.setId(++LastID);
            wiseSayings.add(wiseSaying);
        }
    }

    public Page<WiseSaying> getForList(Pageable pageable) {
        int totalCount = wiseSayings.size();

        List<WiseSaying> content = wiseSayings.reversed()
                .stream()
                .skip(pageable.getSkipCount())
                .limit(pageable.getPageSize())
                .toList();

        return new Page<>(totalCount, pageable.getPageNum(), pageable.getPageSize(), content);
    }

    public Optional<WiseSaying> findById(int id) {
        int index = findIndexById(id);

        if(index == -1) return Optional.empty();
        return Optional.of(wiseSayings.get(index));
    }

    public int findIndexById(int id) {
        return IntStream.range(0, wiseSayings.size())
                .filter(i -> wiseSayings.get(i).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    public boolean delete(WiseSaying wiseSaying) {
        return wiseSayings.remove(wiseSaying);
    }

    public Page<WiseSaying> findForListByContent(String keyword, Pageable pageable) {
        List<WiseSaying> filtered = wiseSayings.reversed().stream()
                .filter(wiseSaying -> wiseSaying.getContent().contains(keyword))
                .toList();

        int totalCount = filtered.size();

        List<WiseSaying> content = filtered.stream()
                .skip(pageable.getSkipCount())
                .limit(pageable.getPageSize())
                .toList();

        return new Page<>(totalCount, pageable.getPageNum(), pageable.getPageSize(), content);
    }

    public Page<WiseSaying> findForListByAuthor(String keyword, Pageable pageable) {
        List<WiseSaying> filtered = wiseSayings.reversed().stream()
                .filter(wiseSaying -> wiseSaying.getAuthor().contains(keyword))
                .toList();

        int totalCount = filtered.size();

        List<WiseSaying> content = filtered.stream()
                .skip(pageable.getSkipCount())
                .limit(pageable.getPageSize())
                .toList();

        return new Page<>(totalCount, pageable.getPageNum(), pageable.getPageSize(), content);
    }

    public Page<WiseSaying> findForListByContentOrAuthor(String keyword1, String keyword2, Pageable pageable) {
        List<WiseSaying> filtered = wiseSayings.reversed().stream()
                .filter(wiseSaying -> wiseSaying.getContent().contains(keyword1) || wiseSaying.getAuthor().contains(keyword2))
                .toList();

        int totalCount = filtered.size();

        List<WiseSaying> content = filtered.stream()
                .skip(pageable.getSkipCount())
                .limit(pageable.getPageSize())
                .toList();

        return new Page<>(totalCount, pageable.getPageNum(), pageable.getPageSize(), content);
    }
}
