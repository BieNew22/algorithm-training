/**
 * blobnom
 * <p>
 * 문제 정리
 * a b c d e 이라는 데이터가 존재할 때
 * c가 가질 수 있는 가장 높은 높이는 c + min(b, d) 이다.
 * 이 때 b또는 d를 키워서 min(b, d)의 최대를 구하자고 할 떄
 * b또는 d를 k 만큼 키우면, c 또한 k 만큼 감소함.
 * b를 키울 때
 *  b > d 인 경우 : c - k + min(b + k, d) = c - k + d
 *  b < d 인 경우 : c - k + min(b + k, d)
 *      b + k < d 인경우 : c - k + b + k
 *      b + k > d 인경우 : c - k + d
 * 결국 c가 가질 수 있는 가장 높은 높이는 초기 상태에서 c + min(b, d) 가 됨.
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        int N = Integer.parseInt(br.readLine());
        int[] data = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        // 시작 탑과 끝 탑이 가장 높은 경우.
        int ans = Math.max(data[0], data[N - 1]);

        // 중간 탑이 가장 높게 만드는 경우.
        for (int i = 1; i < N - 1; i++) {
            ans = Math.max(ans, data[i] + Math.min(data[i - 1], data[i + 1]));
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}