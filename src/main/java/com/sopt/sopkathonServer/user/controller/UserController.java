package com.sopt.sopkathonServer.user.controller;

import com.sopt.sopkathonServer.common.dto.ApiResponse;
import com.sopt.sopkathonServer.common.exception.enums.SuccessType;
import com.sopt.sopkathonServer.user.service.SocialService;
import com.sopt.sopkathonServer.user.domain.dto.response.SocialLoginResponse;
import com.sopt.sopkathonServer.user.kakao.SocialPlatform;
import com.sopt.sopkathonServer.user.kakao.SocialServiceProvider;
import com.sopt.sopkathonServer.user.domain.dto.request.SocialLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final SocialServiceProvider socialServiceProvider;

    @PostMapping("/login")
    public ApiResponse<SocialLoginResponse> login(@RequestHeader("code") String code) {
        SocialService socialService = socialServiceProvider.getSocialService(SocialPlatform.KAKAO);
        return ApiResponse.success(SuccessType.SOCIAL_LOGIN_SUCCESS, socialService.login(SocialLoginRequest.of(code)));
    }
}
