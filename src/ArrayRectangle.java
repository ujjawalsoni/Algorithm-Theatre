/**
 *	Project:	Algorithm Theatre
 *
 * 	Authors:	Ishu Dharmendra Garg (CS13B060)
 *				Ujjawal Soni (CS13B053)
 */


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * A Collection of rectangles that will be used for showing an array of positive numbers 
 */
class ArrayRectangle
{
	/**
	 * Comments -
	 * 	Some of the rectangle may not be a part of the Box (during moving some rectangle) 
	 * 	but in general it defines the properties of each of the rectangle in the box
	 *  
	 * baseX 			- Stores the x coordinate of the first rectangle in the box
	 * baseY			- The largest Y coordinate of each rectangle (according to the java drawing conventions)
	 * 						  (since all rectangles rest on the same base so largest coordinate of each rectangle will be same)
	 * number 		- number of rectangles present in the box
	 * rectArray	- Array of all the rectangles in the box
	 * gap				- The gap between two consecutive rectangle while drawing array of consecutive rectangles
	 * sizeWidth 	- The width of each of the rectangles in the box 
	 * 							(but none of the functions checks whether the rectangle width is same as size width
	 * 						   but just helps in defining the general property of the box )
	 *
	 *	Default values
	 *	baseX 			= 0;
	 *	baseY 			= 0;
	 * 	number 			= 0;
	 *  rectArray[] = null;
	 *  gap 				= 0;
	 *  sizeWidth 	= 20;
	 *  baseColor 	= Color.RED; 
	 */
	
	private int baseX = 0;
	private int baseY = 0;
	private int number = 0;
	private Rectangle rectArray[] = null;
	private int gap = 5;
	private int sizeWidth = 30;
	private Color baseColor = Color.RED;
	
	/**************** constructors ***********************/
	
	public ArrayRectangle()
	{
		baseX = baseY = 100;
		sizeWidth = 30;
		gap = 5;
		number = 0;
		rectArray = null;
		baseColor = Color.RED;
	}
	
	/**
	 * Initializes each rectangle with default values
	 * @param n - number of rectangles
	 */
	public ArrayRectangle(int n)
	{
		baseX = baseY = 100;
		baseColor = Color.RED;
		sizeWidth = 30;
		gap = 5;
		
		if (n > 0)
		{
			number = n;
			rectArray = new Rectangle[n];
			for (int i = 0; i < n; i++)
			{
				rectArray[i] = new Rectangle();
				rectArray[i].setHeight(i * 2); // setting default value
				rectArray[i].setX(baseX + (sizeWidth + gap) * i);
				rectArray[i].setY(baseY - i * 2); // So that all rectangle have same base
				rectArray[i].setColor(baseColor);
				rectArray[i].setData(i * 2);
			}
		}
		else
		{
			number = 0;
			rectArray = null;
		}
	}
	
	/********* getter functions ***********************/
	
	public int getBaseX()
	{
		return baseX;
	}
	
	public int getBaseY()
	{
		return baseY;
	}
	
