class Solution {
    
    // global index to traverse string
    static int i = 0;
    
    // helper function to build tree
    public static void solve(Node root, String s) {
        
        // read number for current node
        if (i < s.length() && Character.isDigit(s.charAt(i))) {
            int sum = 0;
            
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                sum *= 10;
                sum += s.charAt(i) - '0';
                i++;
            }
            
            root.data = sum;
        }
        
        // build left child
        if (i < s.length() && s.charAt(i) == '(') {
            root.left = new Node(0);
            i++;
            solve(root.left, s);
            
            if (root.left.data == 0) root.left = null;
        }
        
        // build right child
        if (i < s.length() && s.charAt(i) == '(') {
            root.right = new Node(0);
            i++;
            solve(root.right, s);
            
            if (root.right.data == 0) root.right = null;
        }
        
        // skip closing bracket
        if (i >= s.length() || s.charAt(i) == ')') {
            i++;
            return;
        }
    }
    
    public static Node treeFromString(String s) {
        
        // reset index for each test case
        i = 0;
        
        // create dummy root
        Node root = new Node(0);
        
        // build tree
        solve(root, s);
        
        return root;
    }
}