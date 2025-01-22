/**
 * 열쇠
 * <p>
 * 문제 정리
 * 가장 먼저 떠오른게 OS에서의 Condition Variable 이었다.
 * 구현 + 탐색 문제
 * 16% 틀... 문제는 쉬우나 놓치기 쉬운 부분들이 너무 많았음...
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    /**
     * 지도의 시작 지점을 res에 추가
     *
     * @param map   탐색할 지도
     * @param res   저장할 저장소
     */
    void initStart(char[][] map, ArrayList<int[]> res) {
        int H = map.length;
        int W = map[0].length;

        for (int i = 0; i < H; i++) {
            res.add(new int[] {i, -1});
            res.add(new int[] {i, W});
        }

        for (int i = 0; i < W; i++) {
            res.add(new int[] {-1, i});
            res.add(new int[] {H, i});
        }
    }

    /**
     * bfs를 통하여 상근이가 훔칠 수 있는 문서의 개수 구하기
     *
     * @param haveKey   상근이가 이미 보유하고 있는 키
     * @param toVisit   상근이가 시작할 수 있는 지점들.
     * @param map       지도
     * @param H         지도 높이
     * @param W         지도 너비
     * @return  int (문서의 개수)
     */
    int getAns(boolean[] haveKey, ArrayList<int[]> toVisit, char[][] map, int H, int W) {
        int ans = 0;

        // 키 : 필요한 열쇠 값.
        // 값 : 해당 열쇠를 요구하는 좌표들.
        HashMap<Character, ArrayList<int[]>> waitKey = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++)
            waitKey.put(c, new ArrayList<>());

        int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        // bfs 탐색 시작.
        while (!toVisit.isEmpty()) {
            ArrayList<int[]> next = new ArrayList<>();

            for (int[] now: toVisit) {
                for (int[] dir: dirs) {
                    int ny = now[0] + dir[0];
                    int nx = now[1] + dir[1];

                    // 지도 밖으로 나감 경우 -> continue
                    if (ny < 0 || ny >= H)
                        continue;
                    if (nx < 0 || nx >= W)
                        continue;

                    if (map[ny][nx] == '*') {
                        // 현재 지점이 벽인 경우
                        continue;
                    } else if (map[ny][nx] == '$') {
                        // 훔칠 문서인 경우
                        ans += 1;

                        // 이!!!! 한 줄 때문에 또 1시간이...
                        next.add(new int[] {ny, nx});
                    } else if (Character.isUpperCase(map[ny][nx])) {
                        // 문을 만났을 경우.
                        // 해당 문에 대한 키 보유한 경우.
                        // 문을 열고이어서 진행.
                        // 없는 경우 대기 열에 넣기

                        char key = Character.toLowerCase(map[ny][nx]);

                        if (haveKey[key - 'a']) {
                            next.add(new int[]{ny, nx});
                        } else {
                            waitKey.get(key).add(new int[] {ny, nx});
                        }
                    } else if (Character.isLowerCase(map[ny][nx])) {
                        // 열쇠를 얻었을 경우.
                        // 열쇠 습득 후 해당 열쇠를 대기 중인 문들을 개방.

                        haveKey[map[ny][nx] - 'a'] = true;

                        // 개방한 문들을 next에 넣기
                        next.addAll(waitKey.get(map[ny][nx]));
                        waitKey.get(map[ny][nx]).clear();

                        // 현재 위치를 next에 넣기
                        // 이!!! 한 줄 때문에 내가 1시간이 ㄷㄷㄷ
                        next.add(new int[] {ny, nx});

                    } else {
                        // 빈 공간을 만났을 경우
                        next.add(new int[] {ny, nx});
                    }

                    // 현재 지점을 방문 처리
                    map[ny][nx] = '*';
                }
            }

            toVisit = next;
        }

        return ans;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());

        StringBuilder ans = new StringBuilder();

        while (TC-->0) {
            st = new StringTokenizer(br.readLine());

            // 데이터 입력 받기
            int H = Integer.parseInt(st.nextToken());   // 지도의 높이
            int W = Integer.parseInt(st.nextToken());   // 지도의 너비

            char[][] map = new char[H][];

            for (int i = 0; i < H; i++) {
                map[i] = br.readLine().toCharArray();
            }

            // 상근이가 이미 보유하고 있는 키들.
            boolean[] haveKey = new boolean[26];

            char[] keyData = br.readLine().toCharArray();
            for (char key: keyData) {
                if (key == '0')
                    continue;
                haveKey[key - 'a'] = true;
            }

            // BFS로 탐색할 지점들을 저장.
            // int[] = {y, x}
            ArrayList<int[]> toVisit = new ArrayList<>();
            initStart(map, toVisit);

            ans.append(getAns(haveKey, toVisit, map, H, W)).append("\n");
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}