/**
 * 제곱 ㄴㄴ 수
 * <p>
 * 문제 정리 (수학, 에라토스테네스의 체)
 * - 결과적으로 보면 알아야하는 최대 범위는 1,000,000 개의 숫자.
 *
 * - x의 제곱수의 1 -> k 배로 탐색하면 최소 값이 MAX(1,000,000,000,000) 경우 MAX / x 만큼 연산이 필요함.
 *      - 최소 값 / x 으로 빠르게 접근 가능 함.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());

        long s = Long.parseLong(st.nextToken());    // 시작 숫자
        long e = Long.parseLong(st.nextToken());    // 끝 숫자

        // s ~ e 사이 숫자에 대하여 제곱 ㄴㄴ 수 여부를 나타냄.
        boolean[] isSquareNum = new boolean[(int) (e - s + 1)];

        long num = 2, square = 4;   // 현재 제외할 제곱수의 배수
        long ans = e - s + 1;       // 총 제곱 ㄴㄴ 수 개수

        //== 제곱 ㄴㄴ 수 구하기
        while (square <= e) {
            long now = (s / square) * square;   // s 보다 작거나 같으면서 가장큰 square의 배수.
            
            // 에라토스테네스의 체 방법으로 범위내 square의 배수를 제거.
            while (now <= e) {
                if (now >= s) {
                    if (!isSquareNum[(int) (now - s)]) {
                        isSquareNum[(int) (now - s)] = true;
                        ans -= 1;
                    }
                }
                now += square;
            }

            num += 1;
            square = num * num;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}