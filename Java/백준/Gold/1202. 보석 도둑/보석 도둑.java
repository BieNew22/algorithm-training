/**
 * 보석 도둑
 * <p>
 * 문제 정리
 * 그리디 & 힙 문제
 * 그리디 : 용량이 작은 가방 부터 넣을 수 있는 가장 가격이 높은 보석을 넣으면 됨.
 *
 * 용량이 Wa 인 가방의 경우 넣을 보석 찾는 방법
 *  - 0 ~ Wa 인 보석 중에서 가격이 가장 높은 것을 선택
 * 용량이 Wb (Wb > Wa) 인 가방의 경우 넣을 보석 찾는 방법
 *  - 0 ~ Wb 인 보석 중에서 가격이 가장 높은 것을 선택
 *  - 이 때 0 ~ Wb를 탐색할 때 0 ~ Wa를
 *  - 이미 탐색 했으므로 Wa + 1 ~ Wb 만 탐색하면 됨.
 *      -> Wa + 1 ~ Wb 만 탐색하려면 기존 0 ~ Wa 보석에 대한 가격 순위를 알아야 함.
 *      -> 힙을 사용하면 해결 됨.
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 보석의 개수
        int K = Integer.parseInt(st.nextToken());   // 상덕이 가방 수

        // int[] : 0(무게), 1(가격)
        ArrayList<int[]> gemList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            gemList.add(Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray());
        }

        ArrayList<Integer> backpack = new ArrayList<>();
        for (int i = 0; i < K; i++){
            backpack.add(Integer.parseInt(br.readLine()));
        }

        // 보석들을 무게 기준 오름차순으로 정렬
        gemList.sort(Comparator.comparingInt(a -> a[0]));

        // 가방들을 용량 기준 오름차순으로 정렬
        Collections.sort(backpack);

        // 이미 탐색한 보석의 가격 순으로 저장
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        
        // 탐색해야할 보석의 인덱스
        int idx = 0;
        
        long ans = 0;
        for (int back: backpack) {
            // 각 가방에 대하여 담을 보석 탐색.
            
            // 현재 가방에 담을 수 있는 보석을 heap 저장
            while (idx < gemList.size() && gemList.get(idx)[0] <= back) {
                heap.add(gemList.get(idx)[1]);
                idx += 1;
            }

            // 다을 수 있는 가장 가치의 보석을 담음.
            if (!heap.isEmpty())
                ans += heap.poll();
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}