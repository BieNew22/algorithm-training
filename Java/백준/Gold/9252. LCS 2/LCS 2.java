/**
 * LCS 2
 * <p>
 * 문제 정리
 * 기본적인 LCS 문제
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.Stack;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] seqA = br.readLine().toCharArray();
        char[] seqB = br.readLine().toCharArray();

        // DP[i][j]에 seqA[i] 과 seqB[j] 까지 LCS 기록
        int[][] DP = new int[seqA.length + 1][seqB.length + 1];

        for (int i = 0; i < seqA.length; i++) {
            for (int j = 0; j < seqB.length; j++) {
                if (seqA[i] == seqB[j]) {
                    DP[i + 1][j + 1] = DP[i][j] + 1;
                } else {
                    DP[i + 1][j + 1] = Math.max(DP[i][j + 1], DP[i + 1][j]);
                }
            }
        }

        // 거꾸로 올라가면서 lcs 문자열 구하지
        Stack<Character> lcs = new Stack<>();

        int y = seqA.length, x = seqB.length;
        while (y != 0 && x != 0) {
            if (DP[y][x] > DP[y - 1][x] && DP[y][x] > DP[y][x - 1]) {
                lcs.push(seqA[y - 1]);
                y -= 1;
                x -= 1;
            } else if (DP[y][x] == DP[y - 1][x]) {
                y -= 1;
            } else {
                x -= 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(lcs.size()).append("\n");
        while (!lcs.isEmpty())
            sb.append(lcs.pop());
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}