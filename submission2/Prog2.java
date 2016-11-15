//Programming Project 2
//Timothy Ratliff tratli2@lsu.edu

import java.io.*;
import java.util.Scanner;
public class Prog2 {

	public static void main(String[] args) throws FileNotFoundException{
		Scanner input = new Scanner(new File(args[0]));
		AVLTree tree = new AVLTree();
		while (input.hasNext()){
			String str = input.next();
			if(str.equals("remove"))
			{
				tree.remove(input.next());
			}
			else if(str.equals("insert"))
			{
				tree.insert(input.next(), input.nextInt());
			}
			else if(str.equals("show"))
			{
				tree.show();
			}
		}
		input.close();
	}
}