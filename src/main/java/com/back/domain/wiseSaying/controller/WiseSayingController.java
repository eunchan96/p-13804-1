package com.back.domain.wiseSaying.controller;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;
import com.back.global.rq.Rq;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WiseSayingController {
    private final Scanner scanner;
    private final WiseSayingService wiseSayingsService;

    public WiseSayingController(){
        this.scanner = AppContext.scanner;
        wiseSayingsService = AppContext.wiseSayingService;
    }

    public void actionWrite() {
        System.out.print("명언: ");
        String content = scanner.nextLine();

        System.out.print("작가: ");
        String author = scanner.nextLine();

        WiseSaying wiseSaying = new WiseSaying(content, author);

        wiseSayingsService.write(wiseSaying);
        System.out.println(wiseSaying.getId() + "번 명언이 등록되었습니다.");
    }

    public void actionList(Rq rq) {
        System.out.println("번호 / 명언 / 작가");
        System.out.println("-------------------------");

        int pageSize = rq.getParamAsInt("pageSize", 5);
        int pageNum = rq.getParamAsInt("page", 1);

        Pageable pageable = new Pageable(pageNum, pageSize);

        String keywordType = rq.getParam("keywordType", "all");
        String keyword = rq.getParam("keyword", "");

        Page<WiseSaying> wiseSayingPage = wiseSayingsService.getForList(keywordType, keyword, pageable);

        for (WiseSaying wiseSaying : wiseSayingPage.getContent()) {
            System.out.printf("%d / %s / %s\n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }

        System.out.print("페이지 : ");

        String pageMenu = IntStream.rangeClosed(1, wiseSayingPage.getTotalPages())
                .mapToObj(i -> (i == wiseSayingPage.getPageNum() ? "[" + i + "]" : String.valueOf(i)))
                .collect(Collectors.joining(" / "));

        System.out.println(pageMenu);
    }

    public void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        if (id == -1) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }

        boolean deleted = wiseSayingsService.delete(id);

        if(deleted) System.out.println(id + "번 명언이 삭제되었습니다.");
        else System.out.println(id + "번 명언은 존재하지 않습니다.");
    }

    public void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }

        Optional<WiseSaying> opWiseSaying = wiseSayingsService.findById(id);

        if (opWiseSaying.isEmpty()) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        WiseSaying wiseSaying = opWiseSaying.get();

        System.out.printf("명언(기존) : %s\n", wiseSaying.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.printf("작가(기존) : %s\n", wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        wiseSayingsService.modify(wiseSaying, content, author);
    }
}
