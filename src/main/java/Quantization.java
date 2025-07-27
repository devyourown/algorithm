import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quantization {
    static int[] arr;
    static int[] sumOfSquare;
    static int[] sumOfArr;
    static int N, S;
    static int[][] cache;
    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int C = Integer.parseInt(br.readLine());
        for (int c=0; c<C; c++) {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            S = Integer.parseInt(input[1]);
            if (N <= S) {
                System.out.println(0);
                continue;
            }
            cache = new int[N][S+1];
            sumOfArr = new int[N];
            sumOfSquare = new int[N];
            for (int i=0; i<N; i++) Arrays.fill(cache[i], -1);
            arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(arr);
            makeRangeSum();
            System.out.println(quantize(0, S));
        }
    }

    static void makeRangeSum() {
        sumOfArr[0] = arr[0];
        sumOfSquare[0] = arr[0] * arr[0];
        for (int i=1; i<N; i++) {
            sumOfArr[i] = sumOfArr[i-1] + arr[i];
            sumOfSquare[i] = (arr[i] * arr[i]) + sumOfSquare[i-1];
        }
    }

    static int calc(int start, int end) {
        if (start == end) return 0;
        int ret = Integer.MAX_VALUE;
        int min = arr[start];
        int max = arr[end];
        int rangeSum = start == 0 ? sumOfArr[end] : sumOfArr[end] - sumOfArr[start-1];
        int rangeSquareSum = start == 0 ? sumOfSquare[end] : sumOfSquare[end] - sumOfSquare[start-1];
        int range = end-start+1;
        for (int i=min; i<=max; i++) {
            ret = Math.min(ret, (i*i*range - 2*i*rangeSum + rangeSquareSum));
        }
        return ret;
    }

    static int quantize(int index, int kind) {
        if (kind == 1) return cache[index][kind] = calc(index, N-1);
        if (cache[index][kind] != -1) return cache[index][kind];
        int ret = Integer.MAX_VALUE;
        for (int i=index; i<=N-kind; i++) {
            ret = Math.min(ret, calc(index, i) + quantize(i+1, kind-1));
        }
        return cache[index][kind] = ret;
    }
}
