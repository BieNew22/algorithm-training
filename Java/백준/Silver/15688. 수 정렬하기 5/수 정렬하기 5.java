/**
 * 수 정렬하기 5
 * <p>
 * 문제 정리 (Counting sort)
 * 입력의 범위가 작아서 Counting sort 사용
 */

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 정렬
        int[] sorted = new int[2000001];

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            sorted[Integer.parseInt(br.readLine()) + 1000000]++;
        }

        //== 출력하기
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sorted.length; i++) {
            while (sorted[i] > 0) {
                sb.append(i - 1000000).append('\n');
                sorted[i] -= 1;
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}