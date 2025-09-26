package com.ohgiraffers.section02.dfsbfs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Practice3Test {

    static Stream<Arguments> provide() {
        return Stream.of(
                Arguments.arguments(
                        "5\n" +
                                "6 8 2 6 2\n" +
                                "3 2 3 4 6\n" +
                                "6 7 3 3 2\n" +
                                "7 2 5 3 6\n" +
                                "8 9 5 2 7\n",
                        "5"
                ),
                Arguments.arguments(
                        "3\n" +
                                "5 5 5\n" +
                                "5 5 5\n" +
                                "5 5 5\n",
                        "1"
                ),
                Arguments.arguments(
                        "2\n" +
                                "1 1\n" +
                                "1 1\n",
                        "1"
                )
        );
    }

    @DisplayName("안전 영역 테스트")
    @ParameterizedTest
    @MethodSource("provide")
    void test(String input, String expected) throws IOException {
        String result = Practice3.solution(input);
        assertEquals(expected, result);
    }
}