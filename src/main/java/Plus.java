import java.io.*;

public class Plus {
    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = br.readLine().split(" ");
        int sum = Integer.parseInt(line[0]) + Integer.parseInt(line[1]);
        bw.write(sum);
        bw.flush();
        bw.close();
    }
}
