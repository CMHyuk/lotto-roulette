package com.lotto.roulette.backend.command.lotteryhistory.infrastructure;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistory;
import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryNumber;
import com.lotto.roulette.backend.common.exception.BusinessException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.lotto.roulette.backend.command.lotteryhistory.exception.LotteryHistoryException.NOT_EXISTS_EXCEL_DATA;

public record LotteryHistoryInfo(
        int round,
        int firstLottoNumber,
        int secondLottoNumber,
        int thirdLottoNumber,
        int fourthLottoNumber,
        int fifthLottoNumber,
        int sixthLottoNumber,
        int bonusNumber,
        String firstPrizeAmount,
        int winnerCount
) {

    public static LotteryHistoryInfo of(DataFormatter formatter, XSSFRow row) {
        return new LotteryHistoryInfo(
                parseInt(formatter, row, 0),
                parseInt(formatter, row, 1),
                parseInt(formatter, row, 2),
                parseInt(formatter, row, 3),
                parseInt(formatter, row, 4),
                parseInt(formatter, row, 5),
                parseInt(formatter, row, 6),
                parseInt(formatter, row, 7),
                formatter.formatCellValue(row.getCell(8)),
                parseInt(formatter, row, 9)
        );
    }

    private static int parseInt(DataFormatter formatter, XSSFRow row, int cellIndex) {
        return Optional.ofNullable(formatter.formatCellValue(row.getCell(cellIndex)))
                .filter(lotteryHistory -> !lotteryHistory.isEmpty())
                .map(Integer::parseInt)
                .orElseThrow(() -> BusinessException.from(NOT_EXISTS_EXCEL_DATA));
    }

    public static List<LotteryHistory> toEntities(List<LotteryHistoryInfo> historyInfos) {
        return historyInfos.stream()
                .map(LotteryHistoryInfo::toEntity)
                .collect(Collectors.toList());
    }

    private static LotteryHistory toEntity(LotteryHistoryInfo info) {
        return LotteryHistory.create(
                LotteryNumber.create(
                        info.firstLottoNumber(),
                        info.secondLottoNumber(),
                        info.thirdLottoNumber(),
                        info.fourthLottoNumber(),
                        info.fifthLottoNumber(),
                        info.sixthLottoNumber()
                ),
                info.firstPrizeAmount(),
                info.winnerCount(),
                info.round()
        );
    }
}
