package com.ohgiraffers.section06.hash;

import java.util.HashMap;
import java.util.Map;

/*
Java Collection Framework에는 HashMap, HashSet과 같은
해시 기반 자료구조가 있다.
HashMap은 내부적으로 해시 테이블을 사용하여 키-값 쌍을 저장하고,
평균적으로 O(1) 시간에 검색/삽입/삭제를 수행할 수 있다.
*/
public class Application {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        // 요소 추가 (put)
        map.put("apple", 3);
        map.put("banana", 2);
        map.put("cherry", 5);

        // 요소 조회 (get)
        System.out.println("apple 개수: " + map.get("apple"));

        // 요소 존재 여부 확인
        System.out.println("banana가 있나? " + map.containsKey("banana"));

        // 요소 제거
        map.remove("banana");

        // 전체 출력
        System.out.println("전체 map: " + map);

        // 맵 비어있는지 확인
        System.out.println("map이 비어있나? " + map.isEmpty());
    }
}