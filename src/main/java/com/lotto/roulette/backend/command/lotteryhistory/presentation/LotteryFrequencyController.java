package com.lotto.roulette.backend.command.lotteryhistory.presentation;

import com.lotto.roulette.backend.command.lotteryhistory.application.LotteryNumberFrequencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LotteryFrequencyController {

    private final LotteryNumberFrequencyService lotteryNumberFrequencyService;

}
