import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class WildCard {
    static String pattern;
    static String file;
    static int[][] cache;
    public static void execute() {
        Scanner scan = new Scanner(System.in);
        int C = scan.nextInt();
        for (int c=0; c<C; c++) {
            pattern = scan.next();
            int N = scan.nextInt();
            List<String> ret = new ArrayList<>();
            for (int i=0; i<N; i++) {
                file = scan.next();
                cache = new int[pattern.length()][file.length()];
                for (int[] temp : cache) Arrays.fill(temp, -1);
                if (match(0, 0) == 1) ret.add(file);
            }
            ret.sort(Comparator.naturalOrder());
            for (String s : ret) System.out.println(s);
        }
    }

    static int match(int wIndex, int fIndex) {
        if (wIndex == pattern.length() && fIndex == file.length()) return 1;
        if (wIndex >= pattern.length()) return 0;
        char p = pattern.charAt(wIndex);
        if (fIndex >= file.length()) return (p == '*' ? match(wIndex+1, fIndex) : 0);
        if (cache[wIndex][fIndex] != -1) return cache[wIndex][fIndex];
        cache[wIndex][fIndex] = 0;
        if (p == '*') {
            cache[wIndex][fIndex] = Math.max(match(wIndex+1, fIndex), match(wIndex, fIndex+1));
        } else if(p == '?' || p == file.charAt(fIndex)) {
            cache[wIndex][fIndex] = match(wIndex+1, fIndex+1);
        }
        return cache[wIndex][fIndex];
    }
}
