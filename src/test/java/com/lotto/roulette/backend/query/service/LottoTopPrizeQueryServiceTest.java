package com.lotto.roulette.backend.query.service;

import com.lotto.roulette.backend.query.dto.TopPrizeResponse;
import com.lotto.roulette.backend.support.enviroment.ServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("/fixture/lottery-history-fixture.sql")
class LottoTopPrizeQueryServiceTest extends ServiceTest {

    @Autowired
    private LotteryHistoryQueryService lottoTopPrizeQueryService;

    @Test
    void 가장_높았던_당첨_금액을_조회한다() {
        // when
        TopPrizeResponse topPrize = lottoTopPrizeQueryService.getTopPrize();

        // then
        assertThat(topPrize.topPrize()).isEqualTo(1500000000);
    }
}
