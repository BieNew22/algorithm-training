/**
 * 크리스마스 트리
 * <p>
 * 문제 정리 (백트랙킹, 수학)
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    /**
     * n! 구하는 함수.
     */
    long pack(long n) {
        long res = 1L;

        while (n > 0) {
            res *= n;
            n -= 1;
        }

        return res;
    }

    /**
     * 트리에 장식을 채우는 함수.
     *
     * @param now   현재 채우는 레벨
     * @param max   최종 트리 레벨
     * @param R     현재 사용 가능한 빨간 장난감 개수
     * @param G     현재 사용 가능한 초록 장난감 개수
     * @param B     현재 사용 가능한 파란 장난감 개수
     * @return      현재 레벨에서 최종 레벨 까지 채우는 경우의 수.
     */
    long dfs(int now, int max, int R, int G, int B) {
        if (now == max) {
            return 1L;
        }

        long res = 0L;

        // 1개 종류로 채우는 경우.
        if (R >= now) {
            res += dfs(now + 1, max, R - now, G, B);
        }
        if (G >= now) {
            res += dfs(now + 1, max, R, G - now, B);
        }
        if (B >= now) {
            res += dfs(now + 1, max, R, G, B - now);
        }

        // 2개 종류로 채울 수 있는 경우.
        if (now % 2 == 0) {
            int need = now / 2; // 2종류로 채우려면 필요한 각 장난감의 개수

            // 이 때 중복을 포함한 순열의 개수.
            long cnt = pack(now);
            long div = pack(need);
            cnt /= (div * div);

            if (R >= need && G >= need) {
                res += dfs(now + 1, max, R - need, G - need, B) * cnt;
            }

            if (R >= need && B >= need) {
                res += dfs(now + 1, max, R - need, G, B - need) * cnt;
            }

            if (G >= need && B >= need) {
                res += dfs(now + 1, max, R, G - need, B - need) * cnt;
            }
        }

        // 3개 종류 채울 수 있는 경우.
        if (now % 3 == 0) {
            int need = now / 3; // 3종류로 채우려면 필요한 각 장난감의 개수.

            long cnt = pack(now);
            long div = pack(need);
            cnt /= (div * div * div);

            if (R >= need && G >= need && B >= need) {
                res += dfs(now + 1, max, R - need, G - need, B - need) * cnt;
            }
        }

        return res;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 트리의 크기

        // 각 장난감의 개수.
        int R = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        //== 해구하기.
        System.out.println(dfs(1, N + 1, R, G, B));
    }


    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}