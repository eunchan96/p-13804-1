package com.back.domain.wiseSaying.repository;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.util.Util;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingFileRepositoryTest {
    private final WiseSayingFileRepository wiseSayingFileRepository;

    public WiseSayingFileRepositoryTest (){
        wiseSayingFileRepository = AppContext.wiseSayingFileRepository;
    }

    @BeforeAll
    public static void beforeAll() {
        AppContext.renew();
    }

    @BeforeEach
    public void beforeEach() {
        Util.file.rmdir("db/wiseSaying");
    }

    @Test
    @DisplayName("명언을 파일로 저장할 수 있다.")
    void t1(){
        WiseSaying wiseSaying1 = new WiseSaying("나의 죽음을 적에게 알리지 마라", "이순신");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying foundWiseSaying = wiseSayingFileRepository.findById(1);

        assertThat(
                foundWiseSaying
        ).isEqualTo(wiseSaying1);
    }

    @Test
    @DisplayName("2번째 등록에서는 2번 명언이 생성된다.")
    void t2() {
        WiseSaying wiseSaying1 = new WiseSaying("호랑이 굴에 가야 호랑이 새끼를 잡는다.", "이순신");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("나의 죽음을 적들에게 알리지 말라.", "이순신");
        wiseSayingFileRepository.save(wiseSaying2);

        assertThat(
                wiseSayingFileRepository.findById(2)
        ).isEqualTo(wiseSaying2);
    }

    @Test
    @DisplayName("명언 삭제")
    void t3() {
        WiseSaying wiseSaying1 = new WiseSaying("호랑이 굴에 가야 호랑이 새끼를 잡는다.", "이순신");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("나의 죽음을 적들에게 알리지 말라.", "이순신");
        wiseSayingFileRepository.save(wiseSaying2);

        wiseSayingFileRepository.delete(wiseSaying1);

        assertThat(
                wiseSayingFileRepository.findById(1)
        ).isNull();
    }

}
