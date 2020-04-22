import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {
        boolean[][] isValid = new boolean[a.length()+1][b.length()+1];
        isValid[0][0] = true;

        //Initializing the table
        boolean containUppercase = false;

        for(int k=1; k<=a.length(); k++){
            int i = k-1;

            if(a.charAt(i) <= 90 && a.charAt(i) >= 65 || containUppercase){
                isValid[k][0] = false;
                containUppercase = true;
            }
            else{
                isValid[k][0] = true;
            }
        }
        //populating the table
        for(int row=1; row<=a.length(); row++){
            
            for(int col=1; col<=b.length(); col++){
                int i = row-1;
                int j = col - 1;
                //Check if two characters are equal
                if(a.charAt(i) == b.charAt(j)){
                    isValid[row][col] = isValid[row-1][col-1];
                }
                //Check if Uppercase of letter of a is equal to letter at B
                else if((int) a.charAt(i) - 32 == (int) b.charAt(j)){
                    isValid[row][col] = isValid[row-1][col-1] || isValid[row-1][col];
                }
                //Check If the character is in Upper case
                else if(a.charAt(i) <= 90 && a.charAt(i) >= 65){
                    isValid[row][col] = false;
                    continue;
                }
                //If the character is in lower case
                else{
                    isValid[row][col]= isValid[row-1][col];
                    continue;
                }
            }
        }
        return (isValid[a.length()][b.length()])? "YES":"NO";

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
