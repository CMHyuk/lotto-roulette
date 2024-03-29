package com.lotto.roulette.backend.query.service;

import com.lotto.roulette.backend.query.dto.LotteryHistoryResponse;
import com.lotto.roulette.backend.query.dto.TopPrizeResponse;
import com.lotto.roulette.backend.support.enviroment.ServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("/fixture/lottery-history-fixture.sql")
class LotteryHistoryQueryServiceTest extends ServiceTest {

    @Autowired
    private LotteryHistoryQueryService lotteryHistoryQueryService;

    @Nested
    @DisplayName("로또 당첨 정보를 조회할 때")
    class getLotteryHistoryService {

        @Test
        void 최고_당첨_금액을_조회한다() {
            // when
            TopPrizeResponse topPrize = lotteryHistoryQueryService.getTopPrize();

            // then
            assertThat(topPrize.topPrize()).isEqualTo("₩1,500,000,000");
        }

        @Test
        void 회차로_검색한다() {
            // given
            int round = 1;

            // when
            LotteryHistoryResponse actual = lotteryHistoryQueryService.getLotteryHistory(round);

            // then
            assertThat(actual).isEqualTo(
                    new LotteryHistoryResponse(1, 2, 3,
                            4, 5, 6,
                            1000000000L, 3, 1)
            );
        }

        @Test
        void 페이지_단위로_조회한다() {
            // given
            Pageable pageable = Pageable.ofSize(1);

            // when
            List<LotteryHistoryResponse> actual = lotteryHistoryQueryService.getLotteryHistories(pageable);

            // then
            assertThat(actual.size()).isEqualTo(10);
        }
    }
}
