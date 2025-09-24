package com.ohgiraffers.section08.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Practice2 {

    static class Gem {
        int M;
        int V;

        Gem(int M, int V) {
            this.M = M;
            this.V = V;
        }
    }

    public long solution(String input) throws IOException {
        BufferedReader br
                = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long answer = 0;

        PriorityQueue<Gem> gemPQ = new PriorityQueue<>((a, b) -> a.M - b.M);
        PriorityQueue<Integer> bagPQ = new PriorityQueue<>();
        PriorityQueue<Integer> valueMaxPQ = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            gemPQ.add(new Gem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            bagPQ.add(Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < k; i++) {
            if (bagPQ.isEmpty()) break;
            int begWeight = bagPQ.poll();

            while(!gemPQ.isEmpty() && gemPQ.peek().M <= begWeight) {
                valueMaxPQ.offer(gemPQ.poll().V);
            }

            if(!valueMaxPQ.isEmpty()) {
                answer += valueMaxPQ.poll();
            }
        }

        return answer;
    }
}
