import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JLIS {
    static int[] A;
    static int[] B;
    static int[][] cache;
    static int n, m;
    static long NEG_INF = Long.MIN_VALUE;
    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());
        for (int c=0; c<C; c++) {
            String[] input = br.readLine().split(" ");
            n = Integer.parseInt(input[0]);
            m = Integer.parseInt(input[1]);
            A = new int[n];
            B = new int[m];
            cache = new int[n+1][m+1];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i=0; i<n; i++) {
                A[i] = Integer.parseInt(st.nextToken());
                Arrays.fill(cache[i], -1);
            }
            Arrays.fill(cache[n], -1);
            st = new StringTokenizer(br.readLine(), " ");
            for (int i=0; i<m; i++) B[i] = Integer.parseInt(st.nextToken());
            sb.append(getJLIS(-1, -1) - 2);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static int getJLIS(int a, int b) {
        if (cache[a+1][b+1] != -1) return cache[a+1][b+1];
        long cur_a = a == -1 ? NEG_INF : A[a];
        long cur_b = b == -1 ? NEG_INF : B[b];
        long maxElement = Math.max(cur_a, cur_b);
        int ret = 2;
        for (int i=a+1; i<n; i++) {
            if (maxElement < A[i]) ret = Math.max(ret, 1 + getJLIS(i, b));
        }
        for (int i=b+1; i<m; i++) {
            if (maxElement < B[i]) ret = Math.max(ret, 1 + getJLIS(a, i));
        }
        return cache[a+1][b+1] = ret;
    }
}
