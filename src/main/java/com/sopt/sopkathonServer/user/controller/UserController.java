package com.sopt.sopkathonServer.user.controller;

import com.sopt.sopkathonServer.common.dto.ApiResponse;
import com.sopt.sopkathonServer.common.exception.enums.SuccessType;
import com.sopt.sopkathonServer.user.service.SocialService;
import com.sopt.sopkathonServer.user.domain.dto.response.SocialLoginResponse;
import com.sopt.sopkathonServer.user.kakao.SocialPlatform;
import com.sopt.sopkathonServer.user.kakao.SocialServiceProvider;
import com.sopt.sopkathonServer.user.domain.dto.request.SocialLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final SocialServiceProvider socialServiceProvider;

    @GetMapping("/login")
    public ApiResponse<SocialLoginResponse> login(@RequestHeader("Authorization") String Authorization) throws IOException {
        SocialService socialService = socialServiceProvider.getSocialService(SocialPlatform.KAKAO);
        return ApiResponse.success(SuccessType.SOCIAL_LOGIN_SUCCESS, socialService.login(SocialLoginRequest.of(Authorization)));
    }
}
