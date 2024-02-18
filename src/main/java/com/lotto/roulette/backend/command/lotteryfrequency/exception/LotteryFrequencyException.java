package com.lotto.roulette.backend.command.lotteryfrequency.exception;

import com.lotto.roulette.backend.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum LotteryFrequencyException implements ErrorCode {

    NOT_EXISTS_LOTTERY_FREQUENCY(404, "존재하지 않는 빈도 수입니다.")
    ;

    private final int httpStatusCode;
    private final String errorMessage;
}
