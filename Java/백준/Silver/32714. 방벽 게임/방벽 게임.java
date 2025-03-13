/**
 * 방벽 게임
 * <p>
 * 문제 정리 (구현)
 * N이 4, 5인 게임을 해보면 최선의 경우가 되는 규칙을 찾을 수 있음.
 */

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if (N == 2) {
            System.out.println(1);
        } else if (N == 3) {
            System.out.println(3);
        } else {
            System.out.println(3 * N - 4);
        }

    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}