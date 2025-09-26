package com.ohgiraffers.section02.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/* BFS는 먼저 방문한 노드를 기준으로 가장 가까운 노드부터 탐색한다.
 * 즉, 시작점에서 출발하여 거리 1짜리 노드를 모두 방문한 후,
 * 거리 2짜리 노드를 방문하는 방식으로 진행 된다.
 * 따라서 어떤 노드에 도달했을 때, 처음 도달한 순간이 곧 최단 거리가 된다.
 * DFS는 한 방향으로 깊게 들어간 후, 막다른 길에 도달하면 다시 되돌아오므로
 * 최단 경로가 아닌 먼 길을 돌아 도착하는 경우도 생길 수 있다.
 * */
public class D_ShortestPath {
    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    // x와 y 좌표를 가지는 Node 클래스
    static class Node {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    // 상하좌우 개념의 좌표 배열
    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0, 0};
    public static int solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            char[] ch = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                map[i][j] = Character.getNumericValue(ch[j]); // '1' -> 1
            }
        }

        /* (0, 0) 에서 출발 */
        bfs(0, 0);

        /* 도착지의 값을 반환한다. (도착 할 때까지의 최적의 경로 단계 수) */
        return map[N - 1][M - 1];
    }

    static void bfs(int x, int y) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(x , y));
        visit[x][y] = true;

        while(!q.isEmpty()) {
            Node n = q.poll();

            for(int i = 0; i < 4; i++) {
                int cx = n.x + dirX[i];
                int cy = n.y + dirY[i];

                /* 좌표가 전체 범위를 넘어간다면 확인 불필요 */
                if(cx < 0 || cy < 0 || cx >= N || cy >= M) continue;

                /* 방문 했던 좌표나 벽이면 확인 불필요 */
                if(visit[cx][cy] || map[cx][cy] == 0) continue;

                q.offer(new Node(cx, cy));
                visit[cx][cy] = true;
                map[cx][cy] = map[n.x][n.y] + 1;

                // 디버깅용 출력
                System.out.println("map[" + cx + "][" + cy + "] = " + map[cx][cy]);

            }
        }
    }
}