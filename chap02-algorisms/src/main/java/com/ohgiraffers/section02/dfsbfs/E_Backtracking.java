package com.ohgiraffers.section02.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/* 백트래킹 (Backtracking)
 * 깊이 우선 탐색(DFS)을 기반으로 한 탐색 기법으로,
 * 가능한 모든 경우를 탐색하되 조건에 맞지 않는 경로는 즉시 되돌아가(백트랙) 탐색을 줄인다.
 * 즉, 해를 찾는 과정에서 유망하지 않은 선택지를 조기에 배제하여 탐색 효율을 높이는 알고리즘이다.
 */
public class E_Backtracking {

    static int L, C;
    static char[] arr, pick;
    static StringBuilder sb;
    static final Set<Character> VOWELS = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static String solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L =  Integer.parseInt(st.nextToken());      // 암호 길이
        C =  Integer.parseInt(st.nextToken());      // 가능성 있는 문자열 갯수

        arr =  new char[C];     // 가능성 있는 문자 보관할 배열
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++) arr[i] = st.nextToken().charAt(0);
        Arrays.sort(arr);   // 가능성 있는 문자 배열 정렬한 상태로 시작 -> 사전 순 응답을 위해

        pick = new char[L];
        sb = new StringBuilder();

        dfs(0, 0, 0, 0);    // arr의 index, 선택 된 갯수, 모음 개수, 자음 개수

        return sb.toString();
    }

    static void dfs(int idx, int chosen, int v, int c) {
        // 남은 문자로도 L을 채우지 못하면 종료
        if(chosen + (C - idx) < L) return;

        // L이 4 이면서 조건을 만족할 때
        if(chosen == L) {
            if(v >= 1 && c >= 2) sb.append(pick).append("\n");
            return;
        }

        // 1) 현재 문자 선택한다
        pick[chosen] = arr[idx];
        if(VOWELS.contains(arr[idx])) dfs(idx + 1, chosen + 1, v + 1, c);
        else dfs(idx + 1, chosen + 1, v, c + 1);

        // 2) 현재 문자를 선택하지 않는다
        dfs(idx + 1, chosen, v, c);
    }
}