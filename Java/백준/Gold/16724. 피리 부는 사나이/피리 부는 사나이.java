/**
 * 피리 부는 사나이
 * <p>
 * 문제 정리
 * DFS + 사이클 탐지 문제.
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    /**
     * dfs 탐색하면 SAFE ZONE 설치 여부를 결정.
     *
     * @param y             시작 y 위칙
     * @param x             시작 x 위치
     * @param W             좌표에 대한 키 값을 만들기 위함. (= 지도의 열 개수)
     * @param map           지도
     * @param beforeVisit   과거에 방문한 지점.
     * @return              SAFE ZONE 설치 여부를 반환. true(설치), false(이미 설치 됨)
     */
    boolean dfs(int y, int x, int W, char[][] map, HashSet<Integer> beforeVisit) {
        HashSet<Integer> nowVisit = new HashSet<>();

        boolean res = true;
        while (true) {
            // 현재 좌표에 대한 키 값
            int key = y * W + x;
            // 현재 지점이 이미 과거에서 방문 여부
            // 방문 함 -> 현재 지점에서 SAFE ZONE으로 이동 가능.
            if (beforeVisit.contains(key)) {
                res = false;
                break;
            }

            // 현재 좌표에 대하여 현재 방문 리스트에 포함 됨.
            // -> 해당 좌표에서 SAFE ZONE으로 이동 불가능.
            // -> SAFE ZONE 설치 해야 함.
            if (nowVisit.contains(key)) {
                res = true;
                break;
            }

            // 현재 좌표를 방문 함.
            nowVisit.add(key);

            // 다음 위치로 이동하기.
            switch(map[y][x]) {
                case 'D' -> y += 1;
                case 'U' -> y -= 1;
                case 'R' -> x += 1;
                case 'L' -> x -= 1;
            }
        }

        // 현재 방문을 과거 방문에 합치기
        beforeVisit.addAll(nowVisit);

        return res;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        char[][] map = new char[H][];
        for (int i = 0; i < H; i++)
            map[i] = br.readLine().toCharArray();

        // 각 좌표에 대한 방문 여부를 저장.
        // 각 좌표(y, x)는 y * W + x 를 통하여 고유의 키를 생성 가능.
        HashSet<Integer> visitSet = new HashSet<>();

        int ans = 0;

        // 각 위치에 대하여 dfs 진행
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (visitSet.contains(i * W + j))
                    continue;

                if(dfs(i, j, W, map, visitSet)) {
                    ans += 1;
                }
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}