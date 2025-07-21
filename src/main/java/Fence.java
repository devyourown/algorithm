import java.io.*;
import java.util.StringTokenizer;

public class Fence {
    static int[] fence;
    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        for (int c=0; c<C; c++) {
            int N = Integer.parseInt(br.readLine());
            fence = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int i = 0;
            while (st.hasMoreTokens()) fence[i++] = Integer.parseInt(st.nextToken());
            bw.write(crop(0, N-1) + "\n");
        }
        bw.flush();
    }

    public static int crop(int left, int right) {
        if (left == right) return fence[left];
        int mid = (left + right) / 2;
        int ret = Math.max(crop(left, mid), crop(mid+1, right));
        int lefty = mid-1;
        int righty = mid+1;
        int cur = fence[mid];
        int width = 1;
        while (left <= lefty && righty <= right) {
            if (fence[lefty] < fence[righty]) {
                cur = Math.min(cur, fence[righty++]);
                ret = Math.max(ret, (++width) * cur);
            }
            else if (fence[lefty] >= fence[righty]) {
                cur = Math.min(cur, fence[lefty--]);
                ret = Math.max(ret, (++width) * cur);
            }
        }
        while (left <= lefty) {
            cur = Math.min(cur, fence[lefty--]);
            ret = Math.max(ret, (++width)*cur);
        }
        while (righty <= right) {
            cur = Math.min(cur, fence[righty++]);
            ret = Math.max(ret, (++width)*cur);
        }
        return ret;
    }
}
