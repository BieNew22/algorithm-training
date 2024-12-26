//  용액
//  문제 정리
//  arr1 : 98, 96, 44, 0
//  arr2 : 97. 94, 23, 2
//  내림 차순으로 정렬된 두 배열의 원소 차가 가장 작은 두 원소를 구하기와 같은 문제

import java.lang.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] data = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong).toArray();

        // case1 : 모든 용액이 산성이거나, 용액이 2개인 경우
        if (data[0] >= 0 || N == 2) {
            System.out.println(data[0] + " " + data[1]);
            return;
        }

        // case2 : 모든 용액이 알칼리성 인 경우
        if (data[N - 1] <= 0) {
            System.out.println(data[N - 2] + " " + data[N - 1]);
            return;
        }

        // 그 외 : 두 포인터로 탐색
        long value = Long.MAX_VALUE, ans1 = 0, ans2 = 0;

        int left = 0, right = N - 1;
        while (left < right) {
            long mix = Math.abs(data[left] + data[right]);

            if (mix <= value) {
                value = mix;
                ans1 = data[left];
                ans2 = data[right];
            }

            // 더 큰 값을 가진 인덱스를 변경
            if (Math.abs(data[left]) < Math.abs(data[right]))
                right -= 1;
            else
                left += 1;

        }

        System.out.println(ans1 + " " + ans2);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}