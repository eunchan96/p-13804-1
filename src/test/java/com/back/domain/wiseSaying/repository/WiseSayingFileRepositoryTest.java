package com.back.domain.wiseSaying.repository;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import org.junit.jupiter.api.BeforeAll;
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
}
