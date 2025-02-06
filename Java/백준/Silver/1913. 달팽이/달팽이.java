/**
 * 달팽이
 * <p>
 * 구현 문제
 */

import java.lang.*;
import java.io.*;


public class Main {

    /**
     * 위 또는 아래로 숫자를 채움.
     *
     * @param board     숫자를 채울 배열
     * @param ny        시작 y 위치
     * @param nx        시작 x 위치
     * @param dist      해당 방향으로 채울 횟수
     * @param num       채울 숫자
     * @param op        채울 방향 : 1 위로, -1 아래로
     * @return          다음에 채울 숫자
     */
    int moveUpOrDown(int[][] board, int ny, int nx, int dist, int num, int op) {
        for (int i = 1; i <= dist; i++) {
            // 위로 이동한 경우에는 배열 범위 밖으로 나갈 수 있음. 따라서 예외 처리.
            if (ny + op * i < 0)
                break;
            board[ny + op * i][nx] = num;
            num++;
        }
        return num;
    }

    /**
     * 왼 또는 오른쪽 방향으로 숫자를 채움
     *
     * @param board     숫자를 채울 배열
     * @param ny        시작 y 위치
     * @param nx        시작 x 위치
     * @param dist      해당 방향으로 채울 횟수
     * @param num       채울 숫자
     * @param op        채울 방향: 1 오른쪽, -1 왼쪽
     * @return          다음에 채울 숫자
     */
    int moveLeftOrRight(int[][] board, int ny, int nx, int dist, int num, int op) {
        for (int i = 1; i <= dist; i++) {
            board[ny][nx + op * i] = num;
            num++;
        }
        return num;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기
        int N = Integer.parseInt(br.readLine());        // 표 크기
        int num = Integer.parseInt(br.readLine());      // 검색할 숫자.

        //== 변수 초기화.
        int[][] board = new int[N][N];  // 달팽이로 채워질 표

        int distance = 1; // 현재 이동 방향으로 이동할 거리.
        int nowN = 2;     // 다음 이동할 곳에 채울 숫자.

        int numY = 0, numX = 0;     // num 의 좌표
        int ny = N / 2, nx = N / 2; // 현재 y과 x 좌표.
        board[ny][nx] = 1;


        //== 표 채우기
        while (true) {
            // 위로 이동.
            nowN = moveUpOrDown(board, ny, nx, distance, nowN, -1);
            ny -= distance;

            if (distance == N) {
                break;
            }

            // 오른쪽으로 이동.
            nowN = moveLeftOrRight(board, ny, nx, distance, nowN, 1);
            nx += distance;

            distance += 1;  // 이동 거리 증가

            // 아래쪽으로 이동.
            nowN = moveUpOrDown(board, ny, nx, distance, nowN, 1);
            ny += distance;

            // 왼쪽으로 이동
            nowN = moveLeftOrRight(board, ny, nx, distance, nowN, -1);
            nx -= distance;

            distance += 1;
        }

        //== num 좌표 구하기 및 달팽이 출력하기.
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]).append(' ');

                if (board[i][j] == num) {
                    numY = i + 1;
                    numX = j + 1;
                }
            }
            sb.append('\n');
        }
        sb.append(numY).append(' ').append(numX);

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}