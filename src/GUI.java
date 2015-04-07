import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GUI
{
	JFrame mainFrame;
	Container mainPane;
	JPanel centrePanel;
	JPanel leftPanel;
	JPanel rightPanel;
	JPanel topPanel;
	JPanel bottomPanel;
	JButton topLeft;
	JButton leftBottom;
	JButton rightMiddle;
	JButton rightBottom;
	JLabel mainLabel;
	JLabel codeTraceLabel;
	JLabel statusLabel;

	public GUI ()
	{
		initialize();
	}
	
	public void initialize ()
	{
		mainFrame = new JFrame ("Algorithm Theatre");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setSize(screenSize);
//	    mainFrame.setResizable (false);

	    mainPane = mainFrame.getContentPane();
	    centrePanel = new JPanel ();
	    centrePanel.setPreferredSize(new Dimension(920, 720));
	    centrePanel.setBackground(Color.white);
	    centrePanel.setLayout(new GridBagLayout());
		mainPane.add(centrePanel, BorderLayout.CENTER);

	    leftPanel = new JPanel ();
	    leftPanel.setPreferredSize(new Dimension(40, 720));
	    leftPanel.setBackground(Color.black);
	    leftPanel.setLayout(new GridBagLayout());
		mainPane.add(leftPanel, BorderLayout.LINE_START);

		rightPanel = new JPanel ();
	    rightPanel.setPreferredSize(new Dimension(40, 720));
	    rightPanel.setBackground(Color.black);
	    rightPanel.setLayout(new GridBagLayout());
		mainPane.add(rightPanel, BorderLayout.LINE_END);

		topPanel = new JPanel ();
	    topPanel.setPreferredSize(new Dimension(1000, 40));
	    topPanel.setBackground(Color.black);
		mainPane.add(topPanel, BorderLayout.PAGE_START);

	    bottomPanel = new JPanel ();
	    bottomPanel.setPreferredSize(new Dimension(1000, 40));
	    bottomPanel.setBackground(Color.black);
		mainPane.add(bottomPanel, BorderLayout.PAGE_END);

		leftBottom = new JButton();
		leftBottom.setIcon(new ImageIcon("arrow_white_right.png"));
		leftBottom.setBackground(Color.cyan);
		leftBottom.setPreferredSize(new Dimension(40,65));
		leftBottom.setBorder(null);
		leftBottom.setFocusPainted(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.gridx = 0;	gbc.gridy = 0;	gbc.weighty=1.0;
		gbc.insets = new Insets(0,0,10,0);
		leftPanel.add (leftBottom, gbc);

		rightMiddle = new JButton();
		rightMiddle.setIcon(new ImageIcon("arrow_white_left.png"));
		rightMiddle.setBackground(Color.orange);
		rightMiddle.setPreferredSize(new Dimension(40,65));
		rightMiddle.setFocusPainted(false);
		rightMiddle.setBorder(null);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;	gbc.gridy = 0;	gbc.weighty=0.5;
		gbc.insets = new Insets(424,0,0,0);
		rightPanel.add (rightMiddle, gbc);

		rightMiddle.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				if (statusLabel.isVisible())
					statusLabel.setVisible (false);
				else
					statusLabel.setVisible (true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}

		});
		
		rightBottom = new JButton();
		rightBottom.setIcon(new ImageIcon("arrow_white_left.png"));
		rightBottom.setBackground(Color.red);
		rightBottom.setPreferredSize(new Dimension(40,170));
		rightBottom.setFocusPainted(false);
		rightBottom.setBorder(null);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.gridx = 0;	gbc.gridy = 1;	gbc.weighty=1.0;
		gbc.insets = new Insets(0,0,0,0);
		rightPanel.add (rightBottom, gbc);

		rightBottom.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				if (codeTraceLabel.isVisible())
					codeTraceLabel.setVisible (false);
				else
					codeTraceLabel.setVisible (true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}

		});

		mainLabel = new JLabel("This it to test the label");
		mainLabel.setOpaque(true);
		mainLabel.setBackground(Color.lightGray);
		mainLabel.setPreferredSize(new Dimension(1200,380));
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.gridx=0;	gbc.gridy=0;	gbc.weightx=0.0;
		gbc.insets = new Insets(0,0,0,0);
		centrePanel.add (mainLabel, gbc);

		statusLabel = new JLabel("This it to test the label");
		statusLabel.setOpaque(true);
		statusLabel.setBackground(Color.orange);
		statusLabel.setPreferredSize(new Dimension(270,65));
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;	gbc.gridy = 1;
		gbc.insets = new Insets(40,800,0,-8);
		centrePanel.add (statusLabel, gbc);
		
		codeTraceLabel = new JLabel("This it to test the label");
		codeTraceLabel.setOpaque(true);
		codeTraceLabel.setBackground(Color.red);
		codeTraceLabel.setPreferredSize(new Dimension(270,170));
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.gridx = 0;	gbc.gridy = 2;
		gbc.weighty=1.0;
		gbc.insets = new Insets(0,800,1,-8);
		centrePanel.add (codeTraceLabel, gbc);
		
		

		mainFrame.pack();
	    mainFrame.setVisible(true);

	}
	
}
