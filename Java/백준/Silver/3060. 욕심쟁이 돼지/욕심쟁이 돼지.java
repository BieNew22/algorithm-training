/**
 * 욕심쟁이 돼지
 * <p>
 * 문제 정리
 * 시뮬레이션 문제
 */

import java.lang.*;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < TC; tc++) {
            int count = 1;

            long food = Long.parseLong(br.readLine());
            long[] pig = Arrays.stream(br.readLine().split(" "))
                    .mapToLong(Long::parseLong).toArray();
            long[] tmp = new long[6];

            while (true) {
                long sum = 0;
                for (int i = 0; i < 6; i++)
                    sum += pig[i];

                if (sum > food)
                    break;

                for (int i = 0; i < 6; i++) {
                    tmp[i] = pig[i] + pig[(6 + i - 1) % 6]; // 왼쪽에 앉은 돼지
                    tmp[i] += pig[(6 + i + 1) % 6]; // 오른쪽에 앉은 돼지
                    tmp[i] += pig[(6 + i + 3) % 6]; // 맞은 편에 앉은 돼지

                    sum += tmp[i];
                }

                System.arraycopy(tmp, 0, pig, 0, 6);
                count += 1;
            }
            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}