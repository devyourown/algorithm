import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Snail {
    static double[][] cache = new double[1000][2001];
    static int n, m;
    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int C = Integer.parseInt(br.readLine());
        for (int c=0; c<C; c++) {
            String[] input = br.readLine().split(" ");
            n = Integer.parseInt(input[0]);
            m = Integer.parseInt(input[1]);
            for (int i=0; i<=m; i++) Arrays.fill(cache[i], -1);
            System.out.println(climb(0, 0));
        }
    }

    static double climb(int days, int climbed) {
        if (days == m) return climbed >= n ? 1 : 0;
        if (cache[days][climbed] != -1) return cache[days][climbed];
        return cache[days][climbed] = climb(days+1, climbed+1) * 0.25 + climb(days+1, climbed+2) * 0.75;
    }
}
