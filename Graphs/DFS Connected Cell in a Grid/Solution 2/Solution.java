import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the maxRegion function below.
    static int maxRegion(int[][] grid) {
        int max = 0;
        for(int i=0; i<grid.length; i++)
            for(int j=0; j<grid[0].length; j++){
                max = Math.max(max, countCells(grid, i, j));
            }
        return max;
    }
    static int countCells(int[][] matrix, int x, int y){
        if(x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length)
            return 0;

        if(matrix[x][y] == 0)
            return 0;

        int count = matrix[x][y]--;

        count += countCells(matrix, x+1, y);
        count += countCells(matrix, x-1, y);
        count += countCells(matrix, x, y+1);
        count += countCells(matrix, x, y-1);
        count += countCells(matrix, x+1, y+1);
        count += countCells(matrix, x-1, y-1);
        count += countCells(matrix, x-1, y+1);
        count += countCells(matrix, x+1, y-1);

        return count;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] gridRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int gridItem = Integer.parseInt(gridRowItems[j]);
                grid[i][j] = gridItem;
            }
        }

        int res = maxRegion(grid);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
