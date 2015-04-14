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

public class Main
{

	public static void main(String[] args)
	{

		GUI g = new GUI();
		String content = new StringBuilder()
							.append("mark first element as sorted\n")
							.append("for each unsorted element\n")
							.append("...'extract' the element\n")
							.append("...for i = lastSortedIndex to 0\n")
							.append("......if currentSortedElement > extractedElement\n")
							.append(".........move sorted element to the right by 1\n")
							.append("......else: insert extracted element\n")
							.toString();
		
		g.setCodeTraceLabel(content);
		
	}
}
/*		final BinaryTree b = new BinaryTree();
		b.insert(12);
		b.insert(34);
		b.insert(22);
		b.insert(46);
		b.insert(76);
		b.insert(9);
		b.insert(100);
		b.insert(70);
		b.print_bfm();
		
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI(b);
			}
		});

	}
	
	private static void createAndShowGUI(BinaryTree b)
	{
		System.out.println("Created GUI on EDT? " + SwingUtilities.isEventDispatchThread());
		JFrame f = new JFrame("Swing Paint Demo");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setMinimumSize(new Dimension(500, 500));
		f.add(new MyPanel_(b));
		f.setSize(250, 250);
		f.setVisible(true);
	}

}

*/