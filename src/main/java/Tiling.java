import java.util.Arrays;
import java.util.Scanner;

public class Tiling {
    static int M = 1000000007;
    static int[] cache = new int[101];
    public static void execute() {
        Scanner scan = new Scanner(System.in);
        int C = scan.nextInt();
        Arrays.fill(cache, -1);
        for (int c=0; c<C; c++) {
            System.out.println(tile(scan.nextInt()));
        }
    }

    static int tile(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (cache[n] != -1) return cache[n];
        return cache[n] = (tile(n-2) + tile(n-1)) % M;
    }
}
