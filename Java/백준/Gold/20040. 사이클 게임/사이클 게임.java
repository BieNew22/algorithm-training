/**
 * 사이클 게임
 * <p>
 * 문제 정리
 * union&find 문제
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.StringTokenizer;


public class Main {

    int getParent(int s, int[] parent) {
        if (s == parent[s])
            return s;

        parent[s] = getParent(parent[s], parent);

        return parent[s];
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int ans = 0;

        int[] parent = new int[N];
        for (int i = 0; i < N; i++)
            parent[i] = i;

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            int v1Parent = getParent(v1, parent);
            int v2Parent = getParent(v2, parent);

            if (v1Parent == v2Parent) {
                ans = i;
                break;
            } else if (v1Parent > v2Parent) {
                parent[v1Parent] = v2Parent;
            } else {
                parent[v2Parent] = v1Parent;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}