/**
 * 문제집
 * <p>
 * 문제 정리
 * 간단한 topological sort 문제
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {


    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 총 문개의 개수
        int M = Integer.parseInt(st.nextToken());   // 문제 상관 관계

        int[] inDegree = new int[N];    // 각 문제의 진입 차수.

        // 각 문제에 대하여 자신 보다 늦게 풀면 좋은 문제를 저장.
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        // 데이터 처리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;

            // s에 자신 보다 늦게 풀면 좋은 문제 e를 저장.
            // e에 자신 보다 먼저 풀면 좋은 문제 개수 추가.
            graph.get(s).add(e);
            inDegree[e] += 1;
        }

        // 풀수 있는 문제 번호를 난이도에 따라서 오름차순으로 저장.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            // minHeap 초기화 : 자신 보다 먼저 풀면 좋은 문제 개수가 0 인 데이터 추가.
            if (inDegree[i] == 0)
                minHeap.add(i);
        }

        // 정답 구하기
        StringBuilder sb = new StringBuilder();

        while (!minHeap.isEmpty()) {
            // 현재 풀 수 있는 문제 중에서 가장 쉬운 문제 풀기
            int now = minHeap.poll();
            sb.append(now + 1).append(" ");

            // 자신 보다 늦게 풀면 좋은 문제 notify 하기.
            for (int d: graph.get(now)) {
                inDegree[d] -= 1;

                if (inDegree[d] == 0)
                    minHeap.add(d);
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}