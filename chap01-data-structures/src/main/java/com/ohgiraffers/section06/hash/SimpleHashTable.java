package com.ohgiraffers.section06.hash;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

/* 간단 해시 테이블 구현(체이닝, LinkedList 사용) */
public class SimpleHashTable<K, V> {

    /* 버킷에 들어갈 엔트리(키-값 한 쌍) */
    private static class Entry<K, V> {
        final K key;
        V value;
        Entry(K k, V v) { this.key = k; this.value = v; }
    }

    /* 내부 테이블: 각 칸은 체인(LinkedList) */
    private LinkedList<Entry<K, V>>[] table;

    /* 현재 저장된 원소 수, 버킷 수 */
    private int size;
    private int capacity;

    /* 기본 설정값: 초기 용량과 부하율(리사이즈 트리거) */
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    @SuppressWarnings("unchecked")
    public SimpleHashTable() {
        this.capacity = DEFAULT_CAPACITY;
        this.table = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /* 단순한 인덱스 계산: hashCode의 절댓값을 테이블 크기로 나눈 나머지 */
    private int index(Object key) {
        int h = (key == null) ? 0 : key.hashCode();
        return Math.abs(h) % capacity;
    }

    /* put: 키가 이미 있으면 값 갱신, 없으면 새로 삽입 */
    public void put(K key, V value) {
        // 리사이즈 조건: 부하율 초과 시 용량 2배
        if ((size + 1) > capacity * LOAD_FACTOR) {
            resize(capacity * 2);
        }
        int idx = index(key);
        if (table[idx] == null) table[idx] = new LinkedList<>();

        for (Entry<K, V> e : table[idx]) {
            if (Objects.equals(e.key, key)) {
                e.value = value; // 기존 키 갱신
                return;
            }
        }
        table[idx].add(new Entry<>(key, value));
        size++;
    }

    /* get: 키에 해당하는 값 조회(없으면 null) */
    public V get(K key) {
        int idx = index(key);
        LinkedList<Entry<K, V>> bucket = table[idx];
        if (bucket == null) return null;

        for (Entry<K, V> e : bucket) {
            if (Objects.equals(e.key, key)) {
                return e.value;
            }
        }
        return null;
    }

    /* containsKey: 키 존재 여부 (값이 null이어도 키 존재는 true로 판단) */
    public boolean containsKey(K key) {
        int idx = index(key);
        LinkedList<Entry<K, V>> bucket = table[idx];
        if (bucket == null) return false;

        for (Entry<K, V> e : bucket) {
            if (Objects.equals(e.key, key)) {
                return true;
            }
        }
        return false;
    }

    /* remove: 키가 있으면 제거하고 기존 값을 반환, 없으면 null */
    public V remove(K key) {
        int idx = index(key);
        LinkedList<Entry<K, V>> bucket = table[idx];
        if (bucket == null) return null;

        for (Iterator<Entry<K, V>> it = bucket.iterator(); it.hasNext(); ) {
            Entry<K, V> e = it.next();
            if (Objects.equals(e.key, key)) {
                it.remove();
                size--;
                return e.value;
            }
        }
        return null;
    }

    /* 전체 초기화 */
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            table[i] = null;
        }
        size = 0;
    }

    /* 리사이즈(재해시): 새 테이블을 만들고 모든 엔트리를 다시 삽입 */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        LinkedList<Entry<K, V>>[] old = table;
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[newCapacity];
        int oldCapacity = capacity;
        capacity = newCapacity;
        size = 0; // insert에서 다시 카운트

        for (int i = 0; i < oldCapacity; i++) {
            if (old[i] == null) continue;
            for (Entry<K, V> e : old[i]) {
                // 기존 put을 그대로 사용해 인덱스 재계산, 삽입
                put(e.key, e.value);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SimpleHashTable\n");
        for (int i = 0; i < capacity; i++) {
            sb.append(i).append(": ");
            if (table[i] != null) {
                for (Entry<K, V> e : table[i]) {
                    sb.append("[").append(e.key).append("=").append(e.value).append("] ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /* 실제 HashMap 과의 차이
    - 해시 보정: 비트 섞기로 분포 개선
    - 인덱스 계산: 단순 % 연산 대신 더 빠른 비트 연산을 사용
    - 체인 길이 임계치 초과 시 레드블랙트리로 변환(최악 성능 O(log n)로 완화)
    */
    public static void main(String[] args) {
        SimpleHashTable<String, Integer> ht = new SimpleHashTable<>();
        // 부하율 초과 유도 -> 리사이즈/재해시 동작 확인
        for (int i = 0; i < 100; i++) {
            ht.put("k" + i, i);
        }
        System.out.println(ht);
    }
}