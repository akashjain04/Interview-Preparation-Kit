import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    public static class  Point{
        public int x,y;
        
        public Point(int x,int y){
            this.x = x;
            this.y = y;
            
        }

    }

    static final int[] X_OFFSETS = {-1,0,1,0};
    static final int[] Y_OFFSETS = {0,1,0,-1};

    // Complete the minimumMoves function below.
    static int minimumMoves(char[][] grid, int startX, int startY, int goalX, int goalY)    {          
        
        if(startX == goalX && startY == goalY)
            return 0;
        
        int n = grid.length;
        int moves[][] = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                moves[i][j] = -1;
            }
        }

        moves[startX][startY] = 0;

        Queue<Point> queue = new LinkedList<>();
        
        queue.add(new Point(startX, startY));

        while(!queue.isEmpty()){
            Point head = queue.remove();

            for(int i=0; i<X_OFFSETS.length; i++){
                int nextX = head.x;
                int nextY = head.y;

                while(isOpen(grid, nextX + X_OFFSETS[i], nextY + Y_OFFSETS[i])){
                    nextX += X_OFFSETS[i];
                    nextY += Y_OFFSETS[i];

                    if(nextX == goalX && nextY == goalY){
                        return moves[head.x][head.y] + 1;
                    }

                    if(moves[nextX][nextY] < 0){
                        moves[nextX][nextY] = moves[head.x][head.y]+1;
                        queue.add(new Point(nextX, nextY));
                    }

                }
            }
        }

        return -1; // Just to avoid error
        
    }

    public static boolean isOpen(char[][] grid, int x, int y){

        if(x >= 0 && x < grid.length && y >= 0 && y < grid.length && grid[x][y] == '.'){
            return true;
        }
        return false;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        char grid[][] = new char[n][n];

        for(int i=0; i<n; i++){
            String s = scanner.nextLine();

            for(int j=0; j<s.length(); j++){

                grid[i][j] = s.charAt(j);
            }
            
        }

        String[] startXStartY = scanner.nextLine().split(" ");

        int startX = Integer.parseInt(startXStartY[0]);

        int startY = Integer.parseInt(startXStartY[1]);

        int goalX = Integer.parseInt(startXStartY[2]);

        int goalY = Integer.parseInt(startXStartY[3]);

        int result = minimumMoves(grid, startX, startY, goalX, goalY);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
