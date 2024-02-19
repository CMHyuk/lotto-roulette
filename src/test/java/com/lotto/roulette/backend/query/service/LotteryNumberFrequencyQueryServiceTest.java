package com.lotto.roulette.backend.query.service;

import com.lotto.roulette.backend.query.dto.LotteryNumberFrequencyResponse;
import com.lotto.roulette.backend.support.enviroment.ServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Sql("/fixture/lottery-number-frequency-fixture.sql")
class LotteryNumberFrequencyQueryServiceTest extends ServiceTest {

    @Autowired
    private LotteryNumberFrequencyQueryService lotteryNumberFrequencyQueryService;

    @Test
    void 로또_번호들의_빈도수를_조회한다() {
        // when
        List<LotteryNumberFrequencyResponse> results = lotteryNumberFrequencyQueryService.findAll();

        // then
        assertThat(results.size()).isEqualTo(45);
        assertThat(results.get(37).lotteryNumber()).isEqualTo(38);
        assertThat(results.get(37).frequency()).isEqualTo(44);
    }
}
