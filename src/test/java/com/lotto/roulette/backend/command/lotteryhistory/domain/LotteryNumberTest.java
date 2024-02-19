package com.lotto.roulette.backend.command.lotteryhistory.domain;


import com.lotto.roulette.backend.command.lotteryhistory.exception.LotteryHistoryException;
import com.lotto.roulette.backend.common.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LotteryNumberTest {

    @Test
    void 로또_번호_범위에_포함되지_않으면_예외가_발생한다() {
        //when then
        assertThatThrownBy(() -> LotteryNumber.create(1,2,3,4,5,99))
                .isInstanceOf(BusinessException.class)
                .extracting("errorCode")
                .isEqualTo(LotteryHistoryException.INVALID_LOTTERY_NUMBER);
    }
}