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
