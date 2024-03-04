package com.lotto.roulette.backend.support;

import com.lotto.roulette.backend.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@RestController
@RequestMapping("/error-codes")
public class ErrorCodeController {

    @GetMapping
    public Map<Integer, List<ErrorCodeResponse>> getErrorCode(@RequestParam(name = "className") String className) throws ClassNotFoundException {
        Class<?> errorCodeType = Class.forName(className);
        ErrorCode[] errorCodes = (ErrorCode[]) errorCodeType.getEnumConstants();
        return Arrays.stream(errorCodes)
                .collect(Collectors.groupingBy(ErrorCode::getHttpStatusCode,
                        Collectors.mapping(ErrorCodeResponse::new, Collectors.toList())));
    }


    record ErrorCodeResponse(
            Integer httpStatus,
            String message
    ) {
        public ErrorCodeResponse(ErrorCode code) {
            this(code.getHttpStatusCode(), code.getErrorMessage());
        }
    }
}
