/**
 * 내 뒤에 나와 다른 수
 * <p>
 * 문제 정리 (구현)
 * 자신(i)의 뒤에서 가장 가까운 다른수의 인덱스(j)를 구하는 문제.
 * j = i + k 이라는 의미는 다음과 같음.
 * - i ~ i + k - 1 까지의 값은 동일 함.
 * - 즉, i ~ i + k - 1의 가강 가까운 다른수의 인데스는 모두 j 가 됨.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());    // 수열의 길이

        // 수열 데이터
        int[] data = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int[] ans = new int[N]; // 자신의 뒤에서 가장 가까운 다른 정수의 인덱스 저장.
        Arrays.fill(ans, -1);   // 기본 값은 조건을 만족하는 인덱스 없다고 가정.


        int s = 0, e = 1;   // s : 찾을 인덱스, e : 찾은 인덱스

        //== 뒤에서 가장 가까운 다른 정수의 인덱스 구하기.
        while (e < N) {
            if (data[s] == data[e]) {
                e++;
            } else {
                // 찾을 인덱스의 수와 다른 정수의 인덱스를 찾은 경우.
                // s ~ e - 1 까지의 모든 수를 해당 e + 1(시작 인덱스 값이 1이여서 더하기 1)로 채움.
                for (int i = s; i < e; i++) {
                    ans[i] = e + 1;
                }

                s = e;
                e = s + 1;
            }
        }

        //== 해 출력하기.
        StringBuilder sb = new StringBuilder();

        for (int a : ans) {
            sb.append(a).append(' ');
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}