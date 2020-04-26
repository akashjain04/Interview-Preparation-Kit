import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    final static long _MOD = 1000000007;

    // Complete the stepPerms function below.
    static long stepPerms(int n, long[] steps) {
        
        if( n == 0)
            return 0;

        if( n == 1)
            return 1;

        if( n == 2)
            return 2;
        
        if( n == 3)
            return 4;
        
        if(steps[n] == 0)
            steps[n] = stepPerms(n-1 , steps) + stepPerms(n-2, steps) + stepPerms(n-3, steps) % _MOD;

        return steps[n];
    }

    

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        
        for (int sItr = 0; sItr < s; sItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            long[] steps = new long[n+1];
            long res = stepPerms(n, steps);

            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
