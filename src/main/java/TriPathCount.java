import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TriPathCount {
    static int[][] board;
    static int[][] cache;
    static int[][] cntCache;
    static int n;
    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int C = Integer.parseInt(br.readLine());
        for (int c=0; c<C; c++) {
            n = Integer.parseInt(br.readLine());
            board = new int[n][n];
            cache = new int[n][n];
            cntCache = new int[n][n];
            for (int i=0; i<n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int j = 0;
                while (st.hasMoreTokens()) board[i][j++] = Integer.parseInt(st.nextToken());
                Arrays.fill(cache[i], -1);
                Arrays.fill(cntCache[i], -1);
                path(0, 0);
            }
            System.out.println(count(0, 0));
        }
    }

    static int count(int y, int x) {
        if (y == n-1) return 1;
        if (cntCache[y][x] != -1) return cntCache[y][x];
        int left = path(y+1, x);
        int right = path(y+1, x+1);
        if (left == right) return cntCache[y][x] = count(y+1, x) + count(y+1, x+1);
        else if (left > right) return cntCache[y][x] = count(y+1, x);
        return cntCache[y][x] = count(y+1, x+1);
    }

    static int path(int y, int x) {
        if (y == n) return 0;
        if (cache[y][x] != -1) return cache[y][x];
        return cache[y][x] = Math.max(path(y+1, x), path(y+1, x+1)) + board[y][x];
    }
}
