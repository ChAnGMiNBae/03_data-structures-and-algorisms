package com.ohgiraffers.section01.arraytech;

/* 슬라이딩 윈도우(Sliding Window)
 * 연속된 구간(윈도우)의 합/평균/최댓값 등을 효율적으로 갱신하며 탐색하는 기법이다.
 * 윈도우를 한 칸 밀 때, 나가는 값은 빼고 들어오는 값은 더해 O(1)에 갱신 -> 전체 O(n).
 * 고정 크기 윈도우와, 조건을 만족하는 최소/최대 길이 윈도우 문제 유형이 있다.
 */
public class C_SlidingWindow {

    /* 고정 크기 윈도우 : 정수 배열과 윈도우 크기 k가 주어졌을 때,
     * 연속한 k개의 합의 최댓값을 구하기 (k <= n)
     */
    public static int maxSumFixedWindow(int[] arr, int k) {
        int windowSum = 0;
        // 전달 된 구간 길이만큼 윈도우 설정하여 초기합
        for(int i = 0; i < k; i++) windowSum += arr[i];

        int max = windowSum;

        for(int right = k; right < arr.length; right++){
            int left = right - k;
            windowSum = windowSum - arr[left] + arr[right];
            if(windowSum > max) max = windowSum;
        }

        return 0;
    }

    /* 가변 크기 윈도우 : 양의 정수 배열에서 합이 S 이상이 되는 최소 길이 연속 부분배열의 길이를 구하기
     * 없으면 0 반환
     */
    public static int minLenSubarrayAtLeastS(int[] arr, int S) {
        int left = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;

        for(int right = 0; right < arr.length; right++) {
            sum += arr[right];

            while(sum >= S) {
                ans = Math.min(ans, right - left + 1);
                sum -= arr[left++];
            }
        }

        return ans ==  Integer.MAX_VALUE ? 0 : ans;
    }
}