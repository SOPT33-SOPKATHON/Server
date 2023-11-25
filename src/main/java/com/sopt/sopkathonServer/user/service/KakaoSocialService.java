package com.sopt.sopkathonServer.user.service;

import com.sopt.sopkathonServer.user.domain.User;
import com.sopt.sopkathonServer.user.domain.dto.response.SocialLoginResponse;
import com.sopt.sopkathonServer.user.kakao.dto.response.KakaoAccessTokenResponse;
import com.sopt.sopkathonServer.user.kakao.dto.response.KakaoUserResponse;
import com.sopt.sopkathonServer.user.domain.dto.request.SocialLoginRequest;
import com.sopt.sopkathonServer.user.kakao.feign.KakaoApiClient;
import com.sopt.sopkathonServer.user.kakao.feign.KakaoAuthApiClient;
import com.sopt.sopkathonServer.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoSocialService extends SocialService {

    @Value("${kakao.client-id}")
    private String clientId;

    private final UserJpaRepository userJpaRepository;

    private final KakaoAuthApiClient kakaoAuthApiClient;
    private final KakaoApiClient kakaoApiClient;

    @Override
    public SocialLoginResponse login(SocialLoginRequest request) {

        System.out.println(clientId);

        // Authorization code로 Access Token 불러오기
        KakaoAccessTokenResponse tokenResponse = kakaoAuthApiClient.getOAuth2AccessToken(
                "authorization_code",
                clientId,
                "http://localhost:8080/kakao/callback",
                request.getCode()
        );

        // Access Token으로 유저 정보 불러오기
        KakaoUserResponse userResponse = kakaoApiClient.getUserInformation("Bearer " + tokenResponse.getAccessToken());

        Optional<User> findUser = userJpaRepository.findBySocialId(userResponse.getId());

        User user;
        if (findUser.isEmpty()) {
            User newUser = User.of(
                    userResponse.getKakaoAccount().getProfile().getNickname(),
                    userResponse.getKakaoAccount().getProfile().getProfileImageUrl(),
                    tokenResponse.getAccessToken(),
                    tokenResponse.getRefreshToken(),
                    userResponse.getId()
            );

            userJpaRepository.save(newUser);

            user = userJpaRepository.findBySocialId(userResponse.getId()).get();
        } else {
            user = findUser.get();
        }

        return SocialLoginResponse.of(user.getId());
    }
}
