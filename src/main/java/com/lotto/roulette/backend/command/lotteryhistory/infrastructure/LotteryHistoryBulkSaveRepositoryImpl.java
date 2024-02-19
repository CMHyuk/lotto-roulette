package com.lotto.roulette.backend.command.lotteryhistory.infrastructure;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistory;
import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistoryBulkSaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class LotteryHistoryBulkSaveRepositoryImpl implements LotteryHistoryBulkSaveRepository {

    private static final String INSERT_SQL =
            "INSERT INTO lottery_history(" +
                    "first_prize_amount, " +
                    "winner_count, " +
                    "round, " +
                    "first_lotto_number, " +
                    "second_lotto_number, " +
                    "third_lotto_number, " +
                    "fourth_lotto_number, " +
                    "fifth_lotto_number, " +
                    "sixth_lotto_number" +
                    ") VALUES (" +
                    ":first_prize_amount, " +
                    ":winner_count, " +
                    ":round, " +
                    ":first_lotto_number, " +
                    ":second_lotto_number, " +
                    ":third_lotto_number, " +
                    ":fourth_lotto_number, " +
                    ":fifth_lotto_number, " +
                    ":sixth_lotto_number" +
                    ")";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void saveAll(MultipartFile excel) {
        jdbcTemplate.batchUpdate(INSERT_SQL, generateParameterSource(excel));
    }

    private SqlParameterSource[] generateParameterSource(MultipartFile excel) {
        List<LotteryHistory> lotteryHistories = LotteryHistoryParser.parseLotteryWinningInfo(excel);
        return lotteryHistories.stream()
                .map(artist -> new MapSqlParameterSource(generateParams(artist)))
                .toArray(SqlParameterSource[]::new);
    }

    private Map<String, Object> generateParams(LotteryHistory lotteryHistory) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", lotteryHistory.getId());
        params.put("first_prize_amount", lotteryHistory.getFirstPrizeAmount());
        params.put("winner_count", lotteryHistory.getWinnerCount());
        params.put("first_lotto_number", lotteryHistory.getLotteryNumber().getFirstLottoNumber());
        params.put("second_lotto_number", lotteryHistory.getLotteryNumber().getSecondLottoNumber());
        params.put("third_lotto_number", lotteryHistory.getLotteryNumber().getThirdLottoNumber());
        params.put("fourth_lotto_number", lotteryHistory.getLotteryNumber().getFourthLottoNumber());
        params.put("fifth_lotto_number", lotteryHistory.getLotteryNumber().getFifthLottoNumber());
        params.put("sixth_lotto_number", lotteryHistory.getLotteryNumber().getSixthLottoNumber());
        params.put("round", lotteryHistory.getRound());
        return params;
    }
}