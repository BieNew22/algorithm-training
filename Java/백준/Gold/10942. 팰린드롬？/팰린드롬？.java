/**
 * 팰린드롬?
 * <p>
 * 문제 정리
 * DP + divided and conquer 문제
 *
 * i 번째 원소를 끝으로 하는 모든 팰린드롬[P(i)]을 알 고 있다면.
 * i + 1 번째 원소를 끝으로 하는 모든 팰린 드롬은 쉽게 구 할 수 있음.
 * - O((i + 1) ^ 2) 가 아닌 O(len(P(i))) 시간 복잡도에 구할 수 있음.
 *
 * 구하는 방법.
 * 012345
 * abcbba 이라는 데이터에 대하여
 *
 * 마지막 b에 대하여 b로 끝나는 팰린드롬은 b, bb, 이고 따라서 P(4) = [4, 3] 이 된다고 이미 알고 있다면.
 * 마지막 a에 대하여서는 abcbba, bcbba, cbba, bba ba가 팰린드롬 여부를 확인할 필요 없이.
 * ba, bba, cbba 대해서만 팰린드롬 여부를 확인 하면 됨.
 * -> 확인하는 방법도 문자열 전체 순환 필요 없이 문자열의 끝 2개가 동일 여부만 확인 하면 됨.
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {


    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 데이터 입력 받기
        int N = Integer.parseInt(br.readLine());
        int[] data = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        // 데이터 DP 구축하기
        ArrayList<HashSet<Integer>> DP = new ArrayList<>();

        // 첫 데이터는 무조건 자기자신만 존재함.
        DP.add(new HashSet<>());
        DP.get(0).add(0);

        for (int i = 1; i < N; i++) {
            HashSet<Integer> now = new HashSet<>();
            now.add(i);

            if (data[i] == data[i - 1])
                now.add(i - 1);

            for (int p: DP.get(i - 1)) {
                if (p - 1 >= 0 && data[p - 1] == data[i]) {
                    now.add(p - 1);
                }
            }
            DP.add(now);
        }

        // 각 쿼리 입력 받기
        StringTokenizer st;

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;

            if (DP.get(e).contains(s)) {
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}