package com.ohgiraffers.section01.arraytech;

import java.util.Arrays;

/* 이분 탐색(Binary Search)
 * 정렬된 배열에서 중앙값과 비교하며 탐색 구간을 절반으로 줄여 가는 탐색 방법이다.
 * 매 단계에서 절반을 버리므로 시간 복잡도는 O(log n)이다.
 * 탐색 대상 배열은 오름차순으로 정렬되어 있어야 한다.
 */
public class A_BinarySearch {

    /* 정렬된 정수 배열과 찾을 값을 입력으로 받아 이분 탐색으로 인덱스를 반환(없으면 -1 반환) */

    // 반복문 방식
    public static int iterative(int[] sorted, int target) {
        int left = 0;
        int right = sorted.length - 1;
        System.out.println("배열 : " + Arrays.toString(sorted) + ", 찾을 값 : " + target);

        while (left <= right) {
            int mid = (left + right) / 2;
            System.out.println("현재 구간 [" + left + ", " + right + "], mid=" + mid + ", mid값=" + sorted[mid]);

            if (sorted[mid] == target) {
                System.out.println("찾았다 인덱스 : " + mid);
                return mid;
            } else if (sorted[mid] < target) {
                System.out.println("중간값이 작음 -> 오른쪽으로 이동");
                left = mid + 1;
            } else {
                System.out.println("중간값이 큼 -> 왼쪽으로 이동");
                right = mid - 1;
            }
        }

        System.out.println("값이 존재하지 않음 -> -1 반환");
        return -1;
    }

    // 재귀 방식 -> 직접 구현
    public static int recursive(int[] sorted, int target) {
        System.out.println("배열 : " + Arrays.toString(sorted) + ", 찾을 값 : " + target);

        return recur(sorted, target, 0, sorted.length - 1);
    }

    private static int recur(int[] arr, int target, int left, int right) {
        if (left > right) {
            System.out.println("값이 존재하지 않음 -> -1 반환");
            return -1;
        }

        int mid = left + (right - left) / 2;
        System.out.println("현재 구간 [" + left + ", " + right + "], mid=" + mid + ", mid값=" + arr[mid]);

        if (arr[mid] == target) {
            System.out.println("찾았다 인덱스 : " + mid);
            return mid;
        } else if (arr[mid] < target) {
            System.out.println("중간값이 작음 -> 오른쪽으로 이동");
            return recur(arr, target, mid + 1, right);
        } else {
            System.out.println("중간값이 큼 -> 왼쪽으로 이동");
            return recur(arr, target, left, mid - 1);
        }
    }
}