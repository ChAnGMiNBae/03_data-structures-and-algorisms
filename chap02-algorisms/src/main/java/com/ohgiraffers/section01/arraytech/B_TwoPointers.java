package com.ohgiraffers.section01.arraytech;

/* 투 포인터(Two Pointers)
 * 정렬된 배열에서 양쪽 끝 포인터를 움직여 조건을 만족하는 쌍/구간을 찾는 기법이다.
 * 시간복잡도: O(n)
 */
public class B_TwoPointers {

    /* 정렬된 배열과 목표 합 target이 주어질 때, 합이 target인 서로 다른 두 원소 쌍의 개수 구하기 */
    public static int countPairSum(int[] sorted, int target) {
        int left = 0;
        int right = sorted.length - 1;
        int count = 0;

        while (left < right) {
            int sum = sorted[left] + sorted[right];

            if(sum == target){
                count++;
                left++;
                right--;
            } else if (sum < target){
                left++;
            } else {
                right--;
            }
        }

        return count;
    }

    /* 정렬된 배열에서 합이 target인 존재 여부 판단 -> 직접 구현 */
    public static boolean existPairSum(int[] sorted, int target) {
        int left = 0;
        int right = sorted.length - 1;

        while (left < right) {
            int sum = sorted[left] + sorted[right];

            if(sum == target){
                return true;
            } else if (sum < target){
                left++;
            } else {
                right--;
            }
        }

        return false;
    }
}