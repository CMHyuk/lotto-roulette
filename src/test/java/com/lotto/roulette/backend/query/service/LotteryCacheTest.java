package com.lotto.roulette.backend.query.service;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistory;
import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryNumber;
import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryNumberFrequency;
import com.lotto.roulette.backend.query.repository.LotteryHistoryQueryRepository;
import com.lotto.roulette.backend.query.repository.LotteryNumberFrequencyQueryRepository;
import com.lotto.roulette.backend.support.enviroment.ServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;

public class LotteryCacheTest extends ServiceTest {

    @Autowired
    private LotteryHistoryQueryService lotteryHistoryQueryService;

    @Autowired
    private LotteryNumberFrequencyQueryService lotteryNumberFrequencyQueryService;

    @MockBean
    private LotteryHistoryQueryRepository lotteryHistoryQueryRepository;

    @MockBean
    private LotteryNumberFrequencyQueryRepository lotteryNumberFrequencyQueryRepository;

    @Test
    void 최고_당첨_금액을_조회할_때_캐시가_적용_됐는지_확인한다() {
        // given
        when(lotteryHistoryQueryRepository.findTopPrize()).thenReturn(Optional.of(1000000000L));

        // when
        IntStream.range(0, 10)
                .forEach(i -> lotteryHistoryQueryService.getTopPrize());

        // then
        verify(lotteryHistoryQueryRepository, times(1)).findTopPrize();
    }

    @Test
    void 회차로_검색할_때_캐시가_적용_됐는지_확인한다() {
        // given
        int round = 1;
        LotteryHistory lotteryHistory = LotteryHistory.create(
                LotteryNumber.create(1, 2, 3, 4, 5, 6),
                1000000000L, 3, 4);
        when(lotteryHistoryQueryRepository.findByRound(round)).thenReturn(Optional.of(lotteryHistory));

        // when
        IntStream.range(0, 10)
                .forEach(i -> lotteryHistoryQueryService.getLotteryHistory(round));

        // then
        verify(lotteryHistoryQueryRepository, times(1)).findByRound(round);
    }

    @Test
    void 로또_당첨_번호들을_조회할_때_캐시가_적용_됐는지_확인한다() {
        // given
        when(lotteryNumberFrequencyQueryRepository.findAll()).thenReturn(List.of(new LotteryNumberFrequency(1)));

        // when
        IntStream.range(0, 10)
                .forEach(i -> lotteryNumberFrequencyQueryService.findAll());

        // then
        verify(lotteryNumberFrequencyQueryRepository, times(1)).findAll();
    }

    @Test
    void 로또_당첨_정보를_페이징할_때_캐시가_적용_됐는지_확인한다() {
        // given
        LotteryHistory lotteryHistory = LotteryHistory.create(
                LotteryNumber.create(1, 2, 3, 4, 5, 6),
                1000000000L, 3, 4);
        Pageable pageable = Pageable.ofSize(1);
        when(lotteryHistoryQueryRepository.findTop10ByOrderByRoundDesc(pageable))
                .thenReturn(List.of(lotteryHistory));

        // when
        IntStream.range(0, 10)
                .forEach(i -> lotteryHistoryQueryService.getLotteryHistories(pageable));

        // then
        verify(lotteryHistoryQueryRepository, times(1)).findTop10ByOrderByRoundDesc(pageable);
    }
}
