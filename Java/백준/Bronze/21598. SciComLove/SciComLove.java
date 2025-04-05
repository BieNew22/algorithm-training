/**
 * SciComLove
 * <p>
 * 문제 정리
 * 단순 구현
 */

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++)
            sb.append("SciComLove\n");

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}