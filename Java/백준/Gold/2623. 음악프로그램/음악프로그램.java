/**
 * 음악프로그램
 * <p>
 * 문제 정리
 * topological sort 문제
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 가수의 수 N
        int M = Integer.parseInt(st.nextToken());   // PD의 수 M

        // 각 가수의 진입 차수
        int[] inDegree = new int[N];

        // adjList.get(i) : i 번째 가수 보다 뒤에 서야하는 학생 번호 List
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++)
            adjList.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            // data[0] : 담당 가수의 수 = data.length - 1
            int[] data = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(a -> Integer.parseInt(a) - 1).toArray();

            for (int j = 1; j < data.length - 1; j++) {
                int a = data[j];
                int b = data[j + 1];

                inDegree[b] += 1;
                adjList.get(a).add(b);
            }
        }

        // topological sort 시작
        Queue<Integer> queue= new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int now = queue.poll();
            count += 1;

            sb.append(now + 1).append("\n");

            for (int next: adjList.get(now)) {
                inDegree[next] -= 1;

                if (inDegree[next] == 0)
                    queue.offer(next);
            }
        }

        if (count == N) {
            System.out.println(sb);
        } else {
            System.out.println(0);
        }

    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}