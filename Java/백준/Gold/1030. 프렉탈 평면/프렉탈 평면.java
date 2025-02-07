/**
 * 프렉탈 평면
 * <p>
 * 문제 정리
 * 재귀 문제, 별 찍기 시리즈 문제랑 비슷함.
 */

import java.lang.*;
import java.io.*;
import java.util.StringTokenizer;


public class Main {

    /**
     * (nY, nX) 에 대하여 gS 일 때 색상 리턴.
     *
     * @param nowS      현재 시간
     * @param gS        목표 시간
     * @param nY        현재 행 좌표
     * @param nX        현재 열 좌표
     * @param gY        목표 행 좌표
     * @param gX        목표 열 좌표
     * @param color     이전 시간에서 해당 색상 (0 : 흰색, 1 : 검은색)
     * @param N         확장 크기
     * @param K         흰색 확장시 채울 검은색 크기
     * @return          최종 gS일 때 색상.
     */
    int getColor(int nowS, int gS, int nY, int nX, int gY, int gX, int color, int N, int K) {
        if (nowS == gS)
            return color;

        // g 좌표를 현재 s + 1에 맞게 좌표 압축.
        int sY = gY, sX = gX;
        for (int i = 0; i < gS - nowS - 1; i++) {
            sY /= N;
            sX /= N;
        }

        // 현재 지점을 확장.
        // 현재 지점이 검은색인 경우 sY, sX도 마찬가지로 검은색에 해당.
        if (color == 1) {
            return getColor(nowS + 1, gS, sY, sX, gY, gX, 1, N, K);
        }

        // 현재 지점이 흰색인 경우 : 검은색 영역.
        int sBlackY = nY * N + (N - K) / 2;
        int eBlackY = sBlackY + K;
        int sBlackX = nX * N + (N - K) / 2;
        int eBlackX = sBlackX + K;

        if (sBlackY <= sY && sY < eBlackY && sBlackX <= sX && sX < eBlackX) {
            return getColor(nowS + 1, gS, sY, sX, gY, gX, 1, N, K);
        }

        return getColor(nowS + 1, gS, sY, sX, gY, gX, 0, N, K);
    };

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());   // 시간
        int N = Integer.parseInt(st.nextToken());   // 쪼개질 크기
        int K = Integer.parseInt(st.nextToken());   // 흰색이 채울 검은색 영역

        // 행열의 시작번호는 0 부터 시작 함.
        int R1 = Integer.parseInt(st.nextToken());  // 시작 행 번호
        int R2 = Integer.parseInt(st.nextToken());  // 종료 행 번호
        int C1 = Integer.parseInt(st.nextToken());  // 시작 열 번호
        int C2 = Integer.parseInt(st.nextToken());  // 종료 행 번호

        //== 각 점에 대한 색상 구하기.
        StringBuilder sb = new StringBuilder();
        for (int y = R1; y <= R2; y++) {
            for (int x = C1; x <= C2; x++) {
                sb.append(getColor(0, s, 0, 0, y, x, 0, N, K));
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}