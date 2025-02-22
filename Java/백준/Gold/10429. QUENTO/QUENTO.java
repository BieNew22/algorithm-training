/**
 * QUENTO
 * <p>
 * 문제 정리
 * 구현 + DFS.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {

    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * goal을 만드는 데 필요한 경로 탐색
     *
     * @param path      현재 까지 탐색한 경로
     * @param maxDep    최대 탐색 길이
     * @param goal      목표 값
     * @param data      현재 보드 상태
     * @return          목표 값을 만드는 좌표들을 문자열로 반환.
     */
    String findAns(Stack<int[]> path, int maxDep, int goal, char[][] data) {
        if (path.size() == maxDep) {
            int sum = 0;    // 현재 경로의 계산 값.
            int mul = 1;    // 숫자의 부호.

            StringBuilder sb = new StringBuilder();
            for (int[] p: path) {
                sb.append(p[0]).append(' ').append(p[1]).append('\n');

                if (Character.isDigit(p[2])) {
                    sum += mul * (p[2] - '0');
                } else if (p[2] == '-') {
                    mul = -1;
                } else {
                    mul = 1;
                }
            }

            if (sum == goal)
                return sb.toString();
            return "";
        }

        String ans = "";   // 최종 경로

        int[] bef = path.get(path.size() - 1);      // 마지막 좌표

        for (int[] dir: dirs) {
            int ny = dir[0] + bef[0];
            int nx = dir[1] + bef[1];

            // 범위를 벗어나면 패스.
            if (ny < 0 || ny >= 3 || nx < 0 || nx >= 3)
                continue;

            char tmp = data[ny][nx];

            // path 원소개수가 짝수이면 -> 숫자가 들어갈 차례.
            // path 원소개수가 홀수이면 -> 기호 들어갈 차례.
            if (path.size() % 2 == 0) {
                if (Character.isDigit(data[ny][nx])) {
                    path.add(new int[] {ny, nx, data[ny][nx]});

                    data[ny][nx] = '*';
                    ans = findAns(path, maxDep, goal, data);
                    data[ny][nx] = tmp;

                    path.pop();
                }
            } else {
                if (data[ny][nx] == '+' || data[ny][nx] == '-') {
                    path.add(new int[] {ny, nx, data[ny][nx]});

                    data[ny][nx] = '*';
                    ans = findAns(path, maxDep, goal, data);
                    data[ny][nx] = tmp;

                    path.pop();
                }
            }

            // 정답을 찾음 더이상 탐색 X.
            if (!ans.isEmpty())
                break;

        }

        return ans;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        //== 입력 받기 및 초기화
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 만들 숫자
        int M = Integer.parseInt(st.nextToken());   // N을 만들 대 사용될 숫자의 개수

        char[][] board = new char[3][];             // 게임 보드
        for (int i = 0; i < 3; i++) {
            board[i] = br.readLine().toCharArray();
        }

        //== 정답 찾기.
        Stack<int[]> path = new Stack<>();
        String ans = "";

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Character.isDigit(board[i][j])) {
                    char tmp = board[i][j];

                    path.add(new int[] {i, j, tmp});
                    board[i][j] = '*';

                    ans = findAns(path, M * 2 - 1, N, board);

                    path.pop();
                    board[i][j] = tmp;
                }

                if (!ans.isEmpty())
                    break;
            }

            if (!ans.isEmpty())
                break;
        }

        //== 정답 출력하기.
        if (ans.isEmpty()) {
            System.out.println("0");
        } else {
            System.out.println(1);
            System.out.println(ans);
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}