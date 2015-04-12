import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

class MyPanel extends JPanel implements ActionListener
{
	private static final int CHANGEX = 1;
	private static final int TIMER_SPEED = 100;
	ArrayRectangle box;
	
	public MyPanel(ArrayList<Integer> list)
	{
		setBorder(BorderFactory.createLineBorder(Color.black));
		box = new ArrayRectangle();
		box.setBaseX(200);
		box.setBaseY(200);
		box.initializeRectangle(list);
		this.paintBox();
		
		System.out.println("\nPass 0\n");
		for (int j = 0; j < box.getArrayRectangle().length; j++)
		{
			System.out.println("data " + box.getRectangle(j).getData() + " Index " + box.getRectangle(j).getHeight());
		}
		selectionSort();
	}
	
	public void bubbleSort()
	{
		final Timer timer = new Timer(2, this);
		
		ActionListener action = new ActionListener()
		{
			// i represents the index of the outer loop
			int i = 0;
			
			// j represents the index of the inner loop
			int j = 0;
			
			// determines if there is any swap or not
			int swap = 0;
			
			// Stores how much a the current rectangle has moved down
			int currentDownCount = 0;
			
			// counts how much a current rectangle has moved up
			int currentUpCount = 0;
			
			// counts how much a current rectangle has moved left
			int currentLeftCount = 0;
			
			// counts how much a rectangle[index] has moved to the right (while shifting the rectangles) 	
			int indexRightCount = 0;
			
			//TODO remove these variables
			//stores the index of the first rectangle whose data is less of equal to the current rectangle 
			int k = 0;
			// varies from k + 1 to i - 1 (used for shifting all rectangles in this range)
			int index = 0;
			
			int codeFlag = 1;
			int rightFlag = 0;
			int rightBlockFlag = 0;
			int downFlag = 0;
			int upFlag = 0;
			int LeftMainFlag = 0;
			int width = 20;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// step zero check whether to continue the algorithm or not
				if ((i >= 12 - 1))
					timer.stop();
				
				else if (j >= 12 - i - 1)
				{
					if (swap == 0)
						timer.stop();
					i++;
					swap = 0;
					j = 0;
				}
				
				// Step one find a suitable index where to place the current rectangle
				else if (codeFlag == 1)
				{
					Rectangle currRect = box.getRectangle(j);
					Rectangle nextRect = box.getRectangle(j + 1);
					if (currRect == null || nextRect == null)
					{
						System.out.println("\n i = " + i + " j = " + j);
					}
					if (nextRect.getData() < currRect.getData())
					{
						swap = 1;
						codeFlag = 0;
						downFlag = 1;
						currentDownCount = 0;
					}
					else
					{
						j++;
					}
					
				}
				else if (downFlag == 1)
				{
					box.addOffsetRectangel(j + 1, 0, 1);
					paintBox();
					currentDownCount++;
					if (currentDownCount >= 100)
					{
						downFlag = 0;
						rightFlag = 1;
						indexRightCount = 0;
					}
				}
				
				else if (rightFlag == 1)
				{
					box.addOffsetRectangel(j, 1, 0);
					paintBox();
					indexRightCount++;
					if (indexRightCount >= width)
					{
						rightFlag = 0;
						LeftMainFlag = 1;
						currentLeftCount = 0;
					}
				}
				//////////////////////////////////////////
				else if (LeftMainFlag == 1)
				{
					box.addOffsetRectangel(j + 1, -1, 0);
					currentLeftCount++;
					paintBox();
					if (currentLeftCount >= width)
					{
						LeftMainFlag = 0;
						upFlag = 1;
						currentUpCount = 0;
					}
				}
				
				else if (upFlag == 1)
				{
					box.addOffsetRectangel(j + 1, 0, -1);
					currentUpCount++;
					paintBox();
					if (currentUpCount >= 100)
					{
						Rectangle currRect = box.getRectangle(j);
						Rectangle nextRect = box.getRectangle(j + 1);
						//	System.out.println("\n\ni value " + box.getRectangle(i).getData());
						//Rectangle scanRect;
						//int t;
						box.setRectangle(currRect, j + 1);
						box.setRectangle(nextRect, j);
						
						System.out.println("\nPass " + i + "\n");
						for (int j = 0; j < box.getArrayRectangle().length; j++)
						{
							System.out.println("data " + box.getRectangle(j).getData() + " Index " + j);
						}
						
						upFlag = 0;
						codeFlag = 1;
						j++;
					}
				}
			}
		};
		
