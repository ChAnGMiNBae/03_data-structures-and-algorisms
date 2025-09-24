package com.ohgiraffers.section09.graph;

import java.util.ArrayList;
import java.util.List;

/* 인접 행렬 기반 무방향 비가중치 그래프
 * - 2차원 배열(행렬)을 사용하여 모든 정점 쌍의 연결 여부를 표현
 */
public class UndirectedGraphAdjMatrix {
    private int vertices;          // 현재 노드(정점)의 총 개수
    private boolean[][] matrix;    // 인접 행렬 (각 정점 쌍의 연결 여부)
    private List<Boolean> active;  // 각 노드의 활성 상태 (true이면 노드가 존재)

    /* 초기 정점 수 만큼 인접 행렬과 active 리스트를 초기화 */
    public UndirectedGraphAdjMatrix(int vertices) {
        this.vertices = vertices;
        matrix = new boolean[vertices][vertices];
        active = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            active.add(true);
        }
    }

    /* 새로운 노드를 동적으로 추가
     * 기존 행렬의 크기를 확장하고, active 리스트에 새 노드를 추가 */
    public int addNode() {
        int newVertex = vertices;
        vertices++;
        // 새로운 크기의 행렬 생성
        boolean[][] newMatrix = new boolean[vertices][vertices];
        // 기존 행렬 값을 복사
        for (int i = 0; i < newVertex; i++) {
            for (int j = 0; j < newVertex; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        matrix = newMatrix;
        active.add(true);
        return newVertex;
    }

    /* 무방향 간선을 추가
     * 노드 u와 v가 모두 활성 상태여야 간선 추가 가능 */
    public void addEdge(int u, int v) {
        if (u < vertices && v < vertices && active.get(u) && active.get(v)) {
            matrix[u][v] = true;
            matrix[v][u] = true;
        }
    }

    /* 무방향 간선을 제거 */
    public void removeEdge(int u, int v) {
        if (u < vertices && v < vertices) {
            matrix[u][v] = false;
            matrix[v][u] = false;
        }
    }

    /* 노드 x를 그래프에서 제거
     * 제거된 노드는 비활성화(active = false) 처리
     * 다른 노드의 인접 행렬에서도 해당 노드와의 간선이 제거
     */
    public void removeNode(int x) {
        if (x < vertices && active.get(x)) {
            active.set(x, false);
            // 노드 x와 관련된 모든 간선 제거 (행과 열 모두 false로 설정)
            for (int i = 0; i < vertices; i++) {
                matrix[x][i] = false;
                matrix[i][x] = false;
            }
        }
    }

    /* 그래프의 현재 상태를 출력
     * 각 활성 노드의 인접 행렬 값을 출력하며, 비활성 노드는 'x'로 표시 */
    public void printGraph() {
        System.out.println("무방향 그래프 (인접 행렬):");
        for (int i = 0; i < vertices; i++) {
            if (active.get(i)) {
                for (int j = 0; j < vertices; j++) {
                    if (active.get(j)) {
                        System.out.print((matrix[i][j] ? 1 : 0) + " ");
                    } else {
                        System.out.print("x "); // 비활성 노드 표시
                    }
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {

        // 1. 초기 그래프 생성: 4개의 노드 (1 ~ 4)
        UndirectedGraphAdjMatrix graphMatrix = new UndirectedGraphAdjMatrix(4);

        // 그래프 구조:
        // 1 - 2
        // | \ |
        // 3 - 4
        graphMatrix.addEdge(0, 1); // 1 - 2
        graphMatrix.addEdge(0, 2); // 1 - 3
        graphMatrix.addEdge(0, 3); // 1 - 4
        graphMatrix.addEdge(1, 3); // 2 - 4
        graphMatrix.addEdge(2, 3); // 3 - 4

        System.out.println("초기 그래프:");
        graphMatrix.printGraph();
        System.out.println();

        // 2. addNode 테스트: 새로운 노드 추가
        int newNode = graphMatrix.addNode();
        System.out.println("새로운 노드 추가: " + newNode);
        // 노드 2, 4 를 새로운 노드 5 와 연결
        // 그래프 구조:
        // 1 - 2 \
        // | \ |  5
        // 3 - 4 /
        graphMatrix.addEdge(newNode, 1);
        graphMatrix.addEdge(newNode, 3);
        System.out.println("노드 5 추가 후 그래프:");
        graphMatrix.printGraph();
        System.out.println();

        // 3. removeEdge 테스트: 노드 1과 4 사이의 간선 제거
        // 그래프 구조:
        // 1 - 2 \
        // |   |  5
        // 3 - 4 /
        System.out.println("노드 1과 4 사이의 간선 제거:");
        graphMatrix.removeEdge(0, 3);
        graphMatrix.printGraph();
        System.out.println();

        // 4. removeNode 테스트: 노드 3 제거
        // 그래프 구조:
        // 1 - 2 \
        //     |  5
        // x   4 /
        System.out.println("노드 3 제거:");
        graphMatrix.removeNode(2);
        graphMatrix.printGraph();
        System.out.println();
    }
}