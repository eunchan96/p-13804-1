package com.back.domain.wiseSaying.controller;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

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

        wiseSayingsService.write(content, author);
    }

    public void actionList() {
        System.out.println("번호 / 명언 / 작가");
        System.out.println("-------------------------");
        for (WiseSaying wiseSaying : wiseSayingsService.getforList()) {
            System.out.printf("%d / %s / %s\n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }
    }
}
