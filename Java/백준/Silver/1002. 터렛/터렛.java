//  터렛
//  문제 정리
//  두 원의 교점의 개수를 구하는 문제

import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < TC; i++) {
            // data = [x1, y1, r1, x2, y2, r2]
            int[] data = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            // 두 반 지름의 합과 차의 제곱
            int sumR = (data[2] + data[5]) * (data[2] + data[5]);
            int subR = (data[2] - data[5]) * (data[2] - data[5]);

            // 두 원의 중점의 거리의 제곱
            int diffX = data[0] - data[3];
            int diffY = data[1] - data[4];
            int dist = diffX * diffX + diffY * diffY;

            if (data[0] == data[3] && data[1] == data[4] && data[2] == data[5]) {
                // case0 : 두 원이 완전히 같은 경우
                sb.append("-1\n");
            } else if (dist > sumR || dist < subR) {
                // case1 : 교점이 0 개 인 경우
                // case1-1 : 두 원이 멀리 떨어진 경우
                // case1-2 : 한 원이 다른 원을 포함하는 경우.
                sb.append("0\n");
            } else if (dist == sumR || dist == subR) {
                // case2 : 접하는 경우
                // case2-1 : 두 원이 외부에서 접하는 경우
                // case2-2 : 한 원이 다른 원 내부에서 접하는 경우
                sb.append("1\n");
            } else {
                // case3 : 그 외, 모두 2개의 교점을 가짐.
                sb.append("2\n");
            }
        }

        System.out.println(sb);

    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}