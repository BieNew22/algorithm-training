/**
 * 계단 수
 * <p>
 * 문제 정리 (DP)
 * - i 번째 자리에 k (1 <= k <= 9) 가 오려면 i - 1 번째 자리에는 k - 1 또는 k + 1 이여야 함.
 *      -- 따라서 i - 1 번째 자리에 k - 1 가능한 경우의 수 와 k + 1이 가능한 경우의 수의 합이
 *         i 번째 자리에 k 가 올 수 있는 경우의 수를 의미하게 됨.
 * - 일의 자리부터 하게 되면. 나중에 가장 높은 차수에서는 0이 올 수 없는 경우에 대한 별도의 처리 필요함.
 *      -- 따라서 가장 높은 차수에 대하여 시작하면 0에 대하여 별도의 처리 불필요함.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());    // 총 자리 수.

        int mod = 1000000000;   // 각 경우의 최대 값.
        int size = 1 << 10;     // DP 크기

        // DP[A][B]
        // A : 앞에서 부터 i 번째 숫자가 A 인 경우를 의미
        // B : i와 A 에서 사용한 숫자들을 나타냄. 이 때 10 bit을 사용하여 표현.
        int[][] DP = new int[10][size];
        for (int i = 1; i <= 9; i++)    // 첫 자리가 1 ~ 9으로 시작하는 숫자에 대하여 DP 구성.
            DP[i][1 << i] = 1;

        //== 해 구하기.
        for (int i = 1; i < N; i++) {
            // i : 현재 자리
            int[][] now = new int[10][size]; // 현재 자리에 대한 DP

            for (int num = 0; num < 10; num++) {
                for (int c = 0; c < size; c++) {
                    if (DP[num][c] == 0)
                        continue;

                    // 이전 길이에서 num으로 끝나고 c 로 채워진 경우가 존재 함.
                    // -> 현재 길이에 num - 1 or num + 1 으로 채울 수 있음.
                    if (num - 1 >= 0) {
                        now[num - 1][c | (1 << (num - 1))] += DP[num][c];
                        now[num - 1][c | (1 << (num - 1))] %= mod;
                    }

                    if (num + 1 <= 9) {
                        now[num + 1][c | (1 << (num + 1))] += DP[num][c];
                        now[num + 1][c | (1 << (num + 1))] %= mod;
                    }
                }
            }

            DP = now;
        }

        int ans = 0;
        for (int i = 0; i < 10; i++) {
            ans = (ans + DP[i][(1 << 10) - 1]) % mod;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}