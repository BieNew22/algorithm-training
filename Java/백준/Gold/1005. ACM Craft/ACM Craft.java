/**
 * ACM Craft
 * <p>
 * 문제 정리
 * topological sort 문제
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] cost = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
            ArrayList<ArrayList<Integer>> reverseAdjList = new ArrayList<>();
            int[] inDegree = new int[N];
            for (int i = 0; i < N; i++) {
                adjList.add(new ArrayList<>());
                reverseAdjList.add(new ArrayList<>());
            }

            for (int i = 0; i < K; i++) {
                // data[0]를 지어야 data[1]를 지을 수 있음.
                int[] data = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(a -> Integer.parseInt(a) - 1).toArray();

                adjList.get(data[0]).add(data[1]);
                reverseAdjList.get(data[1]).add(data[0]);
                inDegree[data[1]] += 1;
            }

            int root = Integer.parseInt(br.readLine()) - 1;
            boolean find = false;
            // 현재 동시에 건설 중인 노드의 집합
            ArrayList<Integer> now = new ArrayList<>();

            // 처음 상태 초기화
            for (int i = 0; i < N; i++) {
                if (inDegree[i] == 0) {
                    now.add(i);

                    if (i == root)
                        find = true;
                }
            }

            while (!now.isEmpty() && !find) {
                ArrayList<Integer> next = new ArrayList<>();

                for (int v: now) {
                    // v : 현재 건설 중인 노드
                    for (int nextV: adjList.get(v)) {
                        // nextV : v가 완료 되면 건설할 수 있는 노드

                        inDegree[nextV] -= 1;

                        // inDegree 가 0이 아니면 다른 v 도 완료 해야
                        if (inDegree[nextV] == 0) {
                            next.add(nextV);

                            // nextV 가 건설 까지 걸린 시간 구하기
                            int max = 0;
                            for (int complete: reverseAdjList.get(nextV)) {
                                max = Math.max(cost[complete], max);
                            }
                            cost[nextV] += max;

                            if (nextV == root) {
                                find = true;
                                break;
                            }
                        }
                    }
                }

                now = next;
            }

            sb.append(cost[root]).append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}