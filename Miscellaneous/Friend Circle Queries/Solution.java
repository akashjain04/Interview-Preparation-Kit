import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    class Node{
        int data;
        Node parent;
        int rank;
    }

    private static Map<Integer, Node> map = new HashMap<>();
    private static int max = 0;

    public void makeSet(int data){
        Node node = new Node();
        node.data = data;
        node.rank = 1;
        node.parent = node;
        map.put(data, node);
    }

    public void union(int data1, int data2){

        Node node1 = map.get(data1);
        Node node2 = map.get(data2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        if(parent1.data == parent2.data)
            return;

        if(parent1.rank >= parent2.rank){
            parent1.rank = parent1.rank + parent2.rank;
            parent2.parent = parent1;
            max = Math.max(max, parent1.rank);
        }
        else{
            parent2.rank = parent2.rank + parent1.rank;
            parent1.parent = parent2;
            max = Math.max(max, parent2.rank);
        }

        return;
    }

    public int findSet(int data){
        return findSet(map.get(data)).data;
    }

    public Node findSet(Node node){
        Node parent = node.parent;

        if(parent == node){
            return parent;
        }
        node.parent = findSet(node.parent);

        return node.parent;
    }
    // Complete the maxCircle function below.
    static int[] maxCircle(int[][] queries) {
        Solution s = new Solution();

        int[] result = new int[queries.length];

        if(queries == null || queries.length == 0)
            return result;
        
        for(int[] i : queries){
            s.makeSet(i[0]);
            s.makeSet(i[1]);
        }

        int index = 0;
        for(int[] i : queries){
            s.union(i[0], i[1]);
            result[index++] = s.max;
        }

        return result;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] queries = new int[q][2];

        for (int i = 0; i < q; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        int[] ans = maxCircle(queries);

        for (int i = 0; i < ans.length; i++) {
            bufferedWriter.write(String.valueOf(ans[i]));

            if (i != ans.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
