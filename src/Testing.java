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

public class Testing
{
	
	public static void main(String[] args)
	{
		final int CHANGEX = 1;
		final ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(67);
		list.add(99);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(44);
		list.add(67);
		list.add(99);
		
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
		SortingFunctions sort = new SortingFunctions(list);
		f.add(sort);
		f.setSize(250, 250);
		f.setVisible(true);
	}
}

class MyPanel extends JPanel implements ActionListener
{
	private static final int CHANGEX = 1;
	private static final int TIMER_SPEED = 100;
	protected ArrayRectangle box;
	
	public MyPanel(ArrayList<Integer> list)
	{
		setBorder(BorderFactory.createLineBorder(Color.black));
		box = new ArrayRectangle();
		box.setBaseX(200);
		box.setBaseY(200);
		box.initializeRectangle(list);
		this.paintBox();
		
	}
	
	public int getRectangleSpace()
	{
		if (this.box.getNumber() > 0)
			return (this.box.getRectangle(0).getWidth() + this.box.getGap());
		else
		{
			System.out.println("The list has not been initialized: error getRectangleSpace ");
			return 0;
		}
	}
	
	void paintBox()
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
