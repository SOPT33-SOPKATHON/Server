package com.sopt.sopkathonServer.celeb.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum PostIt {

    A(1),
    B(2),
    C(3)
    ;

    private final int postItNumber;
}
