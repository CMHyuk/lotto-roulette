package com.lotto.roulette.backend.command.lotteryhistory.infrastructure;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryNumberFrequencyBulkUpdateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Repository
@RequiredArgsConstructor
public class LotteryNumberFrequencyBulkRepositoryImpl implements LotteryNumberFrequencyBulkUpdateRepository {

    private static final int FREQUENCY = 0;

    private static final String INSERT_SQL =
            "INSERT INTO lottery_number_frequency (lottery_number, frequency) VALUES(:lottery_number, :frequency)";

    private static final String UPDATE_SQL =
            "UPDATE lottery_number_frequency SET frequency = frequency + 1 " +
                    "WHERE lottery_number IN (:lottery_number)";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void saveAll() {
        List<Map<String, Object>> batchValues = IntStream.rangeClosed(1, 45)
                .mapToObj(lotteryNumber -> {
                    Map<String, Object> params = new HashMap<>();
                    params.put("lottery_number", lotteryNumber);
                    params.put("frequency", FREQUENCY);
                    return params;
                })
                .toList();

        jdbcTemplate.batchUpdate(INSERT_SQL, batchValues.toArray(new Map[0]));
    }

    @Override
    public void updateAll(List<Integer> lotteryNumbers) {
        jdbcTemplate.batchUpdate(UPDATE_SQL, generateParameterSource(lotteryNumbers));
    }

    private SqlParameterSource[] generateParameterSource(List<Integer> lotteryNumbers) {
        return lotteryNumbers.stream()
                .map(lotteryNumber -> new MapSqlParameterSource(generateParams(lotteryNumber)))
                .toArray(SqlParameterSource[]::new);
    }

    private Map<String, Object> generateParams(Integer lotteryNumber) {
        Map<String, Object> params = new HashMap<>();
        params.put("lottery_number", lotteryNumber);
        return params;
    }
}
