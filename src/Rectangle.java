/**
 *	Project:	Algorithm Theatre
 *
 * 	Authors:	Ishu Dharmendra Garg (CS13B060)
 *				Ujjawal Soni (CS13B053)
 *
 */


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;


/**
 * A basic rectangle unit that is used to show a number graphically (height = data) 
 */
class Rectangle
{
	/** Fields
	 * x 			- x coordinate of the rectangle
	 * y 			- y coordinate of the rectangle
	 * width 	- Width of the rectangle
	 * height - Height of the rectangle  
	 * data 	- Data contained in the rectangle(generally equals the height of the rectangle)
	 * color 	- Color of the rectangle
	 */
	private int x;
	private int y;
	private int width;
	private int height;
	private int data;
	private Color color;
	private final int TEXT_GAP = 4;
	
	/**************** constructors ***********************/
	
	public Rectangle()
	{
		x = 0;
		y = 0;
		width = 20;
		height = 0;
		data = 0;
		color = Color.RED;
	}
	
	public Rectangle(int argX, int argY, int argW, int argH, int argD, Color argC)
	{
		x = argX;
		y = argY;
		width = argW;
		height = argH;
		data = argD;
		color = argC;
	}
	
	/********* getter functions ***********************/
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public int getData()
	{
		return data;
	}
	
	/******** setter functions ************************/
	
	public void setWidth(int x)
	{
		if (x > 0)
			this.width = x;
		else
			this.width = 30;
	}
	
	public void setX(int xPos)
	{
		this.x = xPos;
	}
	
	public void setY(int yPos)
	{
		this.y = yPos;
	}
	
	public void setData(int a)
	{
		data = a;
	}
	
	public void setHeight(int a)
	{
		height = a;
	}
	
	public void setColor(Color a)
	{
		color = a;
	}
	
	/******* other functions *************************/
	
	public void updateCordinates(int newX, int newY)
	{
		x = newX;
		y = newY;
	}
	
	public void addOffset(int xOffset, int yOffset)
	{
		x += xOffset;
		y += yOffset;
	}
	
	/**
	 * origin is considered to be the top left corner of the screen
	 * X axis goes from left to right 
	 * Y axis goes from top to bottom
	 * paints the rectangle at coordinates x,y 
	 * (such that the diagonal to (x,y) is at ((x + width), (y + height))
	 * with color as the color of the rectangle 
	 * @param g - passed from the Jpanel that needs to print it
	 */
	public void paintSquare(Graphics g)
	{
		if (height < 0)
		{
			System.out.println("Height can not be less than 0");
			return;
		}
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		g.setFont(new Font("Roman", Font.BOLD, 12));
		g.drawString(""+this.data, x+width/4, y-TEXT_GAP);
		Toolkit.getDefaultToolkit().sync();
	}
}
