/**
 *	Project:	Algorithm Theatre
 *
 * 	Authors:	Ishu Dharmendra Garg (CS13B060)
 *				Ujjawal Soni (CS13B053)
 */

import java.awt.Graphics;
import java.util.ArrayList;

public class BinaryHeap 
{
	int 			heapSize;
	ArrayList<Node> binaryHeap;

	public BinaryHeap ()
	{
		heapSize = 0;
		binaryHeap = new ArrayList<Node>();
	}
	
	public BinaryHeap (ArrayList<Integer> lst)
	{
		heapSize = 0;
		binaryHeap = new ArrayList<Node>();
		for (int i=0; i<lst.size(); i++)
			insert (lst.get(i));
	}

	public void insert (int d)
	{
		if (heapSize == 0)
		{
			binaryHeap.add(new Node (d, null, 600, 30, 1));
			heapSize++;
			return;
		}
		
		Node p = binaryHeap.get((heapSize-1)/2);
		Node n = new Node (d, p, p.x - 600/(int)Math.pow(2,p.height)*(heapSize%2==0?-1:1), p.y+60, p.height+1);
		if (heapSize%2 == 0)
			p.rightChild = n;
		else
			p.leftChild = n;

		binaryHeap.add(n);

		int i = heapSize;
		while (binaryHeap.get(i).data < binaryHeap.get((i-1)/2).data)
		{	swap (binaryHeap.get(i), binaryHeap.get((i-1)/2));
			i = (i-1)/2;
		}
		heapSize++;
	}
	
	public void print()
	{
		for (int i=0; i<heapSize; i++)
		{
			Node n = binaryHeap.get(i);
			System.out.printf ("%d ", n.data);
		}
		System.out.println();
	}

	public void swap(Node n1, Node n2)
	{
		int temp = n1.data;
		n1.data = n2.data;
		n2.data = temp;
	}

	/**
	 *	Draw the binary heap, by iteratively drawing all its nodes.
	 */
	public void drawTree(Graphics g)
	{
		if (heapSize == 0)
			return;

		for (int i=0; i<heapSize; i++)
			binaryHeap.get(i).drawNode(g);
	}
}