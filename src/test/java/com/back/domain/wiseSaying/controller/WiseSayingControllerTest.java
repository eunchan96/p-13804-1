package com.back.domain.wiseSaying.controller;

import com.back.AppTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingControllerTest {
    @Test
    @DisplayName("등록")
    public void t1() {
        String rs = AppTestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라.
                작자미상
                """);

        assertThat(rs)
                .contains("명언:")
                .contains("작가:");
    }

    @Test
    @DisplayName("등록 시 명언 번호 출력")
    public void t2() {
        String rs = AppTestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라.
                작자미상
                """);

        assertThat(rs)
                .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("등록 시 명언 번호 증가")
    public void t3() {
        String rs = AppTestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라.
                작자미상
                등록
                내 사전에 불가능이란 없다.
                나폴레옹
                """);

        assertThat(rs)
                .contains("1번 명언이 등록되었습니다.")
                .contains("2번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("목록")
    public void t4() {
        String rs = AppTestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라.
                작자미상
                등록
                내 사전에 불가능이란 없다.
                나폴레옹
                목록
                """);

        assertThat(rs)
                .contains("2 / 나폴레옹 / 내 사전에 불가능이란 없다.")
                .contains("1 / 작자미상 / 나의 죽음을 적들에게 알리지 말라.");
    }

    @Test
    @DisplayName("삭제")
    public void t5() {
        String rs = AppTestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라.
                작자미상
                등록
                내 사전에 불가능이란 없다.
                나폴레옹
                삭제?id=1
                목록
                """);

        assertThat(rs)
                .contains("1번 명언이 삭제되었습니다.")
                .contains("2 / 나폴레옹 / 내 사전에 불가능이란 없다.")
                .doesNotContain("1 / 작자미상 / 나의 죽음을 적들에게 알리지 말라.");
    }

    @Test
    @DisplayName("`삭제?id=1`를 2번 시도했을 때의 예외처리")
    void t6() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                삭제?id=1
                삭제?id=1
                """);

        assertThat(rs)
                .contains("1번 명언이 삭제되었습니다.")
                .contains("1번 명언은 존재하지 않습니다.");
    }
}
