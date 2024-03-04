package com.lotto.roulette.backend.command.lotteryhistory.exception;

import com.lotto.roulette.backend.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum LotteryHistoryException implements ErrorCode {

    NOT_EXISTS_LOTTERY_HISTORY(3001, 404, "존재하지 않는 로또 당첨 이력입니다."),
    INVALID_LOTTERY_NUMBER(3002, 400, "알맞지 않은 로또 번호입니다."),
    NOT_EXISTS_EXCEL_DATA(3003, 404, "해당 엑셀에 데이터가 없습니다.")
    ;

    private final int value;
    private final int httpStatusCode;
    private final String errorMessage;
}
