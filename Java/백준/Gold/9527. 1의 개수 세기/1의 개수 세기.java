/**
 * 1의 개수 세기
 *
 * 문제 정리
 * 규칙을 찾아서 하는 구간합 문제
 *
 * x 번째 수의 1 개수 계산하기 (4 ~ 8까지 해보기)
 * 4 : 0000, 0000, 0000, 0000, 0000, 0001, 0010, 0011, 0100 -> 5
 * 5 : 0000, 0000, 0000, 0000, 0001, 0010, 0011, 0100, 0101 -> 7
 * 6 : 0000, 0000, 0000, 0001, 0010, 0011, 0100, 0101, 0110 -> 9
 * 7 : 0000, 0000, 0001, 0010, 0011, 0100, 0101, 0110, 0111 -> 12
 * 8 : 0000, 0001, 0010, 0011, 0100, 0101, 0110, 0111, 1000 -> 13
 *
 * 규칙 찾기
 * f(x): 1 ~ x 까지 모든의 1 개수를 반환 (우리의 목표)
 * g(k): 1 << k 를 만들기위하여 등장하는 1 bit 수
 *      - f(x)에서 x의 k 번째 bit이 1이 되기 위하여 등장하는 모든 1의 수
 *      - 이는 1 ~ 2^k - 1 까지 등장하는 모든의 1 개수를 의미
 *
 * ==> * g(x)가 f(x)의 소단위의 업무 -> log(N)으로 해결 가능 함.
 *
 * x를 n 비트의 수로 가정할 때 f(x)는 다음과 같음.
 * f(x) =  모든 k(1 <= k <= n)에 대하여 다음을 계산하여 더 함.
 *  - k 번째 bit가 1 인 경우 아래 식을 통하여 계산
 *  - g(k) + x & (2^k - 1)
 *      - g(k) 의미 : x의 k 번째 bit이 활성화(1)이 되기 위하여 필요한 1의 개수
 *      - x & * (2^k - 1) 의미 : x의 k 번째 bit이 활성화(1) 된 후 등장하는 횟수
 *
 */

import java.lang.*;
import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Main {

    final HashMap<Long, Long> DP = new HashMap<>();

    /**
     * f(x) 함수
     *
     * @param num x에 해당
     * @return 1 ~ x 까지 총 등장하는 1의 개수
     */
    long getTotalOne(long num) {

        if (DP.containsKey(num))
            return DP.get(num);

        long res = 0;

        char[] data = Long.toBinaryString(num).toCharArray();
        for (int i = 0; i < data.length; i++) {
            if (data[i] == '0')
                continue;

            long bitMask = 1L << (data.length - i - 1);

            // g(x) 값은 f(x - 1) + 1 와 같음.
            // 즉, g(10000) = f(01111) + 1
            long gx = getTotalOne(bitMask - 1) + 1;

            long left = num & (bitMask - 1);

            res += gx + left;
        }

        DP.put(num, res);

        return res;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken()) - 1;
        long B = Long.parseLong(st.nextToken());

        DP.put(0L, 0L);
        DP.put(1L, 1L);

        System.out.println(getTotalOne(B) - getTotalOne(A));
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}