/**
 * 예비 소집 결과 보고서
 * <p>
 * 문제 정리 (구현 문제)
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());

        int count = 0;

        //== 설실하게 참여한 팀 구하기.
        for (int i = 0; i < N; i++) {
            int[] data = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < data.length; j++) {
                if (data[j] == -1)
                    data[j] = Integer.MAX_VALUE;
            }

            int min = data[0];    // 가장 빨리 푼 시간.
            boolean isGood = true;
            for (int j = 1; j < data.length; j++) {
                if (data[j] < data[j - 1]) {
                    isGood = false;
                    break;
                }
            }

            if (isGood && min != Integer.MAX_VALUE) {
                count += 1;
            }
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}