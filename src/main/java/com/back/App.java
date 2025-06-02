package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final Scanner scanner;
    private int LastID = 0;
    private List<WiseSaying> WiseSayings = new ArrayList<>();

    public void run() {
        System.out.println("==명언 앱==");
        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            switch (cmd) {
                case "종료" -> {
                    System.out.println("앱을 종료합니다.");
                    return;
                }
                case "등록" -> {
                    System.out.print("명언: ");
                    String content = scanner.nextLine();

                    System.out.print("작가: ");
                    String author = scanner.nextLine();

                    WiseSayings.add(new WiseSaying(++LastID, content, author));

                    System.out.println(LastID + "번 명언이 등록되었습니다.");
                }
                default -> System.out.println("알 수 없는 명령어입니다.");
            }
        }
    }

    public App(Scanner scanner) {
        this.scanner = scanner;
    }
}
