package com.ohgiraffers.section01.sorting;

import java.util.Arrays;

/* TimSort(하이브리드 정렬)
 * - 작은 구간(RUN 크기)에는 삽입 정렬을 적용하고,
 *   이후 구간 크기를 2배씩 키워가며 병합(merge)하는 방식.
 */
public class F_TimSort {

    // 작은 구간에선 삽입 정렬이 캐시 친화적이라 빠르게 동작(경험적 기준으로 32~64가 흔함)
    private static final int RUN = 32;

    public static void solution(int[] arr) {
        int n = arr.length;

        // 1) 길이 RUN 이하의 작은 구간을 삽입 정렬
        for (int i = 0; i < n; i += RUN) {
            int left = i;
            int right = Math.min(i + RUN - 1, n - 1);
            insertionSort(arr, left, right);
        }
        System.out.println("삽입 정렬(각 RUN) 수행 후 : " + Arrays.toString(arr));

        // 병합에서 재사용할 임시 배열(한 번만 생성하여 전체 과정에서 재사용)
        int[] temp = new int[n];

        // 2) 구간 크기를 2배씩 키워가며 병합
        for (int size = RUN; size < n; size = size * 2) {
            // [left..mid] 와 [mid+1..right] 두 구간을 병합
            for (int left = 0; left < n; left += 2 * size) {

                // mid, right를 배열 범위로 값이 범위를 벗어나지 않도록 강제로 제한(클램프, clamp)
                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);

                // 병합 가능한 구간이 아니면 스킵
                if (mid >= right) {
                    continue;
                }

                System.out.println("병합 대상 구간: left=" + left + ", mid=" + mid + ", right=" + right);
                merge(arr, temp, left, mid, right);
                System.out.println("병합 후 배열 : " + Arrays.toString(arr));
            }
        }

        System.out.println("최종 정렬 결과 : " + Arrays.toString(arr));
    }

    /* [left..right] 구간 삽입 정렬
     * - 작은 구간에서의 삽입 정렬은 실제로 매우 빠르게 동작(배열은 연속된 메모리라 캐시 효율이 높음)
     * - 안정정렬이며 버블 정렬에 비해 교환 횟수가 적음
     */
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /* 두 정렬 구간 [left..mid], [mid+1..right] 병합
     * - 안정 정렬 유지: 같으면 왼쪽 원소를 먼저 쓴다
     * - 임시 배열 temp를 활용하여 [left..right] 범위를 복사해두고, arr에 다시 채워 넣는다.
     */
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // 병합 구간의 데이터를 temp에 복사
        System.arraycopy(arr, left, temp, left, right - left + 1);

        int leftIndex = left;     // 왼쪽 부분 시작
        int rightIndex = mid + 1; // 오른쪽 부분 시작
        int current = left;       // arr에 쓸 위치

        // 두 부분 배열 비교하면서 병합
        while (leftIndex <= mid && rightIndex <= right) {
            if (temp[leftIndex] <= temp[rightIndex]) { // 안정성 유지
                arr[current++] = temp[leftIndex++];
            } else {
                arr[current++] = temp[rightIndex++];
            }
        }

        // 왼쪽 잔여 요소 복사
        while (leftIndex <= mid) {
            arr[current++] = temp[leftIndex++];
        }
        // 오른쪽 잔여는 이미 arr에 제자리로 있음 (추가 복사 불필요)
    }
}