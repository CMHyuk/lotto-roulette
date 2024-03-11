package com.lotto.roulette.backend.query.service;

import com.lotto.roulette.backend.command.lotteryhistory.application.LotteryHistoryService;
import com.lotto.roulette.backend.command.lotteryhistory.application.LotteryNumberFrequencyService;
import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistory;
import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryNumber;
import com.lotto.roulette.backend.query.repository.LotteryHistoryQueryRepository;
import com.lotto.roulette.backend.query.repository.LotteryNumberFrequencyQueryRepository;
import com.lotto.roulette.backend.support.enviroment.ServiceTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;

@Sql(value = {"/fixture/lottery-history-fixture.sql", "/fixture/lottery-number-frequency-fixture.sql"})
public class LotteryCacheTest extends ServiceTest {

    @Autowired
    private LotteryHistoryService lotteryHistoryService;

    @Autowired
    private LotteryNumberFrequencyService lotteryNumberFrequencyService;

    @Autowired
    private LotteryHistoryQueryService lotteryHistoryQueryService;

    @Autowired
    private LotteryNumberFrequencyQueryService lotteryNumberFrequencyQueryService;

    @MockBean
    private LotteryHistoryQueryRepository lotteryHistoryQueryRepository;

    @MockBean
    private LotteryNumberFrequencyQueryRepository lotteryNumberFrequencyQueryRepository;

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("최고_당첨_금액을_조회할_때")
    class FindTopPrizeTest {

        @Test
        @Order(1)
        void 캐시가_적용_됐는지_확인한다() {
            // given
            when(lotteryHistoryQueryRepository.findTopPrize()).thenReturn(Optional.of(1000000000L));

            // when
            IntStream.range(0, 10)
                    .forEach(i -> lotteryHistoryQueryService.getTopPrize());

            // then
            verify(lotteryHistoryQueryRepository, times(1)).findTopPrize();
        }

        @Test
        @Order(2)
        void 정보가_갱신되고_캐시가_갱신되는지_확인한다() {
            // given
            when(lotteryHistoryQueryRepository.findTopPrize()).thenReturn(Optional.of(1000000000L));
            IntStream.range(0, 10)
                    .forEach(i -> lotteryHistoryQueryService.getTopPrize());

            // when
            LotteryNumber lotteryNumber = LotteryNumber.create(1, 2, 3, 4, 5, 6);
            LotteryHistory lotteryHistory = LotteryHistory.create(lotteryNumber, 1000000000L, 3, 11);
            lotteryHistoryService.save(lotteryHistory);
            IntStream.range(0, 10)
                    .forEach(i -> lotteryHistoryQueryService.getTopPrize());

            // then
            verify(lotteryHistoryQueryRepository, times(1)).findTopPrize();
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("회차로_검색할_때")
    class FindLotteryHistoryTest {

        @Test
        @Order(3)
        void 캐시가_적용_됐는지_확인한다() {
            // given
            int round = 1;

            // when
            IntStream.range(0, 10)
                    .forEach(i -> lotteryHistoryQueryService.getLotteryHistory(round));

            // then
            verify(lotteryHistoryQueryRepository, times(1)).findByRound(round);
        }

        @Test
        @Order(4)
        void 로또_당첨_정보가_업데이트_되고_캐시가_갱신_됐는지_확인한다() {
            // given
            int round = 1;
            IntStream.range(0, 10)
                    .forEach(i -> lotteryHistoryQueryService.getLotteryHistory(round));

            // when
            LotteryNumber lotteryNumber = LotteryNumber.create(1, 2, 3, 4, 5, 6);
            LotteryHistory lotteryHistory = LotteryHistory.create(lotteryNumber, 1000000000L, 3, 11);
            lotteryHistoryService.save(lotteryHistory);
            IntStream.range(0, 10)
                    .forEach(i -> lotteryHistoryQueryService.getLotteryHistory(round));

            // then
            verify(lotteryHistoryQueryRepository, times(1)).findByRound(round);
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("로또_당첨_번호들을_조회할_때")
    class FindLotteryNumberFrequencyTest {

        @Test
        @Order(5)
        void 캐시가_적용_됐는지_확인한다() {
            // when
            IntStream.range(0, 10)
                    .forEach(i -> lotteryNumberFrequencyQueryService.findAll());

            // then
            verify(lotteryNumberFrequencyQueryRepository, times(1)).findAll();
        }

        @Test
        @Order(6)
        void 로또_당첨_정보가_업데이트_되고_캐시가_갱신_됐는지_확인한다() {
            // given
            IntStream.range(0, 10)
                    .forEach(i -> lotteryNumberFrequencyQueryService.findAll());

            // when
            lotteryNumberFrequencyService.increaseLotteryNumberFrequency(List.of(1, 2, 3, 4, 5, 6));
            IntStream.range(0, 10)
                    .forEach(i -> lotteryNumberFrequencyQueryService.findAll());

            // then
            verify(lotteryNumberFrequencyQueryRepository, times(1)).findAll();
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("로또_당첨_정보를_페이징할_때")
    class FindLotteryHistoriesTest {

        @Test
        @Order(7)
        void 캐시가_적용_됐는지_확인한다() {
            // given
            Pageable pageable = Pageable.ofSize(1);

            // when
            IntStream.range(0, 10)
                    .forEach(i -> lotteryHistoryQueryService.getLotteryHistories(pageable));

            // then
            verify(lotteryHistoryQueryRepository, times(1)).findTop10ByOrderByRoundDesc(pageable);
        }

        @Test
        @Order(8)
        void 로또_당첨_정보가_업데이트_되고_캐시가_갱신_됐는지_확인한다() {
            // given
            Pageable pageable = Pageable.ofSize(1);
            IntStream.range(0, 10)
                    .forEach(i -> lotteryHistoryQueryService.getLotteryHistories(pageable));

            // when
            LotteryNumber lotteryNumber = LotteryNumber.create(1, 2, 3, 4, 5, 6);
            LotteryHistory aftherlotteryHistory = LotteryHistory.create(lotteryNumber, 1000000000L, 3, 11);
            lotteryHistoryService.save(aftherlotteryHistory);
            IntStream.range(0, 10)
                    .forEach(i -> lotteryHistoryQueryService.getLotteryHistories(pageable));

            // then
            verify(lotteryHistoryQueryRepository, times(1)).findTop10ByOrderByRoundDesc(pageable);
        }
    }
}
