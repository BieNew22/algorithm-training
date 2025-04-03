/**
 * 귀납법
 * <p>
 * 문제 정리 (DP, BFS)
 * - 이를 만족 못 하는 수는 없지만, 혹시나 해서 Wrong proof에 대한 예외 처리 추가.
 */

import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {


    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());    // 테스트 케이스 개수

        int[] data = new int[N];    // 각 TC 양의 정수 k를 저장한 리스트

        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }

        int[] DP = new int[8000000];   // 각 숫자에 대하여 만들기 위한 최솟 개수.
        Arrays.fill(DP, Integer.MAX_VALUE - 1);
        DP[1] = 0;

        ArrayList<Integer> queue = new ArrayList<>();  // 방문해야하는 숫자
        queue.add(1);                               // 방문에 필요한 이동 횟수.
        int cnt = 1;

        //== 모든 수에 대하여 DP 구성하기. BFS 방식.
        while (!queue.isEmpty()) {
            ArrayList<Integer> next = new ArrayList<>();

            for (int k : queue) {
                if (k - 1 > 0 && DP[k - 1] > cnt) {
                    DP[k - 1] = cnt;
                    next.add(k - 1);
                }

                if (k * 2 < DP.length && DP[k * 2] > cnt) {
                    DP[k * 2] = cnt;
                    next.add(k * 2);
                }
            }

            cnt += 1;
            queue = next;
        }

        //== 해 출력하기.
        StringBuilder sb = new StringBuilder();

        for (int k: data) {
            if (DP[k] == Integer.MAX_VALUE - 1) {
                sb.append("Wrong proof!\n");
            } else {
                sb.append(DP[k]).append('\n');
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}