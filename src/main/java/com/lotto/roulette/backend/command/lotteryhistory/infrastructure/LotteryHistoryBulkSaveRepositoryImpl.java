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
                    "first_lottery_number, " +
                    "second_lottery_number, " +
                    "third_lottery_number, " +
                    "fourth_lottery_number, " +
                    "fifth_lottery_number, " +
                    "sixth_lottery_number" +
                    ") VALUES (" +
                    ":first_prize_amount, " +
                    ":winner_count, " +
                    ":round, " +
                    ":first_lottery_number, " +
                    ":second_lottery_number, " +
                    ":third_lottery_number, " +
                    ":fourth_lottery_number, " +
                    ":fifth_lottery_number, " +
                    ":sixth_lottery_number" +
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
        params.put("first_lottery_number", lotteryHistory.getLotteryNumber().getFirstLotteryNumber());
        params.put("second_lottery_number", lotteryHistory.getLotteryNumber().getSecondLotteryNumber());
        params.put("third_lottery_number", lotteryHistory.getLotteryNumber().getThirdLotteryNumber());
        params.put("fourth_lottery_number", lotteryHistory.getLotteryNumber().getFourthLotteryNumber());
        params.put("fifth_lottery_number", lotteryHistory.getLotteryNumber().getFifthLotteryNumber());
        params.put("sixth_lottery_number", lotteryHistory.getLotteryNumber().getSixthLotteryNumber());
        params.put("round", lotteryHistory.getRound());
        return params;
    }
}
