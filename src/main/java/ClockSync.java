import java.io.*;
import java.util.StringTokenizer;

public class ClockSync {
    static int[] clocks = new int[16];
    static final int INF = 987654321;
    static int[][] switches = {
            {0, 1, 2},
            {3, 7, 9, 11},
            {4, 10, 14, 15},
            {0, 4, 5, 6, 7},
            {6, 7, 8, 10, 12},
            {0, 2, 14, 15},
            {3, 14, 15},
            {4, 5, 7, 14, 15},
            {1, 2, 3, 4, 5},
            {3, 4, 5, 9, 13}
    };
    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t=0; t<T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            int i = 0;
            while (st.hasMoreTokens()) {
                clocks[i++] = Integer.parseInt(st.nextToken()) % 12;
            }
            int result = sync(0);
            bw.write((result == INF ? -1 : result) + "\n");
        }
        bw.flush();
    }

    public static boolean isSynced() {
        for (int i=0; i<16; i++) if (clocks[i] != 0) return false;
        return true;
    }

    public static int sync(int num) {
        if (isSynced()) return 0;
        if (num == 10) return INF;
        int ret = INF;
        for (int i=0; i<4; i++) {
            ret = Math.min(ret, sync(num+1)+i);
            for (int j=0; j<switches[num].length; j++)
                clocks[switches[num][j]] = (clocks[switches[num][j]] + 3) % 12;
        }
        return ret;
    }
}
