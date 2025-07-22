import java.io.*;
import java.util.BitSet;

public class FanMeeting {
    public static void execute() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t=0; t<T; t++) {
            String members = br.readLine();
            String fans = br.readLine();
            BitSet memberBits = new BitSet(members.length());
            BitSet fanBits = new BitSet(fans.length());
            for (int i=0; i<members.length(); i++) if (members.charAt(i) == 'M') memberBits.set(i);
            for (int i=0; i<fans.length(); i++) if (fans.charAt(i) == 'M') fanBits.set(i);
            int ret = 0;
            for (int i=0; i<fans.length() - members.length() + 1; i++) {
                BitSet temp = fanBits.get(i, members.length() + i);
                temp.and(memberBits);
                ret += temp.cardinality() == 0 ? 1 : 0;
            }
            bw.write(ret + "\n");
        }
        bw.flush();
    }
}
