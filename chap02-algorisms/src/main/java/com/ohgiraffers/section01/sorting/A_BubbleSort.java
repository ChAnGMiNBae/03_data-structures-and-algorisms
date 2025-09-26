package com.ohgiraffers.section01.sorting;

import java.util.Arrays;

/* 버블 정렬
 * 인접한 두 요소를 비교하여 크기가 순서대로 정렬 되지 않았으면 교환하는 방식의 정렬이다.
 * 배열 전체를 여러 번 반복하며 각 반복마다 가장 큰 요소가 끝으로 이동한다.
 * 시간 복잡도는 O(n^2)이며 데이터가 거의 정렬 된 경우 최선의 경우 O(n)이 될 수도 있다.
 * 하지만 교환 연산이 많아 다른 O(n^2) 정렬보다 느릴 수 있다.
 * */
public class A_BubbleSort {

    /* 문제 : n개의 정수가 주어졌을 때 버블 정렬 알고리즘을 사용하여 오름차순으로 정렬하는 프로그램 작성 */
    public static void solution(int[] arr) {
        /* 외부 for 문 : 한 번의 루프를 통해 맨 끝에 들어갈 값이 결정 된다.
         * 배열 길이 - 1 만큼 반복하면 된다. */
        for(int i = 0; i < arr.length - 1; i++) {
            /* 내부 for 문 : 인접한 값을 비교해서 swap한다.
             * j 값은 i를 제외하고 처리한다. 횟수가 반복 될 수록 줄어든다. */
            for(int j = 0; j < arr.length - i - 1; j++) {
                /* j 인덱스의 값이 j + 1 인덱스 값보다 크다면 오름차순 기준에 맞지 않으므로 swap */
                if(arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println((i + 1) + "번째 : " + Arrays.toString(arr));
        }

    }


}