	public int getSizeWidth()
	{
		return sizeWidth;
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public int getGap()
	{
		return gap;
	}
	
	public Color getBaseColor()
	{
		return baseColor;
	}
	
	public Rectangle[] getArrayRectangle()
	{
		return rectArray;
	}
	
	public Rectangle getRectangle(int index)
	{
		if (index < 0 || index >= number)
			return null;
		else
			return rectArray[index];
	}
	
	/******** setter functions ************************/
	
	/** 
	 * The functions do not cause the properties of the member rectangles to change
	 * It just sets the properties of the Box
	 * Some Peculiar behavior
	 * 1. setRectangle array causes the number = Array.length
	 * 2. setNumber sets number = 0 if argument was less than 0
	 */
	
	public void resetArrayRectangle()
	{
		
		baseColor = Color.RED;
		for (int k = 0; k < number; k++)
		{
			rectArray[k].setX(baseX + (sizeWidth + gap) * k);
			rectArray[k].setY(baseY - rectArray[k].getData()); // So that all rectangle have same base
			rectArray[k].setColor(baseColor);
		}
	}
	
	public void setBaseX(int xPos)
	{
		this.baseX = xPos;
		updateAllRectangleCordinates();
	}
	
	public void setBaseY(int yPos)
	{
		this.baseY = yPos;
		updateAllRectangleCordinates();
	}
	
	public void setAllWidth()
	{
		for (int i = 0; i < number; i++)
		{
			this.getRectangle(i).setWidth(sizeWidth);
		}
	}
	
	public void setSizeWidth(int a)
	{
		sizeWidth = a;
		this.setAllWidth();
		this.updateAllRectangleCordinates();
	}
	
	public void setGap(int a)
	{
		gap = a;
		this.updateAllRectangleCordinates();
	}
	
	public void setRectangleArray(Rectangle array[])
	{
		rectArray = array;
		number = array.length;
		resetArrayRectangle();
	}
	
	public void setRectangle(Rectangle rect, int index)
	{
		if (index < 0 || index >= this.getNumber() || rect == null)
			return;
		rectArray[index] = rect;
		updateAllRectangleCordinates();
	}
	
	/******* other functions *************************/
	
	// Color functions
	
	public void setBaseColor(Color base)
	{
		baseColor = base;
		this.updateAllColor();
	}
	
	public void updateAllColor()
	{
		for (int i = 0; i < number; i++)
		{
			this.getRectangle(i).setColor(baseColor);
		}
	}
	
	public void setColorRange(int i, int j, Color c)
	{
		if (i > j || i < 0 || j >= number)
		{
			System.out.println("wrong index given to the setColorRectangle method\n i = " + i + " j = " + j);
			
			return;
		}
		else
		{
			for (int k = i; k <= j; k++)
				this.getRectangle(k).setColor(c);
		}
	}
	
	/**
	 * It creates a array of rectangles with
	 * number = number of elements in the list  
	 * coordinates of each rectangle defined such that the first rectangle is at the base coordinates of the box
	 * color of each box being the base color of the box 
	 */
	
	public void initializeRectangle(ArrayList<Integer> list)
	{
		number = list.size();
		rectArray = new Rectangle[list.size()];
		int a;
		for (int i = 0; i < number; i++)
		{
			if (list.get(i) >= 0)
				a = list.get(i);
			else
				a = 0;
			rectArray[i] = new Rectangle(i * (gap + sizeWidth) + baseX, baseY - a, sizeWidth, a, a, baseColor);
		}
	}
	
	public void paintArrayRectangle(Graphics g)
	{
		for (int i = 0; i < number; i++)
		{
			rectArray[i].paintSquare(g);
		}
	}
	
	public void printArrayRectangle()
	{
		System.out.println();
		for (int i = 0; i < number; i++)
		{
			System.out.println(i + " " + this.getRectangle(i).getData());
		}
	}
	
	/*
	 * sets coordinates of each rectangle appropriately so that they align to the same line and to
	 * same base
	 */
	public void updateAllRectangleCordinates()
	{
		int h = 0;
		for (int i = 0; i < number; i++)
		{
			h = rectArray[i].getHeight();
			rectArray[i].updateCordinates(baseX + (gap + sizeWidth) * i, baseY - h);
		}
	}
	
	public void updateRectangleCordinates(int index)
	{
		if (index >= number || index < 0)
		{
			System.out.println("wrong index given to the updateRectangleCordinates method");
			return;
		}
		int h = rectArray[index].getHeight();
		rectArray[index].updateCordinates(baseX + (gap + sizeWidth) * index, baseY - h);
	}
	
	public void changeRectangleCordinates(int index, int xPos, int yPos)
	{
		if (index >= number || index < 0)
		{
			System.out.println("wrong index given to the moveRectangle method");
			return;
		}
		int h = rectArray[index].getHeight();
		rectArray[index].updateCordinates(xPos, yPos - h);
	}
	
	public void addOffsetRectangle(int index, int xOffset, int yOffset)
	{
		if (index >= number || index < 0)
		{
			System.out.println("wrong index given to the moveRectangle method");
			return;
		}
		rectArray[index].addOffset(xOffset, yOffset);
	}
	
}
