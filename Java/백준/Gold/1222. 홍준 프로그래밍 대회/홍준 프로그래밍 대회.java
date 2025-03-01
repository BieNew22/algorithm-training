/**
 * 홍준 프로그래밍 대회
 * <p>
 * 문제 정리 (수학)
 * - 각 수의 약수를 구하기.
 * - 나온 약수에 대하여 출현 횟수 구하기.
 * - 약수 값 * 출현 횟수가 가장 큰 값 구하기. (출현 횟수 >= 2)
 * ---- 최댓값에 대하여 항상 int 범위 넘는지 확인할 것...
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    /**
     * people의 약수 구하여 count에 추가.
     *
     * @param count     각 약수의 출현 횟수를 저장한 HashMap
     * @param people    현재 탐색할 학교의 출전 인원 수
     * @param dup       현재 탐색할 학교와 동일한 출전 인원 수를 가진 학교의 개수
     */
    void setCount(HashMap<Integer, Integer> count, int people, int dup) {
        for (int div = 1; div * div <= people; div++) {
            if (people % div != 0)
                continue;

            int div2 = people / div;

            count.put(div, count.getOrDefault(div, 0) + dup);

            if (div != div2) {
                count.put(div2, count.getOrDefault(div2, 0) + dup);
            }
        }
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());        // 참여한 학교 수

        int[] data = Arrays.stream(br.readLine().split(" "))    // 각 학교 참여 인원 수
                .mapToInt(Integer::parseInt).toArray();
        Arrays.sort(data);

        // 각 약수의 출현 횟수를 저장.
        HashMap<Integer, Integer> count = new HashMap<>();

        //== 각 학교가 참여할 수 있는 팀 크기에 넣기.
        int now = 0;
        while (now < N) {
            int dup = 0;    // 중복된 팀원 수.
            int tmp = now;

            // 현재 학교 참여 수와 같은 학교의 개수 계산.
            while (tmp < N && data[now] == data[tmp]) {
                dup += 1;
                tmp += 1;
            }

            setCount(count, data[now], dup);
            now += dup;
        }

        //== 최소 2팀 이상 본선에 참여가능한 최대 인원수 구하기
        long ans = 0;
        for (int key: count.keySet()) {
            if (count.get(key) < 2)
                continue;
            ans = Math.max(ans, (long) key * count.get(key));
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}