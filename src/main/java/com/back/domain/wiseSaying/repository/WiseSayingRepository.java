package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

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

    public List<WiseSaying> getForList(int pageSize, int pageNum) {
        return wiseSayings.reversed()
                .stream()
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
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

    public List<WiseSaying> findForListByContent(String keyword, int pageSize, int pageNum) {
        return wiseSayings.reversed().stream()
                .filter(wiseSaying -> wiseSaying.getContent().contains(keyword))
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public List<WiseSaying> findForListByAuthor(String keyword, int pageSize, int pageNum) {
        return wiseSayings.reversed().stream()
                .filter(wiseSaying -> wiseSaying.getAuthor().contains(keyword))
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList()).reversed();
    }

    public List<WiseSaying> findForListByContentOrAuthor(String keyword1, String keyword2, int pageSize, int pageNum) {
        return wiseSayings.reversed().stream()
                .filter(wiseSaying -> wiseSaying.getContent().contains(keyword1) || wiseSaying.getAuthor().contains(keyword2))
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
