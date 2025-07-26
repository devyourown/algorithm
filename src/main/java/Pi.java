import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pi {
    static String pi;
    static int[] cache;
    static int INF = 987654321;
    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine().trim());
        for (int c=0; c<C; c++) {
            pi = br.readLine().trim();
            cache = new int[pi.length()];
            Arrays.fill(cache, -1);
            sb.append(solve(0) + "\n");
        }
        System.out.print(sb.substring(0, sb.length()-1));
    }

    static int getLevel(char[] piece) {
        boolean isCommonDiff = true;
        boolean isAlter = true;
        int diff = piece[0] - piece[1];
        for (int i=2; i<piece.length; i++) {
            if (piece[i-1] - piece[i] != diff) isCommonDiff = false;
            if (piece[i-2] != piece[i]) isAlter = false;
        }
        if (isCommonDiff) return diff == 0 ? 1 : (Math.abs(diff) == 1 ? 2 : 5);
        if (isAlter) return 4;
        return 10;
    }

    static int solve(int index) {
        if (index == pi.length()) return 0;
        if (cache[index] != -1) return cache[index];
        int ret = INF;
        for (int i=3; i<=5; i++) {
            if (index + i <= pi.length())
                ret = Math.min(ret, getLevel(pi.substring(index, index+i).toCharArray()) + solve(index+i));
        }
        return cache[index] = ret;
    }
}
