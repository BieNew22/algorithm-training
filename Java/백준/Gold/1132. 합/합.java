/**
 * 합
 * <p>
 * 문제 정리 (그리디)
 * 모든 숫자를 더 했을 때 최대 값을 구하자고 함.
 * 이 때 각 자연수 ABC = A * 100 + B * 10 + C 로 나눌 수 있고.
 * TC ABC, BCA 에 대하여 가중치를 구하면 다음과 같고.
 * A(101), B(110), C(11)
 * 내림차순으로 9부터 할당하면 모든 숫자의 합 중 최댓값이 됨.
 *  - B = 9, A = 8, C = 7
 * 주의 : 모든 숫자의 첫 알파벳은 0이 될 수 없음.
 */

import java.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Main {

    static class Weight implements Comparable<Weight> {
        long val;   // 가중치
        int num;    // 가중치에 따른 할당 받을 숫자. -1은 0이 안됨을 의미.

        @Override
        public int compareTo(Weight o) {
            return Long.compare(this.val, o.val);
        }
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());        // 숫자의 개수.

        ArrayList<char[]> data = new ArrayList<>();     // 각 숫자를 저장.
        for (int i = 0; i < N; i++) {
            data.add(br.readLine().toCharArray());
        }

        // 각 문자에 대한 가중치를 저장.
        HashMap<Character, Weight> hashMap = new HashMap<>();
        Weight[] weights = new Weight[10];  // 가중치에 따라서 할당 시킬 숫자 결정 위함.

        for (int i = 0; i < 10; i++) {
            weights[i] = new Weight();
            hashMap.put((char) (i + 'A'), weights[i]);
        }

        long[] tenPower = new long[12];     // 십의 제곱들을 저장함.
        tenPower[0] = 1;
        for (int i = 1; i < tenPower.length; i++) {
            tenPower[i] = tenPower[i - 1] * 10;
        }

        //== 가중치 구하기.
        for (char[] num: data) {
            for (int i = 0; i < num.length; i++) {
                hashMap.get(num[i]).val += tenPower[num.length - i - 1];

                // 해당 문자에 0을 할당 시키면 안됨을 의미.
                if (i == 0) {
                    hashMap.get(num[i]).num = -1;
                }
            }
        }

        //== 정렬 후 각 알파벳에 숫자 부여하기.
        Arrays.sort(weights); // 가중치 기준 오름차순.

        boolean zero = true;
        int giveNum = 1;
        for (int i = 0; i < 10; i++) {
            // 현재 숫자에 0을 할당 시킬 수 있으면 할당.
            // 할당 할 수 없으면 1 부터 오름차순으로 순차적으로 할당.
            if (zero && weights[i].num != -1) {
                weights[i].num = 0;
                zero = false;
            } else {
                weights[i].num = giveNum;
                giveNum++;
            }
        }

        //== 모든 합 구하기.
        long sum = 0;
        for (char[] num: data) {
            long tmp = 0;
            for (char c: num) {
                tmp = tmp * 10 + hashMap.get(c).num;
            }
            sum += tmp;
        }

        System.out.println(sum);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}