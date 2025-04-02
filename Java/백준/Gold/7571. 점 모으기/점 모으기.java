/**
 * 점 모으기
 * <p>
 * 문제 정리 (수학)
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;


public class Main {


    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        //== 입력 받기 및 초기화
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 좌표의 범위
        int M = Integer.parseInt(st.nextToken());   // 좌표의 개수

        // 각 x와 y 좌표를 저장한 배열.
        int[] xLoc = new int[M];
        int[] yLoc = new int[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            xLoc[i] = Integer.parseInt(st.nextToken());
            yLoc[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(xLoc);
        Arrays.sort(yLoc);

        // 좌표들 중 가운데 값으로 모을 때 이동거리의 합이 최솟 값이 됨.
        int goalX = xLoc[M / 2];
        int goalY = yLoc[M / 2];

        //== 총 이동 거리 구하기.
        int ans = 0;

        for (int i = 0; i < M; i++) {
            ans += Math.abs(xLoc[i] - goalX);
            ans += Math.abs(yLoc[i] - goalY);
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}