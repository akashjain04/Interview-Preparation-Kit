import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        long answer = s.length();

        for(int i = 0; i < n; i++){
            /*
                For each letter find the following two cases
            */

            /*
                Step 1: find the strings like aaaa where a repeats 3 times.
                        answer = (3 * 4)/2 = 6.
                        Those are aa,aa,aa,aaa,aaa,aaaa.
            */
            int repeat = 0;
            
            while(i+1 < n && s.charAt(i) == s.charAt(i+1)){
                repeat++;
                i++;
            }

            answer += (repeat * (repeat + 1))/2;
            /*
                Step 2:- find the strings like asa or aadaa where middle element is                             different but other all are equal. Increment the pointer to                              increase the reach. Increasing the pointer increases length on                             both sides.

            */
            int pointer = 1;
            
            while(i + pointer < n && i - pointer >= 0 && 
                    s.charAt(i + pointer) == s.charAt(i - 1) &&
                    s.charAt(i - pointer) == s.charAt(i-1)){
                        answer++;
                        pointer++;
                    }

        }

        return answer;
        

    }
    

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
