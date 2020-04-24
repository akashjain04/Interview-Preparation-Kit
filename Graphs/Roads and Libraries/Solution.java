import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, ArrayList<TreeSet<Integer>> adj) {
        
        long cost = 0L;

        if(c_lib < c_road){
            cost = (long) n * c_lib;
            return cost;
        }

        boolean visited[] = new boolean[n];
        
        for(int i=0; i<n; i++){

            if(!visited[i]){
                long tempCost = c_lib;
                Stack<Integer> stack = new Stack<Integer>();
                stack.push(i);
                visited[i] = true;

                while(!stack.isEmpty()){
                    int u = stack.pop();
                    Iterator<Integer> it = adj.get(u).iterator();

                    while(it.hasNext()){
                        int v = it.next();
                        
                        if(!visited[v]){
                            visited[v] = true;
                            stack.push(v);
                            tempCost += c_road;
                        }
                    }
                } 
                cost += tempCost;
            }
            
        }

        return cost;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            
            int n = scanner.nextInt();

            int m = scanner.nextInt();

            int c_lib = scanner.nextInt();

            int c_road = scanner.nextInt();

            ArrayList<TreeSet<Integer>> adj = new ArrayList<>();
            for(int i=0; i<n; i++)
                adj.add(new TreeSet<Integer>());

            for (int i = 0; i < m; i++) {
                
                int u = scanner.nextInt();
                u--;
                int v = scanner.nextInt();
                v--;
                adj.get(u).add(v);
                adj.get(v).add(u);
            }

            long result = roadsAndLibraries(n, c_lib, c_road, adj);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
