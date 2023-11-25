package com.sopt.sopkathonServer.celeb.controller;

import com.sopt.sopkathonServer.celeb.dto.request.CelebCreateRequest;
import com.sopt.sopkathonServer.celeb.service.CelebService;
import com.sopt.sopkathonServer.common.dto.ApiResponse;
import com.sopt.sopkathonServer.common.exception.enums.SuccessType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/celebs")
@RequiredArgsConstructor
public class CelebController {
    private final CelebService celebService;
    @PostMapping
    public ApiResponse<Long> createPost(@RequestBody CelebCreateRequest request) {
        Long celebId = celebService.createCeleb(request);

        return ApiResponse.success(SuccessType.CELEB_CREATE_SUCCESS, celebId);
    }
}
