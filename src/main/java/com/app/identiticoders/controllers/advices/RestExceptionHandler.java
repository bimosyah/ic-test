package com.app.identiticoders.controllers.advices;

import com.app.identiticoders.exceptions.NotFoundException;
import com.app.identiticoders.responses.BaseResponse;
import com.app.identiticoders.utils.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<BaseResponse> handleNotFoundException(NotFoundException ex) {
        log.warn("handleNotFoundException: {}", ex.getMessage());
        return ResponseHelper.buildNoDataResponse(ex.getMessage());
    }
}
