import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the poisonousPlants function below.
    static int poisonousPlants(int[] p) {
        int days[] =  new int[p.length];
        Stack<Integer> stack = new Stack<>();
        int min = p[0];
        int max = 0;

        stack.push(0);

        for(int i=1; i<p.length; i++){
            if(p[i] > p[i-1])
                days[i] = 1;
            
            min = min < p[i] ? min : p[i];

            while((!stack.isEmpty()) && p[stack.peek()] >= p[i]){

                if(p[i] > min){
                    days[i] = (days[i] > days[stack.peek()] + 1) ? days[i] : days[stack.peek()] + 1;
                    
                }
                stack.pop();
            }

            max = max < days[i] ? days[i] : max;
            stack.push(i);

        }
        return max;


    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] p = new int[n];

        String[] pItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int pItem = Integer.parseInt(pItems[i]);
            p[i] = pItem;
        }

        int result = poisonousPlants(p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
