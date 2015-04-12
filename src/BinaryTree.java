import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class BinaryTree
{
	Node root;
	Node parent = null;

	public BinaryTree ()
	{
		root = null;
	}
	
	public BinaryTree (int d)
	{
		root = new Node (d, null, 0, 0, 1);
	}
	
	public BinaryTree (ArrayList<Integer> lst)
	{
		root = null;
		for (int i=0; i<lst.size(); i++)
			insert (lst.get(i));
	}

	public void insert (int d)
	{
		root = insert_ (root, d, 600, 30, 1);
	}

	public Node insert_ (Node node, int d, int x, int y, int h)
	{
		if (node == null)
			return new Node (d, null, x, y, h);

		if (node.data > d)
		{
			if (node.leftChild == null)
				node.leftChild = new Node (d, node, x-600/(int)Math.pow(2,h), y+60, h+1);
			else
				insert_ (node.leftChild, d, x-600/(int)Math.pow(2,h), y+60, h+1);
		}

		else
		{
			if (node.rightChild == null)
				node.rightChild = new Node (d, node, x+(int)600/(int)Math.pow(2,h), y+60, h+1);
			else
				insert_ (node.rightChild, d, x+(int)600/(int)Math.pow(2,h), y+60, h+1);
		}
		
		return node;
	}
	
	public void delete (int d)
	{
		root = delete_ (root, d);
	}
	
	private Node delete_ (Node node, int d)
	{
		// parent of the Node to be deleted
		parent = null;
		
		// Node to be deleted
		Node child = search (d);
		
		// if the Node has no child
		if (child.leftChild == null && child.rightChild == null)
		{
			// if the bst has only one element
			if (parent == null)
				return null;

			// otherwise
			else
			{
				if (child == parent.leftChild)
					parent.leftChild = null;
				else
					parent.rightChild = null;
			
				return node;
			}
		}
		
		// if Node has one child
		else if ((child.rightChild == null && child.leftChild != null) || (child.rightChild != null && child.leftChild == null))
		{
			// if root is to be removed
			if (parent == null)
			{
				// new root will be his child
				node = (child.rightChild == null) ? child.leftChild : child.rightChild;
				return node;
			}

			// else remove Node and Node's child becomes parent's child
			else
			{
				if (child == parent.leftChild)
					parent.leftChild = (child.rightChild == null) ? child.leftChild : child.rightChild;
				else
					parent.rightChild = (child.rightChild == null) ? child.leftChild : child.rightChild;
				return root;
			}
		}
		
		// if the Node has both the childs
		else
		{
			// parent of the successor and the successor
			Node p_success = child;
			Node successor = child.rightChild;
			
			// finding the successor
			while (successor.leftChild != null)
			{
				p_success = successor;
				successor = successor.leftChild;
			}
			
			// changing the data of the Node to be deleted to successor's data
			child.data = successor.data;
			
			// if successor's parent is the Node
			if (p_success == child)
				child.rightChild = successor.rightChild;
			
			// otherwise, replacing the Node with the successor
			else
			{
				child.data = successor.data;
				p_success.leftChild = successor.rightChild;
			}
			
			return node;
		}
	}


	
	public Node search (int d)
	{
		return search_ (root, d);
	}

	private Node search_ (Node node, int d)
	{
		if (node == null)
			return null;
		
		else if (node.data == d)
			return node;

		else
		{
			parent = node;
			if (d > node.data)
				return search_ (node.rightChild, d);
			else
				return search_ (node.leftChild, d);
		}
	}

	public void print_bfm ()
	{
		if (root == null)
		{
			return;
		}

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add (root.data);

		while (queue.size() != 0)
		{
			int a;
			while (queue.size() > 0)
			{
				a = queue.poll();
				Node temp = search (a);
				System.out.println (a);// + " " + temp.x + " " + temp.y + " " + temp.height);

				if (temp.leftChild != null)
					queue.add (temp.leftChild.data);
				if (temp.rightChild != null)
					queue.add (temp.rightChild.data);
			}
		}
	}

	public void drawTree(Graphics g)
	{
		if (root == null)
			return;

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add (root.data);

		while (queue.size() != 0)
		{
			int a;
			while (queue.size() > 0)
			{
				a = queue.poll();
				Node temp = search (a);
				temp.drawNode(g);
//				System.out.println (a + " " + temp.x + " " + temp.y + " " + temp.height);

				if (temp.leftChild != null)
					queue.add (temp.leftChild.data);
				if (temp.rightChild != null)
					queue.add (temp.rightChild.data);
			}
		}
	}
}