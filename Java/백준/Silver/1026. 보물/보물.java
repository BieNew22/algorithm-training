/**
 * 보물
 * <p>
 * 문제 정리 (그리디)
 * A와 B의 원소는 양수이다.
 * 따라서 S가 최소가 되기 위해서는 B의 가장 큰수에 A의 가장 작은 수를 매칭 시키면 됨.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int[] B = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        Arrays.sort(A);
        Arrays.sort(B);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += A[i] * B[N - 1 - i];
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}