/**
 * 2021은 무엇이 특별할까?
 * <p>
 * 문제 정리
 * 소수 구하는 수학 문제
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


public class Main {

    /**
     * Sieve of Eratosthenes
     *
     * @param num 탐색할 범위
     * @return 소수가 담겨진 ArrayList
     */
    ArrayList<Integer> getPrime(int num) {
        ArrayList<Integer> prime = new ArrayList<>();

        boolean[] isPrime = new boolean[num + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i <= num; i++) {
            if (!isPrime[i])
                continue;

            prime.add(i);
            for (int j = i * 2; j <= num; j += i)
                isPrime[j] = false;
        }

        return prime;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> primes = getPrime(N + 10);

        for (int i = 0; i < primes.size() - 1; i++) {
            int num = primes.get(i) * primes.get(i + 1);

            if (num > N) {
                System.out.println(num);
                break;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}