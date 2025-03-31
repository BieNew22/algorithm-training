/**
 * 동전 2
 * <p>
 * 문제 정리 (DP 문제)
 * - 동전의 가치가 배수로 증가하는 것이 아닌 무작위 이므로 그리디 불가.
 * - 따라서 DP로 해결해야 함.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 동전의 가치 개수
        int k = Integer.parseInt(st.nextToken());   // 구하자고 하는 가치 합.

        // DP[i] = i 원을 만드는데 필요한 최소 동전의 개수 저장.
        int[] DP = new int[100001];
        Arrays.fill(DP, Integer.MAX_VALUE - 1);
        DP[0] = 0;

        HashSet<Integer> coins = new HashSet<>();

        for (int i = 0; i < n; i++) {
            coins.add(Integer.parseInt(br.readLine()));
        }

        //== DP[k] 구하기
        for (int i = 1; i <= k; i++) {
            for (int coin: coins) {
                if (coin <= i) {
                    DP[i] = Math.min(DP[i], DP[i - coin] + 1);
                }
            }
        }

        if (DP[k] == Integer.MAX_VALUE - 1) {
            System.out.println(-1);
        } else {
            System.out.println(DP[k]);
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}