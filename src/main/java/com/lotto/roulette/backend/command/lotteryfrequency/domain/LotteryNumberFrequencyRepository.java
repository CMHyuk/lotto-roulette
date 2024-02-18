package com.lotto.roulette.backend.command.lotteryfrequency.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LotteryNumberFrequencyRepository extends JpaRepository<LotteryNumberFrequency, Long> {
    Optional<LotteryNumberFrequency> findByLottoNumber(Integer lottoNumber);
}
