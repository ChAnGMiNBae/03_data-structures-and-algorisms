package com.ohgiraffers.section02.dfsbfs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Practice4Test {

    static Stream<Arguments> provide() {
        return Stream.of(
                // 1-2-3-4-5-6, 1~6 => 5
                Arguments.arguments(
                        "6\n" +
                                "1 6\n" +
                                "5\n" +
                                "1 2\n" +
                                "2 3\n" +
                                "3 4\n" +
                                "4 5\n" +
                                "5 6\n",
                        "5"
                ),
                // 끊김: 1-2, 3-4 ; 1~4 => -1
                Arguments.arguments(
                        "4\n" +
                                "1 4\n" +
                                "2\n" +
                                "1 2\n" +
                                "3 4\n",
                        "-1"
                ),
                // 자기 자신 => 0
                Arguments.arguments(
                        "5\n" +
                                "3 3\n" +
                                "4\n" +
                                "1 2\n" +
                                "2 3\n" +
                                "3 4\n" +
                                "4 5\n",
                        "0"
                )
        );
    }

    @DisplayName("촌수 계산 테스트")
    @ParameterizedTest
    @MethodSource("provide")
    void test(String input, String expected) throws IOException {
        String result = Practice4.solution(input);
        assertEquals(expected, result);
    }
}