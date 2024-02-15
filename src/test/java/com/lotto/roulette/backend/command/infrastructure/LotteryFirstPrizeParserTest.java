package com.lotto.roulette.backend.command.infrastructure;

import com.lotto.roulette.backend.command.dto.LotteryWinningHistoryInfo;
import com.lotto.roulette.backend.command.infrastructure.lotto.LotteryFirstPrizeParser;
import com.lotto.roulette.backend.common.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LotteryFirstPrizeParserTest {

    @Nested
    @DisplayName("로또 당첨 정보의 JSON 변환을")
    class ConvertTest {

        @Test
        void 성공한다() {
            // given
            String lottoWinnerInfo = "{" +
                    "\"totSellamnt\":56561977000," +
                    "\"returnValue\":\"success\"," +
                    "\"drwNoDate\":\"2004-10-30\"," +
                    "\"firstWinamnt\":3315315525," +
                    "\"drwtNo6\":42," +
                    "\"drwtNo4\":23," +
                    "\"firstPrzwnerCo\":4," +
                    "\"drwtNo5\":37," +
                    "\"bnusNo\":6," +
                    "\"firstAccumamnt\":0," +
                    "\"drwNo\":100," +
                    "\"drwtNo2\":7," +
                    "\"drwtNo3\":11," +
                    "\"drwtNo1\":1" +
                    "}";

            // when
            LotteryWinningHistoryInfo result = LotteryFirstPrizeParser.parseLottoWinnerInfo(lottoWinnerInfo);

            // then
            assertEquals(1, result.firstLottoNumber());
            assertEquals(7, result.secondLottoNumber());
            assertEquals(11, result.thirdLottoNumber());
            assertEquals(23, result.fourthLottoNumber());
            assertEquals(37, result.fifthLottoNumber());
            assertEquals(42, result.sixthLottoNumber());
            assertEquals(6, result.bonusNumber());
            assertEquals("₩3,315,315,525", result.firstPrizeAmount());
            assertEquals("2004-10-30", result.roundDate());
            assertEquals(4, result.winnerCount());
            assertEquals(100, result.round());
        }

        @Test
        void 실패한다() {
            // given
            String invalidJson = "{" +
                    "\"key1\":\"value1\"," +
                    "\"key2\":\"value2\"," +
                    "\"key3\":," +
                    "\"key4\":\"value4\"" +
                    "}";

            // when then
            assertThatThrownBy(() -> LotteryFirstPrizeParser.parseLottoWinnerInfo(invalidJson))
                    .isInstanceOf(BusinessException.class);
        }
    }
}