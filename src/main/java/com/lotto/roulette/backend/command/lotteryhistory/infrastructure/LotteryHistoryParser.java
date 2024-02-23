package com.lotto.roulette.backend.command.lotteryhistory.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistory;
import com.lotto.roulette.backend.command.lotteryhistory.dto.LotteryHistoryApiResponse;
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
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static LotteryHistoryApiResponse parseLotteryHistoryApi(String response) {
        try {
            return objectMapper.readValue(response, LotteryHistoryApiResponse.class);
        } catch (Exception e) {
            throw BusinessException.from(new InternalServerErrorCode(e.getMessage()));
        }
    }

    public static List<LotteryHistory> parseLotteryHistoryExcel(MultipartFile lotteryHistoryExcel) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(lotteryHistoryExcel.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);
            List<LotteryHistoryInfo> historyInfos = range(1, worksheet.getPhysicalNumberOfRows())
                    .mapToObj(worksheet::getRow)
                    .map(row -> LotteryHistoryInfo.of(formatter, row))
                    .toList();
            return LotteryHistoryInfo.toEntities(historyInfos);
        } catch (IOException e) {
            throw BusinessException.from(new InternalServerErrorCode(e.getMessage()));
        }
    }
}
