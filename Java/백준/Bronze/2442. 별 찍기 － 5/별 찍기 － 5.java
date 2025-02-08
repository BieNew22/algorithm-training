/**
 * 별 찍기 - 5
 * <p>
 * 문제 정리
 * 간단한 별찍기.
 */

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        int nowStar = 1;

        for (int i = N; i > 0; i--) {
            sb.append(" ".repeat(i - 1)).append("*".repeat(nowStar)).append('\n');
            nowStar += 2;
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}