package com.ohgiraffers.section01.sorting;

import java.util.Arrays;

/* 최적화 된 버블 정렬 구현하기
 * 버블정렬은 인접한 두 요소를 비교하여 정렬하므로 일반적으로는 O(n^2)의 시간 복잡도를 가지지만
 * 정렬이 어느 정도 되어 있는 경우 불필요한 비교를 줄이는 최적화를 통해 O(n)까지 성능을 개선할 수 있다.*/
public class Practice1 {

    public static void solution(int[] arr) {
        boolean swapped;

        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false; // 교환 발생 여부 체크
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) { // 인접 요소 비교 후 교환
                    swap(arr, j, j + 1);
                    swapped = true; // 교환 발생했음을 표시
                }
            }
            // 교환이 한 번도 발생하지 않으면 정렬 완료
            if (!swapped) break;
            System.out.println((i + 1) + "번째 : " + Arrays.toString(arr));
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}