/**
 * 이상한 곱셈
 * <p>
 * 문제 정리 (구현)
 */

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] num1 = st.nextToken().toCharArray();
        char[] num2 = st.nextToken().toCharArray();

        //== 구현
        long ans = 0;
        for (char a: num1) {
            for(char b: num2) {
                ans += (a - '0') * (b - '0');
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}