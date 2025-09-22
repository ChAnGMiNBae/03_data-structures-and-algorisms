package com.ohgiraffers.section05.deque;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

//public class Practice1 {
//
//    static class Ballon {
//        int order;
//        int noteValue;
//
//        Ballon(int order, int noteValue) {
//            this.order = order;
//            this.noteValue = noteValue;
//        }
//    }
//
//    public String solution(Integer[] notes) {
//        StringBuilder sb = new StringBuilder();
//
//        // 풍선을 Deque에 삽입
//        Deque<Ballon> ballons = new ArrayDeque<>();
//        for (int i = 0; i < notes.length; i++) {
//            ballons.addLast(new Ballon(i + 1, notes[i]));
//        }
//
//        Ballon nowBallon = ballons.removeFirst();
//        int nowMove = nowBallon.noteValue;
//        sb.append(nowBallon.order).append(" ");
//
//        while (!ballons.isEmpty()) {
//            if(nowMove > 0) {
//                for(int i = 0; i < nowMove - 1; i++) {
//                    ballons.addLast(ballons.removeFirst());
//                }
//
//                nowBallon = ballons.removeFirst();
//                nowMove = nowBallon.noteValue;
//                sb.append(nowBallon.order);
//
//                if(!ballons.isEmpty()) {
//                    sb.append(" ");
//                }
//            }
//            else if(nowMove < 0) {
//                for(int i = 0; i < Math.abs(nowMove) - 1; i++) {
//                    ballons.addFirst(ballons.removeLast());
//                }
//
//                nowBallon = ballons.removeLast();
//                nowMove = nowBallon.noteValue;
//                sb.append(nowBallon.order);
//
//                if(!ballons.isEmpty()) {
//                    sb.append(" ");
//                }
//            }
//        }
//
//        return sb.toString();
//    }
//}

public class Practice1 {

    static class Ballon {
        int order;
        int noteValue;

        Ballon(int order, int noteValue) {
            this.order = order;
            this.noteValue = noteValue;
        }
    }

    public String solution(Integer[] notes) {
        StringBuilder sb = new StringBuilder();

        // 풍선을 Deque에 삽입
        Deque<Ballon> ballons = new ArrayDeque<>();
        for (int i = 0; i < notes.length; i++) {
            ballons.addLast(new Ballon(i + 1, notes[i]));
        }

        /* 무조건 첫 번째 풍선이 먼저 제거 */
        Ballon current = ballons.removeFirst();
        sb.append(current.order);

        /* 덱에서 모든 풍선이 제거 될 때까지 반복 */
        while (!ballons.isEmpty()) {

            /* 터진 풍선에 기록 된 이동 값 */
            int moveValue = current.noteValue;

            if(moveValue > 0) {
                /* 양수 : 오른쪽 이동, 앞 요소를 빼서 뒤로 삽입 */
                for(int i = 1; i < moveValue; i++) {
                    ballons.addLast(ballons.removeFirst());
                }
                current = ballons.removeFirst();
            } else {
                /* 음수 : 왼쪽 이동, 뒤 요소를 빼서 앞으로 삽입 */
                for(int i = 1; i < -moveValue; i++) {
                    ballons.addFirst(ballons.removeLast());
                }
                current = ballons.removeLast();
            }

            sb.append(" ").append(current.order);
        }


        return sb.toString();
    }
}
