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
		final ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(10);
		
		list.add(89);
		list.add(95);
		list.add(22);
		list.add(67);
		list.add(8);
		list.add(44);
		list.add(7);
		list.add(67);
		list.add(99);
		list.add(79);
		list.add(6);
		list.add(67);
		list.add(8);
		
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
		//javax.swing.Timer t = new Timer(100, null);
		sort.selectionSort();
		f.setSize(800, 800);
		f.setVisible(true);
	}
}

class MyPanel extends JPanel implements ActionListener
{
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
		repaint();
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
		;
	}
}
