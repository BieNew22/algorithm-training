/**
 * 문자열 압축 해제
 * <p>
 * 문제 정리
 * 단순 구현
 */

import java.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());            // 압축 방법 개수

        HashMap<Character, char[]> data = new HashMap<>();     // 대문자 및 대응하는 패턴 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char[] value = st.nextToken().toCharArray();
            data.put(st.nextToken().charAt(0), value);
        }

        char[] zip = br.readLine().toCharArray();           // 압축된 문장

        st = new StringTokenizer(br.readLine());

        // 압축되기 전 문자열의 부분 길이. 구간은 s - 1 <= i <= e - 1 폼하는 구간.
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        //== 정답구하기.
        char[] ans = new char[e - s + 1];

        int idx = 0; // ans 의 idx
        int nowLength = 0;  // 현재 압축푼 길이.
        for (char c: zip) {
            char[] unzip = data.get(c);

            int sIdx = nowLength;                   // 해당 문자 압축해제시 시작 인덱스
            int eIdx = nowLength + unzip.length;    // 해당 문자 압축해제시 끝 인덱스

            nowLength += unzip.length;

            if (eIdx < s) {
                // case0 : 압축 푼 부분이 너무 앞에 있는 경우.
                continue;
            }
            else if (sIdx < s) {
                // case1 : 압축 푼 부분에서 뒤 부분이 필요한 경우.
                // case1-1 : unzip 일부가 전체 s과 e를 포함하는 것에 대한 예외 처리 필요.
                for (int i = s - sIdx - 1; i < unzip.length; i++) {
                    ans[idx++] = unzip[i];

                    if (idx == ans.length)
                        break;
                }
            } else if (eIdx <= e) {
                // case2 : 압축 푼 부분 전체가 필요한 경우.
                for (char value : unzip) {
                    ans[idx++] = value;
                }
            } else if (sIdx < e){
                // case3 : 압축푼 부분 중 앞부분이 필요한 경우.
                for (int i = 0; i < e - sIdx; i++) {
                    ans[idx++] = unzip[i];
                }
            } else if (sIdx > e) {
                // case4 : 압축푼 부분이 너무 뒤에 있는 경우.
                break;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}