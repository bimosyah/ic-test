package com.app.identiticoders.utils;

import com.app.identiticoders.responses.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {
    public static ResponseEntity<BaseResponse> buildOkResponse(Object data) {
        BaseResponse mainResponse = BaseResponse.builder()
                .data(data)
                .success(true).build();

        return ResponseEntity.ok(mainResponse);
    }

    public static ResponseEntity<BaseResponse> buildOkResponse(String data) {
        BaseResponse mainResponse = BaseResponse.builder()
                .data(data)
                .success(true).build();

        return ResponseEntity.ok(mainResponse);
    }

    public static ResponseEntity<BaseResponse> buildNoDataResponse(String message) {
        BaseResponse mainResponse = BaseResponse.builder()
                .data(message)
                .success(false).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mainResponse);
    }
}
