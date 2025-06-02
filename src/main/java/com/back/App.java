package com.back;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public void run() {
        System.out.println("==명언 앱==");
        while (true) {
            String cmd = scanner.nextLine();

            switch (cmd) {
                case "종료" -> {
                    System.out.println("앱을 종료합니다.");
                    return;
                }
                case "등록" -> {
                    System.out.println("명령) " + cmd);
                    String content = scanner.nextLine();
                    System.out.println("명언: " + content);
                    String author = scanner.nextLine();
                    System.out.println("작가: " + author);
                }
                default -> System.out.println("알 수 없는 명령어입니다.");
            }
        }
    }

    public App(Scanner scanner) {
        this.scanner = scanner;
    }
}
