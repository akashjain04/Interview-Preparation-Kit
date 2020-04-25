import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isBalanced function below.
    static String isBalanced(String s) {
        int length = s.length();
        char[] myStack = new char[length]; 
        int top = -1;
        for (int i=0; i < s.length(); i++)
        {
            if(s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '[')
            {
                myStack[++top] = s.charAt(i);
            }
            else if(top != -1 && s.charAt(i)== '}') 
            {
                
                if(myStack[top] == '{')
                    top--;
                else
                    return "NO";
            }
            else if(top != -1 && s.charAt(i)== ')') 
            {
                
                if(myStack[top] == '(')
                    top--;
                else
                    return "NO";
            }
            else if(top != -1 && s.charAt(i)== ']') 
            {
                
                if(myStack[top] == '[')
                    top--;
                else
                    return "NO";
            }
            else
            {
                return "NO";
            }
        }
        if(top == -1)
        {
            return "YES";
        }
        else
        {
            return "NO";
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
