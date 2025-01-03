/**
 * 앱
 * <p>
 * 문제 정리
 * Knapsack 문제
 */

import java.lang.*;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] mData = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int[] cData = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        // m max: 10,000,000 (1억), c max: 100, N max: 100
        // 따라서 문제 대로 m을 무게 c를 가치로 보고 계산하면 O(Nm) 으로 시간 초과 나게 됨.
        // 결론 : c를 무게로 보고 m을 가치로 보고 계산하면 O(Nc) 으로 시간복잡도 통과.

        long[] DP = new long[100 * 100 + 1];

        for (int i = 0; i < N; i++) {
            for (int j = DP.length - 1; j >= 0; j--) {
                if (j - cData[i] >= 0)
                    DP[j] = Math.max(DP[j], DP[j - cData[i]] + mData[i]);
            }
        }

        // 지금 구한게 c를 만족하는 최대 m(DP[c])을 구함.
        // -> 이 때, 구하자고 하는게 M을 만족하는 최소 c 합 이므로
        // -> DP[c] 가 M 보다 크면 해당 c 가 M을 만족하는 c 중 하나에 해당 됨.

        int ans = 0;
        for (int i = 0; i < DP.length; i++) {
            if (DP[i] >= M) {
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}