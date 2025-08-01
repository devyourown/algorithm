import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Packing {
    static int N, W;
    static String[] names;
    static int[][] cache;
    static int[] sizes, wanted;
    static int[] choices;
    static ArrayList<String> result;
    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int C = Integer.parseInt(br.readLine());
        for (int c=0; c<C; c++) {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            W = Integer.parseInt(input[1]);
            names = new String[N];
            sizes = new int[N];
            wanted = new int[N];
            cache = new int[N][W+1];
            choices = new int[N];
            result = new ArrayList<>();
            for (int i=0; i<N; i++) {
                input = br.readLine().split(" ");
                names[i] = input[0];
                sizes[i] = Integer.parseInt(input[1]);
                wanted[i] = Integer.parseInt(input[2]);
                Arrays.fill(cache[i], -1);
            }
            int ret = pack(0, 0);
            pick(0, 0);
            System.out.println(ret + " " + result.size());
            for (String n : result) System.out.println(n);
        }
    }

    static int pack(int stuff, int size) {
        if (stuff == N) return 0;
        if (cache[stuff][size] != -1) return cache[stuff][size];
        int ret = pack(stuff+1, size);
        if (size + sizes[stuff] <= W) {
            int want = wanted[stuff] + pack(stuff+1, size+sizes[stuff]);
            if (ret < want) {
                ret = want;
            }
        }
        return cache[stuff][size] = ret;
    }

    static void pick(int stuff, int size) {
        if (stuff == N) return;
        if (pack(stuff, size) == pack(stuff + 1, size))
            pick(stuff+1, size);
        else {
            result.add(names[stuff]);
            pick(stuff+1, size+sizes[stuff]);
        }
    }
}
