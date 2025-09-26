package com.ohgiraffers.section02.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/* 2644 촌수 계산 (BFS 최단 거리) */
public class Practice4 {

    static int N, start, end, M;
    static List<Integer>[] graph;
    static boolean[] visited;

    public static String solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        return Integer.toString(bfs());
    }

    static int bfs() {
        visited = new boolean[N + 1];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{start, 0});
        visited[start] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0], dist = cur[1];
            if (node == end) return dist;
            for (int nxt : graph[node]) {
                if (!visited[nxt]) {
                    visited[nxt] = true;
                    q.offer(new int[]{nxt, dist + 1});
                }
            }
        }
        return -1;
    }
}