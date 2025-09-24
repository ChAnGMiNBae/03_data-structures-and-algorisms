package com.ohgiraffers.section04.queue;

import java.util.LinkedList;
import java.util.Queue;

public class Practice2 {
    public int solution(int w, int l, Integer[] truckWeight) {
        Queue<Integer> bridge = new LinkedList<>(); // 다리 칸: 트럭 무게 또는 0
        int time = 0;
        int sum  = 0;  // 다리 위 총 무게
        int idx  = 0;  // 다음에 올릴 트럭 인덱스
        int n    = truckWeight.length;

        // 트럭이 남아 있거나 다리 위에 아직 트럭 무게가 남아 있는 동안
        while (idx < n || sum > 0) {
            time++;

            // 다리가 가득 차 있으면 한 칸 전진(맨 앞 칸 내려감)
            if (bridge.size() == w) {
                sum -= bridge.poll();
            }

            if (idx < n) {
                int next = truckWeight[idx];
                if (sum + next <= l) {
                    bridge.offer(next);  // 트럭 진입
                    sum += next;
                    idx++;
                } else {
                    bridge.offer(0);    // 전진만 (빈 칸)
                }
            } else {
                // 더 올릴 트럭이 없고, 아직 다리 위에 트럭이 남아 있다면 전진을 위해 0 추가
                if (sum > 0) {
                    bridge.offer(0);
                }
            }
        }

        return time;
    }
}