/**
 * 게임 개발
 * <p>
 * 문제 정리 (위상 정렬, DP)
 * - 위상 정렬 -> 각 건물이 언제 지을 수 있는 확인
 * - DP -> 각 건물이 지을 수 있는 시간
 */

import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {


    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());    // 건물의 종류 수.

        // 각 건물의 생성 후 해금되는 건물 정보 저장.
        ArrayList<Set<Integer>> afterMe = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            afterMe.add(new HashSet<>());
        }

        int[] inDegree = new int[N];    // 각 건물의 진입 차수.
        int[] cost = new int[N];        // 각 건물의 건설 비용.

        // 중간 : 내가 건설하기 위하여 마지막에 건설 종료된 건물의 건설 시간.
        // 최종 : 내가 건설하기 위하여 필요한 건설 시간.
        int[] DP = new int[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            // 건설 비용 초기화.
            cost[i] = Integer.parseInt(st.nextToken());

            // 진입 차수 및 afterMe 초기화.
            int val;
            while ((val = Integer.parseInt(st.nextToken())) != -1) {
                afterMe.get(val - 1).add(i);
                inDegree[i] += 1;
            }
        }

        // 현재 건설 가능 한 건물들.
        ArrayList<Integer> able = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                able.add(i);
            }
        }

        //== 각 건물 짓기.
        while (!able.isEmpty()) {
            ArrayList<Integer> next = new ArrayList<>();

            for (int build: able) {
                // 현재 건물을 건설 하므로 최종 시간을 나로 설정.
                DP[build] += cost[build];

                for (int nextBuild: afterMe.get(build)) {
                    inDegree[nextBuild] -= 1;
                    DP[nextBuild] = Math.max(DP[nextBuild], DP[build]);

                    if (inDegree[nextBuild] == 0) {
                        next.add(nextBuild);
                    }
                }
            }

            able = next;
        }

        //== 최종 출력.
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(DP[i]).append('\n');
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}