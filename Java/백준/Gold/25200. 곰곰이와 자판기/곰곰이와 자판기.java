/**
 * 곰곰이와 자판기
 * <p>
 * 문제 정리 (DP 문제)
 * 함수를 다음과 같이 정의함.
 *      - f(차원, 음료) -> 음료 : 해당 차원에서 해당 음료로 시작하여 나머지 차원을 거친 결과물을 반환.
 *          - 나머지 차원 이란? 현재 차원 보다 큰 차원(나중에 입력되는 차원)
 *      - 차원 함수 Ti : Ui -> Vi : i 번째 차원에서 Ui 음료를 Vi음료롤 바꾸는 함수.
 * 마지막에 차원 기준으로 봤을 때 f(n, u) 에 대하여
 *      - u 가 Tn의 Un와 같은 값이면 Vn 반환.
 *      - 그 외에는 u가 그대로 반환 됨.
 * 즉, n 번째 차원에서 모든 음료에 대한 f 값들을 알고 있으면.
 *  - f(n-1, Un-1) 만 계산하면 n - 1 번째 차원에서 모든 음료에 대한 f 값을 알게 됨.
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 음료의 종류 수
        int M = Integer.parseInt(st.nextToken());   // 차원 이동 횟수.

        int[] result = new int[N];      // 현재 M + 1 차원에서 이동할 때 결과. (i -> i)
        for (int i = 0; i < result.length; i++)
            result[i] = i;

        Stack<int[]> dimension = new Stack<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            // 차원 함수 : dimension[0] -> dimension[1] 의미.
            int[] newDimension = new int[] {
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            };

            dimension.push(newDimension);
        }

        //== 각 차원에 대하여 처리하여 result 구하기 (button-up)
        while(!dimension.isEmpty()) {
            int[] now = dimension.pop();

            result[now[0] - 1] = result[now[1] - 1];
        }

        //== 출력하기
        StringBuilder sb = new StringBuilder();
        for (int val: result) {
            sb.append(val + 1).append(' ');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}