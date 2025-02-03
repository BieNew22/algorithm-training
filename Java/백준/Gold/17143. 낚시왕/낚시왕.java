/**
 * 낚시왕
 * <p>
 * 문제 정리
 * 단순 구현 문제.
 * 중의 할 것 : 현재 상어 위치 + speed 한 값이 낚시터의 크기의 몇배가 될 수 있음.
 */

import java.lang.*;
import java.io.*;
import java.util.StringTokenizer;


public class Main {

    public static class Shark {
        int speed, dir, size;

        public Shark(StringTokenizer st) {
            speed = Integer.parseInt(st.nextToken());   // 상어 이동 속도
            dir = Integer.parseInt(st.nextToken());     // 상어 이동 방향
            size = Integer.parseInt(st.nextToken());    // 상어 크기.
        }
    }

    /**
     * 상어를 이동 시킴.
     *
     * @param y             현재 상어의 행 위치
     * @param x             현재 상어의 열 위치
     * @param R             낚시터의 행 크기
     * @param C             낚시터의 열 크기
     * @param leftMove      현재 상어의 남은 이동 횟수
     * @param dir           현재 상어의 이동 방향
     * @return              이동 후 상어 [행, 열, 이동 방향] 위치.
     */
    int[] moveShark(int y, int x, int R, int C, int leftMove, int dir) {
        int resY = y, resX = x, resDir = dir;
        int moved = leftMove;

        // dir = 1(위), 2(아래), 3(오른쪽), 4(왼쪽) 으로 이동을 의미.
        if (dir == 1) {
            if (y - leftMove >= 0) {
                resY = y - leftMove;
            } else {
                resY = 0;
                resDir = 2;
                moved = y;
            }
        } else if (dir == 2) {
            if (y + leftMove < R) {
                resY = y + leftMove;
            } else {
                resY = R - 1;
                resDir = 1;
                moved = R - 1 - y;
            }
        } else if (dir == 4) {
            if (x - leftMove >= 0) {
                resX = x - leftMove;
            } else {
                resX = 0;
                resDir = 3;
                moved = x;
            }
        } else {
            if (x + leftMove < C) {
                resX = x + leftMove;
            } else {
                resX = C - 1;
                resDir = 4;
                moved = C - 1 - x;
            }
        }

        leftMove -= moved;
        if (leftMove == 0)
            return new int[] {resY, resX, resDir};
        return moveShark(resY, resX, R, C, leftMove, resDir);
    }

    /**
     * 낚시터의 모든 상어 이동 시키기.
     *
     * @param board     현재 낚시터 상어 위치 상태
     * @param R         낚시터 행 크기
     * @param C         낚시터 열 크기
     * @return          이동 후 낚시터 상어 위치 상태.
     */
    Shark[][] moveAllShark(Shark[][] board, int R, int C) {
        Shark[][] res = new Shark[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == null)
                    continue;

                Shark nowShark = board[i][j];

                // 해당 위치의 상어 이동 시키기.
                int[] resLoc = moveShark(i, j, R, C, nowShark.speed, nowShark.dir);

                nowShark.dir = resLoc[2];

                // 상어 충돌한 경우 : 큰 상어가 작은 상어를 먹음.
                if (res[resLoc[0]][resLoc[1]] != null) {
                    if (board[i][j].size > res[resLoc[0]][resLoc[1]].size) {
                        res[resLoc[0]][resLoc[1]] = board[i][j];
                    }
                } else {
                    res[resLoc[0]][resLoc[1]] = board[i][j];
                }
            }
        }

        return res;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        //=== 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());   // 낚시터 행 크기
        int C = Integer.parseInt(st.nextToken());   // 낚시터 열 크기
        int M = Integer.parseInt(st.nextToken());   // 낚시터 상어 수

        Shark[][] board = new Shark[R][C];          // 현재 낚시터 상태.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;

            board[y][x] = new Shark(st);
        }

        //=== 시물레이션 돌리기.
        int ans = 0;

        for (int now = 0; now < C; now++) {
            // now : 현재 사람 위치.

            // 1. 땅과 제일 가까운 상어 움직이기.
            for (int j = 0; j < R; j++) {
                if (board[j][now] != null) {
                    ans += board[j][now].size;
                    board[j][now] = null;
                    break;
                }
            }

            // 2. 상어 이동 시키기.
            board = moveAllShark(board, R, C);
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}