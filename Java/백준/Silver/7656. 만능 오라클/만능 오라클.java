/**
 * 만능 오라클
 * <p>
 * 문제 정리
 * 문자열 처리 문제. 생각 보다 조건을 좀 더 섬세히 고려해야 했던거 같음.
 * "--What is?" 같은 데이터를 고려 했어야 함.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        // 데이터 조건
        // 1. 모든 문장이 시작 문자만 대문자임.
        // 2. 문장 끝이 . 또는 ? 으로 만 끝남.
        // 3. 모든 질문의 시작은 What is, 끝은 ? 이다.
        char[] data = br.readLine().toCharArray();

        //== 질문 쿼리 처리하기.
        StringBuilder res = new StringBuilder();    // 최종 리턴 값.
        StringBuilder now = new StringBuilder();    // 현재 문자열.


        for (char c: data) {
            if (Character.isUpperCase(c) || c == '.') {
                // 새로운 문장 시작. 또는 질문이 아닌 경우 문장 초기화
                now.setLength(0);
            }
            if (c == '?') {
                // 해당 문장의 시작이 What is 인지 확인.
                String question = now.append('.').toString();

                if (question.startsWith("What is")) {
                    res.append(question.replace("What", "Forty-two"));
                    res.append('\n');
                }
            }
            now.append(c);
        }

        System.out.println(res);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}