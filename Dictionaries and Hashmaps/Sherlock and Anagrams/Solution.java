import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        int count = 0;
        HashMap<String,Integer> map = new HashMap<>();

        for(int i=0;i<s.length();i++){
            for(int j=i+1;j<=s.length();j++){
                //Get the substring
                String substr = s.substring(i,j);
                //Convert the string to array and sort 
                char[] charArray = substr.toCharArray();
                Arrays.sort(charArray);
                //Convert the sorted array of characters back to String
                substr = new String(charArray);
                //Check if the substr has occured once
                if(!map.containsKey(substr)){ //Add it to the HashMap
                    map.put(substr, 1);
                }   //Else increment the counter
                else{
                    map.put(substr, map.get(substr)+1);   
                }
            }
        }
        //To count the number of anagrams present.
        for(String i:map.keySet()){
            int value = map.get(i);
            count += (value * (value - 1))/2;
        }
        
        return count;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
