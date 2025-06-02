package com.back.domain.system.controller;

import com.back.AppTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SystemControllerTest {
    @Test
    @DisplayName("종료")
    public void t1() {
        String rs = AppTestRunner.run("");

        assertThat(rs).contains("프로그램을 종료합니다.");
    }
}
