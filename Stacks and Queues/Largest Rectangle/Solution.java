import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the largestRectangle function below.
    static long largestRectangle(int[] h) {
        Stack<Integer> stack = new Stack<>();
        long area = 0;
        long maxArea = 0;
        int i=0;

        for(i=0; i < h.length; ){
            if(stack.isEmpty() || h[stack.peek()] <= h[i]){
                stack.push(i++);
            }
            else{
                int top = stack.pop();
                if(stack.isEmpty()){
                    area = h[top] * i;
                }
                else{
                    area = h[top] * (i - stack.peek() - 1);
                }
                maxArea = maxArea < area ? area : maxArea;
            }
        }
        while(!stack.isEmpty()){
            int top = stack.pop();

            if(stack.isEmpty()){
                area = h[top] * i;
            }
            else{
                area = h[top] * (i - stack.peek() - 1);
            }
            
            maxArea = maxArea < area ? area : maxArea;
        }
        return maxArea;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] h = new int[n];

        String[] hItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int hItem = Integer.parseInt(hItems[i]);
            h[i] = hItem;
        }

        long result = largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
