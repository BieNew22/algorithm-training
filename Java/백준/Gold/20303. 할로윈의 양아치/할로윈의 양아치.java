/**
 * 할로윈의 양아치
 * <p>
 * 문제 정리
 * Disjoint set + knapsack 문제
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    /**
     * 속한 그룹의 root(가장 작은) 번호 찾기
     *
     * @param child  찾을 번호
     * @param parent 그룹 정보
     * @return child 가 속한 그룹의 root
     */
    int getParent(int child, int[] parent) {
        if (child == parent[child])
            return child;

        parent[child] = getParent(parent[child], parent);

        return parent[child];
    }

    /**
     * knapsack 문제에 필요한 데이터 구하기
     *
     * @param N             총 아이의 수
     * @param candy         각 그룹이 받은 캔디 수
     * @param relations     각 아이의 관계를 나타냄.
     * @param groupSize     데이터 저장소 : 병합 후 각 그룹의 인원 수
     * @param groupCandy    데이터 저장소 : 병합 후 각 그룹의 사탕수
     */
    void getData(int N, int[] candy, ArrayList<int[]> relations,
                 ArrayList<Integer> groupSize, ArrayList<Integer> groupCandy)  {
        int[] parent = new int[N];        // 각 친구 그룹을 관리
        int[] countPeople = new int[N];   // 각 친구 그룹에 있는 인원 수
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            countPeople[i] = 1;
        }

        // 친구 그룹 구하기
        for (int i = 0; i < relations.size(); i++) {
            int a = relations.get(i)[0];
            int b = relations.get(i)[1];

            // 항상 a < b 를 유지하기 위함.
            // 이를 통하여 항상 번호가 작은 그룹에 병합 시키기 위함.
            if (a > b) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            int aParent = getParent(a, parent);
            int bParent = getParent(b, parent);

            if (aParent != bParent) {
                // 두 그룹을 병합 : b 그룹을 a 그룹에 병합 시킴.
                parent[bParent] = aParent;

                // 사탕 수 병합
                candy[aParent] += candy[bParent];
                candy[bParent] = 0;

                // 사람 수 병합
                countPeople[aParent] += countPeople[bParent];
                countPeople[bParent] = 0;
            }
        }

        // for debug
        // System.out.println("parent : " + Arrays.toString(parent));
        // System.out.println("candy  : " + Arrays.toString(candy));
        // System.out.println("c.p    : " + Arrays.toString(countPeople));

        // 병합 후 데이터 구하기
        for (int i = 0; i < N; i++) {
            if (countPeople[i] == 0)
                continue;
            groupSize.add(countPeople[i]);
            groupCandy.add(candy[i]);
        }
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());    // 거리에 있는 아이 수
        int M = Integer.parseInt(st.nextToken());    // 아이들의 관계수
        int K = Integer.parseInt(st.nextToken());    // 울음소리 공명하기 위한 최소 수

        // 각 그룹이 받은 캔디 수
        int[] candy = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        // 각 아이의 관계
        ArrayList<int[]> relations = new ArrayList<>();
        for (int i = 0; i < M; i++)
            relations.add(Arrays.stream(br.readLine().split(" "))
                    .mapToInt(a -> Integer.parseInt(a) - 1).toArray());

        // knapsack 데이터 구하기 : 각 친구 그룹의 크기 및 캔디수가 데이터에 해당 됨.
        ArrayList<Integer> groupSize = new ArrayList<>();
        ArrayList<Integer> groupCandy = new ArrayList<>();

        getData(N, candy, relations, groupSize, groupCandy);

        // 최종해 구하기
        // groupCandy max : 10,000 * 30,000
        // groupSize max : 30,000
        // -> 따라서 groupSize 기준으로 DP 진행

        int[] DP = new int[K + 1];

        for (int i = 0; i < groupSize.size(); i++) {
            int nowGroup = groupSize.get(i);
            int nowCandy = groupCandy.get(i);

            if (nowGroup > K)
                continue;

            for (int j = K; j >= nowGroup; j--) {
                DP[j] = Math.max(DP[j], nowCandy + DP[j - nowGroup]);
            }
        }

        System.out.println(DP[K - 1]);

    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}