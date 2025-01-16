/**
 * 소트인사이드
 * <p>
 * 문제 정리
 * 단순한 문자열 정열 문제
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] data = br.readLine().toCharArray();

        Arrays.sort(data);

        StringBuilder sb = new StringBuilder();
        for (int i = data.length - 1; i >= 0; i--)
            sb.append(data[i]);

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}