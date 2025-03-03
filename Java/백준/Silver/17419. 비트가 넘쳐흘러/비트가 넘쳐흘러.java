/**
 * 비트가 넘쳐흘러
 * <p>
 * 문제 정리 (해석 문제)
 * K = K-(K&((~K)+1)) 해당 연산이 궁극적으로 무엇을 하는 지 해석 하는 문제.
 * - A(K) = (~K) + 1 : K의 처음에 등장하는 1기준으로 왼쪽은 bit flip을 하고 오른쪽은 그대로 냅둠.
 *      - K(111000100) -> A(K) = 000111100
 * - B(K) = K & A(K) : A를 실행한 결과에서 K와 AND 연산을 수행.
 *      - 결론 : K의 처음에 등장하는 1 만 1이고 나머지는 다 0으로 바꿈.
 * - 결론 - 해당 식은 한 번 실행할 때 마다 K의 가장 처음에 등장하는 1을 지우는 연산이 됨.
 */

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력받기 및 초기화
        int N = Integer.parseInt(br.readLine());    // 자리수
        char[] data = br.readLine().toCharArray();  // N 자리의 이진수

        //== 해구하기
        int ans = 0;
        for (char c: data) {
            ans += c - '0';
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}