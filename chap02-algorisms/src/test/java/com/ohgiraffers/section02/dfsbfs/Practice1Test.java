package com.ohgiraffers.section02.dfsbfs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Practice1Test {

    static Stream<Arguments> provide() {
        return Stream.of(
                Arguments.arguments(
                        "7\n" +
                                "0110100\n" +
                                "0110101\n" +
                                "1110101\n" +
                                "0000111\n" +
                                "0100000\n" +
                                "0111110\n" +
                                "0111000\n",
                        "3\n7\n8\n9"
                ),
                Arguments.arguments(
                        "5\n" +
                                "00000\n" +
                                "00000\n" +
                                "00000\n" +
                                "00000\n" +
                                "00000\n",
                        "0\n"
                ),
                Arguments.arguments(
                        "3\n" +
                                "000\n" +
                                "010\n" +
                                "000\n",
                        "1\n1"
                ),
                Arguments.arguments(
                        "3\n" +
                                "111\n" +
                                "111\n" +
                                "111\n",
                        "1\n9"
                )
        );
    }

    @DisplayName("단지번호 붙이기 테스트")
    @ParameterizedTest
    @MethodSource("provide")
    void test(String input, String expected) throws IOException {
        String result = Practice1.solution(input);
        assertEquals(expected, result);
    }
}