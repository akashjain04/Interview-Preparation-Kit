import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    class Node{
        Node parent;
        int data;
        int rank;
    }
    Map<Integer, Node> map = new HashMap<>();
    int count = 0;
    public void makeSet(int item){
        if(map.containsKey(item))
            return;
        Node cur = new Node();
        cur.data = item;
        cur.parent = cur;
        cur.rank = 1;
        count++;
        map.put(item, cur);
    }

    public void union(int item1, int item2){
        Node node1 = map.get(item1);
        Node node2 = map.get(item2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        if(parent1.data == parent2.data){
            return;
        }

        if(parent1.rank >= parent2.rank){
            parent1.rank = parent1.rank + parent2.rank;
            parent2.parent = parent1;
        }
        else{
            parent2.rank = parent2.rank + parent1.rank;
            parent1.parent = parent2;
        }
        count--;
    }

    public Node findSet(Node node){
        Node parent = node.parent;

        if(node == parent)
            return parent;
        node.parent = findSet(node.parent);

        return node.parent;
    }
    // Complete the journeyToMoon function below.
    static long journeyToMoon(int n, int[][] astronaut) {
        Solution s = new Solution();

        for(int i = 0; i < n; i++){
            s.makeSet(i);
        }

        for(int[] row : astronaut){
            s.union(row[0], row[1]);
        }

        int size = s.count;
        long total = 0;
        long prevCount = 0;

        for(int i = 0; i < n; i++){
            Node parent = s.findSet(s.map.get(i));
            if(parent.data == i){
                int temp = parent.rank;
                total += temp * prevCount;
                prevCount += temp;
            }
        }

        return total;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] np = scanner.nextLine().split(" ");

        int n = Integer.parseInt(np[0]);

        int p = Integer.parseInt(np[1]);

        int[][] astronaut = new int[p][2];

        for (int i = 0; i < p; i++) {
            String[] astronautRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int astronautItem = Integer.parseInt(astronautRowItems[j]);
                astronaut[i][j] = astronautItem;
            }
        }

        long result = journeyToMoon(n, astronaut);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
