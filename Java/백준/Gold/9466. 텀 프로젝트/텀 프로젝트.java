/**
 * 텀 프로젝트
 * <p>
 * 문제 정리
 * 사이클을 찾는 문제
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.*;


public class Main {

    /**
     * 사이클을 찾기 및 사이즈 구하는 함수
     * 
     * @param start 시작 노드
     * @param graph 그래프 정보
     * @param visit 방문 기록
     * @return 사이클 크기 (사이클에 포함되어 있는 노드의 개수)
     */
    int findCycle(int start, int[] graph, boolean[] visit) {
        Stack<Integer> visitStack = new Stack<>();

        // 현재 탐색에 루프의 유무를 판단하기 위함.
        visitStack.add(-1);

        do {
            visitStack.add(start);
            visit[start] = true;

            start = graph[start];

        } while (!visit[start]);

        int cycleSize = 0;

        // 마지막에 방문한 start 가 이전에 탐색한 노드인 경우
        // visitStack이 빌 때 까지 루프를 돌게됨.
        while (!visitStack.isEmpty()) {
            int v = visitStack.pop();

            cycleSize += 1;

            if (v == start)
                break;
        }

        if (visitStack.isEmpty()) {
            return 0;
        }
        return cycleSize;

    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < TC; tc++) {
            // 입력 받기
            int N = Integer.parseInt(br.readLine());
            int[] graph = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(a -> Integer.parseInt(a) - 1).toArray();

            // 각 번호에 대하여 방문 여부를 확인 용
            // 한 번 방문한 번호에 대하여 다시 방문할 일이 없음.
            boolean[] visit = new boolean[N];

            // 각 번호로 시작하여 사이클 탐지 및 크기(사이클 속 노드 개수) 구하기
            int totalCycle = 0;
            for (int i = 0; i < N; i++) {
                if (visit[i])
                    continue;

                totalCycle += findCycle(i, graph, visit);
            }

            sb.append(N - totalCycle).append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}