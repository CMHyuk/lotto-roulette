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
        List<LotteryNumberFrequencyResponse> actual = lotteryNumberFrequencyQueryService.findAll();

        // then
        assertThat(actual.size()).isEqualTo(45);
        assertThat(actual.get(37).lotteryNumber()).isEqualTo(38);
        assertThat(actual.get(37).frequency()).isEqualTo(177);
    }

    @Test
    void 가장_많이_나온_로또_번호_6개를_조회한다() {
        // when
        List<LotteryNumberFrequencyResponse> actual = lotteryNumberFrequencyQueryService.findTop6MostFrequentNumbers();

        // then
        assertThat(actual)
                .containsExactly(
                        new LotteryNumberFrequencyResponse(43, 191),
                        new LotteryNumberFrequencyResponse(34, 190),
                        new LotteryNumberFrequencyResponse(12, 188),
                        new LotteryNumberFrequencyResponse(17, 187),
                        new LotteryNumberFrequencyResponse(27, 186),
                        new LotteryNumberFrequencyResponse(1, 185)
                );
    }
}
