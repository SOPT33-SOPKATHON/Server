package com.sopt.sopkathonServer.user.kakao;

import com.sopt.sopkathonServer.user.service.KakaoSocialService;
import com.sopt.sopkathonServer.user.service.SocialService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SocialServiceProvider {

    private static final Map<SocialPlatform, SocialService> socialServiceMap = new HashMap<>();

    private final KakaoSocialService kakaoSocialService;

    @PostConstruct
    void initializeSocialServiceMap() {
        socialServiceMap.put(SocialPlatform.KAKAO, kakaoSocialService);
    }

    public SocialService getSocialService(SocialPlatform socialPlatform) {
        return socialServiceMap.get(socialPlatform);
    }
}
