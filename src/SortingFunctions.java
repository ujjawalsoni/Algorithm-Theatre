import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class SortingFunctions extends MyPanel
{
	
<<<<<<< HEAD
	private static final int TIMER_SPEED = 1;
	private static final int XCHANGE = 2;
	private static final int YCHANGE = 2;
	private static final int DOWN = 100;
=======
	private static final int TIMER_SPEED = 20;
	private static final int XCHANGE = 4;
	private static final int YCHANGE = 4;
	private static final int DOWN = 200;
>>>>>>> 1ce0eb234a5b8bf70910bcdaf526f6f77b299d31
	
	public SortingFunctions(ArrayList<Integer> list)
	{
		super(list);
	}
	
	public Timer bubbleSort()
	{
		
		final Timer timer = new Timer(TIMER_SPEED, this);
		final int width = super.getRectangleSpace();
		final int size = super.box.getArrayRectangle().length;
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
			
			// different flags for running different parts of the program
			
			// running the main code
			int codeFlag = 1;
			
			// moving a rectangle to the right
			int rightFlag = 0;
			
			// moving a rectangle down
			int downFlag = 0;
			
			// moving a rectangle up
			int upFlag = 0;
			
			// moving a rectangle to the left
			int leftFlag = 0;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// step zero check whether to continue the algorithm or not
				if ((i >= size))
					timer.stop();
				
				// inner loop conditions
				else if (j >= size - 1 - i)
				{
					// if no swap has been done in the inner loop then stop the whole process
					if (swap == 0)
						timer.stop();
					i++;
					swap = 0;
					j = 0;
				}
				
				// Inner loop to compare the rectangle next to the current rectangle has
				// And if yes swap then
				else if (codeFlag == 1)
				{
					Rectangle currRect = box.getRectangle(j);
					Rectangle nextRect = box.getRectangle(j + 1);
					
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
				
				//shifting the next block down
				else if (downFlag == 1)
				{
					box.addOffsetRectangel(j + 1, 0, YCHANGE);
					paintBox();
					currentDownCount += YCHANGE;
					if (currentDownCount >= DOWN)
					{
						int excess = currentDownCount - DOWN;
						box.addOffsetRectangel(j + 1, 0, -excess);
						paintBox();
						downFlag = 0;
						rightFlag = 1;
						indexRightCount = 0;
					}
				}
				
				// shifting the current block to its right
				else if (rightFlag == 1)
				{
					box.addOffsetRectangel(j, XCHANGE, 0);
					paintBox();
					indexRightCount += XCHANGE;
					if (indexRightCount >= width)
					{
						System.out.println("Shifting the currentBlock Right Done");
						int excess = indexRightCount - width;
						box.addOffsetRectangel(j, -excess, 0);
						paintBox();
						rightFlag = 0;
						leftFlag = 1;
						currentLeftCount = 0;
					}
				}
				
				// shifting the next block to left
				else if (leftFlag == 1)
				{
					box.addOffsetRectangel(j + 1, -XCHANGE, 0);
					currentLeftCount += XCHANGE;
					paintBox();
					if (currentLeftCount >= width)
					{
						int excess = currentLeftCount - width;
						box.addOffsetRectangel(j + 1, excess, 0);
						paintBox();
						leftFlag = 0;
						upFlag = 1;
						currentUpCount = 0;
					}
				}
				
				// moving the next block up
				else if (upFlag == 1)
				{
					box.addOffsetRectangel(j + 1, 0, -YCHANGE);
					currentUpCount += YCHANGE;
					paintBox();
					if (currentUpCount >= DOWN)
					{
						int excess = currentUpCount - DOWN;
						box.addOffsetRectangel(j, 0, excess);
						paintBox();
						
						// Reflecting the same procedure internally
						Rectangle currRect = box.getRectangle(j);
						Rectangle nextRect = box.getRectangle(j + 1);
						
						box.setRectangle(currRect, j + 1);
						box.setRectangle(nextRect, j);
						
						upFlag = 0;
						codeFlag = 1;
						j++;
					}
				}
			}
		};
		
		timer.addActionListener(action);
		timer.start();
		return timer;
	}
	
	public Timer insertionSort()
	{
		final Timer timer = new Timer(TIMER_SPEED, this);
		final int width = super.getRectangleSpace();
		final int size = super.box.getArrayRectangle().length;
		
		ActionListener action = new ActionListener()
		{
			
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
			
			// running the main code
			int codeFlag = 1;
			
			// moving a rectangle to the right
			int rightFlag = 0;
			
			// moving a rectangle down
			int downFlag = 0;
			
			// moving a rectangle up
			int upFlag = 0;
			
			// moving all rectangle is range of index
			int rightBlockFlag = 0;
			
			// Moving the current rectangle left
			int leftFlag = 0;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// step zero check whether to continue the algorithm or not
				if (i >= size)
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
				
				//moving the current rectangle down
				else if (downFlag == 1)
				{
					box.addOffsetRectangel(i, 0, YCHANGE);
					paintBox();
					currentDownCount += YCHANGE;
					if (currentDownCount >= DOWN)
					{
						int excess = currentDownCount - DOWN;
						box.addOffsetRectangel(i, 0, -excess);
						paintBox();
						downFlag = 0;
						rightBlockFlag = 1;
						indexRightCount = 0;
					}
				}
				
				// moving the block of rectangle to the left
				else if (rightBlockFlag == 1)
				{
					if (index <= k)
					{
						rightBlockFlag = 0;
						leftFlag = 1;
						currentLeftCount = 0;
					}
					else
					{
						rightFlag = 1;
						rightBlockFlag = 0;
						indexRightCount = 0;
					}
				}
				
				// moving each rectangle of the block to the right
				else if (rightFlag == 1)
				{
					box.addOffsetRectangel(index, XCHANGE, 0);
					paintBox();
					indexRightCount += XCHANGE;
					
					if (indexRightCount >= width)
					{
						int excess = indexRightCount - width;
						box.addOffsetRectangel(index, -excess, 0);
						paintBox();
						rightFlag = 0;
						rightBlockFlag = 1;
						index--;
					}
				}
				
				// moving the current rectangle to the left
				else if (leftFlag == 1)
				{
					box.addOffsetRectangel(i, -XCHANGE, 0);
					currentLeftCount += XCHANGE;
					paintBox();
					if (currentLeftCount >= width * (i - 1 - k))
					{
						int excess = currentLeftCount - width * (i - 1 - k);
						box.addOffsetRectangel(i, excess, 0);
						paintBox();
						leftFlag = 0;
						upFlag = 1;
						currentUpCount = 0;
					}
				}
				
				// moving the current rectangle up
				else if (upFlag == 1)
				{
					box.addOffsetRectangel(i, 0, -YCHANGE);
					currentUpCount += YCHANGE;
					paintBox();
					if (currentUpCount >= DOWN)
					{
						int excess = currentUpCount - DOWN;
						box.addOffsetRectangel(i, 0, excess);
						paintBox();
						
						// Reflecting the changes internally
						
						Rectangle currRect = box.getRectangle(i);
						Rectangle scanRect;
						int t;
						for (t = i - 1; t >= 0; t--)
						{
							scanRect = box.getRectangle(t);
							if (scanRect.getData() > currRect.getData())
								box.setRectangle(box.getRectangle(t), t + 1);
							else
								break;
						}
						box.setRectangle(currRect, t + 1);
						
						upFlag = 0;
						codeFlag = 1;
						i++;
					}
				}
			}
		};
		timer.addActionListener(action);
		timer.start();
		return timer;
	}
	
	public Timer selectionSort()
	{
		final Timer timer = new Timer(TIMER_SPEED, this);
		final int width = super.getRectangleSpace();
		final int size = super.box.getArrayRectangle().length;
		
		ActionListener action = new ActionListener()
		{
			
			// i represents the index of the current rectangle
			int i = 0;
			
			//stores the index of the first rectangle whose data is less of equal to the current rectangle 
			int k = 0;
			
			// 
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
			
			// running the main code
			int codeFlag = 1;
			
			// moving a rectangle to the right
			int rightFlag = 0;
			
			// moving a rectangle down
			int downFlag = 0;
			
			// moving a rectangle up
			int upFlag = 0;
			
			// moving a rectangle to the left
			int leftFlag = 0;
			
			// moving a whole block of rectangle to the right
			int rightBlockFlag = 0;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// step zero check whether to continue the algorithm or not
				if (i >= size)
					timer.stop();
				
				// Step one find a suitable index where to place the current rectangle
				else if (codeFlag == 1)
				{
					Rectangle currRect = box.getRectangle(i);
					Rectangle scanRect;
					smallest = i;
					index = i;
					for (k = i + 1; k < size; k++)
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
				
				// moving the smallest rectangle down
				else if (downFlag == 1)
				{
					box.addOffsetRectangel(smallest, 0, YCHANGE);
					paintBox();
					currentDownCount += YCHANGE;
					if (currentDownCount >= DOWN)
					{
						int excess = currentDownCount - DOWN;
						box.addOffsetRectangel(smallest, 0, -excess);
						paintBox();
						if (smallest == i)
						{
							downFlag = 0;
							upFlag = 1;
							currentUpCount = 0;
						}
						else
						{
							downFlag = 0;
							rightBlockFlag = 1;
							indexRightCount = 0;
						}
					}
				}
				
				//moving the block of rectangle to the right whic are less than the smallest
				else if (rightBlockFlag == 1)
				{
					if (index == smallest || index < i)
					{
						rightBlockFlag = 0;
						leftFlag = 1;
						currentLeftCount = 0;
					}
					else
					{
						rightFlag = 1;
						rightBlockFlag = 0;
						indexRightCount = 0;
					}
				}
				
				// moving each rectangle in the block to the right
				else if (rightFlag == 1)
				{
					box.addOffsetRectangel(index, XCHANGE, 0);
					paintBox();
					indexRightCount += XCHANGE;
					
					if (indexRightCount >= width)
					{
						int excess = indexRightCount - width;
						box.addOffsetRectangel(index, -excess, 0);
						paintBox();
						rightFlag = 0;
						rightBlockFlag = 1;
						index--;
					}
				}
				
				// moving the smallest rectangle to the left
				else if (leftFlag == 1)
				{
					box.addOffsetRectangel(smallest, -XCHANGE, 0);
					currentLeftCount += XCHANGE;
					paintBox();
					if (currentLeftCount >= width * (smallest - i))
					{
						int excess = currentLeftCount - width * (smallest - i);
						box.addOffsetRectangel(smallest, excess, 0);
						paintBox();
						leftFlag = 0;
						upFlag = 1;
						currentUpCount = 0;
					}
				}
				
				// moving the smallest rectangle up
				else if (upFlag == 1)
				{
					box.addOffsetRectangel(smallest, 0, -YCHANGE);
					currentUpCount += YCHANGE;
					paintBox();
					if (currentUpCount >= DOWN)
					{
						int excess = currentUpCount - DOWN;
						box.addOffsetRectangel(smallest, 0, excess);
						paintBox();
						Rectangle smallestRect = box.getRectangle(smallest);
						
						// reflecting the changes internally
						Rectangle scanRect;
						int t;
						for (t = smallest; t > i; t--)
						{
							scanRect = box.getRectangle(t - 1);
							box.setRectangle(box.getRectangle(t - 1), t);
						}
						
						box.setRectangle(smallestRect, i);
						upFlag = 0;
						codeFlag = 1;
						i++;
					}
				}
			}
		};
		timer.addActionListener(action);
		timer.start();
		return timer;
	}
	
	public void mergeSort()
	{
		final Timer timer = new Timer(TIMER_SPEED, this);
		final int width = super.getRectangleSpace();
		final int size = super.box.getArrayRectangle().length;
		
		ActionListener action = new ActionListener()
		{
			int currentSize = 1;
			int leftStart = 0;
			int rightEnd = 0;
			int mid = 0;
			int subCodeFlag = 1;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				System.out.println("Timer1 running");
				// step zero check whether to continue the algorithm or not
				if (currentSize >= 12)
					timer.stop();
				// Step one find a suitable index where to place the current rectangle
				else if (subCodeFlag == 1)
				{
					if (leftStart >= 12 - 1)
					{
						currentSize = currentSize * 2;
						leftStart = 0;
					}
					else
					{
						mid = leftStart + currentSize - 1;
						if (leftStart + 2 * currentSize - 1 >= 12 - 1)
							rightEnd = 12 - 1;
						else
							rightEnd = leftStart + 2 * currentSize - 1;
						timer.stop();
						System.out.println("Timer1 Stop");
						
						leftStart += 2 * currentSize;
					}
				}
			}
		};
		timer.addActionListener(action);
		timer.start();
	}
	
	public void merge(final int l, final int m, final int r)
	{
		final Timer timer = new Timer(TIMER_SPEED, this);
		final int width = super.getRectangleSpace();
		final int size = super.box.getArrayRectangle().length;
		
		ActionListener action = new ActionListener()
		{
			int i = 0;
			int j = 0;
			int index = 0;
			int currentDownCount = 0;
			int indexRightCount = 0;
			int currentLeftCount = 0;
			int currentUpCount = 0;
			int codeFlag = 1;
			int rightFlag = 0;
			int rightBlockFlag = 0;
			int downFlag = 0;
			int upFlag = 0;
			int LeftMainFlag = 0;
<<<<<<< HEAD
			int width = 25;
			int x=0;
=======
			int x = 0;
>>>>>>> 1ce0eb234a5b8bf70910bcdaf526f6f77b299d31
			
			//TODO the stopping condition is wrong 
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Timer2 running");
				// step zero check whether to continue the algorithm or not
				if ((x > m - l + 1) || j > r - m)
				{
					
					timer.stop();
					System.out.println("Timer2 stopping");
					//mergeSortTimer.start();
				}
				
				// Step one find a suitable index where to place the current rectangle
				else if (codeFlag == 1)
				{
					Rectangle currRect = box.getRectangle(l + i);
					Rectangle scanRect = box.getRectangle(m + 1 + j);
					if (currRect.getData() < scanRect.getData())
					{
						i++;
						x++;
					}
					else
					{
						codeFlag = 0;
						downFlag = 1;
						currentDownCount = 0;
					}
				}
				else if (downFlag == 1)
				{
					box.addOffsetRectangel(m + j + 1, 0, YCHANGE);
					paintBox();
					currentDownCount += YCHANGE;
					if (currentDownCount >= DOWN)
					{
						int excess = currentDownCount - DOWN;
						box.addOffsetRectangel(m + j + 1, 0, -excess);
						paintBox();
						downFlag = 0;
						rightBlockFlag = 1;
						indexRightCount = 0;
						index = m + j;
					}
				}
				/////////////////////////////////////////////
				else if (rightBlockFlag == 1)
				{
					if (index < l + i)
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
					box.addOffsetRectangel(m + j + 1, -1, 0);
					currentLeftCount++;
					paintBox();
					if (currentLeftCount >= width * (m + j + 1 - (l + i)))
					{
						LeftMainFlag = 0;
						upFlag = 1;
						currentUpCount = 0;
					}
				}
				
				else if (upFlag == 1)
				{
					box.addOffsetRectangel(m + j + 1, 0, -1);
					currentUpCount++;
					paintBox();
					if (currentUpCount >= 100)
					{
						
						Rectangle smallestRect = box.getRectangle(l + i);
						Rectangle scanRect = box.getRectangle(m + j + 1);
						if (smallestRect.getData() <= scanRect.getData())
							;
						//System.out.println("\n\ni value " + box.getRectangle(i).getData());
						else
						{
							smallestRect = scanRect;
							int t;
							for (t = m + j; t >= i + l; t--)
							{
								scanRect = box.getRectangle(t);
								//System.out.println("t value " + scanRect.getData());
								
								//if (scanRect.getData() > currRect.getData())
								
								box.setRectangle(box.getRectangle(t), t + 1);
							}
							box.setRectangle(smallestRect, i + l);
							j++;
						}
						System.out.println("\nPass " + i + j + "\n");
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
	}
}
