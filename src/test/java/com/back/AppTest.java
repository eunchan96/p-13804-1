package com.back;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    @DisplayName("`==명언 앱==` 출력")
    public void t1() {
        String rs = AppTestRunner.run("");

        assertThat(rs)
                .contains("==명언 앱==");
    }

    @Test
    @DisplayName("등록")
    public void t2() {
        String rs = AppTestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라.
                작자미상
                """);

        assertThat(rs)
                .contains("명령) 등록")
                .contains("명언: 나의 죽음을 적들에게 알리지 말라.");
    }
}
