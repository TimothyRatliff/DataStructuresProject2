package prog2;
//Programming Project 2
//Timothy Ratliff tratli2@lsu.edu

import java.io.*;
import java.util.Scanner;
public class prog2 {

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		tree.insert("Earl", 5);
		tree.insert("Colin", 3);
		tree.insert("Fiona", 6);
		tree.show();
		

	}

}
