package com.sopt.sopkathonServer.common.exception.slack;

import com.sopt.sopkathonServer.common.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slack/test")
public class SlackTestController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse test() {
        throw new IllegalArgumentException();
    }
}
