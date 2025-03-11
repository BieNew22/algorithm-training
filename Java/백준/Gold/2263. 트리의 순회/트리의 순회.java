/**
 * 트리의 순회
 * <p>
 * 문제 정리
 * in-order 구조 : 왼쪽 서브트리 , root , 오른쪽 서브트리.
 * pos-torder 구조 : 왼쪽 서브트리, 오른쪽 서브트리, root
 * - 해당 정보를 이용하여 재귀적으로 구현.
 * - root 기준 : 왼쪽 서브트리 원소 개수 x, 오른쪽 서브트리 원소 개수 k 라고 하면
 *  - in-order   : x, root, k
 *  - post-order : x, k,    root
 *      - in-order에서 root의 위치를 알면 좌/우 서브트리의 원소 개수를 얻을 수 있음.
 *      - 따라서 post-order에서 좌/우 서브 트리 구분이 가능 함.
 *      
 * - 같은 서브트리에 대하여 in-order에서의 위치와 post-order에서의 위치가 다름을 너무 늦에 발견 함...
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());
        int[] inOrder = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int[] postOrder = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        // in-order 에서 각 원소의 위치를 저장.
        int[] inPosition = new int[N + 1];
        for (int i = 0; i < N; i++) {
            inPosition[inOrder[i]] = i;
        }

        // int[4] = {in-order subtree start index, end index, post-order subtree start index, end index}
        // subtree index : 현재 서브 트리의 구간을 각각 in-order 과 post-order 에서 구간을 나타냄.
        Stack<int[]> stack = new Stack<>(); // pre-order 위하여 dfs 로 탐색하지 위함.
        stack.add(new int[] {0, N - 1, 0, N -  1});

        //== 트리구하기.
        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            int[] now = stack.pop();

            // 더 이상 서브트리가 없는 경우.
            if (now[0] > now[1] || now[2] > now[3])
                continue;

            int root = postOrder[now[3]];   // root 노드 값.
            sb.append(root).append(' ');    // root 노드를 출력.

            // 현재 root 기준 왼쪽 서브 트리의 노드 개수 구하기.
            int leftCount = inPosition[root] - now[0];

            // 오른쪽 -> 왼쪽 서브트리 순으로 stack 삽입.
            stack.add(new int[] {inPosition[root] + 1, now[1], now[2] + leftCount, now[3] - 1});
            stack.add(new int[] {now[0], inPosition[root] - 1, now[2], now[2] + leftCount - 1});

        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}