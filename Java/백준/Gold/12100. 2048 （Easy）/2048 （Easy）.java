/**
 * 2048 (Easy)
 * <p>
 * 문제 정리
 * 구현 문제 + DFS
 */

import java.lang.*;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    /**
     * 보드를 위로 이동 시킨 경우
     *
     * @param board 현재 보드 상태
     * @return      이동 후 보드 상태
     */
    int[][] moveUp(int[][] board) {

        int size = board.length;

        int[][] res = new int[size][size];

        // 맨 윗줄 복사.
        System.arraycopy(board[0], 0, res[0], 0, size);

        // 각 열 단위로 이동.
        for (int x = 0; x < size; x++) {
            int resY = 0;   // 현재 열에 놓을 수 있는 위치를 의미.

            for (int y = 1; y < size; y++) {
                if (board[y][x] == 0)
                    continue;

                while(true) {
                    // 현재 위치가 빈칸인 경우 : 해당 칸에 숫자를 배치.
                    // 현재 위치와 같은 숫자 인 경우 : 합침. 다음 숫자는 다음 칸 부터 배치 가능.
                    // 현재 위치와 다른 숫자 인 경우 : 다음 칸 부터 배치 가능.

                    if (res[resY][x] == 0) {
                        res[resY][x] = board[y][x];
                        break;
                    } else if (board[y][x] == res[resY][x]) {
                        res[resY][x] *= 2;
                        resY += 1;
                        break;
                    }
                    resY += 1;
                }
            }
        }

        return res;
    }

    /**
     * 보드를 아래로 이동 시킨 경우
     *
     * @param board 현재 보드 상태
     * @return      이동 후 보드 상태
     */
    int[][] moveDown(int[][] board) {
        int size = board.length;

        int[][] res = new int[size][size];

        // 맨 아래줄 복사.
        System.arraycopy(board[size - 1], 0, res[size - 1], 0, size);

        for (int x = 0; x < size; x++) {
            int resY = size - 1;
            for (int y = size - 2; y >= 0; y--) {
                if (board[y][x] == 0)
                    continue;

                while(true) {
                    if (res[resY][x] == 0) {
                        res[resY][x] = board[y][x];
                        break;
                    } else if (board[y][x] == res[resY][x]) {
                        res[resY][x] *= 2;
                        resY -= 1;
                        break;
                    }
                    resY -= 1;
                }
            }
        }

        return res;
    }

    /**
     * 보드를 외쪽으로 이동 시킨 경우
     *
     * @param board 현재 보드 상태
     * @return      이동 후 보드 상태
     */
    int[][] moveLeft(int[][] board) {
        int size = board.length;

        int[][] res = new int[size][size];

        for (int i = 0; i < size; i++) {
            res[i][0] = board[i][0];
        }

        for (int y = 0; y < size; y++) {
            int resX = 0;

            for (int x = 1; x < size; x++) {
                if (board[y][x] == 0)
                    continue;

                while(true) {
                    if (res[y][resX] == 0) {
                        res[y][resX] = board[y][x];
                        break;
                    } else if (board[y][x] == res[y][resX]) {
                        res[y][resX] *= 2;
                        resX += 1;
                        break;
                    }
                    resX += 1;
                }
            }
        }

        return res;
    }

    /**
     * 보드를 오른쪽으로 이동 시킨 경우
     *
     * @param board 현재 보드 상태
     * @return      이동 후 보드 상태
     */
    int[][] moveRight(int[][] board) {
        int size = board.length;

        int[][] res = new int[size][size];

        for (int i = 0; i < size; i++) {
            res[i][size - 1] = board[i][size - 1];
        }

        for (int y = 0; y < size; y++) {
            int resX = size - 1;

            for (int x = size - 2; x >= 0; x--) {
                if (board[y][x] == 0)
                    continue;

                while(true) {
                    if (res[y][resX] == 0) {
                        res[y][resX] = board[y][x];
                        break;
                    } else if (board[y][x] == res[y][resX]) {
                        res[y][resX] *= 2;
                        resX -= 1;
                        break;
                    }
                    resX -= 1;
                }
            }
        }

        return res;
    }

    /**
     * 다섯번 이동 후 얻을 수 있는 가장 큰 블록 얻기
     *
     * @param depth         현재 이동 횟수
     * @param nowBoard      현재 보드 상태
     * @return              가장 큰 블록 수
     */
    int getMaxNum(int depth, int[][] nowBoard) {
        if (depth == 5) {
            int max = 0;
            for (int[] raw : nowBoard) {
                for (int j = 0; j < nowBoard.length; j++) {
                    max = Math.max(max, raw[j]);
                }
            }

            return max;
        }

        int ans = 0;

        ans = Math.max(ans, getMaxNum(depth + 1, moveDown(nowBoard)));
        ans = Math.max(ans, getMaxNum(depth + 1, moveUp(nowBoard)));
        ans = Math.max(ans, getMaxNum(depth + 1, moveLeft(nowBoard)));
        ans = Math.max(ans, getMaxNum(depth + 1, moveRight(nowBoard)));

        return ans;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //=== 입력 받기
        int N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(getMaxNum(0, board));
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}