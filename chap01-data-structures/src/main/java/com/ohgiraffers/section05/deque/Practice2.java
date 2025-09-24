package com.ohgiraffers.section05.deque;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Practice2 {

    public String solution(String command, Integer[] array) {

        // 전달 된 숫자를 deque에 담음
        ArrayDeque<Integer> deque = new ArrayDeque<>(Arrays.asList(array));
        // 방향 관리
        boolean isRight = true;

        // 함수 반복
        for(char cmd : command.toCharArray()) {
            // 'R' 이면 방향을 변경
            if(cmd == 'R') {
                isRight = !isRight;
            } else {
                // 'D'인데 비어 있으면
                if (deque.isEmpty()) {
                    return "error";
                } else {
                    // 'D'이고 비어있지 않으면 방향에 따라 제거
                    if (isRight) {
                        deque.removeFirst();
                    } else {
                        deque.removeLast();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (!deque.isEmpty()) {
            sb.append(isRight ? deque.removeFirst() : deque.removeLast());
            if (!deque.isEmpty()) sb.append(',');
        }
        sb.append(']');

        return sb.toString();
    }
}