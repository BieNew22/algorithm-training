/**
 * 하노이 탑 이동 순서
 * <p>
 * 문제 정리 (재귀)
 * - 유명한 재귀 문제
 * hanoi(n, a, b, c)
 *  - 함수 정의 : n 개의 디스크가 a에 있고 b를 중간 다리를 통하여 c로 이동하고 싶어.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    void hanoi(int N, int now, int tmp, int goal, StringBuilder sb) {
        if (N <= 0)
            return;

        // now 에 있는 1 ~ N - 1 개의 디스크를 우선 tmp 으로 이동 시킴.
        hanoi(N - 1, now, goal, tmp, sb);

        // N 번째 원판을 목표로 이동 시킴.
        sb.append(now).append(' ').append(goal).append('\n');

        // tmp 에 있는 1 ~ N - 1 개의 디스크를 goal 으로 이동 시킴.
        hanoi(N - 1, tmp, now, goal, sb);
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());    // 원판의 개수

        StringBuilder sb = new StringBuilder();

        //== 재귀 처리하기.
        hanoi(N, 1, 2, 3, sb);

        System.out.println((int)Math.pow(2, N) - 1);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}