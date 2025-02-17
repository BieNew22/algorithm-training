/**
 * 본대 산책2
 * <p>
 * 문제 정리
 * 분할 정복 + 인접행렬 제곱 문제.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    /**
     * 두 행렬의 곱
     *
     * @param d1    행렬1
     * @param d2    행렬2
     * @param mod   결과 행렬의 각 원소의 최댓값.
     * @return      결과 행렬
     */
    long[][] multiply(long[][] d1, long[][] d2, long mod) {
        long[][] res = new long[d1.length][d1.length];

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res.length; j++) {
                long tmp = 0;
                for (int k = 0; k < res.length; k++) {
                    tmp += d1[i][k] * d2[k][j];
                }
                res[i][j] = tmp % mod;
            }
        }
        return res;
    }

    /**
     * 분할 정복을 이용한 행렬 n 승을 구함.
     *
     * @param data  제곱할 행렬
     * @param n     n 제곱
     * @param mod   행렬의 각 원소의 최댓값.
     * @return      제곱후 행렬
     */
    long[][] power(long[][] data, int n, long mod) {
        long[][] res = new long[data.length][data.length];
        for (int i = 0; i < res.length; i++)
            res[i][i] = 1;

        long[][] tmp = new long[data.length][data.length];
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, tmp[i], 0, data.length);
        }

        while (n > 0) {
            if (n % 2 == 0) {
                tmp = multiply(tmp, tmp, mod);
                n /= 2;
            } else {
                res = multiply(res, tmp, mod);
                n -= 1;
            }
        }
        return res;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int D = Integer.parseInt(br.readLine());    // 산책 시간

        long[][] matrix = { // 숭실 대학교 캠퍼스 지도
                //0, 1, 2, 3, 4, 5, 6, 7
                {0, 1, 1, 0, 0, 0, 0, 0}, // 정보 0
                {1, 0, 1, 1, 0, 0, 0, 0}, // 전산 1
                {1, 1, 0, 1, 1, 0, 0, 0}, // 미래 2
                {0, 1, 1, 0, 1, 1, 0, 0}, // 신앙 3
                {0, 0, 1, 1, 0, 1, 0, 1}, // 한경 4
                {0, 0, 0, 1, 1, 0, 1, 0}, // 진리 5
                {0, 0, 0, 0, 0, 1, 0, 1}, // 학생 6
                {0, 0, 0, 0, 1, 0, 1, 0}, // 형남 7
        };

        long mod = 1000000007;
        
        //== 정답 구하기
        long[][] res = power(matrix, D, mod);

        System.out.println(res[0][0]);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}