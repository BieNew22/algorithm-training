/**
 * 가운데를 말해요
 *
 * 문제 정리 (우선순위큐)
 * 아이디어는 새로운 값이 들오얼 때 가운데 값이 이동하는 범위는 (-1 ~ 1) 임.
 * 가운데 값 기준으로 작은 값에 대하여 최대힙, 큰 값에 대하여 최소힙을 구현
 *  - 최대힙과 최소힙의 사이즈를 비교하여 쉽게 가운데 값 여부를 알 수 있음.
 *  - 이 때 이동하려는 방향 기준으로 가운데 값 보다 작은 가장 큰 값
 *  - 가운데 값 보다 큰 가장 작은 값으로 heap 을 통하여 쉽게 이동 가능.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());    // 백준이 외치는 정수의 개수

        StringBuilder sb = new StringBuilder();

        // minHeap : 현재 가운데 값 보다 큰 값을 저장.
        // maxHeap : 현재 가운데 값 보다 작은 값을 저장.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        //== 데이터 하나씩 입력 받아서 처리.
        int now = Integer.parseInt(br.readLine());
        sb.append(now).append('\n');

        for (int i = 1; i < N; i++) {
            int data = Integer.parseInt(br.readLine());

            if (data > now) {
                minHeap.add(data);
            } else {
                maxHeap.add(data);
            }

            // a now b, a now b b 인 경우 -> now 가 여전히 가운데 값.
            if (maxHeap.size() == minHeap.size()
                    || maxHeap.size() + 1 == minHeap.size()) {
                sb.append(now).append('\n');
                continue;
            }

            // a a now b 인 경우
            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(now);
                now = maxHeap.poll();
                sb.append(now).append('\n');
                continue;
            }

            // a now b b b 인 경우
            maxHeap.add(now);
            now = minHeap.poll();
            sb.append(now).append('\n');
        }

        System.out.println(sb);
    }


    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}