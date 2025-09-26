package com.ohgiraffers.section01.arraytech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class B_TwoPointersTest {

    static int[] sorted1, sorted2;

    @BeforeAll
    static void setUp() {
        sorted1 = new int[]{1, 2, 3, 4, 5};
        sorted2  = new int[]{10, 20, 35, 50, 75, 80};
    }

    /* 문제 1: 쌍 개수 */
    static Stream<Arguments> providePairSumCount() {
        return Stream.of(
                Arguments.of(sorted1, 6, 2),   // (1,5), (2,4) -> 양 끝에서 시작해 1+5=6, 2+4=6로 총 2쌍
                Arguments.of(sorted2, 85, 2)    // (10,75), (35,50) -> 10+75=85, 35+50=85로 총 2쌍
        );
    }

    /* 문제 2: 존재 여부 */
    static Stream<Arguments> providePairExist() {
        return Stream.of(
                Arguments.of(sorted1, 6, true),     // (1,5) 또는 (2,4)가 있으므로 true
                Arguments.of(sorted2, 120, false)    // 가능한 합들: 30,45,55,60,70,85,90,95,100,110,115,125,130,155 중 120은 없음 -> false
        );
    }

    @DisplayName("투 포인터 - 쌍 개수 세기 테스트")
    @ParameterizedTest
    @MethodSource("providePairSumCount")
    void testCountPairSum(int[] sorted, int target, int expected) {
        Assertions.assertEquals(expected, B_TwoPointers.countPairSum(sorted, target));
    }

    @DisplayName("투 포인터 - 합 존재 여부 테스트")
    @ParameterizedTest
    @MethodSource("providePairExist")
    void testExistPairSumSorted(int[] sorted, int target, boolean expected) {
        Assertions.assertEquals(expected, B_TwoPointers.existPairSum(sorted, target));
    }
}