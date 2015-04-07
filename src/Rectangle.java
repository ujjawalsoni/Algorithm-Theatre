import java.awt.Color;
import java.awt.Graphics;

class Rectangle
{
	private int x;
	private int y;
	private int width;
	private int height;
	private int data;
	private Color color;
	
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
		// g.drawRect(x + width, y + height, width, height);
		// g.fillRect(xPos + width, yPos + height, width + 10, height + 10);
	}
}
