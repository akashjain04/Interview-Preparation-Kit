import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the pairs function below.
    static int pairs(int k, int[] arr) {
        
        int i=0,j=1,count = 0;
        //Sort the array 
        Arrays.sort(arr);
        //Using two pointer approach
        while(j < arr.length){
            int diff = arr[j] - arr[i];
            
            if(diff == k){ //If the diff is equal to k increment count and point to next element
                count++;
                j++;
            }
            else if(diff > k){ // If diff is greater than k increment i
                i++;
            }
            else if(diff < k){ // If diff is less than k increment j
                j++;
            }

        }

        return count;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = pairs(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
