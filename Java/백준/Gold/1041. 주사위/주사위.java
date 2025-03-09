/**
 * 주사위
 * <p>
 * 문제 정리 (구현 문제)
 * - 주사위에서 1개 면을 선택했을 때 가장 작은 값
 * - 주사위에서 한 번에 볼 수 있는 2개 면을 선택했을 때 가장 작은 값
 * - 주사위에서 한 번에 볼 수 있는 3개 면을 선택했을 때 가장 작은 값
 * - 한 번에 볼 수 있는 면 기준 맞은 편에 있는 면은 볼 수 없음
 *      - A를 보고 있으면 F를 절대 볼 수 없음.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {


    /**
     * 주사위에서 보일 수 있는 x 개의 면을 선택하여 가장 작은 값을 구함.
     *
     * @param now       현재 선택한 면의 그룹. (bit mask)
     * @param max       턴택할 면의 개수
     * @param group     각 마주보는 주사위 그룹
     * @param dice      각 주사위 면에 적혀있는 숫자
     * @param value     현재 선택한 값.
     * @return          최종 최솟값.
     */
    int dfs(int now, int max, int[][] group, int[] dice, int value) {
        if (Integer.bitCount(now) == max) {
            return value;
        }

        int res = Integer.MAX_VALUE;

        for (int i = 0; i < group.length; i++) {
            // 해당 그룹을 이미 선택했으면 패스.
            if ((now & (1 << i)) > 0)
                continue;

            // 해당 그룹 중에서 하나의 주사위를 선택
            for (int j = 0; j < group[i].length; j++) {
                int tmp = dfs(now | 1 << i, max, group, dice, value + dice[group[i][j]]);
                res = Math.min(res, tmp);
            }
        }

        return res;
    }


    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());    // 정육면체의 한 변을 구성하는 주사위 개수

        // 주사위의 각 면에 적형 있는 수 : A, B, C, D, E, F
        // A <-> F, B <-> E, C <-> D
        int[] dice = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int[][] group = {{0, 5}, {1, 4}, {2, 3}};   // 주사위의 마주보는 면을 하나로 그룹으로 묶음.

        int minThree = dfs(0, 3, group, dice, 0);   // 면 3개 선택했을 때 최솟값.
        int minTwo = dfs(0, 2, group, dice, 0);     // 면 2개 선택했을 때 최솟값.
        int minOne = Arrays.stream(dice).min().orElse(0);     // 면 1개 선택했을 때 최솟값.

        //== 해구하기.
        long ans = 0;

        if (N == 1) {
            ans = Arrays.stream(dice).sum() - Arrays.stream(dice).max().orElse(0);
        } else if (N == 2) {
            // 1개 면: X, 2개 면: 4, 3개 면: 4
            ans = 4L * minTwo + 4L * minThree;
        } else {
            // 1개 면: (n - 2) * (n - 2) + (n - 2) * (n - 1) * 4 = (n - 2) * (5 * n - 6)
            ans += (long) (N - 2) * (5L * N - 6) * minOne;   // 한 개 면만 보여지는 주사위 개수
            // 2개 면: (N - 1) * 4 + (N - 2) * 4 = (2 * N - 3) * 4
            ans += (2L * N - 3) * 4 * minTwo;
            // 3개 면: 4
            ans += (long) 4 * minThree;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}