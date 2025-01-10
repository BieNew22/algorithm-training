/**
 * 소수의 연속합
 * <p>
 * 문제 정리
 * 에라토스테네스의 체 + 투 포인터 문제
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.*;


public class Main {

    /**
     * 에라토스테네스의 체
     *
     * @param num 범위
     * @return 2 ~ num 까지의 소수 리스트
     */
    ArrayList<Integer> getPrimes(int num) {
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] isNotPrime = new boolean[num + 1];

        for (int i = 2; i <= num; i++) {
            if (isNotPrime[i])
                continue;

            res.add(i);

            for (int j = i + i; j <= num; j += i)
                isNotPrime[j] = true;
        }
        return res;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        // num에 더하기 1하는 이유 : num 이 1인 경우에 대한 예외처리
        // num이 1인 경우 getPrime은 빈 list를 반환하게 됨.
        ArrayList<Integer> primes = getPrimes(num + 1);

        int ans = 0;

        // 투 포인터를 이용한 탐색
        int total = primes.get(0), s = 0, e = 1;

        while (e < primes.size()) {
            if (total <= num) {
                if (total == num)
                    ans += 1;
                total += primes.get(e);
                e += 1;
            } else{
                total -= primes.get(s);
                s += 1;
            }
        }

        while (total >= num) {
            if (total == num)
                ans += 1;
            total -= primes.get(s);
            s += 1;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}