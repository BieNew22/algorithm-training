/**
 * 두 배열의 합
 * <p>
 * 문제 정리
 * 누적합느낌 문제
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    /**
     * 모든 부배열의 합 구하기
     *
     * @param data 배열 전체
     * @return 모든 부배열의 경우와 경우의 수를 저장한 HashMap
     */
    public HashMap<Integer, Integer> makeNumbers(int[] data) {
        // key : 만들 수 있는 숫자, value : 만들 수 있는 경우의 수
        HashMap<Integer, Integer> result = new HashMap<>();

        for (int s = 0; s < data.length; s++) {
            int subSum = 0;
            for (int e = s; e < data.length; e++) {
                subSum += data[e];

                result.put(subSum, result.getOrDefault(subSum, 0) + 1);
            }
        }

        return result;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        int N = Integer.parseInt(br.readLine());

        int aSize = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int bSize= Integer.parseInt(br.readLine());
        int[] B = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        // 각 데이터 배열로 만들 수 있는 숫자 조합 구하기
        HashMap<Integer, Integer> numByA = makeNumbers(A);
        HashMap<Integer, Integer> numByB = makeNumbers(B);

        long ans = 0;
        for (int numA: numByA.keySet()) {
            int numB = N - numA;

            if (numByB.containsKey(numB)) {
                ans += (long) numByA.get(numA) * numByB.get(numB);
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}