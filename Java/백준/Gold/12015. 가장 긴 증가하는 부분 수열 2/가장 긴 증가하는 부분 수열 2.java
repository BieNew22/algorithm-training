/**
 * 가장 긴 증가하는 부분 수열 2
 * <p>
 * 문제 정리
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

        List<Integer> lis = new ArrayList<>();
        lis.add(data[0]);

        for (int num: data) {
            if (num > lis.get(lis.size() - 1)) {
                lis.add(num);
            } else if (num < lis.get(lis.size() - 1)) {
                int idx = Collections.binarySearch(lis, num);

                if (idx >= 0) {
                    lis.set(idx, num);
                } else {
                    lis.set(-(idx + 1), num);
                }
            }
        }

        System.out.println(lis.size());
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}