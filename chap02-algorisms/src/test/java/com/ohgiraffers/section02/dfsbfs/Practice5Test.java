package com.ohgiraffers.section02.dfsbfs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Practice5Test {

    static Stream<Arguments> provide() {
        return Stream.of(
                Arguments.arguments(
                        "2\n" +
                                "5 6\n" +
                                "0 0 1 0\n",
                        "30\n30"
                ),
                Arguments.arguments(
                        "3\n" +
                                "3 4 5\n" +
                                "1 0 1 0\n",
                        "35\n17"
                ),
                Arguments.arguments(
                        "3\n" +
                                "-5 2 3\n" +
                                "0 0 0 2\n",
                        "0\n0"
                ),
                Arguments.arguments(
                        "3\n" +
                                "10 5 2\n" +
                                "0 2 0 0\n",
                        "3\n3"
                ),
                Arguments.arguments(
                        "4\n" +
                                "1 2 3 4\n" +
                                "1 1 1 0\n",
                        "8\n0"
                )
        );
    }

    @DisplayName("연산자 끼워넣기 테스트")
    @ParameterizedTest
    @MethodSource("provide")
    void test(String input, String expected) throws IOException {
        String result = Practice5.solution(input);
        assertEquals(expected, result);
    }
}