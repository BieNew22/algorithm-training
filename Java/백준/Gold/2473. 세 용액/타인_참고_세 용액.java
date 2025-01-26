/**
 * 세 용액
 * <p>
 * 참고 : https://www.acmicpc.net/source/86750525
 * 두 포인터를 적용한 풀이.
 * 하나의 용액을 포함한 상태에서 최소 값을 구하기.
 * i 번짹 용액을 포함할 때 이미 i - 1 번째 까지는 모든 포함할 때 얻을 수 있는 최소 값을 구함.
 * 즉, i 번째 용액을 포함한 최소 값을 구할 때 0 ~ i - 1 번째 용액은 포함 할 수 없음.
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        int N = Integer.parseInt(br.readLine());    // 데이트 크기
        long[] data = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong).toArray();
        Arrays.sort(data);  // 오름 차순으로 정렬, 이진 탐색 용.

        int ansLow = 0, ansMid = 0, ansHigh = 0;
        long total = Long.MAX_VALUE;

        for (int low = 0; low < N - 1; low++) {
            int mid = low + 1;
            int high = N - 1;

            while (mid < high) {
                long mix = data[low] + data[mid] + data[high];

                if (Math.abs(mix) < total) {
                    total = Math.abs(mix);
                    ansLow = low;
                    ansMid = mid;
                    ansHigh = high;
                }

                if (mix > 0) {
                    high -= 1;
                } else {
                    mid += 1;
                }
            }
        }

        System.out.printf("%d %d %d", data[ansLow], data[ansMid], data[ansHigh]);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}