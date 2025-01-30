/**
 * 게시판
 * <p>
 * 문제 정리
 * 수학 문제
 */

import java.lang.*;
import java.io.*;
import java.util.StringTokenizer;


public class Main {


    /**
     * 두 직선간 겹치는 구간의 길이.
     *
     * @param d1    line1의 시작 위치
     * @param d2    line1의 종료 위치
     * @param d3    line2의 시작 위치
     * @param d4    line2의 종료 위치.
     * @return      겹치는 구간의 길이.
     */
    int getRange(int d1, int d2, int d3, int d4) {
        // 서로 겹치지 않는 경우.
        if (d2 <= d3 || d4 <= d1)
            return 0;

        // 서로 겹치는 경우.
        if (d1 <= d3 && d2 <= d4)
            return d2 - d3;

        if (d3 <= d1 && d4 <= d2)
            return d4 - d1;

        // 서로 포함하는 경우
        if (d1 <= d3)
            return d4 - d3;

        return d2 - d1;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x3 = Integer.parseInt(st.nextToken());
            int y3 = Integer.parseInt(st.nextToken());
            int x4 = Integer.parseInt(st.nextToken());
            int y4 = Integer.parseInt(st.nextToken());

            // 먼저 붙인 포스터의 크기
            int total = (x2 - x1) * (y2 - y1);
            // 두 포스터의 겹친 부분의 크기
            int clash = getRange(x1, x2, x3, x4) * getRange(y1, y2, y3, y4);

            sb.append(total - clash).append('\n');
        }

        System.out.println(sb);

    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}