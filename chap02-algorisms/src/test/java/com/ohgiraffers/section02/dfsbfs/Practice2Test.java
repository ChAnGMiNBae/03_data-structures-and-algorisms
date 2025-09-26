package com.ohgiraffers.section02.dfsbfs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Practice2Test {

    static Stream<Arguments> provide() {
        return Stream.of(
                Arguments.arguments(
                        "5 7 3\n" +
                                "0 2 4 4\n" +
                                "1 1 2 5\n" +
                                "4 0 6 2\n",
                        "3\n1 7 13"
                ),
                Arguments.arguments(
                        "3 4 0\n",
                        "1\n12"
                ),
                Arguments.arguments(
                        "2 2 1\n" +
                                "0 0 2 2\n",
                        "0\n"
                ),
                Arguments.arguments(
                        "4 5 1\n" +
                                "0 0 5 1\n",
                        "1\n15"
                )
        );
    }

    @DisplayName("영역 구하기 테스트")
    @ParameterizedTest
    @MethodSource("provide")
    void test(String input, String expected) throws IOException {
        String result = Practice2.solution(input);
        assertEquals(expected, result);
    }
}