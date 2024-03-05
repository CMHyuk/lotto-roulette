package com.lotto.roulette.backend.command.lotteryhistory.domain;

import java.util.List;

public interface LotteryNumberFrequencyBulkUpdateRepository {

    void updateAll(List<Integer> lotteryNumbers);
}
