package com.lotto.roulette.backend.command.application;

import com.lotto.roulette.backend.command.domain.LotteryNumberFrequency;
import com.lotto.roulette.backend.command.domain.LotteryNumberFrequencyRepository;
import com.lotto.roulette.backend.support.enviroment.ServiceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@Sql("/fixture/lottery-number-frequency-fixture.sql")
class LotteryNumberFrequencyServiceTest extends ServiceTest {

    @Autowired
    private LotteryNumberFrequencyService lotteryNumberFrequencyService;

    @Autowired
    private LotteryNumberFrequencyRepository lotteryNumberFrequencyRepository;

    @Test
    void 로또_당첨_번호_빈도_수를_저장한다() {
        // given
        List<Integer> winningNumbers = List.of(44, 45);

        // when
        lotteryNumberFrequencyService.save(winningNumbers);

        LotteryNumberFrequency lotteryNumberFrequency = lotteryNumberFrequencyRepository.findByLottoNumber(44)
                .orElseThrow();

        // then
        Assertions.assertThat(lotteryNumberFrequency.getFrequency()).isEqualTo(11);
    }

}