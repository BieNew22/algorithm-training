/**
 * 강의실 배정
 * <p>
 * 문제 정리
 * 그리디 문제
 * 기존 강의실 배정이랑 다른 점 -> 모든 강의를 강의실에 배치해야 함.
 */

import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        int N = Integer.parseInt(br.readLine());

        ArrayList<int[]> data = new ArrayList<>(N + 1);
        for (int i = 0;i < N; i++) {
            data.add(Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray());
        }

        // 1. 강의 시작 시간 기준 오름 차순.
        // 2. 강의 종료 시간 기준 오름 차순.
        data.sort((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o1[0], o2[0]);
        });

        // 현재 각 강의실의 강의 종료 시간.
        PriorityQueue<Integer> room = new PriorityQueue<>();

        for (int[] d: data) {
            // 지금 강의를 가장 빨리 끝나는 강의실에 배치 가능한지 확인.
            if (!room.isEmpty() && d[0] >= room.peek()) {
                // 가능 -> 지금 강의실을 해당 강의실에 배치
                room.poll();
                room.add(d[1]);
            } else {
                // 불가능 -> 새로운 강의실이 필요함. 따라서 강의실 새로 할당하여 배치.
                room.add(d[1]);
            }
        }

        System.out.println(room.size());
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}