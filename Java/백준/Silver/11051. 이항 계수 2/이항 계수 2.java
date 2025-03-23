/**
 * 이항 계수 2
 * <p>
 * 문제 정리 (재귀)
 * 평범한 이항 계수 문제
 * nCk = n! / [(n - k)! * k!]
 */

import java.math.BigInteger;
import java.util.*;
import java.lang.*;
import java.io.*;

class Main {


    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        K = Math.min(K, N - K);     // 이항 계수 대칭성 이용.

        //== 해구하기
        BigInteger ans = BigInteger.ONE;
        BigInteger kPack = BigInteger.ONE;

        for (int i = 1; i <= K; i++) {
            ans = ans.multiply(BigInteger.valueOf(N - i + 1));
            kPack = kPack.multiply(BigInteger.valueOf(i));
        }

        System.out.println(ans.divide(kPack).mod(BigInteger.valueOf(10007)));
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}