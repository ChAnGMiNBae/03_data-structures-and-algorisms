package com.ohgiraffers.section08.heap;

import java.util.PriorityQueue;

public class Practice1 {
    public String solution(Integer[] array) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> {
            int absCompare = Integer.compare(Math.abs(o1), Math.abs(o2));
            if(absCompare == 0) {
                return Integer.compare(o1, o2);
            }
            return absCompare;
        });

        StringBuilder sb = new StringBuilder();

        for (int x : array) {
            /* 0은 가장 우선 순위가 높은 값을 출력하라는 의미 */
            if(x == 0) {
                if(pq.isEmpty()) {
                    sb.append("0 ");
                } else {
                    sb.append(pq.poll()).append(" ");
                }
            } else {
                /* 0 외의 유의미한 숫자인 경우 큐에 담는다. */
                pq.offer(x);
            }
        }

        return sb.toString().trim();
    }
}