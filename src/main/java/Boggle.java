import java.io.*;

public class Boggle {
    static int dy[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int dx[] = {-1, 0, 1, -1, 1, -1, 0, 1};
    static String word;
    static String[] board = new String[5];
    static int cache[][][];

    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int t=0; t<T; t++) {
            for (int i=0; i<5; i++) {
                board[i] = br.readLine();
            }
            int numOfWords = Integer.parseInt(br.readLine());
            for (int n=0; n<numOfWords; n++) {
                word = br.readLine();
                cache = new int[5][5][10];
                boolean result = false;
                for (int y=0; y<5; y++) {
                    for (int x=0; x<5; x++) {
                        if (board[y].charAt(x) == word.charAt(0)) {
                            result = findWord(y, x, 1);
                        }
                        if (result) break;
                    }
                    if (result) break;
                }
                bw.write(word.toString());
                if (result) bw.write(" YES\n");
                else bw.write(" NO\n");
            }
        }
        bw.flush();
        bw.close();
    }

    public static boolean findWord(int y, int x, int index) {
        if (index == word.length()) return true;
        if (cache[y][x][index] != 0) return cache[y][x][index] == 1;
        int ret = 2;
        for (int i=0; i<8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (0 <= ny && ny < 5 && 0 <= nx && nx < 5 && word.charAt(index) == board[ny].charAt(nx)) {
                if (findWord(ny, nx, index + 1)) {
                    ret = 1;
                    break;
                }
            }
        }
        cache[y][x][index] = ret;
        return ret == 1;
    }
}
