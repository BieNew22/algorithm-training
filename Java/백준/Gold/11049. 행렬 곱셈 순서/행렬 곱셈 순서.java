/**
 * 행렬 곱셈 순서
 * <p>
 * 문제 정리 (DP 문제)
 * N개의 행열의 곱셈 연산의 수에 어느 두 행열을 먼저 곱하든 최종적으로 반드시
 *  - 1 ~ k 곱한 행열과 k + 1 ~ N 곱한 행열을 곱해야 함. (1 <= k <= N)
 * 즉, 1 ~ k 곱한 행열의 최소 연산 수를 알고, k + 1 ~ N 곱한 행열의 최소 연산 수를 알면 됨.
 */

import java.lang.*;
import java.io.*;
import java.util.Arrays;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());    // 행열의 개수

        int[][] As = new int[N][];            // 각 행열의 크기를 저장
        for (int i = 0; i < N; i++) {
            As[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        int[][] DP = new int[N][N]; // DP[i][j] 는 i ~ j 번째 행열을 곱했을 때 최소 크기를 의미. (i <= j);

        //== DP 구성하기.
        int t = 1;
        for (int j = 0; j < N; j++) {
            for (int i = j - 1; i >= 0; i--) {
                // i ~ j 번째 행열의 곱하는데 필요한 최소 연산의 수 구하기.
                // i에 대하여 top-down 방식 이유 : 0 ~ j를 구하기 위하여서는 1 ~ j를 알아야하기 때문에.

                DP[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    // i ~ k 곱한 행열과 k + 1 ~ N 곱한 행열을 곱한 경우. (i <= k < j)
                    DP[i][j] = Math.min(DP[i][j],
                            DP[i][k] + DP[k + 1][j] + As[i][0] * As[k][1] * As[j][1]);
                }
            }
        }

        System.out.println(DP[0][N - 1]);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}