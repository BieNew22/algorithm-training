/**
 * 공유기 설치
 * <p>
 * 문제 정리 (이진 탐색)
 * 문제 분류를 보기 전까지 이진 탐색임을 떠올리기 힘들었음.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    
    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());    // 집의 개수
        int C = Integer.parseInt(st.nextToken());    // 공유기의 개수

        int[] data = new int[N];        // 각 지의 위치.
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(data);  // 집의 위치를 오름차순으로 정렬 함.

        //== 이진탐색 : 가장 가까운 두 공유기 사이의 거리의 최대를 x로 설정하여 탐색.
        int ans = 0;

        int s = 0, e = data[N - 1] - data[0] + 1;

        while (s <= e) {
            int mid = (s + e) / 2;  // 가장 가까운 두 공유기의 거리.
            int last = data[0];     // 마지막에 공유기 설치한 위치
            int cnt = 1;            // 현재 설치한 공유기 개수

            // 설치할 수 있는 공유기 개수 탐색 시작.
            for (int i = 1; i < N; i++) {
                if (data[i] - last >= mid) {
                    cnt += 1;
                    last = data[i];
                }
                // REFACTOR : 끝까지 탐색할 필요 X, cnt == C를 만족하면 break; 해도됨.
                /**
                 * if (cnt == C) break;
                 */
            }

            if (cnt >= C) {
                // REFACTOR : max 할 필요가 없음. 현재 ans 값을 하한으로 탐색함.
                ans = Math.max(ans, mid);
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}