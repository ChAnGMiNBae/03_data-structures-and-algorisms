package com.ohgiraffers.section01.sorting;

import java.util.Arrays;

/* 병합 정렬
* 분할 정복 방식으로 배열을 반씩 나누어 정렬한 후 병합하는 방식이다.
* 정렬 된 두 개의 배열을 합치는 과정에서 안정 정렬의 특징을 유지할 수 있다.
* 시간 복잡도는 항상 O(nlogn)으로 일정하며, 최악의 경우에도 O(nlogn)이다.
* 단, 추가 메모리 공간이 필요하므로 메모리 제한적 환경에서는 비효율적일 수 있다.
* */
public class E_MergeSort {

    /* n 개의 정수가 주어졌을 때 병합 정렬 알고리즘을 사용하여 오름차순으로 정렬하는 프로그램 */
    public static void solution(int[] arr) {
        System.out.println("원본 배열 : " + Arrays.toString(arr));
        int[] temp = new int[arr.length];   // 추가 메모리 공간
        mergeSort(arr, temp, 0, arr.length - 1);
        System.out.println("정렬 된 배열 : " +  Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        /* 최적화 (이미 정렬 된 경우 병합 생략) */
        if(arr[mid] <= arr[mid + 1]){
            System.out.println("병합 생략 - left : " + left + ", mid : " + mid + ", right : " + right);
            return;
        }
        merge(arr, temp, left, mid, right);
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        System.out.println("병합 전 : " + Arrays.toString(arr));
        System.out.println("left : " + left + ", mid : " + mid + ", right : " + right);

        // 병합 구간의 배열을 임시 배열에 복사
        System.arraycopy(arr, left, temp, left, right - left + 1);

        int leftIndex = left;               // 왼쪽 부분 배열의 시작점
        int rightIndex = mid + 1;           // 오른쪽 부분 배열의 시작점
        int current = left;                 // arr에 넣을 위치

        // 두 부분 배열을 비교하면서 병합
        while(leftIndex <= mid && rightIndex <= right) {
            if(temp[leftIndex] <= temp[rightIndex]) {
                arr[current++] = temp[leftIndex++];
            } else {
                arr[current++] = temp[rightIndex++];
            }
        }

        // 왼쪽 배열에 남은 요소 복사
        while(leftIndex <= mid) {
            arr[current++] = temp[leftIndex++];
        }

        System.out.println("병합 완료 : " + Arrays.toString(arr));
        System.out.println("-------------------------------------");
    }
}
