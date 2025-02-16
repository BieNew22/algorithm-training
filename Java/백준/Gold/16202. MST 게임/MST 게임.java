/**
 * MST 게임
 * <p>
 * 문제 정리
 * 기본적인 크루스칼 or 프림 알고리즘 문제
 * 주어진 데이터가 edge list 형식으로 주기에 크루스칼 선택.
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    /**
     * UNION & FIND
     *
     * @param child     부모를 탐색할 자식
     * @param parent    각 자식의 부모 정보
     * @return          해당 자식의 부모
     */
    int getParent(int child, int[] parent) {
        if (child == parent[child])
            return child;
        parent[child] = getParent(parent[child], parent);
        return parent[child];
    }

    /**
     * 찾은 MST의 비용을 리턴. 찾지 못할 시 0 리턴
     *
     * @param edges         모든 간선 정보
     * @param totalNode     총 정점의 개수
     * @return              MST 비용
     */
    int kruskal(Queue<int[]> edges, int totalNode) {
        int[] parent = new int[totalNode + 1];
        for (int i = 0; i < parent.length; i++)
            parent[i] = i;

        // res : MST 비용, count : MST 사용된 간선의 개수
        int res = 0, count = 0;
        for (int[] edge: edges) {
            int sParent = getParent(edge[0], parent);
            int eParent = getParent(edge[1], parent);

            if (sParent == eParent) {
                continue;
            }

            // 두 정점을 병합.
            parent[eParent] = sParent;

            // MST 구축
            count += 1;
            res += edge[2];
        }

        if (count + 1 != totalNode)
            return 0;
        return res;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        //== 입력 받기 및 초기화
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 정점의 개수
        int M = Integer.parseInt(st.nextToken());   // 간선의 개수
        int K = Integer.parseInt(st.nextToken());   // 게임 턴 수

        // 간선 정보를 저장. int[] { node1, node2, value }
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            queue.offer(new int[] {n1, n2, i + 1});
        }

        //== K 턴간 MST 구하기.
        StringBuilder sb = new StringBuilder();

        boolean canFind = true;     // MST 찾을 수 있는지 여부 나타냄.
        for (int i = 0; i < K; i++) {
            if (canFind) {
                int res = kruskal(queue, N);

                sb.append(res).append(" ");

                // 매 MST 구축할 때마다 queue의 첫 원소는 무조건 들어감.
                // 따라서 구축한 MST에서 최소 간선을 찾지 않아도 됨.
                if (!queue.isEmpty())
                    queue.poll();

                if (res == 0)
                    canFind = false;
            } else {
                sb.append("0 ");
            }

        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}