package com.lotto.roulette.backend.command.infrastructure.lotto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotto.roulette.backend.command.dto.LottoWinnerInfo;
import com.lotto.roulette.backend.common.exception.BusinessException;
import com.lotto.roulette.backend.common.exception.InternalServerErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LottoWinnerParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static LottoWinnerInfo parseLottoWinnerInfo(String lottoWinnerInfo) {
        try {
            LottoWinnerApiResponse response = objectMapper.readValue(lottoWinnerInfo, LottoWinnerApiResponse.class);
            return LottoWinnerInfo.from(response);
        } catch (Exception e) {
            throw BusinessException.from(new InternalServerErrorCode(e.getMessage()));
        }
    }
}
