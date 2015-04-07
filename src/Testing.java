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
	ArrayRectangle box;
	
	public MyPanel(ArrayList<Integer> list)
	{
		setBorder(BorderFactory.createLineBorder(Color.black));
		box = new ArrayRectangle();
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
