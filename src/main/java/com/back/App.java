package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public void run() {
        System.out.println("==명언 앱==");

        WiseSayingController wiseSayingController = AppContext.wiseSayingController;
        SystemController systemController = AppContext.systemController;

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            String actionName = cmd.split("\\?", 2)[0];

            switch (actionName) {
                case "종료" -> {
                    systemController.actionExit();
                    return;
                }
                case "등록" -> wiseSayingController.actionWrite();
                case "목록" -> wiseSayingController.actionList();
                case "삭제" -> wiseSayingController.actionDelete(cmd);
                case "수정" -> wiseSayingController.actionModify(cmd);
                default -> System.out.println("알 수 없는 명령어입니다.");
            }
        }
    }

    public App() {
        scanner = AppContext.scanner;
    }
}
