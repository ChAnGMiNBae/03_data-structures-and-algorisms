package com.ohgiraffers.section02.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_BFS {

    // 배추 밭의 크기와 배추 개수
    static int M, N, K;
    // 배추밭
    static int[][] map;
    // 배추 찾기 시 사용할 방문 배열
    static boolean[][] visit;
    // x와 y 좌표를 가지는 Node 클래스
    static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    // 상하좌우 개념의 좌표 배열
    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0, 0};
    static int cx, cy;

    public static int solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visit = new boolean[M][N];

        /* 배추밭에 배추 심기 */
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
        }

//        /* 배추밭 출력 확인 */
//        System.out.println("배추밭 -----------------");
//        for(int i = 0; i < M; i++) {
//            for(int j = 0; j < N; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }

        /* 지렁이의 개수 */
        int count = 0;

        /* 배추 찾기 */
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                /* 방문하지 않은 위치에 배추가 심어져 있다면 지렁이를 심는다. */
                if(map[i][j] == 1 && !visit[i][j]) {
                    count++;
                    /* 해당 위치와 상하좌우로 붙어있는 배추밭을 방문 체크 한다. */
                    bfs(i, j);
                }
            }
        }

        return 0;
    }

    /* 지렁이가 심어질 때마다 한 번씩 동작하며 상하좌우 배추밭을 방문하는 메소드 */
    static void bfs(int x, int y) {
        Queue<Node> q = new ArrayDeque<>();
        visit[x][y] = true;
        q.offer(new Node(x, y));

        /* 연속적으로 상하좌우에 배추가 심어져있다면 해당 위치를 방문한 것으로 체크하고
        * queue를 통해 탐색을 진행한다. */
        while(!q.isEmpty()) {
            Node node = q.poll();

            /* 상하좌우 살펴보기 */
            for(int i = 0; i < 4; i++) {
                cx = node.x + dirX[i];
                cy = node.y + dirY[i];
                /* 자금 확인하는 방향이 아래 조건을 만족해야 탐색을 계속 진행한다.
                1. 좌표로서 존재하고
                2. 방문한적이 없고
                3. 배추가 심어져있다면 */
                if(rangeCheck() && !visit[cx][cy] && map[cx][cy] == 1) {
                    q.offer(new Node(cx, cy));
                    visit[cx][cy] = true;
                }
            }
        }
    }

    static boolean rangeCheck() {
        return cx >= 0 && cy >= 0 && cx < M && cy < N;
    }

}
