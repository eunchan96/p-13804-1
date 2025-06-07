package com.back.domain.system.controller;

import com.back.AppTestRunner;
import com.back.global.app.AppConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SystemControllerTest {
    @BeforeAll
    public static void beforeAll() {
        AppConfig.setTestMode();
    }

    @Test
    @DisplayName("종료")
    public void t1() {
        String rs = AppTestRunner.run("");

        assertThat(rs).contains("프로그램을 종료합니다.");
    }
}
