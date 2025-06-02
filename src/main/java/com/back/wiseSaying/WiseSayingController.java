package com.back.wiseSaying;

import com.back.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private final Scanner scanner;
    private int LastID = 0;
    private final List<WiseSaying> wiseSayings = new ArrayList<>();

    public WiseSayingController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void actionWrite() {
        System.out.print("명언: ");
        String content = scanner.nextLine();

        System.out.print("작가: ");
        String author = scanner.nextLine();

        wiseSayings.add(new WiseSaying(++LastID, content, author));

        System.out.println(LastID + "번 명언이 등록되었습니다.");
    }

    public void actionList() {
        List<WiseSaying> wiseSayingsReversed = wiseSayings.reversed();

        System.out.println("번호 / 명언 / 작가");
        System.out.println("-------------------------");
        for (WiseSaying wiseSaying : wiseSayingsReversed) {
            System.out.printf("%d / %s / %s%n", wiseSaying.getId(), wiseSaying.getContent(), wiseSaying.getAuthor());
        }
    }
}
