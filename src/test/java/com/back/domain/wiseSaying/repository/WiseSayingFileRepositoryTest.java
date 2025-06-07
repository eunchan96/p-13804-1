package com.back.domain.wiseSaying.repository;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.dto.Pageable;
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
        wiseSayingFileRepository.clear();
    }

    @Test
    @DisplayName("명언을 파일로 저장할 수 있다.")
    void t1(){
        WiseSaying wiseSaying1 = new WiseSaying("나의 죽음을 적에게 알리지 마라", "이순신");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying foundWiseSaying = wiseSayingFileRepository.findById(1).get();

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
                wiseSayingFileRepository.findById(2).get()
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
        ).isEmpty();
    }

    @Test
    @DisplayName("명언 다건 조회")
    void t4() {
        WiseSaying wiseSaying1 = new WiseSaying("호랑이 굴에 가야 호랑이 새끼를 잡는다.", "이순신");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("나의 죽음을 적들에게 알리지 말라.", "이순신");
        wiseSayingFileRepository.save(wiseSaying2);

        assertThat(
                wiseSayingFileRepository.findForList(new Pageable(1, 5)).getContent()
        ).containsExactly(wiseSaying2, wiseSaying1);
    }

    @Test
    @DisplayName("명언 다건조회, Content로 조회")
    void t5() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("나의 죽음을 적들에게 알리지 말라.", "이순신");
        wiseSayingFileRepository.save(wiseSaying2);

        WiseSaying wiseSaying3 = new WiseSaying("생생한 꿈은 현실이 된다.", "작자미상");
        wiseSayingFileRepository.save(wiseSaying3);

        assertThat(
                wiseSayingFileRepository.findForListByContent("꿈", new Pageable(1, 5)).getContent()
        ).containsExactly(wiseSaying3, wiseSaying1);
    }

    @Test
    @DisplayName("명언 다건조회, Author로 조회")
    void t6() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("내가 누구게", "작자미상");
        wiseSayingFileRepository.save(wiseSaying2);

        WiseSaying wiseSaying3 = new WiseSaying("생생한 꿈은 현실이 된다.", "작자미상");
        wiseSayingFileRepository.save(wiseSaying3);

        assertThat(
                wiseSayingFileRepository.findForListByAuthor("작자미상", new Pageable(1, 5)).getContent()
        ).containsExactly(wiseSaying3, wiseSaying2);
    }

    @Test
    @DisplayName("명언 다건조회, findForListByContentContainingOrAuthorContaining")
    public void t7() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("나의 삶의 가치는 나의 결정에 달려있다.", "아인슈타인");
        wiseSayingFileRepository.save(wiseSaying2);

        WiseSaying wiseSaying3 = new WiseSaying("생생한 꿈은 현실이 된다.", "작자미상");
        wiseSayingFileRepository.save(wiseSaying3);

        WiseSaying wiseSaying4 = new WiseSaying("신은 주사위놀이를 하지 않는다.", "아인슈타인");
        wiseSayingFileRepository.save(wiseSaying4);

        WiseSaying wiseSaying5 = new WiseSaying("나의 상상은 현실이 된다.", "아무개");
        wiseSayingFileRepository.save(wiseSaying5);

        assertThat(
                wiseSayingFileRepository.findForListByContentOrAuthor("상", "상", new Pageable(1, 5)).getContent()
        ).containsExactly(wiseSaying5, wiseSaying3);
    }
}
