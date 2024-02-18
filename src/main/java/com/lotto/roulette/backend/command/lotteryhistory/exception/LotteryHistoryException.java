package com.lotto.roulette.backend.command.lotteryhistory.exception;

import com.lotto.roulette.backend.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum LotteryHistoryException implements ErrorCode {

    NOT_EXISTS_LOTTERY_HISTORY(HttpStatus.NOT_FOUND, "존재하지 않는 로또 당첨 이력입니다.")
    ;

    private final HttpStatusCode httpStatusCode;
    private final String errorMessage;
}
