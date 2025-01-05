/**
 * RGB거리 2
 * <p>
 * 문제 정리
 * DP (N이 1번와 달라야 해서 1번 선택한 번호에 대한 DP가 필요)
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.Arrays;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] data = new int[N][];
        for (int i = 0; i < N; i++)
            data[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

        int INF = Integer.MAX_VALUE;

        // 각각 1번째 집이 R, G, B 을 칠 했을 때 경우에 대한 DP
        int[] R = {INF, data[0][0] + data[1][1], data[0][0] + data[1][2]};
        int[] G = {data[0][1] + data[1][0], INF, data[0][1] + data[1][2]};
        int[] B = {data[0][2] + data[1][0], data[0][2] + data[1][1], INF};

        for (int i = 2; i < N; i++) {
            int[] R2 = {Math.min(R[1], R[2]) + data[i][0],
                    Math.min(R[0], R[2]) + data[i][1],
                    Math.min(R[0], R[1]) + data[i][2]};
            int[] G2 = {Math.min(G[1], G[2]) + data[i][0],
                    Math.min(G[0], G[2]) + data[i][1],
                    Math.min(G[0], G[1]) + data[i][2]};
            int[] B2 = {Math.min(B[1], B[2]) + data[i][0],
                    Math.min(B[0], B[2]) + data[i][1],
                    Math.min(B[0], B[1]) + data[i][2]};

            System.arraycopy(R2, 0, R, 0, 3);
            System.arraycopy(G2, 0, G, 0, 3);
            System.arraycopy(B2, 0, B, 0, 3);
        }

        int minR = Math.min(R[1], R[2]);
        int minG = Math.min(G[0], G[2]);
        int minB = Math.min(B[0], B[1]);

        System.out.println(Math.min(Math.min(minR, minG), minB));
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}