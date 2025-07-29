import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AsymTiling {
    static int n;
    static int[] cache = new int[101];
    static int[] asymCache = new int[101];
    static int M = 1000000007;

    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int C = Integer.parseInt(br.readLine());
        Arrays.fill(cache, -1);
        for (int c=0; c<C; c++) {
            n = Integer.parseInt(br.readLine());
            System.out.println(asymTile(n));
        }
    }

    static int tile(int n) {
        if (n <= 2) return n;
        if (cache[n] != -1) return cache[n];
        return cache[n] = (tile(n-1) + tile(n-2)) % M;
    }

    static int asymTile(int n) {
        if (n <= 2) return 0;
        if (n % 2 == 1) return (tile(n)- tile(n/2) + M) % M;
        int ret = (tile(n) - tile(n/2-1) + M) % M;
        return (ret - tile(n/2) + M) % M;
    }
}
