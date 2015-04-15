import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class GUI
{
	/*
	 * mainFrame - the main frame of the gui
	 * the mainPane (container) is divided into 5 jpanels
	 * ** centre
	 * ** left
	 * ** right
	 * ** top
	 * ** bottom
	 * 
	 * the left panel contains the action panel
	 * the right pane contains the status label and code trace label
	 * ** status label is the status of the program
	 * ** code trace label is the pseudocode for the algorithm
	 * 
	 * the centre panel contain two sub panel - mainTopPanel and mainBottomPanel
	 * ** mainTopPanel is to show the animation
	 * ** mainBottomPanel contains the labels and gui for various operations
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

	private JPanel mainTopPanel;
	private JPanel mainBottomPanel;
	private JLabel codeTraceLabel;
	private JLabel statusLabel;
	
	private JButton createList;
	private JButton sortList;
	private JTextField userInputField;
	private JButton goCreateList;
	private JButton goSortList;

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
		leftBottom.setIcon(rightBlackArrow);
		leftBottom.setBackground(Color.cyan);
		leftBottom.setPreferredSize(new Dimension(40,64));
		leftBottom.setBorder(null);
		leftBottom.setFocusPainted(false);
		leftBottom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.gridx = 0;	gbc.gridy = 0;	gbc.weighty=1.0;
		gbc.insets = new Insets(0,0,1,0);
		leftPanel.add (leftBottom, gbc);

		leftBottom.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				if (createList.isVisible())
				{
					leftBottom.setIcon(rightBlackArrow);
					createList.setVisible (false);
					userInputField.setVisible(false);
					goCreateList.setVisible(false);
					sortList.setVisible(false);
					goSortList.setVisible(false);
				}
				else
				{
					leftBottom.setIcon(leftBlackArrow);
					createList.setVisible (true);
					sortList.setVisible(true);
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

		rightMiddle = new JButton();
		rightMiddle.setIcon(leftBlackArrow);
		rightMiddle.setBackground(Color.orange);
		rightMiddle.setPreferredSize(new Dimension(40,65));
		rightMiddle.setFocusPainted(false);
		rightMiddle.setBorder(null);
		rightMiddle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		rightBottom.setBackground(Color.green);
		rightBottom.setPreferredSize(new Dimension(40,170));
		rightBottom.setFocusPainted(false);
		rightBottom.setBorder(null);
		rightBottom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

		mainTopPanel = new JPanel();
		mainTopPanel.setOpaque(true);
		mainTopPanel.setBackground(Color.white);
		mainTopPanel.setPreferredSize(new Dimension(1213,420));
		mainTopPanel.setLayout(null);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.gridx=0;	gbc.gridy=0;	gbc.weightx=0.0;
		gbc.insets = new Insets(0,0,0,0);
		centrePanel.add (mainTopPanel, gbc);

		mainBottomPanel = new JPanel();
		mainBottomPanel.setOpaque(true);
		mainBottomPanel.setBackground(Color.white);
		mainBottomPanel.setPreferredSize(new Dimension(1213,239));
		mainBottomPanel.setLayout(null);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.gridx=0;	gbc.gridy=0;
		gbc.insets = new Insets(424,0,0,0);
		centrePanel.add (mainBottomPanel, gbc);

		statusLabel = new JLabel();
		statusLabel.setOpaque(true);
		statusLabel.setBackground(Color.orange);
		statusLabel.setVisible(false);
		statusLabel.setBounds(850,1,370,65);
		mainBottomPanel.add (statusLabel);
		
		codeTraceLabel = new JLabel();
		codeTraceLabel.setOpaque(true);
		codeTraceLabel.setBackground(Color.green);
//		codeTraceLabel.setForeground(Color.white);
		codeTraceLabel.setVisible(false);
		codeTraceLabel.setBounds(850,69,370,169);
		mainBottomPanel.add (codeTraceLabel);
		
		createList = new JButton(" Create");
		createList.setBackground(Color.cyan);
		createList.setFocusPainted(false);
		createList.setBorder(null);
		createList.setVisible(false);
		createList.setBounds(0,176,110,32);
		createList.setHorizontalAlignment(SwingConstants.LEFT);
		createList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainBottomPanel.add (createList);
		
		createList.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				goSortList.setVisible(false);
				userInputField.setVisible(true);
				goCreateList.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				createList.setBackground(Color.black);
				createList.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				createList.setBackground(Color.cyan);
				createList.setForeground(Color.black);
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}

		});
		
		sortList = new JButton(" Sort");
		sortList.setBackground(Color.cyan);
		sortList.setFocusPainted(false);
		sortList.setBorder(null);
		sortList.setVisible(false);
		sortList.setBounds(0,208,110,32);
		sortList.setHorizontalAlignment(SwingConstants.LEFT);
		sortList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainBottomPanel.add (sortList);
		
		sortList.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				userInputField.setVisible(false);
				goCreateList.setVisible(false);
				goSortList.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sortList.setBackground(Color.black);
				sortList.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sortList.setBackground(Color.cyan);
				sortList.setForeground(Color.black);
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}

		});
		
		userInputField = new JTextField();
		userInputField.setVisible(false);
		userInputField.setBounds(111,176,300,32);
		userInputField.setBackground(Color.black);
		userInputField.setForeground(Color.white);
		userInputField.setCaretColor(Color.white);
		mainBottomPanel.add(userInputField);
		
		goCreateList = new JButton("Go");
		goCreateList.setBackground(Color.cyan);
		goCreateList.setFocusPainted(false);
		goCreateList.setBorder(null);
		goCreateList.setVisible(false);
		goCreateList.setBounds(412,176,32,32);
		goCreateList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainBottomPanel.add (goCreateList);
		
		goCreateList.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				getInputList();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				goCreateList.setBackground(Color.black);
				goCreateList.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				goCreateList.setBackground(Color.cyan);
				goCreateList.setForeground(Color.black);
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}

		});
		
		goSortList = new JButton("Go");
		goSortList.setBackground(Color.cyan);
		goSortList.setFocusPainted(false);
		goSortList.setBorder(null);
		goSortList.setVisible(false);
		goSortList.setBounds(112,208,32,32);
		goSortList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainBottomPanel.add (goSortList);
		
		goSortList.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				goSortList.setBackground(Color.black);
				goSortList.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				goSortList.setBackground(Color.cyan);
				goSortList.setForeground(Color.black);
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}

		});
		
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

		final ArrayList<Integer> list = new ArrayList<Integer>();
		int[] a = {52,34,22,46,26,97,54,76,19,44,67,84,25,4,30,99,98,40,39,33,43,50,55,10,15,12};
		for (int i=0; i<a.length; i++)
			list.add(a[i]);
//		BinarySearchTree b = new BinarySearchTree(list);
		BinaryHeap b = new BinaryHeap (list);

//		SortingFunctions p = new SortingFunctions(list);
//		BSTPanel p = new BSTPanel(b);
		BHPanel p = new BHPanel(b);
		p.setBackground(Color.white);
		p.setVisible (true);
		p.setBounds(0,0,1213,420);
		mainTopPanel.add (p);

//		p.search(84);
//		p.insert(2);
		p.deleteMin();
//		p.delete(76);
//		p.bubbleSort();
//		p.selectionSort();
//		p.insertionSort();
//		p.mergeSort();
		
		mainFrame.pack();
	    mainFrame.setVisible(true);

	}

	
	public ArrayList<Integer> getInputList ()
	{
		String userInputString = userInputField.getText();
		String[] splittedInput = userInputString.split(",");
		ArrayList<Integer> userInputList = new ArrayList<Integer>();
		
		for (int i=0; i<splittedInput.length; i++)
			userInputList.add(Integer.parseInt(splittedInput[i]));

		for (int i=0; i<userInputList.size(); i++)
			System.out.print (userInputList.get(i)+" ");
		System.out.println();

		return userInputList;
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
