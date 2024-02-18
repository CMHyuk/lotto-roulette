package com.lotto.roulette.backend.command.lotteryfrequency.presentation;

import com.lotto.roulette.backend.command.lotteryfrequency.application.LotteryNumberFrequencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LotteryFrequencyController {

    private final LotteryNumberFrequencyService lotteryNumberFrequencyService;

}
