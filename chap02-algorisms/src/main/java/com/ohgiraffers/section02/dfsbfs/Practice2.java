package com.ohgiraffers.section02.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/* 2583 영역 구하기 (DFS/BFS + 영역 넓이 계산) */
public class Practice2 {

    static int M, N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int area;

    public static String solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int y = y1; y < y2; y++) {
                for (int x = x1; x < x2; x++) {
                    map[y][x] = 1;
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x] == 0 && !visited[y][x]) {
                    area = 0;
                    dfs(y, x);
                    list.add(area);
                }
            }
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append('\n');
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) sb.append(' ');
        }
        return sb.toString();
    }

    static void dfs(int y, int x) {
        visited[y][x] = true;
        area++;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d], nx = x + dx[d];
            if (0 <= ny && ny < M && 0 <= nx && nx < N) {
                if (!visited[ny][nx] && map[ny][nx] == 0) {
                    dfs(ny, nx);
                }
            }
        }
    }
}