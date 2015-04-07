import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
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
	/*
	 * 
	 */
	
	private JFrame mainFrame;
	private Container mainPane;

	private JPanel centrePanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;
	
	private JButton topLeft;
	private JButton leftBottom;
	private JButton rightMiddle;
	private JButton rightBottom;
	private JButton developers;

	private JLabel mainLabel;
//	private JPanel centreBottomPanel;
	private JLabel codeTraceLabel;
	private JLabel statusLabel;
	
	private JPanel actionPanel;
	private JButton createList;
	private JButton sortList;

	private ImageIcon rightBlackArrow;
	private ImageIcon rightWhiteArrow;
	private ImageIcon leftBlackArrow;
	private ImageIcon leftWhiteArrow;
	

	public GUI ()
	{
		initialize();
	}
	
	public void initialize ()
	{
		rightBlackArrow = new ImageIcon ("Images/arrow_black_right.png");
		rightWhiteArrow = new ImageIcon ("Images/arrow_white_right.png");
		leftBlackArrow = new ImageIcon ("Images/arrow_black_left.png");
		leftWhiteArrow = new ImageIcon ("Images/arrow_white_left.png");
		
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
		leftBottom.setIcon(rightWhiteArrow);
		leftBottom.setBackground(Color.cyan);
		leftBottom.setPreferredSize(new Dimension(40,65));
		leftBottom.setBorder(null);
		leftBottom.setFocusPainted(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.gridx = 0;	gbc.gridy = 0;	gbc.weighty=1.0;
		gbc.insets = new Insets(0,0,1,0);
		leftPanel.add (leftBottom, gbc);

		rightMiddle = new JButton();
		rightMiddle.setIcon(leftWhiteArrow);
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
				{
					rightMiddle.setIcon(leftBlackArrow);
					statusLabel.setVisible (false);
				}
				else
				{
					rightMiddle.setIcon(rightBlackArrow);
					statusLabel.setVisible (true);
				}
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
		rightBottom.setIcon(leftBlackArrow);
		rightBottom.setBackground(Color.yellow);
		rightBottom.setPreferredSize(new Dimension(40,170));
		rightBottom.setFocusPainted(false);
		rightBottom.setBorder(null);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.gridx = 0;	gbc.gridy = 1;	gbc.weighty=1.0;
		gbc.insets = new Insets(0,0,1,0);
		rightPanel.add (rightBottom, gbc);

		rightBottom.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				if (codeTraceLabel.isVisible())
				{
					rightBottom.setIcon(leftBlackArrow);
					codeTraceLabel.setVisible (false);
				}
				else
				{
					rightBottom.setIcon(rightBlackArrow);
					codeTraceLabel.setVisible (true);
				}
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
		
		mainLabel = new JLabel();
		mainLabel.setOpaque(true);
		mainLabel.setBackground(Color.WHITE);
		mainLabel.setPreferredSize(new Dimension(1200,380));
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.gridx=0;	gbc.gridy=0;	gbc.weightx=0.0;
		gbc.insets = new Insets(0,0,0,0);
		centrePanel.add (mainLabel, gbc);

		statusLabel = new JLabel();
		statusLabel.setOpaque(true);
		statusLabel.setBackground(Color.orange);
		statusLabel.setPreferredSize(new Dimension(270,65));
		statusLabel.setVisible(false);
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.gridx = 0;	gbc.gridy = 0;
		gbc.insets = new Insets(425,800,0,-8);
		centrePanel.add (statusLabel, gbc);
		
		codeTraceLabel = new JLabel("This it to test the label");
		codeTraceLabel.setOpaque(true);
		codeTraceLabel.setBackground(Color.yellow);
//		codeTraceLabel.setForeground(Color.white);
		codeTraceLabel.setPreferredSize(new Dimension(270,170));
		codeTraceLabel.setVisible(false);
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.gridx = 0;	gbc.gridy = 2;
		gbc.weighty=1.0;
		gbc.insets = new Insets(0,800,1,-8);
		centrePanel.add (codeTraceLabel, gbc);
		
		developers = new JButton("Developers");
		developers.setPreferredSize(new Dimension(100,30));
		developers.setFocusPainted(false);
		developers.setBorder(null);
		developers.setBackground(Color.black);
		developers.setForeground(Color.white);
		developers.setContentAreaFilled(false);
		gbc.gridx = 0;	gbc.gridy = 0;	gbc.weightx = 1.0;
		gbc.insets = new Insets(0,0,0,0);
		bottomPanel.add (developers, gbc);
		developers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		mainFrame.pack();
	    mainFrame.setVisible(true);

	}

	
	public void setStatusLabel(String content)
	{
		statusLabel.setText(convertToMultiline(content));
	}
	
	public void setCodeTraceLabel (String content)
	{
		codeTraceLabel.setText(convertToMultiline(content));
	}

	private static String convertToMultiline(String original)
	{	
	    return "<html>" + original.replaceAll("\n", "<br>");
	}
}
