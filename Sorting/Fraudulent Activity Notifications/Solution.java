import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        int count = 0;
        
        int[] histogram = new int[201]; //Max expenditure constraint 200

        for(int i=0;i<d;i++){
            histogram[expenditure[i]]++;
        }

        for(int i=d; i<expenditure.length; i++){
            int cursor = 0;
            int currentAmt = expenditure[i];
            double median = 0;
            int left = -1;
            int right = -1;
            //To find median
            for(int j=0;j<201;j++){
                cursor += histogram[j];

                if(d%2 == 1){
                    if(cursor >= d/2+1){
                        median = j;
                        break;
                    }
                }
                else{
                    if(cursor == d/2){
                        left = j;
                    }

                    if(cursor > d/2 && left != -1){
                        right = j;
                        median = (left + right)/2.0;
                        break;
                    }

                    if(cursor > d/2 && left ==-1){
                        median = j;
                        break;
                    }
                }
            }

            if(currentAmt >= 2*median){
                count++;
            }
            //Update the histogram by sliding it one position right.
            histogram[expenditure[i-d]]--;
            histogram[expenditure[i]]++;

        }
        
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
