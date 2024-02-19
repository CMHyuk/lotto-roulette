package com.lotto.roulette.backend.command.lotteryhistory.infrastructure;

import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistory;
import com.lotto.roulette.backend.common.exception.BusinessException;
import com.lotto.roulette.backend.common.exception.InternalServerErrorCode;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static java.util.stream.IntStream.range;

@Component
@RequiredArgsConstructor
public class LotteryHistoryParser {

    private static final DataFormatter formatter = new DataFormatter();

    public static List<LotteryHistory> parseLotteryWinningInfo(MultipartFile excel) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);
            List<LotteryWinningHistoryInfo> historyInfos = range(1, worksheet.getPhysicalNumberOfRows())
                    .mapToObj(worksheet::getRow)
                    .map(row -> LotteryWinningHistoryInfo.of(formatter, row))
                    .toList();
            return LotteryWinningHistoryInfo.toEntities(historyInfos);
        } catch (IOException e) {
            throw BusinessException.from(new InternalServerErrorCode(e.getMessage()));
        }
    }
}
