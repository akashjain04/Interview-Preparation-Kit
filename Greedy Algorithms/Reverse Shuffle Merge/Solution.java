import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the reverseShuffleMerge function below.
    static String reverseShuffleMerge(String s) {
        int[] frequency = new int[26];
        int uniqueElement = 0;
        for(int i=0; i<s.length();i++){
            char currentChar = s.charAt(i);
            if(frequency[currentChar - 'a'] == 0){
                uniqueElement++;
            }
            frequency[currentChar - 'a']++;
        }
        //Halve the frequency of each character to find the frequency of the characters in the answer.
        for(int i=0; i<26; i++){
            frequency[i] /= 2;
        }
        int lastIndex = s.length();
        StringBuilder result = new StringBuilder(lastIndex/2);
        
        while(uniqueElement > 0){
            //find the smallest window which have elements of frequency as subsequence
            int[] tempFrequency = frequency.clone();
            int tempUniqueElement = uniqueElement;
            int windowSize = 0;

            for(int i=0; i<s.length(); i++){
                char currentChar = s.charAt(i);
                tempFrequency[currentChar - 'a']--;

                if(tempFrequency[currentChar - 'a'] == 0)
                    tempUniqueElement--;

                if(tempUniqueElement == 0)
                    break;

                windowSize++;
            }

            Character minCharacter = null;
            
            for(int i=lastIndex-1; i>=windowSize;i--){
                if(frequency[s.charAt(i) - 'a'] == 0){
                    continue;
                }
                if(minCharacter == null || minCharacter > s.charAt(i)){
                    minCharacter = s.charAt(i);
                    lastIndex = i;
                }

            }

            result.append(minCharacter);
            frequency[minCharacter - 'a']--;

            if(frequency[minCharacter - 'a'] == 0){
                uniqueElement--;
            }
        }
        return result.toString();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = reverseShuffleMerge(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
