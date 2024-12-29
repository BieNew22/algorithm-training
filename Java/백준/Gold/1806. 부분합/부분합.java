/**
 * 부분합
 *
 * 문제 정리
 * 구간 합이 S 이상이 되는 가장 짧은 구간 구하기
 */

import java.lang.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] data = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();


        // 이진 탐색
        int ans = Integer.MAX_VALUE;
        int s = 0, e = 0, sum = 0;
        do {
            // 현재 구간 증가 시키기
            sum += data[e++];

            while (sum >= S) {
                // 현재 구간의 합이 목표 값 이상일 때
                // -> 구간의 길이를 점점 줄이면서 작을 때 까지 줄 임
                ans = Math.min(ans, e - s);
                sum -= data[s++];
            }
        } while (e < N);

        System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}