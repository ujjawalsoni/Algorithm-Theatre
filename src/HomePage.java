/**
 *	Project:	Algorithm Theatre
 *
 * 	Authors:	Ishu Dharmendra Garg (CS13B060)
 *				Ujjawal Soni (CS13B053)
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
	private JFrame mainFrame;
	private Container mainPane;
	private JPanel centrePanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;
	
	private JButton bubbleSort;
	private JButton selectionSort;
	private JButton insertionSort;
	private JButton mergeSort;
	private JButton quickSort;
	private JButton binarySearchTree;
	private JButton binaryHeap;
	
	GUI g;

	public HomePage ()
	{
		mainFrame = new JFrame ("Algorithm Theatre");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setSize(screenSize);
	    mainPane = mainFrame.getContentPane();

	    centrePanel = new JPanel ();
	    centrePanel.setPreferredSize(new Dimension(920, 720));
	    centrePanel.setBackground(Color.white);
	    centrePanel.setLayout(new GridLayout(2,4,20,30));
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
	    topPanel.setLayout(null);
		mainPane.add(topPanel, BorderLayout.PAGE_START);

	    bottomPanel = new JPanel ();
	    bottomPanel.setPreferredSize(new Dimension(1000, 40));
	    bottomPanel.setBackground(Color.black);
		mainPane.add(bottomPanel, BorderLayout.PAGE_END);

		bubbleSort = new JButton("Bubble Sort");
//		bubbleSort = new JButton ("Bubble Sort", new ImageIcon ("Images/bubblesort.jpg"));
//		bubbleSort.setIcon(new ImageIcon ("Images/bubblesort.jpg"));
//		bubbleSort.setHorizontalTextPosition(AbstractButton.LEFT);
//		bubbleSort.setVerticalTextPosition(AbstractButton.TOP);
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