package com.ohgiraffers.section04.queue;

import java.util.LinkedList;
import java.util.Queue;

public class Practice1 {
    public String solution(int n, int k) {
        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder("<");

        /* 인원 수 만큼 큐에 담기 */
        for(int i = 1; i <= n; i++) {
            q.offer(i);
        }

        /* 큐에서 모든 사람이 제거 될 때까지 반복 */
        while(!q.isEmpty()) {

            /* 앞에서 k-1명을 뒤로 보내기 */
            for(int i = 1; i <= k - 1; i++) {
                q.offer(q.poll());
            }

            /* 제거할 사람은 StringBuilder 객체에 기입 */
            sb.append(q.poll());

            /* 제거 할 사람이 남은 경우 ", " 기록 */
            if (!q.isEmpty()) {
                sb.append(", ");
            }
        }

        sb.append(">");
        return sb.toString();
    }
}
