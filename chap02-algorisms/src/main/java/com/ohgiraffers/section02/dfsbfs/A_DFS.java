package com.ohgiraffers.section02.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class A_DFS {

    static int node, edge, count;
    static int[][] map;
    static boolean[] visit;


    public static Integer solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        node = Integer.parseInt(br.readLine());
        edge = Integer.parseInt(br.readLine());

        /* 그래프로 간선 연결 정보를 이차원 배열로 표현 */
        map = new int[node + 1][node + 1];

        /* 방문 배열 생성 (지나간 노드를 다시 방문하지 않기 위함) */
        visit = new boolean[node + 1];

        StringTokenizer st;
        /* map에 그래프 간선 정보 넣기 */
        for(int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 무방향 그래프이므로 둘 다 표기
            map[a][b] = map[b][a] = 1;
        }

        dfsRec(1);

        return count;
    }

    /* 재귀 함수로 DFS 알고리즘을 구현할 메소드 */
    private static void dfsRec(int start) {
        /* 해당 노드는 방문 했으므로 방문 배열에 표기 */
        visit[start] = true;
        /* start 노드의 이웃 노드를 탐색하는 과정 */
        for(int i = 1; i <= node; i++) {
            /* start 정점의 이웃 중 방문하지 않은 이웃을 찾는다. */
            if(map[start][i] == 1 && !visit[i]) {
                /* 바이러스를 전파할 이웃 노드 컴퓨터를 찾음 */
                count++;
                dfsRec(i);
            }
        }
    }

    /* Stack을 활용한 DFS(반복문) */
    private static void dfsStack(int start) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visit[start] = true;

        while(!stack.isEmpty()) {
            int current = stack.pop();
            for(int i = 1; i <= node; i++) {
                if(map[current][i] == 1 && !visit[i]) {
                    stack.push(i);
                    visit[i] = true;
                    count++;
                }
            }
        }
    }
}
