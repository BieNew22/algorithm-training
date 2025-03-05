/**
 * 최솟값과 최댓값
 * <p>
 * 문제 정리 (세그먼트 트리 문제)
 * 기본적인 세그먼트 트리 개념에 대한 문제
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    int[][] tree;   // tree[A][B] : A(구간에 대한 정보), B(0 = 구간내 최솟 값, 1 = 구간내 최댓 값)
    int[] data;     // 숫자 데이터를 저장하는 배열

    /**
     * 세그먼트 트리 생성 함수
     * @param s         시작 범위
     * @param e         종료 범위
     * @param idx       해당 범위에 대한 정보를 저장하는 index.
     * @return          해당 범위에서 최솟값과 최댓값을 반환.
     */
    int[] initTree(int s, int e, int idx) {
        if (s == e) {
            tree[idx] = new int[] {data[s], data[e]};
            return tree[idx];
        }

        int mid = (s + e) / 2;
        int[] lRes = initTree(s, mid, idx * 2);
        int[] rRes = initTree(mid + 1, e, idx * 2 + 1);

        tree[idx] = new int[] {Math.min(lRes[0], rRes[0]), Math.max(lRes[1], rRes[1])};
        return tree[idx];
    }

    /**
     * 구간내 최솟값과 최댓값을 구함.
     *
     * @param s     현재 탐색하는 시작 범위
     * @param e     현재 탐색하는 종료 범위
     * @param idx   현재 탐색하는 범위에 대한
     * @param l     찾으려는 쵯솟값과 최댓값의 시작 범위.
     * @param r     찾으려는 최솟값과 최댓값의 종료 범위.
     * @return      구가내 최솟값과 최댓값.
     */
    int[] findMinMax(int s, int e, int idx, int l, int r) {
        if (l > e || r < s) {
            return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE};
        }

        if (l <= s && e <= r) {
            return tree[idx];
        }

        int mid = (s + e) / 2;

        int[] lRes = findMinMax(s, mid, idx * 2, l, r);
        int[] rRes = findMinMax(mid + 1, e, idx * 2 + 1, l, r);

        return new int[] {Math.min(lRes[0], rRes[0]), Math.max(lRes[1], rRes[1])};

    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 데이터의 개수
        int M = Integer.parseInt(st.nextToken());   // 쿼리의 개수

        data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }

        tree = new int[N * 4][];
        initTree(0, N - 1, 1);  // 세그먼트 트리 생성.

        //== 쿼리 처리하기.
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;

            int[] find = findMinMax(0, N - 1, 1, s, e);

            sb.append(find[0]).append(' ').append(find[1]).append('\n');
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}