package com.sopt.sopkathonServer.user;


import com.sopt.sopkathonServer.user.domain.dto.request.SocialLoginRequest;
import com.sopt.sopkathonServer.user.domain.dto.response.SocialLoginResponse;

public abstract class SocialService {
    public abstract SocialLoginResponse login(SocialLoginRequest request);

}