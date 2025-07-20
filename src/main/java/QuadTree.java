import java.io.*;

public class QuadTree {
    static String pixel;
    static int index;
    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        for (int c=0; c<C; c++) {
            pixel = br.readLine();
            index = 0;
            bw.write(reverse() + "\n");
        }
        bw.flush();
    }

    public static String reverse() {
        if (pixel.charAt(index) != 'x') return String.valueOf(pixel.charAt(index++));
        index++;
        String upperLeft = reverse();
        String upperRight = reverse();
        String lowerLeft = reverse();
        String lowerRight = reverse();
        return "x" + lowerLeft + lowerRight + upperLeft + upperRight;
    }
}
