import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class ArrayRectangle {


	private int baseX;
	private int baseY;
	private int number;
	private Rectangle rectArray[];
	private int gap;
	private int sizeWidth;
	
	// static final int width = 50;
	
	public ArrayRectangle()
	{
		baseX = baseY = 0;
		sizeWidth = gap = 20;
		number = 0;
		rectArray = null;
	}
	
	public ArrayRectangle(int n)
	{
		baseX = baseY = 0;
		if (n > 0)
		{
			sizeWidth = gap = 20;
			number = n;
			rectArray = new Rectangle[n];
			for (int i = 0; i < n; i++)
			{
				rectArray[i] = new Rectangle();
				rectArray[i].setHeight(2 * i + 10);
				int h = rectArray[i].getHeight();
				rectArray[i].setX(baseX + gap * i);
				rectArray[i].setY(baseY - h);
				rectArray[i].setColor(Color.RED);
				rectArray[i].setData(2 * i + 10);
			}
		}
		
		else
		{
			sizeWidth = gap = 20;
			number = 0;
			rectArray = null;
		}
	}
	
	public void initializeRectangle(ArrayList<Integer> list)
	{
		number = list.size();
		rectArray = new Rectangle[list.size()];
		
		int a = 0;
		for (int i = 0; i < number; i++)
		{
			if (list.get(i) >= 0)
				a = list.get(i);
			else
				a = 0;
			rectArray[i] = new Rectangle(i * gap + baseX, baseY - a, sizeWidth, a, a, Color.RED);
		}
	}
	
	public void paintArrayRectangle(Graphics g)
	{
		for (int i = 0; i < number; i++)
		{
			rectArray[i].paintSquare(g);
		}
	}
	
	public void updateAllRectangleCordinates()
	{
		int h = 0;
		for (int i = 0; i < number; i++)
		{
			h = rectArray[i].getHeight();
			rectArray[i].updateCordinates(baseX + gap * i, baseY - h);
		}
	}
	
	public void updateRectangleCordinates(int index)
	{
		if (index >= number || index < 0)
		{
			System.out.println("wrong index given to the updateRectangelCordinates method");
			return;
		}
		int h = rectArray[index].getHeight();
		rectArray[index].updateCordinates(baseX + gap * index, baseY - h);
	}
	
	public void moveRectangel(int index, int xPos, int yPos)
	{
		if (index >= number || index < 0)
		{
			System.out.println("wrong index given to the moveRectangel method");
			return;
		}
		int h = rectArray[index].getHeight();
		rectArray[index].updateCordinates(xPos, yPos - h);
	}
	
	public void addOffsetRectangel(int index, int xOffset, int yOffset)
	{
		if (index >= number || index < 0)
		{
			System.out.println("wrong index given to the moveRectangel method");
			return;
		}
		rectArray[index].addOffset(xOffset, yOffset);
	}
	
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
	
	public int getGap()
	{
		return gap;
	}
	
	public void setBaseX(int xPos)
	{
		this.baseX = xPos;
	}
	
	public void setBaseY(int yPos)
	{
		this.baseY = yPos;
	}
	
	public void setSizeWidth(int a)
	{
		sizeWidth = a;
	}
	
	public void setGap(int a)
	{
		gap = a;
	}
}
