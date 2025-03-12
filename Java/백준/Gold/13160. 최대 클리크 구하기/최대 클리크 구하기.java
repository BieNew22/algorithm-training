/**
 * 최대 클리크 구하기
 * <p>
 * 문제 정리
 * x 축 상에 [s, e] 인 선분이 N개 주어질 때
 * x = k 이라는 수직선을 그었을 때 가장 많이 겹치는 선분의 개수및 선분 번호를 구하는 문제.
 *  - 이 때 모든 범위에서 할 필요 없고 각 선분의 시작 또는 종료점에 찍으면 됨.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {


    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());    // 간선의 개수

        int[][] lines = new int[N][];               // 각 간선의 정보
        int[][] dots = new int[N * 2][];           // 각 간선에서 지점의 정보
        for (int i = 0; i < N; i++) {
            lines[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            // 1 : 간선의 시작점을 의미, -1 : 간선의 종료 지점을 의미.
            dots[i * 2] = new int[] {lines[i][0], 1};
            dots[i * 2 + 1] = new int[] {lines[i][1] + 1, -1};
        }

        // 각 지점을 오름차순으로 정렬.
        Arrays.sort(dots, Comparator.comparingInt(o -> o[0]));

        //== 가장 큰 클리크 구하기.
        int ans = 0, k = 0;  // 최대 클리크를 구할 수 있는 k 와 그때 선분의 개수(ans)
        int count = 0;          // count : k에 수직선을 내렸을 때 겹치는 선분의 개수

        for (int[] dot: dots) {
            // 간선의 시작점에 찍은 경우 -> 새로운 간선 하나 포함됨.
            // 간선의 종료점 + 1에 찍은 경우 -> 무조건 간선 하나 제외됨.
            if (dot[1] > 0) {
                count += 1;
            } else {
                count -= 1;
            }

            if (count > ans) {
                ans = count;
                k = dot[0];
            }
        }

        //== 가장 큰 클리크에 들어가는 선분의 번호 구하기.
        StringBuilder sb = new StringBuilder();
        sb.append(ans).append('\n');

        for (int i = 0; i < N; i++) {
            if (lines[i][0] <= k && k <= lines[i][1]) {
                sb.append(i + 1).append(' ');
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}