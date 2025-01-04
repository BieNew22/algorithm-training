/**
 * 도시 분할 계획
 * <p>
 * 문제 정리
 * 스패닝트리 구하고, 가장 큰 가중치를 가지는 간선 제거하는 문제
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    int getParent(int node, int[] parent) {
        if (node == parent[node])
            return node;
        parent[node] = getParent(parent[node], parent);
        return parent[node];
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 집의 개수
        int M = Integer.parseInt(st.nextToken());   // 길의 개수

        // int[] = [v1, v2, cost] 길의 비용 작은 순
        PriorityQueue<int[]> nodeHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

        for (int i = 0; i < M; i++) {
            nodeHeap.add(Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray());
        }

        // union & find 용
        int[] parent = new int[N + 1];
        for (int i = 0; i <= N; i++)
            parent[i] = i;

        int ans = 0, max = 0;

        while (!nodeHeap.isEmpty()) {
            int[] now = nodeHeap.poll();

            // find
            int v1Parent = getParent(now[0], parent);
            int v2Parent = getParent(now[1], parent);

            if (v1Parent == v2Parent)
                continue;

            // union
            parent[v1Parent] = v2Parent;

            // 마을에 하나 이상의 집이 존재 하면 됨.
            // 스패닝트리에서 가장 유지 비용을 차지하는 도로를 제거하면 됨.
            ans += now[2];
            max = Math.max(now[2], max);
        }

        System.out.println(ans - max);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}