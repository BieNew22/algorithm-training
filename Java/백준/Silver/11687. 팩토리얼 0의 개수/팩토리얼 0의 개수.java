/**
 * 팩토리얼 0의 개수
 * <p>
 * 문제 정리
 * 끝 자리가 0이 n개 들어가려면 x!은 5 ** n 과 2 ** n 의 약수이여야 합니다.
 * 이 때 2의 약수의 개수는 5보다 무조건 많으므로 5 개수를 계산하면 됨.
 * -> 즉 최소 x는 무조건 5의 배수가 됨.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int M = Integer.parseInt(br.readLine());

        int cnt = 0;    // 현재 x!에서 5의 개수 계산.
        int power = 0;  // 최종 x가 5의 몇 배인지 나타냄.

        //== 처리하기.
        while (cnt < M) {
            // power 하나 증가는 5가 하나 추가 됨을 의미.
            power += 1;
            cnt += 1;

            // 단, power 가 5의 n 승인 경우 추가로 5가 n개 추가 됨을 의미합니다.
            int tmp = power;
            while (tmp > 0 && tmp % 5 == 0) {
                cnt += 1;
                tmp /= 5;
            }
        }

        if (cnt == M) {
            System.out.println((long) power * 5);
        } else {
            System.out.println(-1);
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}