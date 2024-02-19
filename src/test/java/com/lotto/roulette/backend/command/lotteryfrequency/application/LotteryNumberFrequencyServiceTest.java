package com.lotto.roulette.backend.command.lotteryfrequency.application;

import com.lotto.roulette.backend.command.lotteryhistory.application.LotteryNumberFrequencyService;
import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryNumberFrequency;
import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryNumberFrequencyRepository;
import com.lotto.roulette.backend.common.exception.BusinessException;
import com.lotto.roulette.backend.support.enviroment.ServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.lotto.roulette.backend.command.lotteryhistory.exception.LotteryFrequencyException.NOT_EXISTS_LOTTERY_FREQUENCY;
import static org.assertj.core.api.Assertions.assertThat;

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
        LotteryNumberFrequency lotteryNumberFrequency = lotteryNumberFrequencyRepository.findByLotteryNumber(44)
                .orElseThrow(() -> BusinessException.from(NOT_EXISTS_LOTTERY_FREQUENCY));

        // then
        assertThat(lotteryNumberFrequency.getFrequency()).isEqualTo(24);
    }
}
