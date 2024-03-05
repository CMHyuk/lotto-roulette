package com.lotto.roulette.backend.command.lotteryhistory.domain;

import java.util.List;

public interface LotteryNumberFrequencyBulkUpdateRepository {

    void saveAll();

    void updateAll(List<Integer> lotteryNumbers);
}
