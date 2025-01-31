/**
 * 점 숫자
 * <p>
 * 문제 정리 (수학 문제, 구현 문제)
 * 숫자 -> 좌표로 변환 : 두 좌표를 더하기 위함.
 * 좌표 -> 숫자로 변환 : 정답을 구하기 위 함.
 *
 * 좌표 <-> 숫자간 관계
 *  (x, y)에서 (1, y)에 대하여 각 y에 대하여 매칭 되는 숫자의 상관 관계는 다음과 같음.
 *  f(y) = v 이라고 했을 때
 *      - f(1)=1, f(2)=2=f(1) + 2 - 1, f(3)=4=f(2) + 3 - 1, f(4)=7=f(3) + 4 - 1 ...
 *      - * f(y + 1) = f(y) + y,
 *      - 따라서 (1, y)에 대하여 쉽게 구할 수 있음.
 *  임의의 (x, y)에 대하여 해당 (x, y)가 속한 (1, y)을 안다면
 *  (x, y)의 값은 f(y) + x - 1 이 됨.
 *
 *  1 + 2 + 3 + 4 ... n = n * (n + 1) / 2 >= 20000
 *  약 f(200) 까지 구하면 됨.
 */

import java.lang.*;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    /**
     * 숫자 -> 좌표로 변환
     *
     * @param num   변환할 숫자
     * @param f     f 함수
     * @return      변환된 좌표
     */
    int[] numToLoc(int num, int[] f) {
        int y = 0;

        // y 좌표 찾기
        while (f[++y] <= num);
        y -= 1;

        return new int[] {y - num + f[y], 1 + num - f[y]};
    }

    /**
     * 좌표 -> 숫자 변환
     * @param loc   좌표
     * @param f     f 함수
     * @return      변환된 좌표
     */
    int locToNum(int[] loc, int[] f) {
        return f[loc[0] + loc[1] - 1] + loc[1] - 1;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());   // 총 테스트 케이스 수.

        // (1, y)에 대하여 각 y에 대하여 매칭 되는 숫자를 저장
        int[] f = new int[300];
        f[1] = 1;
        for (int i = 1; i < f.length - 1; i++) {
            f[i + 1] = f[i] + i;
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());

            int[] a = numToLoc(Integer.parseInt(st.nextToken()), f);
            int[] b = numToLoc(Integer.parseInt(st.nextToken()), f);

            a[0] += b[0];
            a[1] += b[1];

            sb.append(locToNum(a, f)).append('\n');
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}