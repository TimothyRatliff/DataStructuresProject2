package prog2;
//Programming Project 2
//Timothy Ratliff tratli2@lsu.edu

public class AVLTree {
	private Node root;
	private int size;
	
	public AVLTree(){
		root = null;
		size = 0;
	}
	
	private class Node{
		public String name;
		public int data;
		public int height;
		public Node left;
		public Node right;
		
		public Node(String n, int d, Node l, Node r){
			this.data = d;    
			this.left = l;    
			this.right = r;
			this.height = 1 + Math.max(l.height, r.height);
		}
	}
	
	public void insert(String n, int d){
		Node v = new Node(n, d, null, null);
		insert(v, n);
	}
	
	private Node insert(Node v, String k){
		if(v == null)
			return new Node(k, 0, null, null);
		int compare = k.compareTo(v.name);
		if(compare < 0)
			return toAVL(new Node(v.name, v.data, insert(v.left, k), v.right));
		else
			return toAVL(new Node(v.name, v.data, v.left, insert(v.right, k)));
	}
	
	public void remove(String n, int d){
		Node v = new Node(n, d, null, null);
		remove(v, n);
	}
	
	private Node remove(Node v, String k){
		if(v == null)
			return null;
		int compare = k.compareTo(v.name);
		if(compare < 0)
			return toAVL(new Node(v.name, v.data, remove(v.left, k), v.right));
		if(v.left != null && v.right != null){
			Node y = findMin(v.right);
			return toAVL(new Node(v.name, v.data, v.left, remove(v.right, y.name)));
		}
		if(v.left != null)
			return v.left;
		else
			return v.right;
	}
	
	private Node findMin(Node v){
		if(v == null)
			return v;
		while(v.left != null){
			v = v.left;
		}
		return v;
	}
	
	private Node toAVL(Node z){
		if(z.left.height>z.right.height+1)
			return restructureL(z, z.left);
		if(z.right.height>z.left.height+1)
			return restructureR(z, z.right);
		return z;
	}
	
	private Node restructureL(Node z, Node y){
		if(y.left.height >= y.right.height)
			return restructureLL(z, y, y.left);
		else
			return restructureLR(z, y, y.right);
	}

	private Node restructureLL(Node z, Node y, Node x){
		return new Node(y.name, y.data, x, new Node(z.name, z.data, y.right, z.right));
	}
	
	private Node restructureLR(Node z, Node y, Node x){
		return new Node(x.name, x.data, y, new Node(z.name, z.data, x.right, z.right));
	}
	
	//the following to be altered to be symmetrical to restructure left side
	private Node restructureR(Node z, Node y){
		if(y.left.height >= y.right.height)
			return restructureRR(z, y, y.left);
		else
			return restructureRL(z, y, y.right);
	}
	
	private Node restructureRR(Node z, Node y, Node x){
		return new Node(y.name, y.data, x, new Node(z.name, z.data, y.right, z.right));
	}
	
	private Node restructureRL(Node z, Node y, Node x){
		return new Node(x.name, x.data, y, new Node(z.name, z.data, x.right, z.right));
	}
	
	public void show(){
		show(root);
	}
	private void show(Node v){
		if(v != null){
			show(v.left);
			System.out.print(""+v.name+" "+v.data+"");
			show(v.right);
		}
	}
}
