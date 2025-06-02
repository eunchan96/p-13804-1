package com.back.domain.wiseSaying.controller;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    public void actionList() {
        System.out.println("번호 / 명언 / 작가");
        System.out.println("-------------------------");
        for (WiseSaying wiseSaying : wiseSayingsService.getforList()) {
            System.out.printf("%d / %s / %s\n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }
    }

    public void actionDelete(String cmd) {
        String[] cmdBits = cmd.split("\\?",2);
        String queryString = cmdBits[1].trim();

        Map<String, String> params = Arrays.stream(queryString.split("&"))
                .map(e -> e.split("=", 2))
                .filter(e -> e.length == 2 && e[0].length() > 0 && e[1].length() > 0)
                .collect(Collectors.toMap(e -> e[0].trim(), e -> e[1].trim()));

        String idStr = params.getOrDefault("id", "");
        int id = Integer.parseInt(idStr);

        boolean deleted = wiseSayingsService.delete(id);

        if(deleted) System.out.println(id + "번 명언이 삭제되었습니다.");
        else System.out.println(id + "번 명언은 존재하지 않습니다.");
    }

    public void actionModify(String cmd) {
        String[] cmdBits = cmd.split("\\?", 2);
        String queryString = cmdBits[1].trim();

        Map<String, String> params = Arrays.stream(queryString.split("&"))
                .map(e -> e.split("=", 2))
                .filter(e -> e.length == 2 && e[0].length() > 0 && e[1].length() > 0)
                .collect(Collectors.toMap(e -> e[0].trim(), e -> e[1].trim()));

        String idStr = params.getOrDefault("id", "");
        int id = Integer.parseInt(idStr);

        WiseSaying wiseSaying = wiseSayingsService.findById(id);

        if (wiseSaying == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        System.out.printf("명언(기존) : %s", wiseSaying.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.printf("작가(기존) : %s", wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        wiseSayingsService.modify(wiseSaying, content, author);
    }
}
