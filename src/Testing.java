import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

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

class arrayRectangle
{
	private int baseX;
	private int baseY;
	private int number;
	private Rectangle rectArray[];
	private int gap;
	private int sizeWidth;
	
	// static final int width = 50;
	
	public arrayRectangle()
	{
		baseX = baseY = 0;
		sizeWidth = gap = 20;
		number = 0;
		rectArray = null;
	}
	
	public arrayRectangle(int n)
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

public class Testing
{
	public static void main(String[] args)
	{
		final ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(100);
		list.add(2);
		list.add(20);
		list.add(80);
		list.add(26);
		list.add(97);
		list.add(54);
		list.add(76);
		list.add(34);
		list.add(44);
		list.add(87);
		list.add(34);
		
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI(list);
			}
		});
	}
	
	private static void createAndShowGUI(ArrayList<Integer> list)
	{
		System.out.println("Created GUI on EDT? " + SwingUtilities.isEventDispatchThread());
		JFrame f = new JFrame("Swing Paint Demo");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setMinimumSize(new Dimension(500, 500));
		f.add(new MyPanel(list));
		f.setSize(250, 250);
		f.setVisible(true);
	}
}

class MyPanel extends JPanel implements ActionListener
{
	private static final int TIMER_SPEED = 100;
	arrayRectangle box;
	
	public MyPanel(ArrayList<Integer> list)
	{
		setBorder(BorderFactory.createLineBorder(Color.black));
		box = new arrayRectangle();
		box.setBaseX(200);
		box.setBaseY(200);
		box.initializeRectangle(list);
		this.paintBox();
		Timer timer = new Timer(25, this);
		timer.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				box.addOffsetRectangel(2, 0, 1);
				paintBox();
			}
			
		});
		
		for (int i = 0; i < 10; i++)
		{
			timer.start();
		}
	}
	
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
