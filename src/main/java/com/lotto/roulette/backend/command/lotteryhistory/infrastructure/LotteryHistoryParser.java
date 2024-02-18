package com.lotto.roulette.backend.command.lotteryhistory.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotto.roulette.backend.common.exception.BusinessException;
import com.lotto.roulette.backend.common.exception.InternalServerErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LotteryHistoryParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static LotteryWinningHistoryInfo parseLottoWinnerInfo(String lottoWinnerInfo) {
        try {
            LotteryWinningHistoryResponse response = objectMapper.readValue(lottoWinnerInfo, LotteryWinningHistoryResponse.class);
            return LotteryWinningHistoryInfo.from(response);
        } catch (Exception e) {
            throw BusinessException.from(new InternalServerErrorCode(e.getMessage()));
        }
    }
}
