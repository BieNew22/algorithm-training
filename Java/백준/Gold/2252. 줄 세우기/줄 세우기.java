/**
 * 줄 세우기
 * <p>
 * 문제 정리
 * 기본적인 topological sort 문제
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 학생 수
        int M = Integer.parseInt(st.nextToken());   // 학생 대소 관계 수

        // 각 학생의 진입 차수
        int[] inDegree = new int[N];

        // adjList.get(i) : i 는 학생이 보다 뒤에 서야하는 학생 번호
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++)
            adjList.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;

            inDegree[B] += 1;
            adjList.get(A).add(B);
        }

        // topological sort 수행

        // 진입 차수가 0인 학생 번호들을 저장.
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }

        // ans 구하기
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int now = queue.poll();

            sb.append(now + 1).append(" ");

            for (int next: adjList.get(now)) {
                inDegree[next] -= 1;

                if (inDegree[next] == 0)
                    queue.offer(next);
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}