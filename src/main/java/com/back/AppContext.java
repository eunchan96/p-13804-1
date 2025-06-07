package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.domain.wiseSaying.repository.WiseSayingFileRepository;
import com.back.domain.wiseSaying.repository.WiseSayingMemoryRepository;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

public class AppContext {
    public static Scanner scanner;
    public static WiseSayingRepository wiseSayingRepository;
    public static WiseSayingMemoryRepository wiseSayingMemoryRepository;
    public static WiseSayingFileRepository wiseSayingFileRepository;
    public static WiseSayingService wiseSayingService;
    public static WiseSayingController wiseSayingController;
    public static SystemController systemController;

    public static void renew(Scanner _scanner) {
        scanner = _scanner;
        wiseSayingMemoryRepository = new WiseSayingMemoryRepository();
        wiseSayingFileRepository = new WiseSayingFileRepository();
        wiseSayingRepository = wiseSayingMemoryRepository;
        wiseSayingService = new WiseSayingService();
        wiseSayingController = new WiseSayingController();
        systemController = new SystemController();
    }

    public static void renew() {
        renew(new Scanner(System.in));
    }
}
