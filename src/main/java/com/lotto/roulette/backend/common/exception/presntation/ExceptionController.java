package com.lotto.roulette.backend.common.exception.presntation;

import com.lotto.roulette.backend.common.exception.BusinessException;
import com.lotto.roulette.backend.common.exception.ErrorCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> handleBusinessException(BusinessException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                errorCode.getHttpStatusCode(), errorCode.getErrorMessage());
        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(problemDetail);
    }
}
