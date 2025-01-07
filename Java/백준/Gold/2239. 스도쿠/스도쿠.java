/**
 * 스도쿠
 * <p>
 * 기본적인 백트랙킹 문제
 * 81 자리의 수가 제일 작은 경우 출력
 * => 앞자리 부터 1 -> 9으로 증가 하는 순으로 탐색하면 최초으로 찾은 답이 해답.
 * => 앞자리 의미 : y 값이 작고, y 값이 같으면 x 값이 작은 순으로 정렬 한 위치 순.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {

    ArrayList<int[]> zeros = new ArrayList<>();
    boolean[][] rows = new boolean[9][10];
    boolean[][] cols = new boolean[9][10];
    boolean[][] boxes = new boolean[9][10];

    /**
     * 백트랙킹을 통하여 스도쿠 풀기
     *
     * @param idx   idx 번째 0을 결정하는 것을 의미
     * @param data  현재 스도쿠
     * @return true (해답 찾음 -> 더 이상 탐색 X), false (해당 못 찾음 -> 이어서 탐색)
     */
    boolean backTacking(int idx, int[][] data) {
        if (idx == zeros.size()) {
            return true;
        }

        boolean res = false;

        int[] now = zeros.get(idx);

        for (int i = 1; i <= 9; i++) {
            if (rows[now[0]][i])
                continue;
            if (cols[now[1]][i])
                continue;

            int boxIdx = now[0] / 3 * 3 + now[1] / 3;
            if (boxes[boxIdx][i])
                continue;

            rows[now[0]][i] = true;
            cols[now[1]][i] = true;
            boxes[boxIdx][i] = true;
            data[now[0]][now[1]] = i;

            res = backTacking(idx + 1, data);

            // data[now[0]][now[1]] = i 에서 최초로 문제를 품
            // -> 다시 탐색 X 이므로 다시 값을 초기화 X.
            if (res)
                break;

            // 다음 탐색을 위하여 원래 상태로 복구.
            rows[now[0]][i] = false;
            cols[now[1]][i] = false;
            boxes[boxIdx][i] = false;
            data[now[0]][now[1]] = 0;
        }

        return res;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] data = new int[9][];

        for (int i = 0; i < 9; i++)
            data[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (data[i][j] == 0) {
                    zeros.add(new int[] {i, j});
                }

                // 현재 행, 열, 박스에 있는 숫자의 상태를 기록
                rows[i][data[i][j]] = true;
                cols[j][data[i][j]] = true;

                int idx = i / 3 * 3 + j / 3;
                boxes[idx][data[i][j]] = true;
            }
        }

        backTacking(0, data);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(data[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}