/**
 * 친구 네트워크
 * <p>
 * 문제 정리 (disjoint set, union-set)
 * 기본적인 union-set 문제에 문자열을 곁들인...
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    /**
     * find 함수
     * 
     * @param child     찾을 그룹의 대표.
     * @param parent    현재 그룹 상태..
     * @return          child의 그룹 대표 반환.
     */
    int getParent(int child, int[] parent) {
        if (child == parent[child])
            return child;
        parent[child] = getParent(parent[child], parent);
        return parent[child];
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int TC = Integer.parseInt(br.readLine());    // 테스트 케이스 개수.

        StringBuilder sb = new StringBuilder();

        //== 처리하기, Union & Find
        for (int tc = 0; tc < TC; tc++) {
            int N = Integer.parseInt(br.readLine());    // 친구 관계의 수.

            int[] count = new int[N * 2];   // 각 그룹의 크기
            Arrays.fill(count, 1);;

            int[] parent = new int[N * 2];  // 각 그룸을 의미.
            for (int i = 0; i < parent.length; i++)
                parent[i] = i;

            // key : 사용자 아이디
            // val : 사용자가 속한 그룹.
            HashMap<String, Integer> db = new HashMap<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String[] users = new String[] {st.nextToken(), st.nextToken()};

                // 각 사용자를 그룹에 추가.
                for (int j = 0; j < users.length; j++) {
                    if (!db.containsKey(users[j])) {
                        db.put(users[j], i * 2 + j);
                    }
                }

                // 각 사용자의 그룹 대표를 찾음.
                int aParent = getParent(db.get(users[0]), parent);
                int bParent = getParent(db.get(users[1]), parent);

                // 두 그룹 대표가 다르면 합치기.
                if (aParent != bParent) {
                    count[aParent] += count[bParent];
                    count[bParent] = 0;

                    parent[bParent] = aParent;
                }

                sb.append(count[aParent]).append('\n');
            }
        }

        System.out.println(sb);
    }


    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}