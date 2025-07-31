import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Numb3rs {
    static int[][] board;
    static double[][] cache;
    static int[] path;
    static int N, D, P, Q;
    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int C = Integer.parseInt(br.readLine());
        for (int c=0; c<C; c++) {
            ArrayList<Double> ret = new ArrayList<>();
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            D = Integer.parseInt(input[1]);
            P = Integer.parseInt(input[2]);
            board = new int[N][N];
            cache = new double[N][D];
            path = new int[N];
            StringTokenizer st;
            for (int i=0; i<N; i++) {
                Arrays.fill(cache[i], -1);
                st = new StringTokenizer(br.readLine(), " ");
                int val = 0;
                for (int k = 0; k < N; k++) {
                    board[i][k] = Integer.parseInt(st.nextToken());
                    if (board[i][k] == 1) val++;
                }
                path[i] = val;
            }
            Q = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            while (st.hasMoreElements()) ret.add(solve(Integer.parseInt(st.nextToken()), 0));
            for (double value : ret) System.out.print(value + " ");
            System.out.println();
        }
    }

    static double solve(int from, int days) {
        if (days == D) return from == P ? 1 : 0;
        if (cache[from][days] != -1) return cache[from][days];
        double ret = 0;
        for (int i=0; i<N; i++) {
            if (board[from][i] == 1) {
                ret +=  (1 / (double) path[i]) * solve(i, days+1);
            }
        }
        return cache[from][days] = ret;
    }
}
