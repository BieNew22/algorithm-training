/**
 * IQ Test
 * <p>
 * 문제 정리 (수학)
 * 연립 방정식을 이용한 문제
 * 나누기 할 때 divided by zero 조심하기
 */

import java.lang.*;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] data = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        if (N == 1) {
            // 숫자 1개만 제공 : 다음 수가 여러개
            System.out.println('A');
        } else if (N == 2 || data[0] == data[1]) {
            // 모든 수가 같으면 : 다음 수도 동일 함.
            if (Arrays.stream(data).allMatch(x -> x == data[0])) {
                System.out.println(data[0]);
            } else {
                if (N == 2)
                    System.out.println('A');
                else
                    System.out.println('B');
            }
        } else {
            // data[0] * a + b = data[1]
            // data[1] * a + b = data[2]
            // a = (data[1] - data[2]) / (data[0] - data[1])
            // b = data[1] - data[0] * a
            
            if ((data[1] - data[2]) % (data[0] - data[1]) != 0) {
                // a를 만족하는 정수가 없음 -> 다음 수를 구할 수 없음.
                System.out.println('B');
            } else {
                int a = (data[1] - data[2]) / (data[0] - data[1]);
                int b = data[1] - data[0] * a;

                // 주어진 데이터에 대하여 모두 a와 b를 만족 여부 확인.
                boolean check = true;
                for (int i = 1; i < N; i++) {
                    if (data[i - 1] * a + b != data[i]) {
                        check = false;
                        break;
                    }
                }

                if (check) {
                    System.out.println(data[N - 1] * a + b);
                } else {
                    System.out.println('B');
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}