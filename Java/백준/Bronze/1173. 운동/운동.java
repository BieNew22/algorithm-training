/**
 * 운동
 *
 * 문제 정리
 * 간단한 시뮬레이션 문제
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int ans = 0, work = 0, now = m;

        if (m + T > M) {
            ans = -1;
        } else {
            while (work < N) {
                if (now + T <= M) {
                    now += T;
                    work += 1;
                } else {
                    now = Math.max(now - R, m);
                }
                ans += 1;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}