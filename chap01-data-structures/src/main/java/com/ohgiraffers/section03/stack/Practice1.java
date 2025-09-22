package com.ohgiraffers.section03.stack;

import java.util.Stack;

public class Practice1 {
    public String solution(String input) {
        Stack<Character> stack = new Stack<>();

        for(char ch : input.toCharArray()) {
            if(ch == '(') {
                // 여는 괄호일 경우 스택에 push 해서 보관
                stack.push(ch);
            }
            else if(ch == ')') {
                // 스택에 비어서 괄호를 꺼낼 수 없다면 여는 괄호가 부족해서 짝이 맞지 않는 상황
                if(stack.isEmpty()) return "NO";
                // 닫는 괄호일 경우 스택에서 pop 해서 여는 괄호와 짝을 맞춤
                stack.pop();
            }
        }

        // 모든 문자에 대해서 처리 했는데 스택에 여는 괄호가 남아있다는 것은
        // 닫는 괄호가 부족해서 짝이 맞지 않는 상황
        if(!stack.isEmpty()) return "NO";

        return "YES";
    }
}
