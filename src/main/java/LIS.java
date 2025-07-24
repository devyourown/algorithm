import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LIS {
    static int[] seq;
    static int[] cache;
    static int N;
    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int C = Integer.parseInt(br.readLine());
        StringBuilder ret = new StringBuilder();
        for (int c=0; c<C; c++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            seq = new int[N];
            cache = new int[N];
            Arrays.fill(cache, -1);
            for (int i=0; i<N; i++) seq[i] = Integer.parseInt(st.nextToken());
            ret.append(getLIS(-1) - 1 + "\n");
        }
        System.out.print(ret);

    }

    static int getLIS(int index) {
        if (index == N) return 0;
        if (index != -1 && cache[index] != -1) return cache[index];
        int ret = 1;
        for (int i=index+1; i<N; i++) {
            if (index == -1 || seq[index] < seq[i]) ret = Math.max(ret, 1+getLIS(i));
        }
        if (index == -1) return ret;
        return cache[index] = ret;
    }
}
