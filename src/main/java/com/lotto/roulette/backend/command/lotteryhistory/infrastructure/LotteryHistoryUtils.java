package com.lotto.roulette.backend.command.lotteryhistory.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotto.roulette.backend.command.lotteryhistory.domain.LotteryHistory;
import com.lotto.roulette.backend.command.lotteryhistory.dto.LotteryHistoryInfo;
import com.lotto.roulette.backend.command.lotteryhistory.infrastructure.lotteryapi.LotteryHistoryApiResponse;
import com.lotto.roulette.backend.common.exception.BusinessException;
import com.lotto.roulette.backend.common.exception.presntation.InternalServerErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static java.util.stream.IntStream.range;

@Slf4j
@Component
@RequiredArgsConstructor
public class LotteryHistoryUtils {

    private static final DataFormatter formatter = new DataFormatter();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static LotteryHistoryApiResponse parseLotteryHistoryApi(String response) {
        try {
            return objectMapper.readValue(response, LotteryHistoryApiResponse.class);
        } catch (Exception e) {
            log.error("로또 데이터 변환 중 에러가 발생했습니다.");
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

    public static MultipartFile convertFileToMultipartFile(File lotteryHistoryExcel) {
        return new MultipartFile() {
            @Override
            public String getName() {
                return lotteryHistoryExcel.getName();
            }

            @Override
            public String getOriginalFilename() {
                return lotteryHistoryExcel.getName();
            }

            @Override
            public String getContentType() {
                return "xlsx";
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return lotteryHistoryExcel.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                FileInputStream fileInputStream = new FileInputStream(lotteryHistoryExcel);
                byte[] bytes = new byte[(int) lotteryHistoryExcel.length()];
                fileInputStream.read(bytes);
                fileInputStream.close();
                return bytes;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(lotteryHistoryExcel);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                Files.copy(lotteryHistoryExcel.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        };
    }
}
