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

	public void insert (final int val)
	{
		final Timer timer = new Timer(TIMER_SPEED, this);
		
		ActionListener action = new ActionListener()
		{
			Node temp = bh.heapSize > 0 ? bh.binaryHeap.get((bh.heapSize-1)/2) : null;
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
							temp.rightChild = n;
						else
							temp.leftChild = n;
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
								bh.swap (temp, temp.parent);
								temp = temp.parent;
								temp.changeNodeBackgroundColor(Color.yellow);
								temp.changeNodeColor(Color.yellow);
								temp.changeTextColor(Color.black);
								flag = !flag;
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
		
	}

	public void deleteMin ()
	{
		final Timer timer = new Timer(TIMER_SPEED, this);

		ActionListener action = new ActionListener()
		{
			Node temp = bh.heapSize > 0 ? bh.binaryHeap.get(0) : null;
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
						bh.swap (temp, bh.binaryHeap.get(bh.heapSize-1));
						flag = !flag;
						swapped = true;
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
					bh.heapSize--;
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
								bh.swap (temp, n);
								temp = temp.rightChild;
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
								bh.swap (temp, n);
								temp = temp.leftChild;
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
//								n.changeNodeColor(Color.yellow);
//								n.changeNodeBackgroundColor(Color.yellow);
								n.changeEdgeColor(Color.yellow);
								flag = !flag;
							}
							else
							{
								n.changeNodeColor(Color.yellow);
								n.changeNodeBackgroundColor(Color.yellow);
								bh.swap (temp, n);
								temp = n;
								flag = !flag;
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
				}
				
				paintBox();
			}
		};
		
		timer.addActionListener(action);
		timer.start();
		
	}

	public void move (final Node n1, final Node n2)
	{
		final int x1 = n1.x;
		final int y1 = n1.y;
		final int x2 = n2.x;
		final int y2 = n2.y;
		final double deltaX = (x2-x1)/10.0;
		final double deltaY = (y2-y1)/10.0;

		final Timer timer = new Timer(50, this);

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
					timer.stop();
				}
				paintBox();
			}
		};
		
		timer.addActionListener(action);
		timer.start();
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
