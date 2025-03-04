/**
 * 외판원 순회
 * <p>
 * 문제 정리 (DP)
 * well-known 문제
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    int BASE = -1;
    int INF = 20_000_000;

    /**
     * 이미 방문한 도시 기준, 현재 도시에서 남은 도시를 방문후 시작 도시로 돌아가는 데 드는 비용을 구함.
     * @param visited   외판원이 이미 방문한 도시를 모음
     * @param now       외판원이 현조 위치한 도시
     * @param start     외판원의 시작 도시
     * @param W         각 도시간 이동하는데 드는 비용
     * @param DP        이미 방문한 도시 기준 현재 도시에서 남은 도시를 방문할 때 드는 최소 비용을 저장.
     */
    void visit(int visited, int now, int start, int[][] W, int[][] DP) {
        if (visited == (1 << W.length) - 1) {
            // 모든 도시를 방문 한 경우 -> 현재 위치에서 다시 시작 위치로 이동
            if (W[now][start] != 0) {
                DP[visited][now] = W[now][start];
            } else {
                DP[visited][now] = INF;
            }
            return;
        }

        DP[visited][now] = INF;

        // 다음 지점으로 이동.
        for (int next = 0; next < W.length; next++) {
            // 이미 방문하였거나 현지 도시에서 이동할 수 없는 경우 패스
            if ((visited & (1 << next)) > 0 || W[now][next] == 0) {
                continue;
            }

            int next_visited = visited | (1 << next);

            // next에 대하여 남은 도시를 모두 방문할 때 드는 비용을 구하지 않은 경우. 구하기
            if (DP[next_visited][next] == BASE) {
                visit(next_visited, next, start, W, DP);
            }

            DP[visited][now] = Math.min(DP[visited][now], DP[next_visited][next] + W[now][next]);
        }
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());    // 도시의 수

        // W[A][B] : A -> B로 가는 비용을 의미
        // W[A][B] = 0 이면 A -> B로 이동할 수 없음을 의미
        int[][] W = new int[N][];
        for (int i = 0; i < N; i++)
            W[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

        // DP[A][B] : B는 마지막에 방문한 도시를 의미, A는 B까지 이동한 도시들을 의미.
        // 값 의미 : B에서 방문하지 않은 도시를 방문하고 다시 처음 도시로 돌아갈 때 필요한 최소 비용을 저장 함.
        int[][] DP = new int[1 << N][N];
        for (int i = 0; i < DP.length; i++)
            Arrays.fill(DP[i], BASE);

        //== 외판원 순회 시작 0 번 도시를 시작지점으로 설정
        visit(1, 0, 0, W, DP);

        System.out.println(DP[1][0]);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}