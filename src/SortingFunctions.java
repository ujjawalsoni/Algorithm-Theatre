/**
 *	Project:	Algorithm Theatre
 *
 * 	Authors:	Ishu Dharmendra Garg (CS13B060)
 *				Ujjawal Soni (CS13B053)
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.Timer;

public class SortingFunctions extends MyPanel
{
	private static final long serialVersionUID = 1L;

	private static final int TIMER_SPEED = 25;
	private static final int XCHANGE = 8;
	private static final int YCHANGE = 4;
	private static final int DOWN = 100;
	private static final int DELAY = 20;
	private static final Color BASE_COLOR = Color.RED;
	private static final Color END_COLOR = Color.YELLOW;
	private static final Color DFAULT_COLOR = Color.YELLOW;
	private static final Color FOCUS_COLOR1 = Color.BLUE;
	private static final Color FOCUS_COLOR2 = Color.GREEN;
	private static final Color BLOCK_COLOR1 = Color.PINK;
	private static final Color BLOCK_COLOR2 = Color.GRAY;
	Random random;
	
	public SortingFunctions(ArrayList<Integer> list)
	{
		super(list);
		random = new Random();
	}
	
	public Timer bubbleSort()
	{
		box.setBaseColor(BASE_COLOR);
		box.resetArrayRectangle();
		paintBox();
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
			
			// for halting for some time
			int delay1 = 0;
			
			// code for delay
			int delayFlag1 = 0;
			
			int delay2 = 0;
			
			// code for delay
			int delayFlag2 = 0;
			
			int delay3 = 0;
			
			// code for delay
			int delayFlag3 = 0;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// step zero check whether to continue the algorithm or not
				if ((i >= size))
				{
					timer.stop();
					box.setColorRange(0, box.getNumber() - 1, END_COLOR);
					paintBox();
				}
				// inner loop conditions
				else if (j >= size - 1 - i)
				{
					// if no swap has been done in the inner loop then stop the whole process
					if (swap == 0)
					{
						timer.stop();
						box.setColorRange(0, box.getNumber() - 1, END_COLOR);
						paintBox();
					}
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
					box.getRectangle(j).setColor(FOCUS_COLOR1);
					box.getRectangle(j + 1).setColor(FOCUS_COLOR2);
					paintBox();
					if (nextRect.getData() < currRect.getData())
					{
						swap = 1;
						codeFlag = 0;
						delay3 = 0;
						delayFlag3 = 1;
					}
					else
					{
						codeFlag = 0;
						delay1 = 0;
						delayFlag1 = 1;
					}
				}
				else if (delayFlag3 == 1)
				{
					delay3++;
					if (delay3 > DELAY)
					{
						delayFlag3 = 0;
						downFlag = 1;
						currentDownCount = 0;
					}
				}
				// introducing delay
				else if (delayFlag1 == 1)
				{
					delay1++;
					if (delay1 > DELAY)
					{
						delayFlag1 = 0;
						codeFlag = 1;
						box.getRectangle(j).setColor(BASE_COLOR);
						box.getRectangle(j + 1).setColor(BASE_COLOR);
						paintBox();
						j++;
					}
				}
				//shifting the next block down
				else if (downFlag == 1)
				{
					box.addOffsetRectangle(j + 1, 0, YCHANGE);
					paintBox();
					currentDownCount += YCHANGE;
					if (currentDownCount >= DOWN)
					{
						int excess = currentDownCount - DOWN;
						box.addOffsetRectangle(j + 1, 0, -excess);
						paintBox();
						downFlag = 0;
						rightFlag = 1;
						indexRightCount = 0;
					}
				}
				
				// shifting the current block to its right
				else if (rightFlag == 1)
				{
					box.addOffsetRectangle(j, XCHANGE, 0);
					paintBox();
					indexRightCount += XCHANGE;
					if (indexRightCount >= width)
					{
						int excess = indexRightCount - width;
						box.addOffsetRectangle(j, -excess, 0);
						paintBox();
						rightFlag = 0;
						leftFlag = 1;
						currentLeftCount = 0;
					}
				}
				
				// shifting the next block to left
				else if (leftFlag == 1)
				{
					box.addOffsetRectangle(j + 1, -XCHANGE, 0);
					currentLeftCount += XCHANGE;
					paintBox();
					if (currentLeftCount >= width)
					{
						int excess = currentLeftCount - width;
						box.addOffsetRectangle(j + 1, excess, 0);
						paintBox();
						leftFlag = 0;
						upFlag = 1;
						currentUpCount = 0;
					}
				}
				
				// moving the next block up
				else if (upFlag == 1)
				{
					box.addOffsetRectangle(j + 1, 0, -YCHANGE);
					currentUpCount += YCHANGE;
					paintBox();
					if (currentUpCount >= DOWN)
					{
						int excess = currentUpCount - DOWN;
						box.addOffsetRectangle(j, 0, excess);
						paintBox();
						
						// Reflecting the same procedure internally
						Rectangle currRect = box.getRectangle(j);
						Rectangle nextRect = box.getRectangle(j + 1);
						
						box.setRectangle(currRect, j + 1);
						box.setRectangle(nextRect, j);
						upFlag = 0;
						delayFlag2 = 1;
						delay2 = 0;
						
					}
				}
				else if (delayFlag2 == 1)
				{
					delay2++;
					if (delay2 > DELAY)
					{
						delayFlag2 = 0;
						codeFlag = 1;
						box.getRectangle(j).setColor(BASE_COLOR);
						box.getRectangle(j + 1).setColor(BASE_COLOR);
						j++;
					}
				}
			}
		};
		timer.addActionListener(action);
		return timer;
	}
	
	public Timer insertionSort()
	{
		final Timer timer = new Timer(TIMER_SPEED, this);
		final int width = super.getRectangleSpace();
		final int size = super.box.getArrayRectangle().length;
		box.resetArrayRectangle();
		paintBox();
		
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
			
			// for halting for some time
			int delay1 = 0;
			
			// code for delay
			int delayFlag1 = 0;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// step zero check whether to continue the algorithm or not
				if (i >= size)
				{
					System.out.println("insertion sort terminated");
					timer.stop();
					box.setColorRange(0, box.getNumber() - 1, END_COLOR);
					paintBox();
				}
				// Step one find a suitable index where to place the current rectangle
				else if (codeFlag == 1)
				{
					Rectangle currRect = box.getRectangle(i);
					currRect.setColor(FOCUS_COLOR1);
					Rectangle scanRect;
					for (k = i - 1; k >= 0; k--)
					{
						scanRect = box.getRectangle(k);
						scanRect.setColor(FOCUS_COLOR2);
						if (scanRect.getData() > currRect.getData())
						{
							continue;
						}
						else
							break;
					}
					
					// getting the index of first rectangle to move to the left
					index = i - 1;
					codeFlag = 0;
					downFlag = 1;
					currentDownCount = 0;
				}
				
				//moving the current rectangle down
				else if (downFlag == 1)
				{
					box.addOffsetRectangle(i, 0, YCHANGE);
					paintBox();
					currentDownCount += YCHANGE;
					if (currentDownCount >= DOWN)
					{
						int excess = currentDownCount - DOWN;
						box.addOffsetRectangle(i, 0, -excess);
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
					box.addOffsetRectangle(index, XCHANGE, 0);
					paintBox();
					indexRightCount += XCHANGE;
					
					if (indexRightCount >= width)
					{
						int excess = indexRightCount - width;
						box.addOffsetRectangle(index, -excess, 0);
						paintBox();
						rightFlag = 0;
						rightBlockFlag = 1;
						index--;
					}
				}
				
				// moving the current rectangle to the left
				else if (leftFlag == 1)
				{
					box.addOffsetRectangle(i, -XCHANGE, 0);
					currentLeftCount += XCHANGE;
					paintBox();
					if (currentLeftCount >= width * (i - 1 - k))
					{
						int excess = currentLeftCount - width * (i - 1 - k);
						box.addOffsetRectangle(i, excess, 0);
						paintBox();
						leftFlag = 0;
						upFlag = 1;
						currentUpCount = 0;
					}
				}
				
				// moving the current rectangle up
				else if (upFlag == 1)
				{
					box.addOffsetRectangle(i, 0, -YCHANGE);
					currentUpCount += YCHANGE;
					paintBox();
					if (currentUpCount >= DOWN)
					{
						int excess = currentUpCount - DOWN;
						box.addOffsetRectangle(i, 0, excess);
						paintBox();
						
						// Reflecting the changes internally
						
						Rectangle currRect = box.getRectangle(i);
						currRect.setColor(FOCUS_COLOR2);
						paintBox();
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
						delayFlag1 = 1;
						upFlag = 0;
						delay1 = 0;
						upFlag = 0;
					}
				}
				else if (delayFlag1 == 1)
				{
					delay1++;
					if (delay1 >= DELAY)
					{
						delayFlag1 = 0;
						codeFlag = 1;
						i++;
					}
				}
			}
		};
		timer.addActionListener(action);
		return timer;
	}
	
	public Timer selectionSort()
	{
		final Timer timer = new Timer(TIMER_SPEED, this);
		final int width = super.getRectangleSpace();
		final int size = super.box.getArrayRectangle().length;
		box.resetArrayRectangle();
		paintBox();

		box.setColorRange(0, size - 1, BLOCK_COLOR1);
		box.getRectangle(0).setColor(FOCUS_COLOR2);
		paintBox();

		ActionListener action = new ActionListener()
		{
			
			// i represents the index of the current rectangle
			int i = 0;
			
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
			
			int codeSearchFlag = 0;
			
			int codeInitializeFlag = 1;
			
			int l = 0;
			
			// current smallest rectangle in the unsorted array
			Rectangle smallestRect;
			
			// delay flags for introducing delays
			int delay1 = 0;
			int delayFlag1 = 0;
			int delay2 = 0;
			int delayFlag2 = 0;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// step zero check whether to continue the algorithm or not
				if (i >= size - 1)
				{
					timer.stop();
					box.setBaseColor(END_COLOR);
					paintBox();
				}
				// Step one find a suitable index where to place the current rectangle
				else if (codeFlag == 1)
				{
					// initializing conditions for a block
					if (codeInitializeFlag == 1)
					{
						l = i + 1;
						smallest = i;
						index = i;
						smallestRect = box.getRectangle(i);
						codeInitializeFlag = 0;
						codeSearchFlag = 1;
					}
					
					// searching the smallest rectangle in the unsorted list
					else if (codeSearchFlag == 1)
					{
						if (l >= size)
						{
							codeFlag = 0;
							codeSearchFlag = 0;
							codeInitializeFlag = 1;
							
							downFlag = 1;
							currentDownCount = 0;
							
						}
						else
						{
							Rectangle currentRect = box.getRectangle(l);
							
							if (currentRect.getData() < smallestRect.getData())
							{
								smallestRect.setColor(BLOCK_COLOR2);
								
								smallestRect = currentRect;
								smallestRect.setColor(FOCUS_COLOR2);
								smallest = l;
								index = l - 1;
								paintBox();
							}
							else
							{
								currentRect.setColor(BLOCK_COLOR2);
								paintBox();
							}
							codeSearchFlag = 0;
							delayFlag1 = 1;
							delay1 = 0;
						}
					}
					// delay after the smallest element has been searched
					else if (delayFlag1 == 1)
					{
						delay1++;
						if (delay1 >= DELAY)
						{
							l++;
							delayFlag1 = 0;
							codeSearchFlag = 1;
						}
					}
				}
				
				// moving the smallest rectangle down
				else if (downFlag == 1)
				{
					box.addOffsetRectangle(smallest, 0, YCHANGE);
					paintBox();
					currentDownCount += YCHANGE;
					if (currentDownCount >= DOWN)
					{
						int excess = currentDownCount - DOWN;
						box.addOffsetRectangle(smallest, 0, -excess);
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
				
				//moving the block of rectangle to the right which are less than the smallest
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
					box.addOffsetRectangle(index, XCHANGE, 0);
					paintBox();
					indexRightCount += XCHANGE;
					
					if (indexRightCount >= width)
					{
						int excess = indexRightCount - width;
						box.addOffsetRectangle(index, -excess, 0);
						paintBox();
						rightFlag = 0;
						rightBlockFlag = 1;
						index--;
					}
				}
				
				// moving the smallest rectangle to the left
				else if (leftFlag == 1)
				{
					box.addOffsetRectangle(smallest, -XCHANGE, 0);
					currentLeftCount += XCHANGE;
					paintBox();
					if (currentLeftCount >= width * (smallest - i))
					{
						int excess = currentLeftCount - width * (smallest - i);
						box.addOffsetRectangle(smallest, excess, 0);
						paintBox();
						leftFlag = 0;
						upFlag = 1;
						currentUpCount = 0;
					}
				}
				
				// moving the smallest rectangle up
				else if (upFlag == 1)
				{
					box.addOffsetRectangle(smallest, 0, -YCHANGE);
					currentUpCount += YCHANGE;
					paintBox();
					if (currentUpCount >= DOWN)
					{
						int excess = currentUpCount - DOWN;
						box.addOffsetRectangle(smallest, 0, excess);
						paintBox();
						Rectangle smallestRect = box.getRectangle(smallest);
						
						// reflecting the changes internally
						int t;
						for (t = smallest; t > i; t--)
						{
							box.setRectangle(box.getRectangle(t - 1), t);
						}
						
						box.setRectangle(smallestRect, i);
						upFlag = 0;
						delayFlag2 = 1;
						delay2 = 0;
						if (i + 1 < size)
						{
							box.setColorRange(i + 1, size - 1, BLOCK_COLOR1);
							box.getRectangle(i + 1).setColor(FOCUS_COLOR2);
						}
						box.getRectangle(i).setColor(FOCUS_COLOR1);
						paintBox();
					}
				}
				else if (delayFlag2 == 1)
				{
					delay2++;
					if (delay2 >= DELAY)
					{
						delayFlag2 = 0;
						codeFlag = 1;
						i++;
					}
				}
			}
		};
		timer.addActionListener(action);
		return timer;
	}
	
	public Timer mergeSort()
	{
		final Timer timer = new Timer(TIMER_SPEED, this);
		final int size = super.box.getArrayRectangle().length;
		final int width = super.getRectangleSpace();
		box.resetArrayRectangle();
		paintBox();
		
		ActionListener action = new ActionListener()
		{
			// the current size of the block to be sorted 
			int currentSize = 1;
			
			// left start , mid and right end of the block to be sorted
			int leftStart = 0;
			int rightEnd = 0;
			int mid = 0;
			
			// delay flags
			int mergeSortDelayFlag1 = 0;
			int mergeSortdelay1 = 0;
			
			// current index of the first array to be merged
			int i;
			// current index of first element of second array to be merged 
			int j;
			
			// index is used to shift all the element that are between i and j (including i) 
			int index = 0;
			
			// merge flags
			int mergeSortCodeFlag = 1;
			int codeFlag = 1;
			int rightFlag = 0;
			int rightBlockFlag = 0;
			int downFlag = 0;
			int upFlag = 0;
			int leftFlag = 0;
			
			int currentDownCount = 0;
			int indexRightCount = 0;
			int currentLeftCount = 0;
			int currentUpCount = 0;
			
			int delay1 = 0;
			int delayFlag1 = 0;
			int delay2 = 0;
			int delayFlag2 = 0;
			int colorDelayFlag2 = 0;
			
			int colorFlag1 = 1;
			int colorDelayFlag1 = 0;
			
			int colorDelay1 = 0;
			int colorFlag2 = 1;
			int colorDelay2 = 0;
			
			// left mid and right
			int l = 0;
			int m = 0;
			int r = 0;
			
			// for integrating the merge with the mergeSort
			int mergeFlag = 0;
			int mergeInitializeFlag = 0;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// step zero check whether to continue the algorithm or not
				if (currentSize >= size)
				{
					System.out.println("MERGESORT END");
					timer.stop();
				}
				
				// deciding the block to to merged
				else if (mergeSortCodeFlag == 1)
				{
					if (leftStart >= size - 1)
					{
						currentSize = currentSize * 2;
						leftStart = 0;
					}
					
					else
					{
						mid = leftStart + currentSize - 1;
						if (leftStart + 2 * currentSize - 1 >= size - 1)
							rightEnd = size - 1;
						else
							rightEnd = leftStart + 2 * currentSize - 1;
						box.setBaseColor(BASE_COLOR);
						paintBox();
						mergeSortDelayFlag1 = 1;
						mergeSortdelay1 = 0;
						mergeSortCodeFlag = 0;
						if(mid >= rightEnd)
						{
							leftStart += 2 * currentSize;
							mergeSortDelayFlag1 = 0;
							mergeSortCodeFlag = 1;
						}
					}
				}
				else if (mergeSortDelayFlag1 == 1)
				{
					mergeSortdelay1++;
					if (mergeSortdelay1 >= DELAY)
					{
						l = leftStart;
						m = mid;
						r = rightEnd;
						mergeSortDelayFlag1 = 0;
						mergeInitializeFlag = 1;
						leftStart += 2 * currentSize;
					}
				}
				
				// Merging the block selected
				
				// initializing the variables needed by the merge block 
				else if (mergeInitializeFlag == 1)
				{
					i = l;
					j = m + 1;
					index = 0;
					currentDownCount = 0;
					indexRightCount = 0;
					currentLeftCount = 0;
					currentUpCount = 0;
					codeFlag = 1;
					rightFlag = 0;
					rightBlockFlag = 0;
					downFlag = 0;
					upFlag = 0;
					leftFlag = 0;
					delay1 = 0;
					delayFlag1 = 0;
					delay2 = 0;
					delayFlag2 = 0;
					colorFlag1 = 1;
					colorDelayFlag1 = 0;
					colorDelay1 = 0;
					colorFlag2 = 1;
					colorDelayFlag2 = 0;
					colorDelay2 = 0;
					box.setBaseColor(BASE_COLOR);
					box.setColorRange(l, m, BLOCK_COLOR1);
					box.setColorRange(m + 1, r, BLOCK_COLOR2);
					paintBox();
					
					mergeFlag = 1;
					mergeInitializeFlag = 0;
				}
				
				// actual merging starts here
				else if (mergeFlag == 1)
				{
					// step zero check whether to continue merging or not
					if (j > r)
					{
						if (i >= j)
						{
							mergeFlag = 0;
							mergeSortCodeFlag = 1;
						}
						else if (colorFlag1 == 1)
						{
							box.getRectangle(i).setColor(END_COLOR);
							paintBox();
							colorDelay1 = 0;
							colorDelayFlag1 = 1;
							colorFlag1 = 0;
						}
						else if (colorDelayFlag1 == 1)
						{
							colorDelay1++;
							if (colorDelay1 >= DELAY)
							{
								i++;
								colorDelayFlag1 = 0;
								colorFlag1 = 1;
								colorDelay1 = 0;
							}
						}
					}
					
					else if (i >= j)
					{
						if (j > r)
						{
							mergeFlag = 0;
							mergeSortCodeFlag = 1;
						}
						else if (colorFlag2 == 1)
						{
							box.getRectangle(j).setColor(END_COLOR);
							paintBox();
							colorDelay2 = 0;
							colorDelayFlag2 = 1;
							colorFlag2 = 0;
						}
						else if (colorDelayFlag2 == 1)
						{
							colorDelay2++;
							if (colorDelay2 >= DELAY)
							{
								j++;
								colorDelayFlag2 = 0;
								colorFlag2 = 1;
								colorDelay2 = 0;
							}
						}
					}
					
					// Step one Check whether a[i] <= a[j]
					else if (codeFlag == 1)
					{
						Rectangle currRect = box.getRectangle(i);
						Rectangle scanRect = box.getRectangle(j);
						currRect.setColor(FOCUS_COLOR1);
						scanRect.setColor(FOCUS_COLOR2);
						paintBox();
						delayFlag2 = 1;
						codeFlag = 0;
						delay2 = 0;
					}
					
					else if (delayFlag2 == 1)
					{
						delay2++;
						if (delay2 >= DELAY)
						{
							Rectangle currRect = box.getRectangle(i);
							Rectangle scanRect = box.getRectangle(j);
							if (currRect.getData() < scanRect.getData())
							{
								box.getRectangle(i).setColor(END_COLOR);
								paintBox();
								delay1 = 0;
								delayFlag2 = 0;
								delayFlag1 = 1;
							}
							else
							{
								box.getRectangle(j).setColor(END_COLOR);
								paintBox();
								delayFlag2 = 0;
								downFlag = 1;
								currentDownCount = 0;
							}
						}
					}
					else if (delayFlag1 == 1)
					{
						delay1++;
						if (delay1 >= DELAY)
						{
							codeFlag = 1;
							delayFlag1 = 0;
							i++;
						}
					}
					
					// Moving the first rectangle on right down (if it was smaller than the first one on the left)
					else if (downFlag == 1)
					{
						box.addOffsetRectangle(j, 0, YCHANGE);
						paintBox();
						currentDownCount += YCHANGE;
						if (currentDownCount >= DOWN)
						{
							int excess = currentDownCount - DOWN;
							box.addOffsetRectangle(j, 0, -excess);
							paintBox();
							downFlag = 0;
							rightBlockFlag = 1;
							indexRightCount = 0;
							index = j - 1;
						}
					}
					
					// moving each of the rectangles in the block to the right(between the two smallest rectangle on both side)
					// including the first rectangle
					else if (rightBlockFlag == 1)
					{
						if (index < i)
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
					
					// moving a rectangle in the block to the right
					else if (rightFlag == 1)
					{
						box.addOffsetRectangle(index, XCHANGE, 0);
						paintBox();
						indexRightCount += XCHANGE;
						if (indexRightCount >= width)
						{
							int excess = indexRightCount - width;
							box.addOffsetRectangle(index, -excess, 0);
							paintBox();
							rightFlag = 0;
							rightBlockFlag = 1;
							index--;
						}
					}
					
					// moving the rectangle left (the smallest in the un-merged block)
					else if (leftFlag == 1)
					{
						box.addOffsetRectangle(j, -XCHANGE, 0);
						currentLeftCount += XCHANGE;
						paintBox();
						if (currentLeftCount >= width * (j - i))
						{
							int excess = currentLeftCount - width * (j - i);
							box.addOffsetRectangle(j, excess, 0);
							paintBox();
							
							leftFlag = 0;
							upFlag = 1;
							currentUpCount = 0;
						}
					}
					
					// moving the rectangle up (the smallest in the un-merged block)
					else if (upFlag == 1)
					{
						box.addOffsetRectangle(j, 0, -YCHANGE);
						currentUpCount += YCHANGE;
						paintBox();
						if (currentUpCount >= DOWN)
						{
							int excess = currentUpCount - DOWN;
							box.addOffsetRectangle(j, 0, excess);
							paintBox();
							
							Rectangle smallestRect = box.getRectangle(j);
							
							for (int t = j - 1; t >= i; t--)
							{
								box.setRectangle(box.getRectangle(t), t + 1);
							}
							box.setRectangle(smallestRect, i);
							upFlag = 0;
							codeFlag = 1;
							j++;
							i++;
						}
					}
				}
			}
		};
		timer.addActionListener(action);
		return timer;
	}
	
	public Timer quickSort()
	{
		box.setBaseColor(BASE_COLOR);
		box.resetArrayRectangle();
		paintBox();

		final Timer timer = new Timer(TIMER_SPEED, this);
		final int width = super.getRectangleSpace();
		
		ActionListener action = new ActionListener()
		{
			// stores the left index of the block still to be sorted
			Stack<Integer> leftP = new Stack<Integer>();
			
			// stores the right index of the block still to be sorted
			Stack<Integer> rightP = new Stack<Integer>();
			
			// the pivot selected in the current block
			int pivot = 0;
			
			// left and right end of the block selected for sorting
			int left = 0;
			int right = 0;
			
			int rightBlockFlag = 0;
			int index = 0;
			int partetionFlag = 0;
			int small = 0, big = 0;
			Rectangle rect = null;
			int partetionCodeFlag = 1;
			int partetionSwapFalg = 0;
			int partetionEnd = 0;
			int initializeStackFlag = 1;
			
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
			int codeFlag = 0;
			
			// moving a rectangle to the right
			int rightFlag = 0;
			
			// moving a rectangle down
			int downFlag = 0;
			
			// moving a rectangle up
			int upFlag = 0;
			
			// moving a rectangle to the left
			int leftFlag = 0;
			
			int delay1 = 0;
			int delayFlag1 = 0;
			
			int delay2 = 0;
			int delayFlag2 = 0;
			
			int delay4 = 0;
			int delayFlag4 = 0;
			
			int delay5 = 0;
			int delayFlag5 = 0;
			
			int delay6 = 0;
			int delayFlag6 = 0;
			
			int delay7 = 0;
			int delayFlag7 = 0;
			
			int delay3 = 0;
			
			// code for delay
			int delayFlag3 = 0;
			
			int seperateFlag = 0;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// putting first index(0) and last index (size -1) in the stacks 
				if (initializeStackFlag == 1)
				{
					leftP.add(0);
					rightP.add(box.getArrayRectangle().length - 1);
					initializeStackFlag = 0;
					codeFlag = 1;
				}
				
				// Decides which block has to be sorted 
				else if (codeFlag == 1)
				{
					// if the stack becomes empty then i means that our algorithm is over
					if (rightP.isEmpty() || leftP.isEmpty())
					{
						codeFlag = 0;
						timer.stop();
						box.setColorRange(0, box.getNumber() - 1, END_COLOR);
						paintBox();
						System.out.println("The quickSort is complete");
					}
					
					if (delayFlag7 == 1)
					{
						delay7++;
						if (delay7 >= DELAY)
						{
							delayFlag7 = 0;
							delay7 = 0;
						}
					}
					// step zero check whether to continue the algorithm or not
					
					else
					{
						// takes out the left and right index from the stack
						left = leftP.pop();
						right = rightP.pop();
						
						// selecting a pivot in the block
						pivot = random.nextInt(right - left + 1) + left;
						
						//box.setColorRange(0, box.getNumber() - 1, BLOCK_COLOR2);
						box.setColorRange(left, right, BLOCK_COLOR1);
						box.getRectangle(pivot).setColor(FOCUS_COLOR1);
						
						paintBox();
						
						codeFlag = 0;
						delay3 = 0;
						delayFlag3 = 1;
					}
				}
				
				else if (delayFlag3 == 1)
				{
					delay3++;
					if (delay3 >= DELAY)
					{
						delayFlag3 = 0;
						delay3 = 0;
						
						downFlag = 1;
						seperateFlag = 1;
						currentDownCount = 0;
						
						// if block has only one element then just sort the next block
						if (left == right)
						{
							downFlag = 0;
							seperateFlag = 0;
							delayFlag7 = 1;
							delay7 = 0;
							codeFlag = 1;
							box.getRectangle(left).setColor(BASE_COLOR);
						}
						
						// if pivot selected is left then just start separating the elements(lesser and bigger ones)
						if (pivot == left)
						{
							downFlag = 0;
							seperateFlag = 0;
							currentDownCount = 0;
							
							upFlag = 0;
							seperateFlag = 0;
							
							partetionFlag = 1;
							partetionCodeFlag = 1;
							delayFlag5 = 0;
							delay5 = 0;
							
							rect = box.getRectangle(left);
							big = left + 1;
							small = left;
							
						}
						
					}
				}
				
				// first bring the pivot at the beginning of the block
				
				// moving the pivot down
				else if (seperateFlag == 1)
				{
					if (downFlag == 1)
					{
						box.addOffsetRectangle(pivot, 0, YCHANGE);
						paintBox();
						currentDownCount += YCHANGE;
						if (currentDownCount >= DOWN)
						{
							int excess = currentDownCount - DOWN;
							box.addOffsetRectangle(pivot, 0, -excess);
							paintBox();
							downFlag = 0;
							rightBlockFlag = 1;
							index = pivot - 1;
						}
					}
					
					// moving the block of rectangle (to the left of the pivot) to the right
					else if (rightBlockFlag == 1)
					{
						if (index < left)
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
						box.addOffsetRectangle(index, XCHANGE, 0);
						paintBox();
						indexRightCount += XCHANGE;
						
						if (indexRightCount >= width)
						{
							int excess = indexRightCount - width;
							box.addOffsetRectangle(index, -excess, 0);
							paintBox();
							rightFlag = 0;
							rightBlockFlag = 1;
							index--;
						}
					}
					
					// shifting the pivot to left
					else if (leftFlag == 1)
					{
						box.addOffsetRectangle(pivot, -XCHANGE, 0);
						currentLeftCount += XCHANGE;
						paintBox();
						if (currentLeftCount >= (pivot - left) * width)
						{
							int excess = currentLeftCount - (pivot - left) * width;
							box.addOffsetRectangle(pivot, excess, 0);
							paintBox();
							
							leftFlag = 0;
							upFlag = 1;
							currentUpCount = 0;
						}
					}
					
					// moving the piot up
					else if (upFlag == 1)
					{
						box.addOffsetRectangle(pivot, 0, -YCHANGE);
						currentUpCount += YCHANGE;
						paintBox();
						if (currentUpCount >= DOWN)
						{
							int excess = currentUpCount - DOWN;
							box.addOffsetRectangle(pivot, 0, excess);
							paintBox();
							
							upFlag = 0;
							seperateFlag = 0;
							
							partetionFlag = 1;
							partetionCodeFlag = 1;
							
							delayFlag5 = 0;
							delay5 = 0;
							Rectangle pivotRectangle = box.getRectangle(pivot);
							
							for (int t = pivot - 1; t >= left; t--)
								box.setRectangle(box.getRectangle(t), t + 1);
							
							box.setRectangle(pivotRectangle, left);
							rect = box.getRectangle(left);
							big = left + 1;
							small = left;
						}
					}
				}
				
				// after the pivot has been moved to the starting index then start separating the rectangle
				// those which are smaller and those which are bigger than than pivot are separated
				
				else if (partetionFlag == 1)
				{
					if (delayFlag5 == 1)
					{
						delay5++;
						if (delay5 >= DELAY)
						{
							delayFlag5 = 0;
							delay5 = 0;
							
							partetionFlag = 0;
							partetionCodeFlag = 0;
							downFlag = 0;
							
							leftP.add(left + 1);
							rightP.add(right);
							codeFlag = 1;
						}
					}
					else if (big > right)
					{
						partetionFlag = 0;
						
						partetionEnd = 1;
						delayFlag4 = 1;
						delay4 = 0;
						downFlag = 1;
						
						currentDownCount = 0;
						partetionFlag = 0;
						
						delayFlag1 = 0;
						delay1 = 0;
						
						delayFlag2 = 0;
						delay2 = 0;
						
						if (left == small)
						{
							box.setColorRange(left + 1, right, BLOCK_COLOR1);
							box.getRectangle(small).setColor(BASE_COLOR);
							paintBox();
							partetionEnd = 0;
							delayFlag4 = 0;
							delay4 = 0;
							
							partetionFlag = 1;
							delayFlag5 = 1;
							delay5 = 0;
						}
					}
					
					else if (partetionCodeFlag == 1)
					{
						
						if (delayFlag1 == 1)
						{
							delay1++;
							if (delay1 >= DELAY)
							{
								delayFlag1 = 0;
								delay1 = 0;
								big++;
							}
						}
						else if (delayFlag2 == 1)
						{
							delay2++;
							if (delay2 >= DELAY)
							{
								delayFlag2 = 0;
								delay2 = 0;
								big++;
								small++;
							}
						}
						else if (rect.getData() <= box.getRectangle(big).getData())
						{
							delayFlag1 = 1;
							delay1 = 0;
							box.getRectangle(big).setColor(BLOCK_COLOR2);
							paintBox();
						}
						
						else
						{
							
							if (big == small + 1)
							{
								delayFlag2 = 1;
								delay2 = 0;
								box.getRectangle(big).setColor(DFAULT_COLOR);
								paintBox();
							}
							else
							{
								partetionCodeFlag = 0;
								partetionSwapFalg = 1;
								currentDownCount = 0;
								downFlag = 1;
								box.getRectangle(big).setColor(DFAULT_COLOR);
								delayFlag4 = 1;
								delay4 = 0;
								paintBox();
							}
						}
					}
					else if (partetionSwapFalg == 1)
					{
						if (delayFlag4 == 1)
						{
							delay4++;
							if (delay4 >= DELAY)
							{
								delayFlag4 = 0;
								delay4 = 0;
								downFlag = 1;
								currentDownCount = 0;
							}
						}
						else if (downFlag == 1)
						{
							box.addOffsetRectangle(big, 0, YCHANGE);
							box.addOffsetRectangle(small + 1, 0, YCHANGE);
							paintBox();
							currentDownCount += YCHANGE;
							if (currentDownCount >= DOWN)
							{
								int excess = currentDownCount - DOWN;
								box.addOffsetRectangle(big, 0, -excess);
								box.addOffsetRectangle(small + 1, 0, -excess);
								paintBox();
								downFlag = 0;
								leftFlag = 1;
								currentLeftCount = 0;
							}
						}
						
						// shifting the next block to left
						else if (leftFlag == 1)
						{
							box.addOffsetRectangle(big, -XCHANGE, 0);
							box.addOffsetRectangle(small + 1, XCHANGE, 0);
							
							currentLeftCount += XCHANGE;
							paintBox();
							if (currentLeftCount >= width * (big - small - 1))
							{
								int excess = currentLeftCount - width * (big - small - 1);
								box.addOffsetRectangle(big, excess, 0);
								box.addOffsetRectangle(small + 1, -excess, 0);
								paintBox();
								leftFlag = 0;
								upFlag = 1;
								currentUpCount = 0;
							}
						}
						
						// moving the next block up
						else if (upFlag == 1)
						{
							box.addOffsetRectangle(small + 1, 0, -YCHANGE);
							box.addOffsetRectangle(big, 0, -YCHANGE);
							
							currentUpCount += YCHANGE;
							paintBox();
							if (currentUpCount >= DOWN)
							{
								int excess = currentUpCount - DOWN;
								box.addOffsetRectangle(big, 0, excess);
								box.addOffsetRectangle(small + 1, 0, excess);
								paintBox();
								
								// Reflecting the same procedure internally
								Rectangle currRect = box.getRectangle(small + 1);
								Rectangle nextRect = box.getRectangle(big);
								
								box.setRectangle(currRect, big);
								box.setRectangle(nextRect, small + 1);
								
								big++;
								small++;
								
								upFlag = 0;
								partetionSwapFalg = 0;
								
								partetionCodeFlag = 1;
								
							}
						}
					}
				}
				
				// replacing the pivot at its correct position
				else if (partetionEnd == 1)
				{
					
					if (downFlag == 1)
					{
						box.addOffsetRectangle(left, 0, YCHANGE);
						box.addOffsetRectangle(small, 0, YCHANGE);
						paintBox();
						currentDownCount += YCHANGE;
						if (currentDownCount >= DOWN)
						{
							int excess = currentDownCount - DOWN;
							box.addOffsetRectangle(left, 0, -excess);
							box.addOffsetRectangle(small, 0, -excess);
							paintBox();
							downFlag = 0;
							leftFlag = 1;
							currentLeftCount = 0;
						}
					}
					
					// shifting the next block to left and pivot to right
					else if (leftFlag == 1)
					{
						box.addOffsetRectangle(left, XCHANGE, 0);
						box.addOffsetRectangle(small, -XCHANGE, 0);
						
						currentLeftCount += XCHANGE;
						paintBox();
						if (currentLeftCount >= width * (small - left))
						{
							int excess = currentLeftCount - width * (small - left);
							box.addOffsetRectangle(small, excess, 0);
							box.addOffsetRectangle(left, -excess, 0);
							paintBox();
							leftFlag = 0;
							upFlag = 1;
							currentUpCount = 0;
						}
					}
					
					// moving the pivot up
					else if (upFlag == 1)
					{
						box.addOffsetRectangle(small, 0, -YCHANGE);
						box.addOffsetRectangle(left, 0, -YCHANGE);
						
						currentUpCount += YCHANGE;
						paintBox();
						if (currentUpCount >= DOWN)
						{
							int excess = currentUpCount - DOWN;
							box.addOffsetRectangle(left, 0, excess);
							box.addOffsetRectangle(small, 0, excess);
							paintBox();
							
							// Reflecting the same procedure internally
							Rectangle currRect = box.getRectangle(small);
							Rectangle nextRect = box.getRectangle(left);
							
							box.setRectangle(currRect, left);
							box.setRectangle(nextRect, small);
							
							// Add the stuffs to stack
							if (left < small)
							{
								box.setColorRange(left, small - 1, BLOCK_COLOR1);
								box.getRectangle(small).setColor(BASE_COLOR);
								paintBox();
								leftP.add(left);
								rightP.add(small - 1);
							}
							
							if (small < right)
							{
								box.getRectangle(small).setColor(BASE_COLOR);
								box.setColorRange(small + 1, right, BLOCK_COLOR1);
								paintBox();
								leftP.add(small + 1);
								rightP.add(right);
							}
							upFlag = 0;
							delayFlag6 = 1;
							delay6 = 0;
						}
					}
					else if (delayFlag6 == 1)
					{
						delay6++;
						if (delay6 >= DELAY)
						{
							delayFlag6 = 0;
							delay6 = 0;
							partetionEnd = 0;
							codeFlag = 1;
						}
					}
				}
			}
		};
		
		timer.addActionListener(action);
		return timer;
	}
}
