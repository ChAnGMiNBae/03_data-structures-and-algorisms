package com.ohgiraffers.section09.graph;

import java.util.ArrayList;
import java.util.List;

/* 인접 리스트 기반 무방향 비가중치 그래프 예제
 * - 각 노드의 인접 노드 목록을 리스트로 관리
 */
public class UndirectedGraphAdjList {
    private int vertices;                   // 노드(정점)의 총 개수 (생성 및 추가된 노드 수)
    private List<List<Integer>> adjList;    // 각 정점의 인접 노드 목록
    private List<Boolean> active;           // 각 노드의 활성 상태 (true이면 존재하는 노드)

    /* 초기 정점 수 만큼 인접 리스트와 active 리스트 초기화 */
    public UndirectedGraphAdjList(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>();
        active = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
            active.add(true);
        }
    }

    /* 새로운 노드를 추가 */
    public int addNode() {
        adjList.add(new ArrayList<>());
        active.add(true);
        vertices++;
        return vertices - 1;
    }

    /* 무방향 간선을 추가 (노드 u와 v가 모두 활성 상태여야 함) */
    public void addEdge(int u, int v) {
        if (u < vertices && v < vertices && active.get(u) && active.get(v)) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
    }

    /* 무방향 간선을 제거 */
    public void removeEdge(int u, int v) {
        if (u < vertices && v < vertices) {
            if (active.get(u)) {
                adjList.get(u).remove((Integer)v);
            }
            if (active.get(v)) {
                adjList.get(v).remove((Integer)u);
            }
        }
    }

    /* 노드 x를 그래프에서 제거
     * 제거된 노드는 비활성화(active=false) 처리되고, 다른 노드의 인접 목록에서도 삭제 */
    public void removeNode(int x) {
        if (x < vertices && active.get(x)) {
            active.set(x, false);
            // 다른 모든 노드의 인접 목록에서 x 제거
            for (int i = 0; i < vertices; i++) {
                if (active.get(i)) {
                    adjList.get(i).remove((Integer)x);
                }
            }
            // 자신의 인접 리스트도 비움
            adjList.get(x).clear();
        }
    }


    /* 그래프의 상태를 출력
     * 각 활성 노드의 인접 목록을 출력 */
    public void printGraph() {
        System.out.println("무방향 그래프 (인접 리스트):");
        for (int i = 0; i < vertices; i++) {
            if (active.get(i)) {
                System.out.print(i + ": ");
                for (int neighbor : adjList.get(i)) {
                    System.out.print(neighbor + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {

        // 1. 초기 그래프 생성: 4개의 노드 (1 ~ 4)
        UndirectedGraphAdjList graphList = new UndirectedGraphAdjList(4);

        // 그래프 구조:
        // 1 - 2
        // | \ |
        // 3 - 4
        graphList.addEdge(0, 1); // 1 - 2
        graphList.addEdge(0, 2); // 1 - 3
        graphList.addEdge(0, 3); // 1 - 4
        graphList.addEdge(1, 3); // 2 - 4
        graphList.addEdge(2, 3); // 3 - 4

        System.out.println("초기 그래프:");
        graphList.printGraph();
        System.out.println();

        // 2. addNode 테스트: 새로운 노드 추가
        int newNode = graphList.addNode();
        System.out.println("새로운 노드 추가: " + newNode);
        // 노드 2, 4 를 새로운 노드 5 와 연결
        // 그래프 구조:
        // 1 - 2 \
        // | \ |  5
        // 3 - 4 /
        graphList.addEdge(newNode, 1);
        graphList.addEdge(newNode, 3);
        System.out.println("노드 5 추가 후 그래프:");
        graphList.printGraph();
        System.out.println();

        // 3. removeEdge 테스트: 노드 1과 4 사이의 간선 제거
        // 그래프 구조:
        // 1 - 2 \
        // |   |  5
        // 3 - 4 /
        System.out.println("노드 1과 4 사이의 간선 제거:");
        graphList.removeEdge(0, 3);
        graphList.printGraph();
        System.out.println();

        // 4. removeNode 테스트: 노드 3 제거
        // 그래프 구조:
        // 1 - 2 \
        //     |  5
        // x   4 /
        System.out.println("노드 3 제거:");
        graphList.removeNode(2);
        graphList.printGraph();
        System.out.println();
    }
}