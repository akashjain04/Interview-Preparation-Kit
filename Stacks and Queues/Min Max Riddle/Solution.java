import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the riddle function below.
    static long[] riddle(long[] arr) {
        // complete this function
        long[] result = new long[arr.length];
        Map<Integer, Long> map = new HashMap<Integer, Long>();
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(-1);

        for(int i=0; i<arr.length; i++){
            while(stack.peek() != -1 && arr[stack.peek()] > arr[i]){
                int top = stack.pop();
                int key = i - stack.peek() - 1;
                map.put(key, Math.max(arr[top], map.getOrDefault(key,0L)));
            }
            stack.push(i);
        }

        while(stack.peek() != -1){
            int top = stack.pop();
            int key = arr.length - stack.peek() - 1;
            map.put(key, Math.max(arr[top], map.getOrDefault(key, 0L)));
        }

        long last = 0;
        List<Long> list = new ArrayList<>();

        for(int size = arr.length; size >= 1; size--){
            if(map.containsKey(size)){
                last = Math.max(last, map.get(size));
            }
            list.add(last);
        }
        
        int i = 0;

        for(int j = list.size()-1; j >= 0; j--){
            result[i++] = list.get(j);
        }

        return result;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] arr = new long[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long arrItem = Long.parseLong(arrItems[i]);
            arr[i] = arrItem;
        }

        long[] res = riddle(arr);

        for (int i = 0; i < res.length; i++) {
            bufferedWriter.write(String.valueOf(res[i]));

            if (i != res.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
