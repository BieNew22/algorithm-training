/**
 * 공항
 * <p>
 * 문제 정리
 * Greedy 문제
 * 각 i 번째 gi에 대하여 gi -> 1 번째 게이트로 탐색하면서 가장 큰 번호의 빈 게이트에 할당 하면 됨.
 *
 * Union & Find 적용
 * 각 G에 대하여 현재 G가 할당 할 게이트를 설정.
 * init G
 *  - idx: 0 1 2 3 4
 *  - val: 0 1 2 3 4
 * input data : 2 -> G(2)이 2 이므로 2번 게이트에 할당
 *              이 때 다시 2가 들어오면 2번 게이트에는 1번에 할당하는 게 최적 이므로 1번 게이트로 설정.
 *  - idx: 0 1 2 3 4
 *  - val: 0 1 1 3 4
 */

import java.lang.*;
import java.io.*;


public class Main {


    /**
     * now 에 할당 시킬 게이트 번호 구하기
     *
     * @param now       할당 받을 번호 (gi)
     * @param data      현재 게이트 상태
     * @return          게이트 번호
     */
    int getParent(int now, int[] data) {
        if (now == data[now])
            return now;
        data[now] = getParent(data[now], data);

        return data[now];
    }


    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //=== 초기화 ===
        int G = Integer.parseInt(br.readLine());    // 총 게이트 수
        int P = Integer.parseInt(br.readLine());    // 총 비행기 수

        // allocate[gi]는 gi에 대하여 할당 할 가장 큰 번호의 게이트 번호를 가리킴.
        int[] allocate = new int[G + 1];
        for (int i = 0; i < allocate.length; i++)
            allocate[i] = i;

        int ans = 0;

        //=== 계산하기 ===
        for (int i = 0; i < P; i++) {
            int gi = Integer.parseInt(br.readLine());

            int giParent = getParent(gi, allocate);

            // gi 에 대하여 할당 시킬 게이트가 없으면 종료.
            if (giParent == 0) {
                break;
            }

            ans += 1;
            allocate[giParent] = giParent - 1;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}