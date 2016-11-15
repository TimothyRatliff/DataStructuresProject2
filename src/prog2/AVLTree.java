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
		
		public Node(String n, int d, Node l, Node r){
			this.name = n;
			this.data = d;    
			this.left = l;    
			this.right = r;
			if(left == null && right == null)
				height = 0;
			else if(left == null)
				height = right.height + 1;
			else if(right == null)
				height = left.height + 1;
			else
				height = 1 + Math.max(left.height, right.height);
		}
	}
	
	public void insert(String n, int d){
		root = insert(root, n, d);
	}
	
	private Node insert(Node v, String k, int d){
		if(v == null)
			return new Node(k, d, null, null);
		else{
			if(k.compareTo(v.name) >= 0)
				return toAVL(new Node(v.name, v.data, v.left, insert(v.right, k, d)));
			else
				return toAVL(new Node(v.name, v.data, insert(v.left, k, d), v.right));
		}
	}
	
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
	
	private Node findMin(Node v){
		while(v.left != null){
			v = v.left;
		}
		return v;
	}
	
	private Node toAVL(Node z){
		if(z.left != null && z.right != null){
			if(z.left.height>z.right.height+1)
				return restructureL(z, z.left);
			if(z.right.height>z.left.height+1)
				return restructureR(z, z.right);
		}
		return z;
	}
	
	private Node restructureL(Node z, Node y){
		if (y.left.height >= y.right.height) {
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
		if (y.left.height < y.right.height) {
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
	
	public void show(){
		show(root, 0);
	}
	
	private void show(Node v, int level){
		if (v != null) {
            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }
            System.out.print(v.name + " " + v.data + "\n");
            show(v.left, level + 1);
            show(v.right, level + 1);
		}
	}
}
