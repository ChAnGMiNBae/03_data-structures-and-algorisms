package com.ohgiraffers.section01.arraytech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class C_SlidingWindowTest {

    static int[] arr1, arr2;

    @BeforeAll
    static void setUp() {
        arr1 = new int[]{1, 2, 3, 4, 5};
        arr2 = new int[]{2, 1, 5, 2, 3, 2, 1, 4};
    }

    static Stream<Arguments> provideFixedWindow() {
        return Stream.of(
                Arguments.of(arr1, 2, 9),  // [4,5] -> 합 9가 최대
                Arguments.of(arr2, 3, 10) // [5,2,3] -> 합 10이 최대
        );
    }

    static Stream<Arguments> provideMinLenSubarray() {
        return Stream.of(
                Arguments.of(arr1, 11, 3), // [3,4,5] -> 합 12 >= 11, 길이 3이 최소
                Arguments.of(arr2, 7, 2),  // [5,2] -> 합 7 >= 7, 길이 2가 최소
                Arguments.of(arr2, 21, 0)  // 어떤 연속 구간도 합 >= 21 없음
        );
    }

    @DisplayName("슬라이딩 윈도우 - 고정 크기 최댓값 테스트")
    @ParameterizedTest
    @MethodSource("provideFixedWindow")
    void testMaxSumFixedWindow(int[] arr, int k, int expected) {
        Assertions.assertEquals(expected, C_SlidingWindow.maxSumFixedWindow(arr, k));
    }

    @DisplayName("슬라이딩 윈도우 - 최소 길이 부분배열 테스트")
    @ParameterizedTest
    @MethodSource("provideMinLenSubarray")
    void testMinLenSubarrayAtLeastS(int[] arr, int S, int expected) {
        Assertions.assertEquals(expected, C_SlidingWindow.minLenSubarrayAtLeastS(arr, S));
    }
}