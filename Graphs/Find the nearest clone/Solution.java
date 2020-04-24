import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the findShortest function below.

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
     *
     */

    public static ArrayList<Integer>[] adjacencies;
    public static boolean[] visited;

    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        //To hold adjacencies in array of ArrayList 

        adjacencies =  new ArrayList[graphNodes + 1];
        adjacencies[0] = new ArrayList<Integer>();
        //To mark if visited or not
        visited = new boolean[graphNodes + 1];

        int numCorrectColors = 0;

        for(int i=1; i<= graphNodes; i++){
            adjacencies[i] = new ArrayList<Integer>();

            if(ids[i-1] == val)
                numCorrectColors++;
        }

        if(numCorrectColors < 2){
            return -1;
        }

        //Add adjacencies of graph to ArrayList
        for(int i=0; i<graphFrom.length; i++){

            int node1 = graphFrom[i];
            int node2 = graphTo[i];
            adjacencies[node1].add(node2);
            adjacencies[node2].add(node1);

        }

        // Intially we set the shortest path to longest possible. 
        // That is number of nodes

        int shortestPath = graphNodes;

        for(int i=1; i< adjacencies.length; i++){
            long currentNodeColor = ids[i-1];
            int curPath = 0;

            if(currentNodeColor != val){
                continue;
            }

            curPath += findPathLength(i, val, ids);
            shortestPath = Math.min(shortestPath, curPath);

        }

        return shortestPath;
        
    }

    //Function to find the path length
    static int findPathLength(int curNode, long color, long[] ids){

        visited[curNode] = true;
        ArrayList<Integer> curAdj = adjacencies[curNode];
        int minPath = ids.length;

        for(int i=0; i<curAdj.size(); i++){
            int curNeighbor = curAdj.get(i);
            int curPath = 1;

            if(visited[curNeighbor] == true)
                continue;
            
            if(ids[curNeighbor-1] == color)
                return curPath;

            curPath += findPathLength(curNeighbor, color, ids);
            minPath = Math.min(curPath, minPath);
        }
        
        return minPath;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] graphNodesEdges = scanner.nextLine().split(" ");
        int graph_nodes = Integer.parseInt(graphNodesEdges[0].trim());
        int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

        int[] graphFrom = new int[graphEdges];
        int[] graphTo = new int[graphEdges];

        for (int i = 0; i < graphEdges; i++) {
            String[] graphFromTo = scanner.nextLine().split(" ");
            graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
            graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
        }

        long[] ids = new long[graph_nodes];

        String[] idsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < graph_nodes; i++) {
            long idsItem = Long.parseLong(idsItems[i]);
            ids[i] = idsItem;
        }

        int val = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int ans = findShortest(graph_nodes, graphFrom, graphTo, ids, val);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
