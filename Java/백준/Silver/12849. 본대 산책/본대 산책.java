/**
 * 본대 산책
 * <p>
 * 문제 정리
 * BFS 문제
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int D = Integer.parseInt(br.readLine());    // 산책 시간

        long[][] matrix = { // 숭실 대학교 캠퍼스 지도
                //0, 1, 2, 3, 4, 5, 6, 7
                { 0, 1, 1, 0, 0, 0, 0, 0}, // 정보 0
                { 1, 0, 1, 1, 0, 0, 0, 0}, // 전산 1
                { 1, 1, 0, 1, 1, 0, 0, 0}, // 미래 2
                { 0, 1, 1, 0, 1, 1, 0, 0}, // 신양 3
                { 0, 0, 1, 1, 0, 1, 0, 1}, // 한경 4
                { 0, 0, 0, 1, 1, 0, 1, 0}, // 진리 5
                { 0, 0, 0, 0, 0, 1, 0, 1}, // 학생 6
                { 0, 0, 0, 0, 1, 0, 1, 0}, // 형남 7
        };

        long mod = 1000000007;

        long[] ans = new long[matrix.length];   // D 초에 도달 할 수 있는

        //== 해구하기
        ans[0] = 1; // 0초에 도착 가능 위치 초기화. (0초는 시작점에 존재 따라서 시작점 1을 설정)

        for (int i = 0; i < D; i++) {
            // 현재 i + 1 초에 각 지점에 도달 할 수 있는 경우의 수
            long[] next = new long[matrix.length];  

            for (int now = 0; now < matrix.length; now++) {
                long tmp = 0;
                for (int j = 0; j < matrix.length; j++) {
                    tmp += matrix[now][j] * ans[j];
                }
                next[now] = tmp % mod;
            }

            ans = next;
        }

        System.out.println(ans[0]);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}