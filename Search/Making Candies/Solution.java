import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumPasses function below.
    static long minimumPasses(long m, long w, long p, long n) {
        long passes = 0L;
        long candy = 0L;
        long run = Long.MAX_VALUE;
        long step = 0L;

        while(candy < n){
            step = (m > Long.MAX_VALUE / w)?0:(p-candy)/(m * w);

            if(step <= 0){
                long mw = candy / p;

                if(m >= w + mw){
                    w += mw;
                }
                else if( w >= m + mw){
                    m += mw;
                }
                else{
                    long total = m + w + mw;
                    m = total / 2;
                    w = total - m;
                }
                candy %= p;
                step = 1;
            }
            passes += step;

            if(step * m > Long.MAX_VALUE / w){
                candy = Long.MAX_VALUE;
            }
            else{
                candy += step * m * w;
                run = Math.min(run, (long)(passes + Math.ceil((n - candy) / (m*w*1.0))));
            
            }

        }

        return Math.min(passes, run);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] mwpn = scanner.nextLine().split(" ");

        long m = Long.parseLong(mwpn[0]);

        long w = Long.parseLong(mwpn[1]);

        long p = Long.parseLong(mwpn[2]);

        long n = Long.parseLong(mwpn[3]);

        long result = minimumPasses(m, w, p, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
