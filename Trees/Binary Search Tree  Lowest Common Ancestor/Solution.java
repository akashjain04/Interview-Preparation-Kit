import java.util.*;
import java.io.*;

class Node {
    Node left;
    Node right;
    int data;
    
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {

	/*
    class Node 
    	int data;
    	Node left;
    	Node right;
	*/
	public static Node lca(Node root, int v1, int v2) {
      	// Write your code here.
        if(root == null)
            return root;
        
        if(root.data == v1 || root.data == v2){
            return root;
        }

        Node left,right;
        
        left = lca(root.left, v1, v2);
        right = lca(root.right, v1, v2);

        if(left != null && right != null)
            return root;
            
        if(left != null && right == null)
            return left;
        else    
            return right;

        
    }

	public static Node insert(Node root, int data) {