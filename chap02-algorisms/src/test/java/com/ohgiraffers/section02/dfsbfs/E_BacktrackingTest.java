package com.ohgiraffers.section02.dfsbfs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/* 암호 만들기 - 백준(1759) */
class E_BacktrackingTest {

    static String input1, input2;
    static String output1, output2;

    @BeforeAll
    public static void set() {
        input1 = "4 6\n" +
                "a t c i s w";
        output1 =
                "acis\n" +
                        "acit\n" +
                        "aciw\n" +
                        "acst\n" +
                        "acsw\n" +
                        "actw\n" +
                        "aist\n" +
                        "aisw\n" +
                        "aitw\n" +
                        "astw\n" +
                        "cist\n" +
                        "cisw\n" +
                        "citw\n" +
                        "istw\n";

        input2 = "3 5\n" +
                "a b c d e";
        output2 =
                "abc\n" +
                        "abd\n" +
                        "acd\n" +
                        "bce\n" +
                        "bde\n" +
                        "cde\n";
    }

    public static Stream<Arguments> provideSource() {
        return Stream.of(
                arguments(input1, output1),
                arguments(input2, output2)
        );
    }

    @DisplayName("BOJ1759_Backtracking")
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    @ParameterizedTest
    @MethodSource("provideSource")
    public void test(String input, String expected) throws Exception {
        String result = E_Backtracking.solution(input);
        Assertions.assertEquals(expected, result);
    }
}