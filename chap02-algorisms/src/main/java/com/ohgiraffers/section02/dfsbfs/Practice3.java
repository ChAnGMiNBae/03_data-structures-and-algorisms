package com.ohgiraffers.section02.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

/* 2468 안전 영역 (DFS/BFS + 조건 변화 반복) */
public class Practice3 {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static String solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int maxH = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxH = Math.max(maxH, map[i][j]);
            }
        }

        int ans = 0;
        for (int h = 0; h <= maxH; h++) {
            visited = new boolean[N][N];
            int cnt = 0;
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (map[x][y] > h && !visited[x][y]) {
                        dfs(x, y, h);
                        cnt++;
                    }
                }
            }
            ans = Math.max(ans, cnt);
        }
        return Integer.toString(ans);
    }

    static void dfs(int x, int y, int h) {
        visited[x][y] = true;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                if (!visited[nx][ny] && map[nx][ny] > h) {
                    dfs(nx, ny, h);
                }
            }
        }
    }
}