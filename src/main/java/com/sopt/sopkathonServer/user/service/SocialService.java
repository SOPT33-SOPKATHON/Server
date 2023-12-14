package com.sopt.sopkathonServer.user.service;


import com.sopt.sopkathonServer.user.domain.dto.request.SocialLoginRequest;
import com.sopt.sopkathonServer.user.domain.dto.response.SocialLoginResponse;

import java.io.IOException;

public abstract class SocialService {
    public abstract SocialLoginResponse login(SocialLoginRequest request) throws IOException;

}