import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static class MyQueue<T> {
        Stack<T> s1 = new Stack<T>();
        Stack<T> s2 = new Stack<T>();

        public void enqueue(T value) { // Push onto newest stack
            s1.push(value);
        }

        public T peek() {
            if(s2.isEmpty())
                while(!s1.isEmpty())
                    s2.push(s1.pop());
             return s2.peek();
        }

        public T dequeue() {
            if(s2.isEmpty())
                while(!s1.isEmpty())
                    s2.push(s1.pop());
            return s2.pop();
        }
    }

    
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();
        
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        
        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}
