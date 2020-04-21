import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        HashMap<Long,Long> potential = new HashMap<>(); //To keep track of potential to form triplet
        HashMap<Long,Long> counters = new HashMap<>(); //To count the triplets possible
        Long count = 0L;

        for(int i=arr.size()-1; i>=0;i--){
            
	    Long a = arr.get(i);

            if(counters.containsKey(a * r)){
                count += counters.get(a*r);
            }

            if(potential.containsKey(a*r)){
                Long c = potential.get(a*r);
                counters.put(a, counters.getOrDefault(a, 0L) + c);
            }

            potential.put(a, potential.getOrDefault(a, 0L)+1);
        }

        return count;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
