package com.app.identiticoders.controllers;

import com.app.identiticoders.responses.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("asteroid")
public interface AsteroidController {

    @GetMapping
    ResponseEntity<BaseResponse> getClosestAsteroid(@RequestParam(value = "start_date", required = false) String startDate,
                                                    @RequestParam(value = "end_date", required = false) String endDate);
}
