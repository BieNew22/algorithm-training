/**
 * 수 나누기 게임
 * <p>
 * 문제 정리
 * 문제 자체는 어렵지 않음. 최적화 문제
 * N max : 100,000
 * x max : 1,000,000
 * O(xlogx) 로 생각하는 과정이 좀 어려웠음.
 * -> 각 수의 배수를 구하는 문제로 보면 쉽게 떠올릴 수 있는데, 그 과정이 어려웠음.
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        br.readLine();  // data 크기를 입력 받음. 사용하지 않는 데이터

        int[] data = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        // 각 수에 대하여 자신의 점수 획득 정보를 저장
        HashMap<Integer, Integer> ans = new HashMap<>();

        // 탐색할 최대 범위.
        int dataMax = 0;

        // ans, data max 초기화
        for (int d: data) {
            ans.put(d, 0);
            dataMax = Math.max(dataMax, d);
        }

        // 각 데이터에 대하여 자신의 배수 중 포함되어 있는 데이터를 확인.
        // d는 배수의 개수만큼 점수 플러스 함.
        for (int d: data) {
            for (int i = d * 2; i <= dataMax; i += d) {
                if (ans.containsKey(i)) {
                    ans.put(d, ans.get(d) + 1);
                    ans.put(i, ans.get(i) - 1);
                }
            }
        }

        // 출력하기
        StringBuilder sb = new StringBuilder();
        for (int d: data) {
            sb.append(ans.get(d)).append(" ");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}