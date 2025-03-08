/**
 * 빙산
 * <p>
 * 문제 정리 (BFS, DFS)
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    /**
     * 현재 지도에서 빙산의 조각의 개수를 계산하여 반환
     *
     * @param data      현재 지도
     * @param N         지도의 행 크기
     * @param M         지도의 열 크기
     * @return          빙산의 조각 개수
     */
    int countIceberg(int[][] data, int N, int M) {
        // data 복제본.
        int[][] tmp = new int[N][M];
        for (int i = 0; i < tmp.length; i++) {
            System.arraycopy(data[i], 0, tmp[i], 0, M);
        }

        int count = 0;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmp[i][j] == 0)
                    continue;

                count += 1;

                // BFS 로 빙산의 조각 탐색.
                tmp[i][j] = 0;

                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[] {i, j});

                while (!queue.isEmpty()) {
                    int[] now = queue.poll();

                    for (int[] dir: dirs) {
                        int ny = now[0] + dir[0];
                        int nx = now[1] + dir[1];

                        if (ny < 0 || ny >= N)
                            continue;
                        if (nx < 0 || nx >= M)
                            continue;
                        if (tmp[ny][nx] == 0)
                            continue;

                        tmp[ny][nx] = 0;
                        queue.add(new int[] {ny, nx});
                    }
                }
            }
        }

        return count;
    }

    /**
     * 1년뒤 빙산의 모습을 반환
     *
     * @param data  현재 빙산의 모습
     * @param N     지도의 행 크기
     * @param M     지도의 열 크기
     * @return      1년 뒤 빙산의 모습
     */
    int[][] aYearLater(int[][] data, int N, int M) {
        int[][] res = new int[N][M];

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (data[i][j] == 0)
                    continue;

                int cnt = 0;    // 인접한 바닷물 칸의 개수
                for (int[] dir: dirs) {
                    if (data[i + dir[0]][j + dir[1]] == 0)
                        cnt += 1;
                }

                res[i][j] = data[i][j] - Math.min(data[i][j], cnt);
            }
        }

        return res;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        //== 입력 받기 및 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 행의 개수
        int M = Integer.parseInt(st.nextToken());   // 열의 개수

        int[][] matrix = new int[N][];  // 빙산의 높이를 나타냄.
        for (int i = 0; i < N; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        int ans = 0;    // 빙산 분리되는 최초의 시간

        //== ans 구하기
        while (true) {
            int count = countIceberg(matrix, N, M);
            
            if (count == 0) {
                // 빙산이 다 녹을 때 까지 빙산이 분리되지 않음.
                ans = 0;
                break;
            } else if (count == 1) {
                // 방산이 분리되지 않음.
                ans += 1;
                matrix = aYearLater(matrix, N, M);
            } else {
                break;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}