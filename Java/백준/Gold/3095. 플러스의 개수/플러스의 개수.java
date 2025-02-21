/**
 * 플러스의 개수
 * <p>
 * 문제 정리 (구현)
 * 사이즈가 K 인 플러스는 사이즈가 K - 1 인 플러스를 포함하고 있음을 이용하면 됨.
 * 따라서 어떤 좌표 (y, x)에서 사이즈가 K 인 플러스를 만족한다면 
 * K + 1 인 플러스 만족 여부는 K + 1인 플러스에 해당하는 테두리 부분만 검사하면 됨. 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    /**
     * 현재 (y, x)을 중심으로 하여 만들 수 있는 플러스의 개수를 계산.
     *
     * @param data      현재 행렬 데이터
     * @param y         시작 y 좌표
     * @param x         시작 x 좌표
     * @param size      탐색할 플러스의 크기
     * @return          최종 만들 수 있는 플러스의 개수
     */
    int countPlus(char[][] data, int y, int x, int size) {
        
        // 현재 중심 좌표 기준으로 size 만큼 크기의 플러스 만들지 못하는 경우
        if (y - size < 0 || y + size >= data.length)
            return 0;
        if (x - size < 0 || x + size >= data.length)
            return 0;

        // 현재 size 를 탐색한다는 것은 이전 size의 플러스를 만들었음을 의미.
        // 따라서 현재 플러스의 테두리 부분 탐색하면 됨.

        // 현재 size의 플러스의 끝 점이 1인지 확인.
        if (data[y - size][x] == '0')
            return 0;
        if (data[y + size][x] == '0')
            return 0;
        if (data[y][x - size] == '0')
            return 0;
        if (data[y][x + size] == '0')
            return 0;

        // 현재 테두리의 1의 개수 계산.
        int countOne = 0;

        int sX = x - size, sY = y - size;
        for (int i = 0; i < size * 2; i++) {
            if (data[sY][sX] == '1')
                countOne++;
            sX++;
        }
        for (int i = 0; i < size * 2; i++) {
            if (data[sY][sX] == '1')
                countOne++;
            sY++;
        }
        for (int i = 0; i < size * 2; i++) {
            if (data[sY][sX] == '1')
                countOne++;
            sX--;
        }

        for (int i = 0; i < size * 2; i++) {
            if (data[sY][sX] == '1')
                countOne++;
            sY--;
        }

        if (countOne == 4)
            return 1 + countPlus(data, y, x, size + 1);

        return 0;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());    // 행렬의 크기

        char[][] board = new char[N][];     // 행렬 데이터
        for (int i = 0; i < N; i++)
            board[i] = br.readLine().toCharArray();

        //== 플러스 개수 구하기.
        int ans = 0;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (board[i][j] == '0')
                    continue;
                ans += countPlus(board, i, j, 1);
            }
        }

        System.out.println(ans);

    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}