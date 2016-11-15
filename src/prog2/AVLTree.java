package prog2;
//Programming Project 2
//Timothy Ratliff tratli2@lsu.edu

public class AVLTree {
	Node root;
	
	public AVLTree(){
		root = null;
	}
	
	private class Node{
		public String name;
		public int data;
		public int height;
		public Node left;
		public Node right;
		
		/**
		 * Retrieves the height of the given node
		 * @param v a node
		 * @return the height of the node
		 */
	    
	    /**
	     * Constructs a node
	     * @param n a name string
	     * @param d an integer
	     * @param l the left component
	     * @param r the right component
	     */
		public Node(String n, int d, Node l, Node r){
			this.name = n;
			this.data = d;    
			this.left = l;    
			this.right = r;
			if(left != null && right != null)
				height = 1 + Math.max(left.height, right.height);
			else if(left != null)
				height = 1 + left.height;
			else if(right != null)
				height = 1 + right.height;
			else if(left == null && right == null)
				height = 1;
			else
				height = 0;
		}
	}
	
	private int height(Node v) {
        if (v != null) {
            return v.height;
        }
        return 0;
    }
	
	/**
	 * Inserts a name and integer as a new node in the tree
	 * @param n a name string
	 * @param d an integer
	 */
	public void insert(String n, int d){
		root = insert(root, n, d);
	}
	
	private Node insert(Node v, String k, int d){
		if (v == null) {
            return new Node(k, d, null, null);
        }
        else {
            if (k.compareTo(v.name) > 0) {
                return toAVL(new Node(v.name, v.data, v.left, insert(v.right, k, d)));
            }
            else {
                return toAVL(new Node(v.name, v.data, insert(v.left, k, d), v.right));
            }
        }
	}
	
	/**
	 * Removes the node with the given name string
	 * @param n the given name string 
	 */
	public void remove(String n){
		root = remove(root, n);
	}
	
	private Node remove(Node v, String k){
		if(v != null){
			if(k.compareTo(v.name) < 0)
				return toAVL(new Node(v.name, v.data, remove(v.left, k), v.right));
			if(k.compareTo(v.name) > 0)
				return toAVL(new Node(v.name, v.data, v.left, remove(v.right, k)));
			if(v.left != null && v.right != null){
				Node y = findMin(v.right);
				return toAVL(new Node(y.name, y.data, v.left, remove(v.right, y.name)));
			}
			if(v.left != null)
				return v.left;
			else
				return v.right;
		}
		return null;
	}
	
	/**
	 * Find the smallest node of the tree, on the left
	 * @param v The root of the tree
	 * @return
	 */
	private Node findMin(Node v){
		while(v.left != null){
			v = v.left;
		}
		return v;
	}
	
	/**
	 * Restructures a given tree rooted at z to be in compliance with AVL regulations
	 * @param z The given root
	 * @return
	 */
	private Node toAVL(Node z){
		if(height(z.left)>height(z.right)+1)
			return restructureL(z, z.left);
		if(height(z.right)>height(z.left)+1)
			return restructureR(z, z.right);
		return z;
	}
	
	private Node restructureL(Node z, Node y){
		if (height(y.left) >= height(y.right)) {
            return restructureLL(z,y,y.left);
        } else {
            return restructureLR(z,y,y.right);
        }
    }

	private Node restructureLL(Node z, Node y, Node x){
		return new Node(y.name, y.data, new Node(x.name, x.data, x.left, x.right), new Node(z.name, z.data, y.right, z.right));
	}
	
	private Node restructureLR(Node z, Node y, Node x){
		return new Node(x.name, x.data, new Node(y.name, y.data, y.left, x.left), new Node(z.name, z.data, x.right, z.right));
	}
	
	private Node restructureR(Node z, Node y){
		if (height(y.left) < height(y.right)) {
            return restructureRR(z, y, y.right);
        } else {
            return restructureRL(z, y, y.left);
        }
    }
	
	private Node restructureRR(Node z, Node y, Node x){
		return new Node(y.name, y.data, new Node(z.name, z.data, z.left, y.left), new Node(x.name, x.data, x.left, x.right));	}
	
	private Node restructureRL(Node z, Node y, Node x){
		return new Node(x.name, x.data, new Node(z.name, z.data, z.left, x.left), new Node(y.name, y.data, x.right, y.right));
	}
	
	/**
	 * Prints out the tree
	 */
	public void show(){
		show(root, 0);
	}
	
	private void show(Node v, int level){
		if (v != null) {
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.print(v.name + " " + v.data + "\n");
            show(v.left, level + 1);
            show(v.right, level + 1);
		}
	}
}
