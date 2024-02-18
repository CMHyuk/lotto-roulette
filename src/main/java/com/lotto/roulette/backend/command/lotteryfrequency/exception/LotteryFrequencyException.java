package com.lotto.roulette.backend.command.lotteryfrequency.exception;

import com.lotto.roulette.backend.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum LotteryFrequencyException implements ErrorCode {

    NOT_EXISTS_LOTTERY_FREQUENCY(HttpStatus.NOT_FOUND, "존재하지 않는 빈도 수입니다.")
    ;

    private final HttpStatusCode httpStatusCode;
    private final String errorMessage;
}
