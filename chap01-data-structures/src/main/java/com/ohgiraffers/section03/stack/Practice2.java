package com.ohgiraffers.section03.stack;

import java.util.Stack;

public class Practice2 {
    public int solution(String input) {
        Stack<Character> stack = new Stack<>();
        int cnt = 0;
        char prev = 0;

        for(char ch : input.toCharArray()) {
            if(ch == '(') {
                stack.push(ch);
            }
            else if(ch == ')') {
                if(stack.isEmpty()) return -1;
                stack.pop();

                if(prev == '(') {
                    cnt += stack.size();
                }
                else {
                    cnt++;
                }
            }

            prev = ch;
        }

        if(!stack.isEmpty()) return -1;

        return cnt;
    }
}