package com.lotto.roulette.backend.support.enviroment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotto.roulette.backend.command.lotteryfrequency.application.LotteryNumberFrequencyService;
import com.lotto.roulette.backend.common.exception.ErrorCode;
import com.lotto.roulette.backend.query.service.LotteryHistoryQueryService;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

@WebMvcTest
@AutoConfigureRestDocs
public class DocumentationTest {

    protected static final OperationRequestPreprocessor HOST_INFO = preprocessRequest(modifyUris()
            .scheme("https")
            .host("www.api.lottery.com")
            .removePort(), prettyPrint()
    );

    protected static final OperationResponsePreprocessor DOCUMENT_RESPONSE = preprocessResponse(prettyPrint());

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected LotteryNumberFrequencyService lotteryNumberFrequencyService;

    @Mock
    protected LotteryHistoryQueryService lotteryHistoryQueryService;

    protected List<FieldDescriptor> getErrorDescriptor(ErrorCode[] errorCodes) {
        return Arrays.stream(errorCodes)
                .flatMap(errorCode -> Stream.of(
                        fieldWithPath(errorCode.getHttpStatusCode() + ".httpStatus").description(errorCode.getHttpStatusCode()),
                        fieldWithPath(errorCode.getErrorMessage() + ".message").description(errorCode.getErrorMessage())
                )).toList();
    }
}