		timer.addActionListener(action);
		timer.start();
		//timer1.start();
		
	}
	
	public void insertionSort()
	{
		final Timer timer = new Timer(5, this);
		
		ActionListener action = new ActionListener()
		{
			// for running the code flag part initial conditions 
			
			// i represents the index of the current rectangle
			int i = 1;
			
			//stores the index of the first rectangle whose data is less of equal to the current rectangle 
			int k = 0;
			
			// varies from k + 1 to i - 1 (used for shifting all rectangles in this range)
			int index = 0;
			
			// Stores how much a the current rectangle has moved down
			int currentDownCount = 0;
			
			// counts how much a rectangle[index] has moved to the right (while shifting the rectangles) 	
			int indexRightCount = 0;
			
			// counts how much a current rectangle has moved left
			int currentLeftCount = 0;
			
			//  counts how much a current rectangle has moved up
			int currentUpCount = 0;
			
			int codeFlag = 1;
			int rightFlag = 0;
			int rightBlockFlag = 0;
			int downFlag = 0;
			int upFlag = 0;
			int LeftMainFlag = 0;
			int width = 20;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// step zero check whether to continue the algorithm or not
				if (i == 12)
					timer.stop();
				
				// Step one find a suitable index where to place the current rectangle
				else if (codeFlag == 1)
				{
					Rectangle currRect = box.getRectangle(i);
					Rectangle scanRect;
					for (k = i - 1; k >= 0; k--)
					{
						scanRect = box.getRectangle(k);
						if (scanRect.getData() > currRect.getData())
							continue;
						else
						{
							// getting the index of first rectangle to move to the left
							index = i - 1;
							break;
						}
					}
					codeFlag = 0;
					downFlag = 1;
					currentDownCount = 0;
				}
				
				else if (downFlag == 1)
				{
					box.addOffsetRectangel(i, 0, 1);
					paintBox();
					currentDownCount++;
					if (currentDownCount >= 100)
					{
						downFlag = 0;
						rightBlockFlag = 1;
						indexRightCount = 0;
					}
				}
				/////////////////////////////////////////////
				else if (rightBlockFlag == 1)
				{
					if (index <= k)
					{
						rightBlockFlag = 0;
						LeftMainFlag = 1;
						currentLeftCount = 0;
					}
					else
					{
						rightFlag = 1;
						rightBlockFlag = 0;
						indexRightCount = 0;
					}
				}
				
				else if (rightFlag == 1)
				{
					box.addOffsetRectangel(index, 1, 0);
					paintBox();
					indexRightCount++;
					
					if (indexRightCount >= width)
					{
						rightFlag = 0;
						rightBlockFlag = 1;
						index--;
					}
				}
				//////////////////////////////////////////
				else if (LeftMainFlag == 1)
				{
					box.addOffsetRectangel(i, -1, 0);
					currentLeftCount++;
					paintBox();
					if (currentLeftCount >= width * (i - 1 - k))
					{
						LeftMainFlag = 0;
						upFlag = 1;
						currentUpCount = 0;
					}
				}
				
				else if (upFlag == 1)
				{
					box.addOffsetRectangel(i, 0, -1);
					currentUpCount++;
					paintBox();
					if (currentUpCount >= 100)
					{
						
						Rectangle currRect = box.getRectangle(i);
						System.out.println("\n\ni value " + box.getRectangle(i).getData());
						Rectangle scanRect;
						int t;
						for (t = i - 1; t >= 0; t--)
						{
							scanRect = box.getRectangle(t);
							System.out.println("t value " + scanRect.getData());
							
							if (scanRect.getData() > currRect.getData())
								box.setRectangle(box.getRectangle(t), t + 1);
							else
							{
								break;
							}
						}
						box.setRectangle(currRect, t + 1);
						System.out.println("\nPass " + i + "\n");
						for (int j = 0; j < box.getArrayRectangle().length; j++)
						{
							System.out.println("data " + box.getRectangle(j).getData() + " Index " + j);
						}
						upFlag = 0;
						codeFlag = 1;
						i++;
					}
				}
			}
		};
		
		timer.addActionListener(action);
		timer.start();
		//timer1.start();
		
	}
	
	public void selectionSort()
	{
		final Timer timer = new Timer(3, this);
		
		ActionListener action = new ActionListener()
		{
			// for running the code flag part initial conditions 
			
			// i represents the index of the current rectangle
			int i = 0;
			
			//stores the index of the first rectangle whose data is less of equal to the current rectangle 
			int k = 0;
			
			// varies from i + 1 to n - 1 (used for shifting all rectangles in this range)
			int index = 0;
			
			// Stores how much a the current rectangle has moved down
			int currentDownCount = 0;
			
			// counts how much a rectangle[index] has moved to the right (while shifting the rectangles) 	
			int indexRightCount = 0;
			
			// counts how much a current rectangle has moved left
			int currentLeftCount = 0;
			
			//stores the index of the least value node
			int smallest = 0;
			
			//  counts how much a current rectangle has moved up
			int currentUpCount = 0;
			
			int codeFlag = 1;
			int rightFlag = 0;
			int rightBlockFlag = 0;
			int downFlag = 0;
			int upFlag = 0;
			int LeftMainFlag = 0;
			int width = 20;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// step zero check whether to continue the algorithm or not
				if (i == 12)
					timer.stop();
				
				// Step one find a suitable index where to place the current rectangle
				else if (codeFlag == 1)
				{
					Rectangle currRect = box.getRectangle(i);
					Rectangle scanRect;
					smallest = i;
					index = i;
					for (k = i + 1; k < 12; k++)
					{
						scanRect = box.getRectangle(k);
						if (currRect.getData() > scanRect.getData())
						{
							currRect = scanRect;
							smallest = k;
							index = k - 1;
						}
					}
					codeFlag = 0;
					downFlag = 1;
					currentDownCount = 0;
				}
				
				else if (downFlag == 1)
				{
					box.addOffsetRectangel(smallest, 0, 1);
					paintBox();
					currentDownCount++;
					if (currentDownCount >= 100)
					{
						downFlag = 0;
						rightBlockFlag = 1;
						indexRightCount = 0;
					}
				}
				/////////////////////////////////////////////
				else if (rightBlockFlag == 1)
				{
					if (index == smallest || index < i)
					{
						rightBlockFlag = 0;
						LeftMainFlag = 1;
						currentLeftCount = 0;
					}
					else
					{
						rightFlag = 1;
						rightBlockFlag = 0;
						indexRightCount = 0;
					}
				}
				
				else if (rightFlag == 1)
				{
					box.addOffsetRectangel(index, 1, 0);
					paintBox();
					indexRightCount++;
					
					if (indexRightCount >= width)
					{
						rightFlag = 0;
						rightBlockFlag = 1;
						index--;
					}
				}
				//////////////////////////////////////////
				else if (LeftMainFlag == 1)
				{
					box.addOffsetRectangel(smallest, -1, 0);
					currentLeftCount++;
					paintBox();
					if (currentLeftCount >= width * (smallest - i))
					{
						LeftMainFlag = 0;
						upFlag = 1;
						currentUpCount = 0;
					}
				}
				
				else if (upFlag == 1)
				{
					box.addOffsetRectangel(smallest, 0, -1);
					currentUpCount++;
					paintBox();
					if (currentUpCount >= 100)
					{
						
						Rectangle smallestRect = box.getRectangle(smallest);
						//System.out.println("\n\ni value " + box.getRectangle(i).getData());
						Rectangle scanRect;
						int t;
						for (t = smallest; t > i; t--)
						{
							scanRect = box.getRectangle(t - 1);
							//System.out.println("t value " + scanRect.getData());
							
							//if (scanRect.getData() > currRect.getData())
							
							box.setRectangle(box.getRectangle(t - 1), t);
							
						}
						box.setRectangle(smallestRect, i);
						System.out.println("\nPass " + i + "\n");
						for (int j = 0; j < box.getArrayRectangle().length; j++)
						{
							System.out.println("data " + box.getRectangle(j).getData() + " Index " + j);
						}
						upFlag = 0;
						codeFlag = 1;
						i++;
					}
				}
			}
		};
		
		timer.addActionListener(action);
		timer.start();
		//timer1.start();
		
	}
	
