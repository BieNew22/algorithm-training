/**
 * 단어 공부
 * <p>
 * 문제 정리
 * 간단한 문자열 처리 문제, 오늘은 쉬어가는 날
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        char[] data = br.readLine().toUpperCase().toCharArray();    // 주어진 단어를 대문자로 교체

        int[] count = new int[26];  // 각 문자의 출현 횟수를 저장하는 배열

        //== 각 문자의 출현 횟수를 구하기.
        for (char c: data)
            count[c - 'A'] += 1;

        //== 해구하기.
        char ans = 'A';     // 가장 많이 출현한 문자.
        int cnt = 0;        // 가장 많이 출현한 횟수.

        for (int i = 0; i < count.length; i++) {
            if (cnt == count[i]) {
                ans = '?';
            } else if (cnt < count[i]) {
                cnt = count[i];
                ans = (char) ('A' + i);
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}