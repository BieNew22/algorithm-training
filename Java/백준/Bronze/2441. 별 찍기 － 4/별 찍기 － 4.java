/**
 * 별 찍기 - 4
 * <p>
 * 문제 정리
 * 쉬어 가는날...
 * 포트폴리어 작성 너무 어렵다... 뭔가 한게 많은 거 같은데...
 * 정리하면 별게 없네...
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());    // 첫줄의 별의  개수.

        StringBuilder sb = new StringBuilder();

        for (int i = N; i > 0; i--) {
            sb.append(" ".repeat(N - i)).append("*".repeat(i)).append('\n');
        }

        System.out.println(sb);
    }


    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}