package com.back;

import com.back.system.SystemController;
import com.back.wiseSaying.WiseSayingController;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public void run() {
        System.out.println("==명언 앱==");

        WiseSayingController wiseSayingController = new WiseSayingController(scanner);
        SystemController systemController = new SystemController();

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            switch (cmd) {
                case "종료" -> {
                    systemController.actionExit();
                    return;
                }
                case "등록" -> wiseSayingController.actionWrite();
                case "목록" -> wiseSayingController.actionList();
                default -> System.out.println("알 수 없는 명령어입니다.");
            }
        }
    }

    public App(Scanner scanner) {
        this.scanner = scanner;
    }
}
