/**
 * 셀프 넘버
 * <p>
 * 문제 정리 (에라토스 테네스의 체 문제)
 */

import java.util.Arrays;


public class Main {

    int d(int n) {
        int res = n;

        while (n > 0) {
            res += n % 10;
            n /= 10;
        }

        return res;
    }

    void solve() {

        //== 초기화
        // isSelf[i] 는 i 가 셀프 넘버 여부를 나타냄.
        boolean[] isSelf = new boolean[10001];
        Arrays.fill(isSelf, true);

        //== 에라토스 테네스의 체 처럼 셀프 넘버가 아닌 수를 걸러 냄.
        for (int i = 1; i < isSelf.length; i++) {
            if (!isSelf[i])
                continue;

            int tmp = d(i);
            while (tmp < isSelf.length) {
                isSelf[tmp] = false;
                tmp = d(tmp);
            }
        }

        //== 출력하기
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < isSelf.length; i++) {
            if (isSelf[i]) {
                sb.append(i).append('\n');
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}