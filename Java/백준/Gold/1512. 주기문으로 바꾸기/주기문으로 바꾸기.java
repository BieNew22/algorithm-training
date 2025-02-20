/**
 * 주기문으로 바꾸기
 * <p>
 * 문제 정리, 시뮬레이션
 * i, i + p, i + 2p ... i + kp (i + kp < L) 가 모든 같은 문자여야 함을 의미.
 * 따라서 각 자리에 있는 A, C, G, T 수를 계산하여 가장 작은 3개를 합함.
 * 이것이 i 에서 p 간격이라고 했을 때 바뀌어야 하는 최소 문자의 개수를 의미.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int gab = Integer.parseInt(br.readLine());  // i 번째 원소와 같아야 하는 최대 거리
        char[] data = br.readLine().toCharArray();  // DNA 문자열.

        //== 정답 구하기.
        int ans = Integer.MAX_VALUE;
        for (int dis = 1; dis <= gab; dis++) {
            // 주기문의 길이가 dis 일 때에 대하여 계산.

            int count = 0;  // 총 바뀌어야하는 문자의 개수.

            for (int i = 0; i < dis; i++) {
                // i 는 시작 위치를 의미.

                // 0 : A, 1 : G, 2 : C, 3 : T 각 문자의 개수.
                int[] agct = new int[4];
                int total = 0;  // i 으로 k*dis 만큼 떨어진 모든 문자의 개수.

                // i 으로 k*dis 만큼 떨어진 모든 문자를 조사.
                int tmp = i;
                while (tmp < data.length) {
                    switch (data[tmp]) {
                        case 'A' -> agct[0]++;
                        case 'G' -> agct[1]++;
                        case 'C' -> agct[2]++;
                        case 'T' -> agct[3]++;
                    }
                    total++;

                    tmp += dis;
                }

                // 조사 한 것 중에 가장 많은 것을 제외하고 나머지를 바꿔야 함.
                // 한 개 인경우에는 수정할 필요가 없음.
                if (total > 1) {
                    Arrays.sort(agct);

                    count += agct[0] + agct[1] + agct[2];
                }
            }

            ans = Math.min(count, ans);
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}