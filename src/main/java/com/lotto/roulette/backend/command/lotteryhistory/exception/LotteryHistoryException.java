package com.lotto.roulette.backend.command.lotteryhistory.exception;

import com.lotto.roulette.backend.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum LotteryHistoryException implements ErrorCode {

    NOT_EXISTS_LOTTERY_HISTORY(404, "존재하지 않는 로또 당첨 이력입니다."),
    INVALID_LOTTERY_NUMBER(400, "존재하지 않는 로또 당첨 이력입니다.")
    ;

    private final int httpStatusCode;
    private final String errorMessage;
}
