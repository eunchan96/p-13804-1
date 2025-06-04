package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.dto.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

    public List<WiseSaying> getForList(Pageable pageable) {
        return wiseSayings.reversed()
                .stream()
                .skip(pageable.getSkipCount())
                .limit(pageable.getPageSize())
                .toList();
    }

    public WiseSaying findById(int id) {
        int index = findIndexById(id);

        if(index == -1) return null;
        return wiseSayings.get(index);
    }

    public int findIndexById(int id) {
        return IntStream.range(0, wiseSayings.size())
                .filter(i -> wiseSayings.get(i).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    public void delete(WiseSaying wiseSaying) {
        wiseSayings.remove(wiseSaying);
    }

    public List<WiseSaying> findForListByContent(String keyword, Pageable pageable) {
        return wiseSayings.reversed().stream()
                .filter(wiseSaying -> wiseSaying.getContent().contains(keyword))
                .skip(pageable.getSkipCount())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
    }

    public List<WiseSaying> findForListByAuthor(String keyword, Pageable pageable) {
        return wiseSayings.reversed().stream()
                .filter(wiseSaying -> wiseSaying.getAuthor().contains(keyword))
                .skip(pageable.getSkipCount())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList()).reversed();
    }

    public List<WiseSaying> findForListByContentOrAuthor(String keyword1, String keyword2, Pageable pageable) {
        return wiseSayings.reversed().stream()
                .filter(wiseSaying -> wiseSaying.getContent().contains(keyword1) || wiseSaying.getAuthor().contains(keyword2))
                .skip(pageable.getSkipCount())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
    }
}
