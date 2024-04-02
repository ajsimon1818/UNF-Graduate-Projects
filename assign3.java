import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;


public class assign3{

	public static Tree T = new Tree();
	public static ArrayList<Node> nodes = new ArrayList<Node>();//list of nodes for reference
	public static Deque<Node> bfsQ = new ArrayDeque<Node>();//queue for bfs traversal
	public static boolean isBinary = true;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		fileReader("tree.txt");
		
		if(isBinary) {
			System.out.print("Pre-Order: ");
			preOrder(T.root);
			System.out.println();
			
			System.out.print("Post-Order: ");
			postOrder(T.root);
			System.out.println();
			
			System.out.print("In-Order: ");
			inOrder(T.root);
			System.out.println();
			
			System.out.print("BFS: ");
			bfsOrder();
			System.out.println();
			
		}
		
		
		else {
			System.out.print("Pre-Order: ");
			preOrder(T.root);
			System.out.println();
			
			System.out.print("Post-Order: ");
			postOrder(T.root);
			System.out.println();
			
			System.out.print("BFS: ");
			bfsOrder();
			System.out.println();
			
		}
	}//end main
	
	public static void fileReader(String fileName) throws FileNotFoundException {
		File inputFile = new File(fileName);
		Scanner lineRead = new Scanner(inputFile);
		int counter = 0;//for isolating first line
		
		while(lineRead.hasNextLine()) {

			String line = lineRead.nextLine();
			String[]lineSplit = line.split(" ");
			
			if(lineSplit.length > 3) {//binary check
				isBinary = false;
			}
			
			if(counter == 0) {//rules for first line
				
				Node root = new Node(lineSplit[0]);
				T.root = root;

				for(int i = 1; i < lineSplit.length; i++) {
					Node n = new Node(lineSplit[i]);
					nodes.add(n);
					n.parent = root;
					root.addChild(n);
					if(isBinary &&  i == 1) {
						root.left = n;
					}
					if(isBinary &&  i == 2) {
						root.right = n;
					}
					
				}
			}
			
			else {//rules for following lines
				
				for(int i = 0; i < nodes.size(); i++) {
					if(lineSplit[0].equals(nodes.get(i).value)) {
						
						for(int j = 1; j < lineSplit.length; j++) {
							Node n = new Node(lineSplit[j]);
							n.parent = nodes.get(i);
							nodes.get(i).addChild(n);
							nodes.add(n);
							if(isBinary &&  j == 1) {
								nodes.get(i).left= n;
							}
							if(isBinary &&  j == 2) {
								nodes.get(i).right = n;
							}
						}
					}
				}
			}//end else

			counter++;
		}//end reader loop
		lineRead.close();
	}//end reader method
	
	public static void preOrder(Node node) {
		if( node.children == null) return;
		
		System.out.print(node.value + " ");
		
		for(Node child : node.children) {
			preOrder(child);
		}
	}
	
	public static void postOrder(Node node) {
		if(node == null) {
			return;
		}
		for(Node child: node.children) {
			postOrder(child);
		}
		System.out.print(node.value + " ");
	}
	
	public static void bfsOrderTrav(Node node, int level) {
		if(node == null) {
			return;
		}
		
		if(level == 0) {
			System.out.print(node.value + " ");
		}
		else {
			
			for(Node child : node.children) {
				bfsOrderTrav(child, level-1);
			}
			
		}
		
	}
	
	public static void bfsOrder() {
		int height = T.height(T.root);
		for(int i = 0; i < height; i++) {
			bfsOrderTrav(T.root, i);
		}
	}
	
	public static void inOrder(Node node) {
		
		if(node == null) {
			return;
		}
		
		
		if(node.left != null) {
			inOrder(node.left);
		}
		System.out.print(node.value + " ");
		
		if(node.right != null) {
			inOrder(node.right);
		}
		
	}
	
	public static class Node{
		public ArrayList<Node> children = null;
		public String value = "";
		public Node parent = null;
		public Node left = null;
		public Node right = null;
		
		public Node(String value) {
			this.children = new ArrayList<Node>();
			this.value = value;
		}
		
		public void addChild(Node child) {
			children.add(child);
		}
		
		public static boolean isExternal(Node node){
			if(node.children == null) {
				return true;
			}
			else {
				return false;
			}
		}
		
		
		public static boolean isInternal(Node node){
			if(node.children == null) {
				return false;
			}
			else {
				return true;
			}
		}
	}//end node class

	public static class Tree {
		Node root;
		
		public static int height(Node root) {
			
			if(root.isExternal(root)) {
				return 0;
			}
			else {
				int depth = 0;
				for(Node child : root.children) {
					depth = Math.max(depth, height(child));
				}
				return depth + 1;
			}
			
		}
		
	}//end tree class
}//end program
