import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minTime function below.
    static long minTime(long[] machines, long goal) {
        
        Arrays.sort(machines);

        long max_days = machines[0] * goal;
        long min_days = 1L;
        long mid, production;

        if(machines.length == 1)
            return max_days;

        while(min_days != max_days){

            mid = ( min_days + max_days)/2;
            production = 0;

            for(int i=0; i<machines.length; i++){
                production += Math.floor(mid / machines[i]);
            }

            if(production >= goal)
                max_days = mid;
            else
                min_days = mid+1;
        }

        return min_days;


    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nGoal = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nGoal[0]);

        long goal = Long.parseLong(nGoal[1]);

        long[] machines = new long[n];

        String[] machinesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long machinesItem = Long.parseLong(machinesItems[i]);
            machines[i] = machinesItem;
        }

        long ans = minTime(machines, goal);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
