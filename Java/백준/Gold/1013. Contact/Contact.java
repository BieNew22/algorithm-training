/**
 * Contact
 * <p>
 * 문제 정리
 * 정규표현식 문제
 */

import java.lang.*;
import java.io.*;


public class Main {

    /**
     * 재귀로 주어진 데이터의 패턴 확인
     *
     * @param idx       현재까지 확인 한 index
     * @param data      현재 데이터
     * @return          패턴 참/거짓 리턴
     */
    boolean checkPattern(int idx, char[] data) {
        if (idx == data.length)
            return true;
        // 01 패턴 인 경우.
        if (data[idx] == '0') {
            if (idx + 1 < data.length && data[idx + 1] == '1') {
                return checkPattern(idx + 2, data);
            } else {
                return false;
            }
        } else {
            // 100+ 부분 확인
            int countZero = 0;

            while(idx + 1 < data.length && data[idx + 1] == '0') {
                idx += 1;
                countZero += 1;
            }

            if (countZero < 2)
                return false;

            // 1+ 부분 확인
            int countOne = 0;
            while (idx + 1 < data.length && data[idx + 1] == '1') {
                idx += 1;
                countOne += 1;
            }

            if (countOne == 0)
                return false;
            else if (countOne == 1) {
                return checkPattern(idx + 1, data);
            } else {
                // 100011110?1 경우로
                // ?가 1인 경우 : 10001111 01       case 1
                // ?가 0인 경우 : 1000111 1001      case 2
                // 두 가지 경우의 수가 존재.

                boolean res = false;

                // case 1
                res |= checkPattern(idx, data);
                // case 2
                res |= checkPattern(idx + 1, data);

                return res;
            }
        }
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < TC; tc++) {
            char[] data = br.readLine().toCharArray();

            if (checkPattern(0, data)) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}