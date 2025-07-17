import java.io.*;
import java.util.StringTokenizer;

public class Picnic {
    static boolean[][] isFriends;
    static int n;
    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        for (int c=0; c<C; c++) {
            n = Integer.parseInt(br.readLine().split(" ")[0]);
            isFriends = new boolean[n][n];
            StringTokenizer tokenizer = new StringTokenizer(br.readLine(), " ");
            while (tokenizer.hasMoreTokens()) {
                int first = Integer.parseInt(tokenizer.nextToken());
                int second = Integer.parseInt(tokenizer.nextToken());
                isFriends[first][second] = true;
                isFriends[second][first] = true;
            }
            bw.write(makePairs(new boolean[n]) + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static int makePairs(boolean[] friends) {
        int student = -1;
        for (int i=0; i<n; i++) {
            if (!friends[i]) {
                student = i;
                break;
            }
        }
        if (student == -1) return 1;
        int ret = 0;
        for (int i=student+1; i<n; i++) {
            if (isFriends[student][i] && !friends[i]) {
                friends[student] = true;
                friends[i] = true;
                ret += makePairs(friends);
                friends[i] = false;
                friends[student] = false;
            }
        }
        return ret;
    }
}
