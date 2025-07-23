import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TrianglePath {
    static int[][] path;
    static int[][] cache;
    static int n;
    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int C = Integer.parseInt(br.readLine());
        for (int c=0; c<C; c++) {
            n = Integer.parseInt(br.readLine());
            path = new int[n][n];
            cache = new int[n][n];
            for (int i=0; i<n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int j = 0;
                Arrays.fill(cache[i], -1);
                while (st.hasMoreTokens()) path[i][j++] = Integer.parseInt(st.nextToken());
            }
            System.out.println(getMax(0, 0));
        }
    }

    static int getMax(int y, int x) {
        if (y == n) return 0;
        if (cache[y][x] != -1) return cache[y][x];
        cache[y][x] = Math.max(getMax(y+1, x), getMax(y+1,x+1)) + path[y][x];
        return cache[y][x];
    }
}
