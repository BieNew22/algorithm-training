/**
 * 절사평균
 * <p>
 * 문제 정리 (수학 문제)
 * 소수점 처리가 힘든 문제
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        // 0 : N (전체 점수의 개수)
        // 1 : K (제외되는 점수의 개수)
        int[] NK = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        double[] data = new double[NK[0]];
        for (int i = 0; i < NK[0]; i++) {
            data[i] = Double.parseDouble(br.readLine());
        }
        Arrays.sort(data);

        double trimmed = 0.0;       // 절사 평균의 합.
        double calibrated = 0.0;    // 보정 평균의 합.

        //== 절상 평균과 보정 평균의 합 구하기
        for (int i = 0; i < data.length; i++) {
            if (NK[1] <= i && i < NK[0] - NK[1]) {
                trimmed += data[i];
            }

            if (i < NK[1]) {
                calibrated += data[NK[1]];
            } else if (i >= NK[0] - NK[1]) {
                calibrated += data[NK[0] - NK[1] - 1];
            } else {
                calibrated += data[i];
            }
        }

        //== 평균 구하여 출력하기.
        trimmed /= (NK[0] - 2 * NK[1]);
        calibrated /= NK[0];

        System.out.printf("%.2f%n", trimmed);
        System.out.printf("%.2f%n", calibrated);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}