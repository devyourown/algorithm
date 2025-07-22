import java.io.*;
import java.util.*;

public class Main {
    static Map<String, Double> gradeScore = new HashMap<>() {{
        put("A+", 4.5); put("A0", 4.0); put("B+", 3.5);
        put("B0", 3.0); put("C+", 2.5); put("C0", 2.0);
        put("D+", 1.5); put("D0", 1.0); put("F", 0.0);
    }};
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        double ret = 0;
        double total = 0;
        for (int i=0; i<20; i++) {
            scan.next();
            double score = scan.nextDouble();
            String grade = scan.next();
            if (grade.equals("P")) continue;
            ret += score * gradeScore.get(grade);
            total += score;
        }
        System.out.println(ret/total);
    }
}
