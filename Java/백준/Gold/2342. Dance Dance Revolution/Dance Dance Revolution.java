/**
 * Dance Dance Revolution
 * <p>
 * 문제 정리
 * DP 문제.
 * 발모양의 경우의 수는 최대 25가지 -> O(N * 25) = O(N)
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    /**
     * 현재 발모양에서 특정 발을 옮기는 것.
     *
     * @param befValue  현재 발모양까지 만드는데 필요한 최소 힘
     * @param now       지금 발판번호를 밟았을 때 발모양에대한 DP
     * @param from      발 시작 위치
     * @param to        발 목표 위치
     * @param other     욺직이지 않는 발 위치
     * @param isLeft    왼발을 욺지는 것인가? T -> 왼발 이동 , F -> 오른발 이동
     */
    void moveStep(int befValue, int[][] now, int from, int to, int other, boolean isLeft) {
        int added = 0;

        if (from == to) {
            // 같은 위치 인 경우
            added = 1;
        } else if (from == 0) {
            // 시작 위치에 있는 경우.
            added = 2;
        } else if (from % 2 == to % 2) {
            // 반대 편으로 이동한 경우.
            added = 4;
        } else {
            // 인접 위치로 이동한 경우.
            added = 3;
        }

        if (isLeft) {
            // 왼발을 움직이는 경우.
            now[to][other] = Math.min(now[to][other], befValue + added);
        } else {
            // 오른발을 움직이는 경우.
            now[other][to] = Math.min(now[other][to], befValue + added);
        }
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());

        int INF = 1000000000;

        // DP : 현재 발모양에 도달 하는데 걸린 비용
        // int[l][r] : l(왼발 위치), r(오른발 위치)
        int[][] bef = new int[5][5];    // 이전 발판에 각 발 모양에 대한 DP
        int[][] now = new int[5][5];    // 현재 발판에 각 발 묘양에 대한 DP
        for (int i = 0; i < 5; i++) {
            Arrays.fill(bef[i], INF);
            Arrays.fill(now[i], INF);
        }

        bef[0][0] = 0;  // 초기 발모양 초기화.

        // DP 시작
        while (st.hasMoreTokens()) {
            // 지금 밟아야하는 발판 번호.
            int nowStep = Integer.parseInt(st.nextToken());

            if (nowStep == 0)
                break;

            for (int l = 0; l < 5; l++) {
                for (int r = 0; r < 5; r++) {
                    // 현재 발모양으로 도달한 경우가 없는 경우
                    if (bef[l][r] == INF)
                        continue;

                    // 현재 발모양에서 왼발을 옮기는 경우
                    moveStep(bef[l][r], now, l, nowStep, r, true);

                    // 현재 발모양에서 오른발을 옮기는 경우
                    moveStep(bef[l][r], now, r, nowStep, l, false);
                }
            }

            // 현재 발모양을 과거로 저장 및 현재를 다시 초기화.
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    bef[i][j] = now[i][j];
                    now[i][j] = INF;
                }
            }
        }

        // 최종해 구하기
        int ans = INF;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                ans = Math.min(ans, bef[i][j]);

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}