/**
 * 선 긋기
 * <p>
 * 문제 정리 (스위핑)
 * 기본적인 스위핑 문제
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    // 그릴선의 시작위치와 끝 위치를 나타내는 Object
    static class Line implements Comparable<Line> {
        int s, e;   // s : 선의 시작 위치, e : 선의 끝 위치

        public Line(int x, int y) {
            this.s = x;
            this.e = y;
        }

        @Override
        public int compareTo(Line o) {
            return Integer.compare(this.s, o.s);
        }
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        //== 입력 받기 및 초기화
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());    // 선을 그은 횟수

        Line[] draws = new Line[N]; // 각 그은 선의 정보를 저장한 리스트
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            draws[i] = new Line(s, e);
        }

        Arrays.sort(draws); // 선의 시작 위치 기준 정렬

        //== 선의 총 길이 구하기
        int ans = 0;
        int s = draws[0].s, e = draws[0].e;

        for (Line d: draws) {
            // 각 선 그리기.

            if (d.s <= e) {
                // 현재 그리는 선이 과거에 그린 선에 겹치면 두 선을 연결하기.
                e = Math.max(e, d.e);
            } else {
                // 현재 그리는 선이 과거에 그린 선 밖에 있는 경우.
                // 데이터가 시작 위치 기준으로 정렬되어서 과거에 그린 선은 그걸로 길이 확정 됨.
                ans += e - s;

                s = d.s;
                e = d.e;
            }
        }

        ans += e - s;

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}