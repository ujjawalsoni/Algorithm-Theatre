import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class HomePage
{
	private JFrame mainFrame;
	private Container mainPane;
	private JPanel mainPanel;
	private JPanel topPanel;



	public HomePage ()
	{
		mainFrame = new JFrame ("Algorithm Theatre");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setSize(screenSize);
	    mainPane = mainFrame.getContentPane();

	    mainPanel = new JPanel ();
	    mainPanel.setPreferredSize(new Dimension(920, 720));
	    mainPanel.setBackground(Color.white);
	    mainPanel.setLayout(new GridBagLayout());
		mainPane.add(mainPanel, BorderLayout.CENTER);
		
		topPanel = new JPanel ();
	    topPanel.setPreferredSize(new Dimension(1000, 40));
	    topPanel.setBackground(Color.black);
		mainPane.add(topPanel, BorderLayout.PAGE_START);

		mainFrame.pack();
	    mainFrame.setVisible(true);

	}
}