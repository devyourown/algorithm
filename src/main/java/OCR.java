import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OCR {
    static int m, q, n;
    static Map<String, Integer> words = new HashMap<>();
    static String[] indexWords;
    static double[] B;
    static double[][] T;
    static double[][] M;
    static int[] phrase;
    static int[][] result;
    static double[][] cache;
    static StringBuilder sb;

    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        m = Integer.parseInt(input[0]);
        q = Integer.parseInt(input[1]);
        B = new double[m];
        T = new double[m][m];
        M = new double[m][m];
        input = br.readLine().split(" ");
        indexWords = new String[m];
        for (int i=0; i<m; i++) {
            words.put(input[i], i);
            indexWords[i] = input[i];
        }
        input = br.readLine().split(" ");
        for (int i=0; i<m; i++) B[i] = Double.parseDouble(input[i]);
        for (int i=0; i<m; i++) {
            input = br.readLine().split(" ");
            for (int k=0; k<m; k++) T[i][k] = Double.parseDouble(input[k]);
        }
        for (int i=0; i<m; i++) {
            input = br.readLine().split(" ");
            for (int k=0; k<m; k++) M[i][k] = Double.parseDouble(input[k]);
        }
        for (int i=0; i<q; i++) {
            sb = new StringBuilder();
            input = br.readLine().split(" ");
            n = Integer.parseInt(input[0]);
            cache = new double[n][m];
            phrase = new int[n];
            for (int k=0; k<n; k++) {
                phrase[k] = words.get(input[k+1]);
                Arrays.fill(cache[k], -1);
            }
            result = new int[n][m];
            judge(0, 0);
            reconstruct(0, 0);
            System.out.println(sb);
        }
    }

    static double judge(int index, int before) {
        if (index == n) return 1;
        if (cache[index][before] != -1) return cache[index][before];
        int choice = phrase[index];
        double ret = 0.0;
        for (int i=0; i<m; i++) {
            double temp;
            if (index == 0) {
                temp = B[i] * M[i][phrase[index]];
            } else {
                temp = T[before][i] * M[i][phrase[index]];
            }
            if (temp == 0.0) continue;
            temp *= judge(index+1, i);
            if (temp > ret) {
                choice = i;
                ret = temp;
            }
        }
        result[index][before] = choice;
        return cache[index][before] = ret;
    }

    static void reconstruct(int index, int before) {
        int choose = result[index][before];
        sb.append(indexWords[choose]).append(" ");
        if (index < n-1) reconstruct(index+1, choose);
    }
}
