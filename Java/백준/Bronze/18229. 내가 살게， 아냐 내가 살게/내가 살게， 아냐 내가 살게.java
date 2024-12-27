//  내가 살게, 아냐 내가 살게
//  문제 정리
//  여러 배열 중에서 가장 적게 누적 합하여 K 이상 되는 배열 중 가장 짧은 배열 구하기

import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {

    /**
     * 답 구하기
     * 
     * @param board 각 사람의 손 뻗는 2차원 정보 테이블
     * @param arraySize 사람이 총 손 뻗는 횟수
     * @param K 목표 값
     * @return [사람 번호, 손 뻗은 횟수]
     */
    int[] getAns(int[][] board, int arraySize, int K) {
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < board.length; j++) {
                // i = 손 뻗은 횟수, j = 사람 번호
                if (i != 0)
                    board[j][i] += board[j][i - 1];

                if (board[j][i] >= K)
                    return new int[] {j + 1, i + 1};
            }
        }
        return new int[] {0, 0};
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int totalPerson = Integer.parseInt(st.nextToken());
        int arraySize = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] board = new int[totalPerson][];
        for (int i = 0; i < board.length; i++)
            board[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

        int[] ans = getAns(board, arraySize, K);

        System.out.println(ans[0] + " " + ans[1]);

    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}