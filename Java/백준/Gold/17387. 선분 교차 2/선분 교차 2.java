/**
 * 선분 교차 2
 * <p>
 * 문제 정리
 * CCW 문제
 */

import java.lang.*;
import java.io.*;
import java.util.Arrays;


public class Main {

    long ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long res = (x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1);

        if (res > 0) return 1L;
        else if (res < 0) return -1L;

        return 0L;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // [x1, y1, x2, y2]
        long[] line1 = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong).toArray();
        long[] line2 = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong).toArray();

        long exp1 = ccw(line1[0], line1[1], line1[2], line1[3], line2[0], line2[1]);
        exp1 *= ccw(line1[0], line1[1], line1[2], line1[3], line2[2], line2[3]);
        long exp2 = ccw(line2[0], line2[1], line2[2], line2[3], line1[0], line1[1]);
        exp2 *= ccw(line2[0], line2[1], line2[2], line2[3], line1[2], line1[3]);

        int ans = 0;

        if (exp1 == 0 && exp2 == 0) {
            // case1 : -- 모양 (4 점이 한 직선상에 존재하는 경우
            // 해당 경우는 ccw(line1, d3) = 0 && ccw(line1, d4) = 0
            // 이 때 - - 모양이 있을 수 있으므로 점 기준으로 포함 여부를 확인 해야 함.

            if (Math.min(line1[0], line1[2]) <= Math.max(line2[0], line2[2])
                    && Math.min(line2[0], line2[2]) <= Math.max(line1[0], line1[2])
                    && Math.min(line1[1], line1[3]) <= Math.max(line2[1], line2[3])
                    && Math.min(line2[1], line2[3]) <= Math.max(line1[1], line1[3])) {
                ans = 1;
            }
        } else if (exp1 <= 0 && exp2 <= 0) {
            // case2 : +, ㄴ, ㅏ 모양
            // 해당 경우는 exp1 = ccw(line1, d3) * ccw(line1, d4) <= 0 이여야 함.
            // 이때 | - 모양에서 line1이 - 이면 exp1 <= 0 이므로. lin2 기준으로도 확인 해야 함.
            // 결론 : ccw(line1, d3) * ccw(line1, d4) <= 0 && ccw(line2, d1) * ccw(line2, d2) <= 0

            ans = 1;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}