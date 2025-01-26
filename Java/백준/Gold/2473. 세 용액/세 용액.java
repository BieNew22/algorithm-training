/**
 * 세 용액
 * <p>
 * 문제 정리
 * 완전 탐색 + 이진 탐색 (가능 이유: 모든 용액의 특성 값이 다름)
 * O(N^2logN)
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    /**
     * 이미 선택한 2개의 용액에서 합이 0와 가장 가까이 되는 idx 탐색.
     *
     * @param data      전체 용액 데이터
     * @param value     이미 선택한 2개의 용액의 합.
     * @return
     */
    int getMinIdx(long[] data, long value) {
        // 이진 탐색.
        int idx = Arrays.binarySearch(data, -value);

        // 0이 되는 정확한 값이 존재.
        if (idx >= 0)
            return idx;

        // 0이 되는 정확한 값이 존재X -> 근사 위치
        idx = -(idx + 1);

        // 0이 되기 위하여 data의 가장 큰 값 필요 함.
        if (idx == data.length) {
            return idx - 1;
        }

        // 그 외인 경우 : idx 주위에서 value로 부터 더욱 가까운 idx 찾아야 함..
        // ex: {-97, -6, -2, 6, 98} -> -1 를 찾는 경우 초기 idx = 3(data[3] = 6)
        // 근데 -1에는 -2 가 6보다 더 가까움 따라서 최적의 idx는 2이여야 함.
        // 즉, idx, idx - 1, idx + 1 중에서 최적을 구해야 함.
        // 주의 : idx가 0일 때 idx - 1, N - 1일 때 idx + 1 는 무시해야 함.
        long dis1 = Math.abs(-value - data[idx]);
        long dis2 = idx + 1 < data.length ? Math.abs(-value - data[idx + 1]) : Long.MAX_VALUE;
        long dis3 = idx - 1 >= 0 ? Math.abs(-value - data[idx - 1]) : Long.MAX_VALUE;

        if (dis1 <= dis2 && dis1 <= dis3)
            return idx;
        else if (dis2 <= dis1 && dis2 <= dis3)
            return idx + 1;
        return idx - 1;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        int N = Integer.parseInt(br.readLine());    // 데이트 크기
        long[] data = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong).toArray();
        Arrays.sort(data);  // 오름 차순으로 정렬, 이진 탐색 용.

        long[] ans = new long[3];       // 선택한 3개의 용액
        long value = Long.MAX_VALUE;    // 현재 선택한 3개의 용액의 합.

        for (int left = 0; left < N; left++) {
            for (int right = 0; right < N; right++) {
                if (left == right)
                    continue;

                long mix = data[left] + data[right];

                // 3개 합이 0이 되는 최소 idx 값 탐색.
                int idx = getMinIdx(data, mix);

                if (idx == left || idx == right)
                    continue;

                mix = Math.abs(mix + data[idx]);

                if (mix <= value) {
                    value = mix;
                    ans[0] = data[left];
                    ans[1] = data[right];
                    ans[2] = data[idx];
                }
            }
        }

        // 정답 정렬 후 출력.
        Arrays.sort(ans);
        System.out.printf("%d %d %d", ans[0], ans[1], ans[2]);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}