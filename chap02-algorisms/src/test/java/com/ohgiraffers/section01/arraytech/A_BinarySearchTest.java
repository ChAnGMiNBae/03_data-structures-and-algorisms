package com.ohgiraffers.section01.arraytech;

import com.ohgiraffers.section01.arraytech.A_BinarySearch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class A_BinarySearchTest {

    static int[] sorted1, sorted2;

    @BeforeAll
    static void setUp() {
        sorted1 = new int[]{1, 5, 9, 12, 23, 24, 34};
        sorted2 = new int[]{8, 33, 35, 38, 40, 47};
    }

    static Stream<Arguments> provideSearchSource() {
        return Stream.of(
                Arguments.of(sorted1, 12, 3),
                Arguments.of(sorted1, 34, 6),
                Arguments.of(sorted1, 7, -1),
                Arguments.of(sorted2, 35, 2),
                Arguments.of(sorted2, 50, -1)
        );
    }

    @DisplayName("이분 탐색 테스트 - 반복문 방식")
    @ParameterizedTest
    @MethodSource("provideSearchSource")
    void testIterativeBinarySearch(int[] arr, int target, int expected) {
        Assertions.assertEquals(expected, A_BinarySearch.iterative(arr, target));
    }

    @DisplayName("이분 탐색 테스트 - 재귀 방식")
    @ParameterizedTest
    @MethodSource("provideSearchSource")
    void testRecursiveBinarySearch(int[] arr, int target, int expected) {
        Assertions.assertEquals(expected, A_BinarySearch.recursive(arr, target));
    }
}