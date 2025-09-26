package com.ohgiraffers.section02.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

/* 14888 연산자 끼워넣기 (백트래킹) */
public class Practice5 {

    static int N;
    static int[] nums;
    static int[] op = new int[4]; // + - * /
    static int max, min;

    public static String solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) op[i] = Integer.parseInt(st.nextToken());

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        dfs(1, nums[0]);

        return max + "\n" + min;
    }

    static void dfs(int idx, int result) {
        if (idx == N) {
            if (result > max) max = result;
            if (result < min) min = result;
            return;
        }

        int cur = nums[idx];

        if (op[0] > 0) { op[0]--; dfs(idx + 1, result + cur); op[0]++; }
        if (op[1] > 0) { op[1]--; dfs(idx + 1, result - cur); op[1]++; }
        if (op[2] > 0) { op[2]--; dfs(idx + 1, result * cur); op[2]++; }
        if (op[3] > 0) { op[3]--; dfs(idx + 1, result / cur); op[3]++; }
    }
}