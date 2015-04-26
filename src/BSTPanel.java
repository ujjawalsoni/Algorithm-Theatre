/**
 *	Project:	Algorithm Theatre
 *
 * 	Authors:	Ishu Dharmendra Garg (CS13B060)
 *				Ujjawal Soni (CS13B053)
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;
import javax.swing.Timer;


class BSTPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	BinarySearchTree bst;
	private static final int TIMER_SPEED = 800;

	
	public BSTPanel(BinarySearchTree b)
	{
		bst = b;
	}

	/**
	 *	Search an element in the binary search tree
	 *	and return the timer of the animation, so as to
	 *	start and stop whenever required
	 */
	public Timer search (final int val)
	{
		final Timer timer = new Timer(TIMER_SPEED, this);
		
		ActionListener action = new ActionListener()
		{
			Node 	temp = bst.root;
			boolean flag;
			boolean finish;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (!finish)
				{
					if (temp.data == val)
					{
						temp.changeNodeBackgroundColor(Color.blue);
						temp.changeNodeColor(Color.blue);
						temp.changeTextColor(Color.white);
						finish = true;
					}
					
					else 
					{	if (!flag)
						{	temp.changeNodeColor (Color.yellow);
							temp.changeNodeBackgroundColor(Color.yellow);
							flag = !flag;
						}
						else
						{	temp.changeNodeBackgroundColor(Color.white);
							if (temp.data > val)
								temp = temp.leftChild;
							else
								temp = temp.rightChild;
							temp.changeEdgeColor(Color.yellow);
							flag = !flag;
						}
					}
				}
				else
				{	unColorPath(temp);
					timer.stop();
				}
				paintBox();
			}
		};
		
		timer.addActionListener(action);
		timer.start();
		return timer;
	}
	

	/**
	 *	Insert an element in the binary search tree
	 *	and return the timer of the animation
	 */
	public Timer insert (final int val)
	{
		final Timer timer = new Timer(TIMER_SPEED, this);
		
		ActionListener action = new ActionListener()
		{
			Node 	temp = bst.root;
			boolean flag;
			boolean finish;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (temp == null)
				{
					bst.root = new Node (val, null, 600, 30, 1);
					timer.stop();
				}
				
				else if (finish)
				{
					temp.changeEdgeColor(Color.black);
					temp.changeNodeBackgroundColor(Color.white);
					temp.changeNodeColor(Color.black);
					temp.changeTextColor(Color.black);
					timer.stop();
				}
				
				else 
				{
					if (!flag)
					{
						temp.changeNodeColor (Color.yellow);
						temp.changeNodeBackgroundColor(Color.yellow);
						temp.changeEdgeColor(Color.black);
						flag = !flag;
					}
					
					else
					{
						temp.changeNodeColor(Color.black);
						temp.changeNodeBackgroundColor(Color.white);
						if (temp.data > val)
						{
							if (temp.leftChild == null)
							{	temp.leftChild = new Node (val, temp, 
										temp.x-600/(int)Math.pow(2,temp.height), temp.y+60, temp.height+1);
								temp = temp.leftChild;
								temp.changeEdgeColor(Color.yellow);
								temp.changeNodeBackgroundColor(Color.white);
								temp.changeNodeColor(Color.white);
								temp.changeTextColor(Color.white);
								finish = true;
							}
							else
							{
								temp = temp.leftChild;
								temp.changeEdgeColor(Color.yellow);
							}
						}
						else
						{
							if (temp.rightChild == null)
							{	temp.rightChild = new Node (val, temp, 
										temp.x+600/(int)Math.pow(2,temp.height), temp.y+60, temp.height+1);
								temp = temp.rightChild;
								temp.changeEdgeColor(Color.yellow);
								temp.changeNodeBackgroundColor(Color.white);
								temp.changeNodeColor(Color.white);
								temp.changeTextColor(Color.white);
								finish = true;
							}
							else
							{
								temp = temp.rightChild;
								temp.changeEdgeColor(Color.yellow);
							}
						}
						flag = !flag;
					}
				}
				
				paintBox();
			}
		};
		
		timer.addActionListener(action);
		timer.start();
		return timer;
	}


	/**
	 *	Delete an element in the binary search tree
	 *	and return the timer of the animation
	 */
	public Timer delete (final int val)
	{
		final Timer timer = new Timer(TIMER_SPEED, this);
		
		ActionListener action = new ActionListener()
		{
			Node 	temp = bst.root;
			Node 	successor;
			Node 	parentSuccessor;
			boolean flag;
			boolean searched;
			boolean finish;
			boolean successflag;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (!(searched || finish))
				{
					if (temp.data == val)
					{
						temp.changeNodeColor(Color.red);
						searched = true;
					}
					
					else 
					{
						if (!flag)
						{
							temp.changeNodeColor (Color.yellow);
							temp.changeNodeBackgroundColor(Color.yellow);
							flag = !flag;
						}
						else
						{
							temp.changeNodeBackgroundColor(Color.white);
							if (temp.data > val)
								temp = temp.leftChild;
							else
								temp = temp.rightChild;
							temp.changeEdgeColor(Color.yellow);
							flag = !flag;
						}
					}
				}
				
				else if (!finish)
				{
					if (temp.leftChild == null && temp.rightChild == null)
					{
						if (temp.parent == null)
							bst.root = null;
						else
						{
							if (temp == temp.parent.leftChild)
								temp.parent.leftChild = null;
							else
								temp.parent.rightChild = null;
						}
						finish = true;
					}
					else if (temp.rightChild == null)
					{
						if (temp.parent == null)
						{
							bst.root = temp.leftChild;
							bst.root.parent = null;
						}
						else
						{
							if (temp == temp.parent.leftChild)
							{
								temp.parent.leftChild = temp.leftChild;
								temp.parent.leftChild.parent = temp.parent;
							}
							else
							{
								temp.parent.rightChild = temp.leftChild;
								temp.parent.rightChild.parent = temp.parent;
							}
						}
						temp = temp.leftChild;
						finish = true;
					}
					else if (temp.leftChild == null)
					{
						if (temp.parent == null)
						{
							bst.root = temp.rightChild;
							bst.root.parent = null;
						}
						else
						{
							if (temp == temp.parent.leftChild)
							{
								temp.parent.leftChild = temp.rightChild;
								temp.parent.leftChild.parent = temp.parent;
							}
							else
							{
								temp.parent.rightChild = temp.rightChild;
								temp.parent.rightChild.parent = temp.parent;
							}
						}
						temp = temp.rightChild;
						finish = true;
					}
					else
					{
						if (!successflag)
						{
							unColorPath(temp.parent);
							temp.changeEdgeColor(Color.black);
							successor = temp.rightChild;
							successor.changeEdgeColor(Color.yellow);
							successflag = true;
						}

						if (successor != null)
						{
							if (flag)
							{
								successor.changeNodeColor (Color.yellow);
								successor.changeNodeBackgroundColor(Color.yellow);
								parentSuccessor = successor;
								successor = successor.leftChild;
								flag = !flag;
							}
							else
							{
								successor.parent.changeNodeBackgroundColor(Color.white);
								successor.changeEdgeColor(Color.yellow);
								flag = !flag;
							}
						}
						else
						{
							temp.changeNodeData (parentSuccessor.data);
							temp = parentSuccessor;
						}
					}
				}
				
				else
				{
					updateAllNodes(temp);
					unColorPath(temp);
					timer.stop();
				}
				paintBox();
			}
		};

		timer.addActionListener(action);
		timer.start();
		return timer;
	}

	public void print_bfm ()
	{
		if (bst.root == null)
			return;

		Queue<Node> queue = new LinkedList<Node>();
		queue.add (bst.root);

		while (queue.size() > 0)
		{
			Node temp = queue.poll();
			System.out.printf ("%d ", temp.data);

			if (temp.leftChild != null)
				queue.add (temp.leftChild);
			if (temp.rightChild != null)
				queue.add (temp.rightChild);
		}
		System.out.println();
	}
	

	/**
	 *	update the coordinates of all the nodes below given node
	 *	(required while deleting a node with children)
	 */
	private void updateAllNodes (Node node)
	{
		Queue<Node> queue = new LinkedList<Node>();
		queue.add (node);

		while (queue.size() != 0)
		{
			Node a;
			while (queue.size() > 0)
			{
				a = queue.poll();
				if (a.parent == null)
				{
					a.height = 1;
					a.x = 600;
					a.y = 30;
				}
				else
				{
					if (a == a.parent.leftChild)
					{
						a.height = a.parent.height+1;
						a.x = a.parent.x - 600/(int)Math.pow(2,a.parent.height);
						a.y = a.parent.y + 60;
					}
					else
					{
						a.height = a.parent.height+1;
						a.x = a.parent.x + 600/(int)Math.pow(2,a.parent.height);
						a.y = a.parent.y + 60;
					}
				}

				if (a.leftChild != null)
					queue.add (a.leftChild);
				if (a.rightChild != null)
					queue.add (a.rightChild);
			}
		}
	}

	private void unColorPath (Node n)
	{
		Node temp = n;
		while (temp != null)
		{
			temp.changeNodeColor(Color.black);
			temp.changeEdgeColor(Color.black);
			temp.changeNodeBackgroundColor(Color.white);
			temp.changeTextColor(Color.black);
			temp = temp.parent;
		}
	}

	private void paintBox()
	{
		repaint();
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(500, 500);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		bst.drawTree(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}