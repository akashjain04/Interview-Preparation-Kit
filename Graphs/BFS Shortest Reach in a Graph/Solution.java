import java.io.*;
import java.util.*;

public class Solution {
    
    public static class Graph {
        List<List<Integer>> adjLst; 
        int size;
        public Graph(int size) {

            adjLst = new ArrayList<>();
            this.size = size;
            while(size-- > 0)//Initialize the adjancency list.
                adjLst.add(new ArrayList<Integer>());
        }

        public void addEdge(int first, int second) {
            adjLst.get(first).add(second);
            adjLst.get(second).add(first); 
// For undirected graph, you gotta add edges from both sides.
        }
        
        public int[] shortestReach(int startId) { // 0 indexed
            int[] distances = new int[size];

            Arrays.fill(distances, -1); // O(n) space.	

            Queue<Integer> que = new LinkedList<>();
            
            que.add(startId); // Initialize queue.

            distances[startId] = 0;
            
	    HashSet<Integer> seen = new HashSet<>(); //O(n) space. 
            
            seen.add(startId);

            while(!que.isEmpty()) { // Standard BFS loop.

                Integer curr = que.poll();

                for(int node : adjLst.get(curr)) {

                    if(!seen.contains(node)) {
                        que.offer(node);
            		// Right place to add the visited set.
                        seen.add(node); 
            		// keep on increasing distance level by level.
                        distances[node] = distances[curr] + 6; 

                    }
                }
            } 
  
            return distances;
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        int n,m,T;
        Scanner sc = new Scanner(System.in);
        
        T = sc.nextInt();
        while(T-- > 0){
            n = sc.nextInt();
            m = sc.nextInt();

            Graph g = new Graph(n);
            for(int i=0; i<m; i++){
                
                int node1 = sc.nextInt()-1;
                int node2 = sc.nextInt()-1;
                g.addEdge(node1, node2);
                
            }

            int s = sc.nextInt()-1;

            int result[] = g.shortestReach( s);

            for(int i=0; i<n; i++){
                if(i != s)
                    System.out.print(result[i]+" ");
            }
            System.out.println(" ");

        }
    }
}