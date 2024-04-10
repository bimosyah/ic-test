package com.app.identiticoders.controllers;

import com.app.identiticoders.responses.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public interface GeneralController {

    @GetMapping("/ping")
    ResponseEntity<BaseResponse> ping();

    @GetMapping
    ResponseEntity<BaseResponse> hello();
}