/*	public void merge(final int l1, final int m1, final int r2, Timer mergeSortTimer)
	{
		final Timer timer = new Timer(3, this);
		ActionListener action = new ActionListener()
		{
			int i = 0;
			int k = 0;
			int index = 0;
			int currentDownCount = 0;
			int indexRightCount = 0;
			int currentLeftCount = 0;
			int smallest = 0;
			int currentUpCount = 0;
			int codeFlag = 1;
			int rightFlag = 0;
			int rightBlockFlag = 0;
			int downFlag = 0;
			int upFlag = 0;
			int LeftMainFlag = 0;
			int width = 20;
			int n1 = 0,n2 = 0;
			int j;
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// step zero check whether to continue the algorithm or not
				if (i >= n1 && j >= n2)
					timer.stop();
				
				// Step one find a suitable index where to place the current rectangle
				else if (codeFlag == 1)
				{
					Rectangle currRect = box.getRectangle(l + i);
					Rectangle scanRect = box.getRectangle(m + j);
					if(currRect)
						
					for (k = i + 1; k < 12; k++)
					{
						scanRect = box.getRectangle(k);
						if (currRect.getData() > scanRect.getData())
						{
							currRect = scanRect;
							smallest = k;
							index = k - 1;
						}
					}
					codeFlag = 0;
					downFlag = 1;
					currentDownCount = 0;
				}
				
				else if (downFlag == 1)
				{
					box.addOffsetRectangel(smallest, 0, 1);
					paintBox();
					currentDownCount++;
					if (currentDownCount >= 100)
					{
						downFlag = 0;
						rightBlockFlag = 1;
						indexRightCount = 0;
					}
				}
				/////////////////////////////////////////////
				else if (rightBlockFlag == 1)
				{
					if (index == smallest || index < i)
					{
						rightBlockFlag = 0;
						LeftMainFlag = 1;
						currentLeftCount = 0;
					}
					else
					{
						rightFlag = 1;
						rightBlockFlag = 0;
						indexRightCount = 0;
					}
				}
				
				else if (rightFlag == 1)
				{
					box.addOffsetRectangel(index, 1, 0);
					paintBox();
					indexRightCount++;
					
					if (indexRightCount >= width)
					{
						rightFlag = 0;
						rightBlockFlag = 1;
						index--;
					}
				}
				//////////////////////////////////////////
				else if (LeftMainFlag == 1)
				{
					box.addOffsetRectangel(smallest, -1, 0);
					currentLeftCount++;
					paintBox();
					if (currentLeftCount >= width * (smallest - i))
					{
						LeftMainFlag = 0;
						upFlag = 1;
						currentUpCount = 0;
					}
				}
				
				else if (upFlag == 1)
				{
					box.addOffsetRectangel(smallest, 0, -1);
					currentUpCount++;
					paintBox();
					if (currentUpCount >= 100)
					{
						
						Rectangle smallestRect = box.getRectangle(smallest);
						//System.out.println("\n\ni value " + box.getRectangle(i).getData());
						Rectangle scanRect;
						int t;
						for (t = smallest; t > i; t--)
						{
							scanRect = box.getRectangle(t - 1);
							//System.out.println("t value " + scanRect.getData());
							
							//if (scanRect.getData() > currRect.getData())
							
							box.setRectangle(box.getRectangle(t - 1), t);
							
						}
						box.setRectangle(smallestRect, i);
						System.out.println("\nPass " + i + "\n");
						for (int j = 0; j < box.getArrayRectangle().length; j++)
						{
							System.out.println("data " + box.getRectangle(j).getData() + " Index " + j);
						}
						upFlag = 0;
						codeFlag = 1;
						i++;
					}
				}
			}
	}
*/
	
	private void paintBox()
	{
		
		// Current square state, stored as final variables
		// to avoid repeat invocations of the same methods.
		
		// The square is moving, repaint background
		// over the old square location.
		repaint();
		// delay(100);
		// repaint(CURR_X + redSquare.getWidth(), CURR_Y + redSquare.getHeight(),
		// CURR_W + OFFSET + 10, CURR_H + OFFSET + 10);
		
		// Update coordinates.
		// box.setBaseX(x);
		// box.setBaseY(y);
		// Repaint the square at the new location.
		// repaint(redSquare.getX(), redSquare.getY(), redSquare.getWidth() +
		// OFFSET, redSquare.getHeight() + OFFSET);
		// repaint(CURR_X + redSquare.getWidth(), CURR_Y + redSquare.getHeight(),
		// CURR_W + OFFSET + 10, CURR_H + OFFSET + 10);
		// repaint();
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(500, 500);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawString("This is my custom Panel!", 10, 20);
		box.paintArrayRectangle(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
