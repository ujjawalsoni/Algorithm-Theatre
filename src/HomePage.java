/**
 *	Project:	Algorithm Theatre
 *
 * 	Authors:	Ishu Dharmendra Garg (CS13B060)
 *				Ujjawal Soni (CS13B053)
 */

/**
 *	HomePage- Class for the first page, opens the option buttons, 
 *	to open whichever algorithm or data-structure user want.
 *	Gives following options:
 *	-->	Bubble Sort
 *	-->	Selection Sort
 *	-->	Insertion Sort
 *	-->	Merge Sort
 *	-->	Quick Sort
 *	-->	Binary Search Tree
 *	-->	Binary Heap
 *
 *	Opens the algorithm animation window on button click, GUI class.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class HomePage
{
	/**
	 * mainFrame - the main frame of the gui
	 * the mainPane (container) is divided into 5 jpanels
	 * ** centre
	 * ** left
	 * ** right
	 * ** top
	 * ** bottom
	 * 
	 * the centre panel contain the buttons for all the algos.
	 */
	private JFrame 		mainFrame;
	private Container 	mainPane;
	private JPanel 		centrePanel;
	private JPanel 		leftPanel;
	private JPanel 		rightPanel;
	private JPanel 		topPanel;
	private JPanel 		bottomPanel;
	
	/**
	 *	Buttons for the algorithms/data-structures
	 */
	private JButton 	bubbleSort;
	private JButton 	selectionSort;
	private JButton 	insertionSort;
	private JButton 	mergeSort;
	private JButton 	quickSort;
	private JButton 	binarySearchTree;
	private JButton 	binaryHeap;

	GUI g;

	public HomePage ()
	{
		mainFrame = new JFrame ("Algorithm Theatre");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		/**
		 *	Open the main frame in full screen size.
		 */
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setSize(screenSize);
	    mainPane = mainFrame.getContentPane();

	    /**
		 *	centre panel has grid-layout and contains all the buttons.
	     */
	    centrePanel = new JPanel ();
	    centrePanel.setPreferredSize(new Dimension(920, 720));
	    centrePanel.setBackground(Color.white);
	    centrePanel.setLayout(new GridLayout(2,4,20,30));
		mainPane.add(centrePanel, BorderLayout.CENTER);

		/**
		 *	Top-panel, bottom-panel, right-panel, left-panel are all just 
		 *	for good look, and dont contain any special feature.
		 */
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
	    topPanel.setLayout(null);
		mainPane.add(topPanel, BorderLayout.PAGE_START);

	    bottomPanel = new JPanel ();
	    bottomPanel.setPreferredSize(new Dimension(1000, 40));
	    bottomPanel.setBackground(Color.black);
		mainPane.add(bottomPanel, BorderLayout.PAGE_END);

		/**
		 *	Button for opening the GUI window for bubble sort
		 */
		bubbleSort = new JButton("Bubble Sort");
		bubbleSort.setBorder(null);
		bubbleSort.setFocusPainted(false);
		bubbleSort.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bubbleSort.setBackground(Color.yellow);
		bubbleSort.setFont(new Font("Arial", Font.PLAIN, 30));
		centrePanel.add(bubbleSort);

		bubbleSort.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				/**
				 *	GUI window is opened, and its
				 *	bubbleSortWindowInitialize() function is called.
				 */
				g = new GUI(true);
				g.bubbleSortWindowInitialize();
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

		/**
		 *	Button for opening the GUI window for selection sort
		 */
		selectionSort = new JButton("Selection Sort");
		selectionSort.setBorder(null);
		selectionSort.setFocusPainted(false);
		selectionSort.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		selectionSort.setBackground(Color.yellow);
		selectionSort.setFont(new Font("Arial", Font.PLAIN, 30));
		centrePanel.add(selectionSort);

		selectionSort.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				/**
				 *	GUI window is opened, and its
				 *	selectionSortWindowInitialize() function is called.
				 */
				g = new GUI(true);
				g.selectionSortWindowInitialize();
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

		
		/**
		 *	Button for opening the GUI window for insertion sort
		 */
		insertionSort = new JButton("Insertion Sort");
		insertionSort.setBorder(null);
		insertionSort.setFocusPainted(false);
		insertionSort.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		insertionSort.setBackground(Color.yellow);
		insertionSort.setFont(new Font("Arial", Font.PLAIN, 30));
		centrePanel.add(insertionSort);
		
		insertionSort.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				/**
				 *	GUI window is opened, and its
				 *	insertionSortWindowInitialize() function is called.
				 */
				g = new GUI(true);
				g.insertionSortWindowInitialize();
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


		/**
		 *	Button for opening the GUI window for merge sort
		 */
		mergeSort = new JButton("Merge Sort");
		mergeSort.setBorder(null);
		mergeSort.setFocusPainted(false);
		mergeSort.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mergeSort.setBackground(Color.yellow);
		mergeSort.setFont(new Font("Arial", Font.PLAIN, 30));
		centrePanel.add(mergeSort);

		mergeSort.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				/**
				 *	GUI window is opened, and its
				 *	mergeSortWindowInitialize() function is called.
				 */
				g = new GUI(true);
				g.mergeSortWindowInitialize();
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


		/**
		 *	Button for opening the GUI window for quick sort
		 */
		quickSort = new JButton("Quick Sort");
		quickSort.setBorder(null);
		quickSort.setFocusPainted(false);
		quickSort.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		quickSort.setBackground(Color.yellow);
		quickSort.setFont(new Font("Arial", Font.PLAIN, 30));
		centrePanel.add(quickSort);

		quickSort.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				/**
				 *	GUI window is opened, and its
				 *	quickSortWindowInitialize() function is called.
				 */
				g = new GUI(true);
				g.quickSortWindowInitialize();
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


		/**
		 *	Button for opening the GUI window for binary search tree
		 */
		binarySearchTree = new JButton("<html>Binary Search<br><center>Tree</center></html>");
		binarySearchTree.setBorder(null);
		binarySearchTree.setFocusPainted(false);
		binarySearchTree.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		binarySearchTree.setBackground(Color.yellow);
		binarySearchTree.setFont(new Font("Arial", Font.PLAIN, 30));
		centrePanel.add(binarySearchTree);
		
		binarySearchTree.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				/**
				 *	GUI window is opened, and its
				 *	bianrySearchTreeWindowInitialize() function is called.
				 */
				g = new GUI(true);
				g.binarySearchTreeWindowInitialize();
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


		/**
		 *	Button for opening the GUI window for binary heap
		 */
		binaryHeap = new JButton("Binary Heap");
		binaryHeap.setBorder(null);
		binaryHeap.setFocusPainted(false);
		binaryHeap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		binaryHeap.setBackground(Color.yellow);
		binaryHeap.setFont(new Font("Arial", Font.PLAIN, 30));
		centrePanel.add(binaryHeap);

		binaryHeap.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				/**
				 *	GUI window is opened, and its
				 *	binaryHeapWindowInitialize() function is called.
				 */
				g = new GUI(true);
				g.binaryHeapWindowInitialize();
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

		mainFrame.pack();
	    mainFrame.setVisible(true);
	}
}