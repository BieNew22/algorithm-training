/**
 * 알파벳 개수
 * <p>
 * 문제 정리 (단순 구현)
 * 당분간 쉬어가는 날...
 */

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        char[] data = br.readLine().toCharArray();    // 단어 s
        int[] cnt = new int[26];    // cnt[0] = a ... cnt[25] = z 로 각 문자의 출현 횟수

        //== 각 문자의 개수 계산하기
        for (char c: data)
            cnt[c - 'a'] += 1;

        //== 출력
        StringBuilder sb = new StringBuilder();
        for (int c: cnt)
            sb.append(c).append(' ');
        System.out.println(sb);

    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}