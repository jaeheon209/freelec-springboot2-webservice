package com.jojodu.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// RequiredArgsConstructor - 생성자에 final이 선언된 모든 필드변수를 포함하여 생성
// ㄴ final이 없으면 생성자에 포함되지 않음.
@Getter
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
