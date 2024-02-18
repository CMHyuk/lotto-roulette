package com.lotto.roulette.backend.command.lotteryhistory.domain;

import java.util.List;

public interface LotteryHistoryBulkSaveRepository {
    void saveAll(List<LotteryHistory> lotteryHistories);
}
