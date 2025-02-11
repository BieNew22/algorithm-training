/**
 * 팰린드롬 분할
 * <p>
 * 문제 정리 (DP가 2개인 문제)
 * 1) i 번째 문자를 끝으로 하는 팰린드롬의 시작 index을 모두 알고(=all(i)).
 *  - 이것 또한 i - 1 번째 문자를 끝으로 하는 팰린드롬의 시작 index을 알면 i 번째도 쉽게 구할 수 있음.
 *  
 * 2) 0 ~ k (k < i) 번째 문자를 팰린드롬으로 분할 한 최소 개수를 알면(= DP[0 ~ k]).
 * 
 * e) min( DP[0 ~ s - 1] + 1 for s in all(i) ) 로 구할 수 있음. 
 */

import java.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        char[] data = br.readLine().toCharArray();  // 문자열 데이터

        // DP[i] 의미 : 0 ~ i 문자를 팰린드롬으로 분할 한 최소 개수.
        int[] DP = new int[data.length];
        Arrays.fill(DP, Integer.MAX_VALUE);
        DP[0] = 1;

        // palindrome.get(i) 의미 : i 번째 문자를 끝으로 하는 팰린드롬의 시작 index 들.
        ArrayList<ArrayList<Integer>> palindrome = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            ArrayList<Integer> now = new ArrayList<>();
            now.add(i);
            palindrome.add(now);
        }

        //== 정답 구하기.
        for (int i = 1; i < data.length; i++) {
            // i 번째 문자에 대하여 해당 문자를 끝으로 하는 모든 팰린드롬 구하기.
            ArrayList<Integer> now = palindrome.get(i);

            if (data[i] == data[i - 1])
                now.add(i - 1);
            for (int before: palindrome.get(i - 1)) {
                if (before - 1 >= 0 && data[i] == data[before - 1]) {
                    now.add(before - 1);
                }
            }

            // 0 ~ i 번째 문자까지 팰린드롬으로 분할하는 최소 개수 구하기.
            for (int start: now) {
                if (start - 1 < 0) {
                    DP[i] = 1;
                } else {
                    DP[i] = Math.min(DP[i], DP[start - 1] + 1);
                }
            }
        }

        System.out.println(DP[data.length - 1]);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}