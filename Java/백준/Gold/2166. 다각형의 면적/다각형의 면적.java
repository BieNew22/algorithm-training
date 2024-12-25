//  다각형의 면적

import java.lang.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] dot = new int[N + 1][];
        int minY = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // dot[i] = [xLoc, yLoc]
            dot[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            minY = Math.min(minY, dot[i][1]);
        }
        // 마지막 점에서 시작점으로 가는 경로(벡터)를 추가
        dot[N] = dot[0];

        System.out.println(getAns(dot, minY));
    }

    private BigDecimal getAns(int[][] dot, int minY) {
        int[] before = dot[0];

        BigDecimal ans = BigDecimal.ZERO;   // 기본 기준이 도형이 시계 방향으로 만들어진다고 가정.
        for (int[] now: dot) {
            // y = minY, x = now[0], x = before[0], now 와 before 를 연결한 직선
            // 로 유계 된 영역 크기 구하기

            long difX = Math.abs(now[0] - before[0]);
            long difY1 = Math.min(now[1], before[1]) - minY;
            long difY2 = Math.abs(now[1] - before[1]);

            // 네모 부분
            BigDecimal square = BigDecimal.valueOf(difX * difY1);

            // 세모 부분
            BigDecimal triangle = BigDecimal.valueOf(difX * difY2 / 2.0);

            // 이전 점에 비하여
            if (now[0] >= before[0]) {
                // 이전 점에 비하여 x 방향으로 증가 -> 영역 크기 더하기
                ans = ans.add(square).add(triangle);
            } else {
                // 이전 점에 비하여 x 방향으로 감소 -> 영여 크기 빼기
                ans = ans.subtract(square).subtract(triangle);
            }

            before = now;
        }

        // 도형이 반 시계 방향으로 만들어진 경우
        ans = ans.abs();

        // 소수점 둘 째 자리에서 반 올림.
        return ans.setScale(1, RoundingMode.CEILING);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}