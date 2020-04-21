import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        Map<String,Integer> magazineOccurance = new HashMap<String,Integer>();
        Map<String,Integer> ransomOccurance = new HashMap<String,Integer>();
        boolean flag=true;

        for(String word:magazine){
            if(!magazineOccurance.containsKey(word))
                magazineOccurance.put(word,0);
            magazineOccurance.put(word,magazineOccurance.get(word)+1);
        }

        for(String word:note)
        {
            if(!ransomOccurance.containsKey(word))
                ransomOccurance.put(word,0);
            ransomOccurance.put(word,ransomOccurance.get(word)+1);
        }

        for(String word:ransomOccurance.keySet())
        {
            if(!magazineOccurance.containsKey(word)){
                flag = false;
                break;
            }
            else{
                int needed = ransomOccurance.get(word);
                if(magazineOccurance.get(word) < ransomOccurance.get(word)){
                    flag = false;
                    break;
                }
            }
        }

        if(flag)
            System.out.println("Yes");
        else
            System.out.println("No");
        
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}
