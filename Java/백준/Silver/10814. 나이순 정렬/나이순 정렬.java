import java.io.*;
import java.util.*;


public class Main {
    static class Person implements Comparable<Person> {
        int idx, age;
        String name;

        public Person(int i, int a, String n) {
            idx = i;
            age = a;
            name = n;
        }

        @Override
        public int compareTo(Person o) {
            if (age != o.age)
                return age - o.age;
            return idx - o.idx;
        }

        @Override
        public String toString() {
            return age + " " + name + "\n";
        }
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Person> data = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] d = br.readLine().split(" ");
            data.add(new Person(i, Integer.parseInt(d[0]), d[1]));
        }
        Collections.sort(data);

        StringBuilder sb = new StringBuilder();
        for (Person p: data)
            sb.append(p);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}
