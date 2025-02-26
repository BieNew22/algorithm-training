/**
 * 그림 교환
 * <p>
 * 문제 정리 (DP)
 * 1번 노드에서 시작하여서 가격이 점점 증가하는 가장 긴 방문 길이를 구하는 문제.
 * 이 때 1 -> 2 -> 3 -> ...(A) 과 2 -> 1 -> 3 -> ...(B) 방문하는 경우
 * B에서 3이 x 원에 구매한 경우 x ~ 9 원으로 판매하는 것을 시도할 수 있음.
 * A에서 3이 k 원에 구매한 경우 k ~ 9 원으로 판매하는 것을 시도할 수 있음.
 * 여기서 1과 2를 방문한 것은 같고 max(x, k) ~ 9 원으로 판매하는 것을 시도하는 부분이 중복이 되고.
 * 최종적으로 3 입장에서는 1과 2를 방문하고 min(x, k) ~ 9 원으로 판매하는 것을 시도할 수 있음.
 * -- 당분간 DP는 없다. 너무 어렵다...
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    /**
     * log 로 거쳐서 seller 가 그림을 판매하는 것을 의미.
     *
     * @param log       과거에 그림을 소유했던 사람들.
     * @param seller    현재 판매자
     * @param DP        현재 log에 대하여 seller가 구매한 그림 가격
     * @param cost      모든 거래 기록들
     */
    void sells(int log, int seller, int[][] DP, int[][] cost) {
        for (int buyer = 0; buyer < cost.length; buyer++) {
            // 이미 한 번 구매한 buyer 인 경우 패스.
            // seller 의 구매가가 buyer 한테 판매가 보다 싼 경우 패스.
            if ((log & (1 << buyer)) > 0 || DP[log][seller] > cost[seller][buyer])
                continue;

            int newLog = log | (1 << buyer);

            // DP 갱신 의미 : buyer가 현재 경로에서 더 싸게 그림을 구매한 경우 발견
            // 과거 값이 k 이고 현재 값이 m이라고 할 때 (k > m)
            // 과거에 이미 k ~ 9 원 판매하는 것을 탐색 함.
            // m ~ k - 1 원에 판매하는 것을 탐색.
            if (DP[newLog][buyer] > cost[seller][buyer]) {
                DP[newLog][buyer] = cost[seller][buyer];
                sells(newLog, buyer, DP, cost);
            }
        }
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());    // 총 예술가 수.

        int[][] cost = new int[N][];                // 각 예술가의 그림 판매 현황.
        for (int i = 0; i < N; i++) {
            cost[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
        }

        // DP[A][B] : B의 구매가
        // A : 과거에 거친 예술자의 번호 (bit 단위로 저장)
        // B : 현재 보유자.
        int[][] DP = new int[1 << N][N];
        for (int[] line : DP) {
            Arrays.fill(line, Integer.MAX_VALUE);
        }

        DP[1][0] = 0;   // 초기 0번 예술가가 그림을 구매한 경우.

        //== 그림 판매하기.
        sells(1, 0, DP, cost);
        
        //== 정답구하기.
        int ans = 0;
        for (int log = 0; log < DP.length; log++) {
            for (int last = 0; last < N; last++) {
                if (DP[log][last] == Integer.MAX_VALUE)
                    continue;
                ans = Math.max(ans, Integer.bitCount(log));
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}