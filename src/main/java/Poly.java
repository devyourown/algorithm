import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Poly {
    static int MOD = 10000000;
    static int[][] cache = new int[101][101];

    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int C = Integer.parseInt(br.readLine());
        for (int i=0; i<101; i++) Arrays.fill(cache[i], -1);
        for (int c=0; c<C; c++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(makePoly(0, n));
        }
    }

    static int makePoly(int top, int left) {
        if (left == 0) return 1;
        if (cache[top][left] != -1) return cache[top][left];
        int ret = 0;
        for (int i=1; i<=left; i++) {
            if (top == 0) ret += makePoly(i, left-i);
            else ret += (top+i-1) * makePoly(i, left-i);
            ret = ret % MOD;
        }
        return cache[top][left] = ret;
    }
}
