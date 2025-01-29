/**
 * 벽 부수고 이동하기 4
 * <p>
 * 문제 정리
 * Disjoint set + DP + 탐색 문제
 * - DP : 모든 0으로 구성된 영역의 크기 저장
 * - Disjoint set : 같은 영역의 (y, x)에 대하여 하나로 묶음.
 */


import java.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;


public class Main {

    /**
     * (y, x)에 대하여 0으로 구성된 영역을 구하고, DP 추가 및, 해당 좌표들을 하나로 묶음.
     *
     * @param y         시작 y 좌표
     * @param x         시작 x 좌표
     * @param board     영역 상태
     * @param DP        0으로 구성된 영역의 크기를 저장.
     * @param parent    각 좌표에 대하여 묶어 놓은 테이블.
     */
    void countZero(int y, int x, char[][] board, HashMap<Integer, Integer> DP, int[][] parent) {
        //=== 변수 초기화
        int H = board.length;       // board 세로 크기
        int W = board[0].length;    // board 가로 크기
        int key = y * W + x + 1;    // 현재 영역의 키 값.

        ArrayList<int[]> nowJob = new ArrayList<>(); // 현재 탐색해야할 좌표들 저장.

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};  // 탐색할 방향.

        int count = 1;  // 현재 탐색할 영역의 크기

        //=== bfs 탐색 시작.
        nowJob.add(new int[] {y, x});
        parent[y][x] = key; // 현재 좌표를 현재 영역에 묶음.
        board[y][x] = '2';    // 재 방문 예방.

        while (!nowJob.isEmpty()) {
            ArrayList<int[]> nextJob = new ArrayList<>();   // 다음에 탐색해야할 좌표들 저장.

            for (int[] now: nowJob) {
                for (int[] dir: dirs) {
                    int ny = now[0] + dir[0];
                    int nx = now[1] + dir[1];

                    if (ny < 0 || ny >= H || nx < 0 || nx >= W)
                        continue;

                    if (board[ny][nx] != '0')
                        continue;

                    count += 1;

                    parent[ny][nx] = key;
                    board[ny][nx] = '2';

                    nextJob.add(new int[] {ny, nx});
                }
            }

            nowJob = nextJob;
        }

        //=== 현재 탐색한 영역을 DP에 추가.
        DP.put(key, count);
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //=== 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());   // board 세로 크기
        int W = Integer.parseInt(st.nextToken());   // board 가로 크기

        char[][] board = new char[H][];
        for (int i = 0; i < H; i++)
            board[i] = br.readLine().toCharArray();

        // key : (y, x) -> y * W + x;
        // 모든 0으로 구성된 영역의 크기 저장
        // 영역의 대표 키 값은 탐색 시작 좌표로 지정.
        HashMap<Integer, Integer> DP = new HashMap<>();
        DP.put(0, 0);

        int[][] parent = new int[H][W]; // 같은 영역의 (y, x)에 대하여 하나로 묶음.

        //=== 각 좌표에서 얻을 수 있는 모든 0의 개수를 구함.
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (board[i][j] != '0')
                    continue;
                countZero(i, j, board, DP, parent);
            }
        }
        
        //=== 정답 구하기.
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};  // 탐색할 방향.

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (board[i][j] == '2') {
                    sb.append('0');
                    continue;
                }

                // 현재 좌표의 상하좌우에 있는 0으로 구성된 영역을 수집하기 위함.
                HashSet<Integer> areaSet = new HashSet<>();

                for (int[] dir: dirs) {
                    int ny = i + dir[0];
                    int nx = j + dir[1];

                    if (ny < 0 || ny >= H || nx < 0 || nx >= W)
                        continue;

                    areaSet.add(parent[ny][nx]);
                }

                int totalZero = 1;
                for (int area: areaSet)
                    totalZero += DP.get(area);

                sb.append(totalZero % 10);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}