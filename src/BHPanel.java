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
import javax.swing.JPanel;
import javax.swing.Timer;


public class BHPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	BinaryHeap bh;
	private static final int TIMER_SPEED = 800;


	public BHPanel(BinaryHeap b)
	{
		bh = b;
	}

	/**
	 *	Insert an element into the binary heap 
	 *	and return the timer of the animation
	 */
	public Timer insert (final int val)
	{
		final Timer timer = new Timer(TIMER_SPEED, this);
		
		ActionListener action = new ActionListener()
		{
			Node 	temp = bh.heapSize > 0 ? bh.binaryHeap.get((bh.heapSize-1)/2) : null;
			boolean flag;
			boolean inserted;
			boolean finish;

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (temp == null)
				{
					bh.binaryHeap.add(new Node (val, null, 600, 30, 1));
					bh.heapSize++;
					timer.stop();
				}

				else if (!inserted)
				{
					if (!flag)
					{
						temp.changeNodeColor(Color.yellow);
						temp.changeNodeBackgroundColor(Color.yellow);
						temp.changeEdgeColor(Color.black);
						flag = !flag;
					}
					else
					{
						temp.changeNodeColor(Color.black);
						temp.changeNodeBackgroundColor(Color.white);
						Node n = new Node (val, temp, temp.x - 600/(int)Math.pow(2,temp.height)*(bh.heapSize%2==0?-1:1),
											temp.y+60, temp.height+1);
						bh.binaryHeap.add(n);
						bh.heapSize++;
						if (bh.heapSize%2==0) 
							temp.leftChild = n;
						else
							temp.rightChild = n;
						temp = n;

						temp.changeEdgeColor(Color.yellow);
						temp.changeNodeColor(Color.white);
						temp.changeTextColor(Color.white);
						flag = !flag;
						inserted = true;
					}
				}

				else if (!finish)
				{
					if (temp.parent != null)
					{
						if (temp.data < temp.parent.data)
						{
							if (!flag)
							{
								temp.changeTextColor(Color.black);
								temp.changeEdgeColor(Color.yellow);
								temp.changeNodeBackgroundColor(Color.yellow);
								temp.changeNodeColor(Color.yellow);
								flag = !flag;
							}
							else
							{
								swap (temp.parent, temp, timer);
								temp.changeNodeBackgroundColor(Color.yellow);
								temp.changeNodeColor(Color.yellow);
								temp.changeTextColor(Color.black);
								flag = !flag;
								timer.stop();
							}
						}
						else 
							finish = true;
					}
					else
						finish = true;
				}

				else
				{
					unColorPath(bh.binaryHeap.get(bh.heapSize-1));
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
	 *	Delete the minimum element of the binary heap 
	 *	and return the timer of the animation
	 */
	public Timer deleteMin ()
	{
		final Timer timer = new Timer(TIMER_SPEED, this);

		ActionListener action = new ActionListener()
		{
			Node 	temp = bh.heapSize > 0 ? bh.binaryHeap.get(0) : null;
			boolean flag;
			boolean swapped;
			boolean remove;
			boolean finish;

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (temp == null)
				{
					timer.stop();
				}

				else if (!swapped)
				{
					if (!flag)
					{
						Node n = bh.binaryHeap.get(bh.heapSize-1);
						temp.changeNodeColor (Color.yellow);
						temp.changeNodeBackgroundColor(Color.yellow);
						n.changeNodeColor(Color.yellow);
						n.changeNodeBackgroundColor(Color.yellow);
						flag = !flag;
					}
					else
					{
						swap (temp, bh.binaryHeap.get(bh.heapSize-1), timer);
						flag = !flag;
						swapped = true;
						timer.stop();
					}
				}

				else if (!remove)
				{
					Node n = bh.binaryHeap.get(bh.heapSize-1);
					n.changeNodeColor(Color.white);
					n.changeNodeBackgroundColor(Color.white);
					if (n.parent != null)
					{
						if (n == n.parent.leftChild)
							n.parent.leftChild = null;
						else
							n.parent.rightChild = null;
					}
					bh.binaryHeap.remove(bh.heapSize-1);
					bh.heapSize--;
					temp = bh.binaryHeap.get(0);
					remove = true;
				}

				else if (remove && !finish)
				{
					if (temp.leftChild == null && temp.rightChild == null)
					{
						finish = true;
					}
					else if (temp.leftChild == null)
					{
						if (temp.data > temp.rightChild.data)
						{
							Node n = temp.rightChild;
							if (!flag)
							{
								n.changeNodeColor(Color.yellow);
								n.changeNodeBackgroundColor(Color.yellow);
								flag = !flag;
							}
							else
							{
								swap (temp, n, timer);
								temp = temp.rightChild;
								timer.stop();
							}
						}
						else
							finish = true;
					}
					else if (temp.rightChild == null)
					{
						if (temp.data > temp.leftChild.data)
						{
							Node n = temp.leftChild;
							if (!flag)
							{
								n.changeNodeColor(Color.yellow);
								n.changeNodeBackgroundColor(Color.yellow);
								flag = !flag;
							}
							else
							{
								swap (temp, n ,timer);
								temp = temp.leftChild;
								timer.stop();
							}
						}
						else
							finish = true;
					}
					else
					{
						Node n = temp.leftChild.data < temp.rightChild.data ? temp.leftChild : temp.rightChild;
						if (temp.data > n.data)
						{
							if (!flag)
							{
								flag = !flag;
							}
							else
							{
								n.changeNodeColor(Color.yellow);
								n.changeNodeBackgroundColor(Color.yellow);
								swap (temp, n, timer);
								flag = !flag;
								timer.stop();
							}
						}
						else
							finish = true;
					}
				}

				else
				{
					unColorPath(temp);
					timer.stop();
//					bh.print();
				}
				
				paintBox();
			}
		};
		
		timer.addActionListener(action);
		timer.start();
		return timer;
	}
	

	/**
	 *	Swap two nodes in the binary heap
	 *	required during insert and delete-min, 
	 *	when swaping a node with its parent
	 */
	public void swap (final Node n1, final Node n2, final Timer timer)
	{
		final int x1 = n1.x;
		final int y1 = n1.y;
		final int x2 = n2.x;
		final int y2 = n2.y;
		final double deltaX = (x2-x1)/10.0;
		final double deltaY = (y2-y1)/10.0;

		int i1=-1;
		int i2=-1;
		for (int i=0; i<bh.heapSize; i++)
		{
			if (bh.binaryHeap.get(i) == n1)
				i1 = i;
			if (bh.binaryHeap.get(i) == n2)
				i2 = i;
		}

		bh.binaryHeap.set(i1, n2);
		bh.binaryHeap.set(i2, n1);

		Node p1 = n1.parent;
		Node l1 = n1.leftChild;
		Node r1 = n1.rightChild;
		int h1 = n1.height;
		boolean f = false;
		if (p1 != null)
			f = (n1 == p1.leftChild);
		boolean f2 = (n1 == n2.parent);
		boolean f3 = (n2 == n1.leftChild);
		
		if (!f2)
		{
			n1.parent = n2.parent;
			if (n2.parent != null)
			{
				if (n2 == n2.parent.leftChild)
					n2.parent.leftChild = n1;
				else
					n2.parent.rightChild = n1;
			}
		}
		else
		{
			n1.parent = n2;
		}
		n1.leftChild = n2.leftChild;
		if (n1.leftChild != null)	n1.leftChild.parent = n1;
		n1.rightChild = n2.rightChild;
		if (n1.rightChild != null)	n1.rightChild.parent = n1;
		n1.height = n2.height;

		n2.parent = p1;
		if (p1 != null)
		{
			if (f)	p1.leftChild = n2;
			else	p1.rightChild = n2;
		}
		if (!f2)
		{
			n2.leftChild = l1;
			if (n2.leftChild != null)	n2.leftChild.parent = n2;
			n2.rightChild = r1;
			if (n2.rightChild != null)	n2.rightChild.parent = n2;
		}
		else
		{
			if (f3)
			{
				n2.rightChild = r1;
				if (r1 != null)	r1.parent = n2;
				n2.leftChild = n1;
			}
			else
			{
				n2.leftChild = l1;
				if (l1 != null)	l1.parent = n2;
				n2.rightChild = n1;
			}
		}
		n2.height = h1;


		final Timer swapTimer = new Timer(100, this);

		ActionListener action = new ActionListener()
		{
			int i = 1;
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (i < 10)
				{
					n1.x += deltaX;
					n2.x -= deltaX;
					n1.y += deltaY;
					n2.y -= deltaY;
					i++;
				}
				else
				{
					n1.x = x2;
					n1.y = y2;
					n2.x = x1;
					n2.y = y1;

					swapTimer.stop();
					timer.start();
				}
				paintBox();
			}
		};
		
		swapTimer.addActionListener(action);
		swapTimer.start();
	}
	
	public void paintBox()
	{
		repaint();
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

	public Dimension getPreferredSize()
	{
		return new Dimension(500, 500);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		bh.drawTree(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}