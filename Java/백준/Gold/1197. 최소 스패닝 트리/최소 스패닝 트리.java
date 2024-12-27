//  최소 스패닝 트리
//  문제 정리
//  - 기본적인 크루스칼, 프림 알고리즘 문제

import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {

    /**
     * union&find 에서 find 해당
     *
     * @param node 검색할 노드
     * @param parent 각 노드의 부모 노드를 저장한 테이블
     * @return 검색한 노드의 부모 노드
     */
    int findParent(int node, int[] parent) {
        if (node == parent[node])
            return node;
        parent[node] = findParent(parent[node], parent);

        return parent[node];
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 각 정점의 부모를 가리 키는 테이블. (union & find)
        int[] parent = new int[V + 1];
        for (int i = 1; i <= V; i++)
            parent[i] = i;

        // 간선의 가중치가 낮은 거 순 저장.
        // 간선 정보 : int[] = [vertex1(s), vertex2(e), value]
        PriorityQueue<int[]> edges = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        for (int i = 0; i < E; i++) {
            edges.add(Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray());
        }

        int ans = 0;

        // 크루스칼 알고리즘
        while (!edges.isEmpty()) {
            // 현재 간선
            int[] edge = edges.poll();

            int sParent = findParent(edge[0], parent);
            int eParent = findParent(edge[1], parent);

            if (sParent == eParent)
                continue;

            ans += edge[2];

            // 두 정점 union
            parent[sParent] = eParent;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}