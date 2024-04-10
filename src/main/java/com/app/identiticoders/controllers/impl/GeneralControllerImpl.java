package com.app.identiticoders.controllers.impl;

import com.app.identiticoders.controllers.GeneralController;
import com.app.identiticoders.responses.BaseResponse;
import com.app.identiticoders.utils.ResponseHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class GeneralControllerImpl implements GeneralController {

    @Override
    public ResponseEntity<BaseResponse> ping() {
        return ResponseHelper.buildOkResponse("PONG");
    }

    @Override
    public ResponseEntity<BaseResponse> hello() {
        return ResponseHelper.buildOkResponse("Hellow");
    }
}
