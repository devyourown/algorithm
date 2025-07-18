import java.io.*;

public class BoardCover {
    static int dy[][] = {{0, 1}, {1, 1}, {1, 1}, {0, 1}};
    static int dx[][] = {{1, 1}, {0, 1}, {-1, 0}, {1, 0}};
    static int[][] board;
    static int H, W;
    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        for (int c=0; c<C; c++) {
            String[] input = br.readLine().split(" ");
            H = Integer.parseInt(input[0]);
            W = Integer.parseInt(input[1]);
            board = new int[H][W];
            for (int i = 0; i < H; i++) {
                String line = br.readLine();
                for (int j = 0; j < W; j++) {
                    if (line.charAt(j) == '#') board[i][j] = 1;
                    else board[i][j] = 0;
                }
            }
            bw.write(cover(0, 0) + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    static boolean inRange(int y, int x) {
        if (y >= H || y < 0 || x >= W || x < 0) return false;
        return true;
    }

    public static boolean set(int y, int x, int delta, int block) {
        int y1 = y + dy[delta][0];
        int y2 = y + dy[delta][1];
        int x1 = x + dx[delta][0];
        int x2 = x + dx[delta][1];
        if (!inRange(y1, x1) || !inRange(y2, x2)) return false;
        if (block == board[y1][x1] || block == board[y2][x2]) return false;
        board[y1][x1] = board[y2][x2] = block;
        return true;
    }

    public static int cover(int y, int x) {
        if (x >= W) return cover(y+1, 0);
        if (y == H) return 1;
        if (board[y][x] >= 1) return cover(y, x+1);
        int ret = 0;
        for (int i=0; i<4; i++) {
            if (set(y, x, i, 1)) {
                ret += cover(y, x+1);
                set(y, x, i, 0);
            }
        }
        return ret;
    }
}
