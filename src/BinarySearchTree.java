/**
 *	Project:	Algorithm Theatre
 *
 * 	Authors:	Ishu Dharmendra Garg (CS13B060)
 *				Ujjawal Soni (CS13B053)
 */


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class BinarySearchTree
{
	Node 	root;
	Node 	parent = null;

	public BinarySearchTree ()
	{
		root = null;
	}
	
	public BinarySearchTree (int d)
	{
		root = new Node (d, null, 0, 0, 1);
	}
	
	public BinarySearchTree (ArrayList<Integer> lst)
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

	/**
	 *	Draw the binary search tree, by iteratively drawing all its nodes.
	 */
	public void drawTree(Graphics g)
	{
		if (root == null)
			return;

		Queue<Node> queue = new LinkedList<Node>();
		queue.add (root);

		while (queue.size() > 0)
		{
			Node temp = queue.poll();
			temp.drawNode(g);

			if (temp.leftChild != null)
				queue.add (temp.leftChild);
			if (temp.rightChild != null)
				queue.add (temp.rightChild);
		}
	}
}