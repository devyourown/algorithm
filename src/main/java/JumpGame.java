import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JumpGame {
    static int[][] board;
    static int[][] cache;
    static int N;

    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int C = Integer.parseInt(br.readLine());
        for (int c=0; c<C; c++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            cache = new int[N][N];
            for (int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                Arrays.fill(cache[i], -1);
                for (int j=0; j<N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            bw.write(jump(0, 0) >= 1 ? "YES" : "NO");
            bw.write("\n");
        }
        bw.flush();
    }

    static int jump(int y, int x) {
        if (y >= N || x >= N) return 0;
        if (y == N-1 && x == N-1) return 1;
        if (cache[y][x] != -1) return cache[y][x];
        cache[y][x] = Math.max(jump(y + board[y][x], x), jump(y, x + board[y][x]));
        return cache[y][x];
    }
}
