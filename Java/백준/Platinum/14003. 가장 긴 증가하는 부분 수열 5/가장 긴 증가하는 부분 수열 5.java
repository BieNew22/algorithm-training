/**
 * 가장 긴 증가하는 부분 수열 5
 * <p>
 * LIS O(NlogN) 문제
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] data = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        // 각 data[i]로 끝나는 lis 길이를 저장
        int[] DP = new int[N];

        List<Integer> lis = new ArrayList<>();
        lis.add(data[0]);
        DP[0] = 0;

        // 각 data[i]로 끝나는 lis 길이를 저장
        for (int i = 0; i < N; i++) {
            int num = data[i];

            if (num > lis.get(lis.size() - 1)) {
                lis.add(num);
                DP[i] = lis.size() - 1;
            } else if (num < lis.get(lis.size() - 1)) {
                int idx = Collections.binarySearch(lis, num);

                if (idx >= 0) {
                    lis.set(idx, num);
                    DP[i] = idx;
                } else {
                    lis.set(-(idx + 1), num);
                    DP[i] = -(idx + 1);
                }
            } else {
                DP[i] = lis.size() - 1;
            }
        }

        // 최종 LIS 길이 구하기
        int lastIdx = lis.size() - 1;
        Stack<Integer> resLis = new Stack<>();

        for (int i = N - 1; i >= 0; i--) {
            if (DP[i] == lastIdx) {
                resLis.add(data[i]);
                lastIdx -= 1;
            }
        }

        // 최종 출력하기
        StringBuilder sb = new StringBuilder();
        sb.append(lis.size()).append("\n");

        while (!resLis.isEmpty())
            sb.append(resLis.pop()).append(" ");

        System.out.println(sb);

    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}