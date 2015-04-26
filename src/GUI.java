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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class GUI
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
	 * the left panel contains the action panel
	 * the right pane contains the status label and code trace label
	 * ** status label is the status of the program
	 * ** code trace label is the pseudocode for the algorithm
	 * 
	 * the centre panel contain two sub panel - mainTopPanel and mainBottomPanel
	 * ** mainTopPanel is to show the animation
	 * ** mainBottomPanel contains the labels and gui for various operations
	 */

	private JFrame 		mainFrame;
	private Container 	mainPane;

	private JPanel 		centrePanel;
	private JPanel 		leftPanel;
	private JPanel 		rightPanel;
	private JPanel 		topPanel;
	private JPanel 		bottomPanel;

	private JButton 	leftBottom;
	private JButton 	rightMiddle;
	private JButton 	rightBottom;
	private JButton 	developers;

	/**
	 *	centre panel contains two panels mainTopPanel and mainBottomPanel
	 *	
	 *	mainTopPanel contains the animation panel corresponding to the function called.
	 *	mainBottomPanel contains 
	 *	-->	the codeTracePanel, which contains
	 *		JLabel array, which contains the pseudocodes of the corresponding operation.
	 *	-->	the statusLabel, shows the status of the operation being performed.
	 */
	private JPanel 		mainTopPanel;
	private JPanel 		mainBottomPanel;
	private JPanel 		codeTracePanel;
	private JLabel[] 	codeTraceLabels;
	private JLabel 		statusLabel;

	/**
	 *	createList - a JButton to take user input
	 *	userInputField - a Textfield to take the input list from user
	 *	input is read from the TextField when goCreateList button is clicked
	 */
	private JButton 	createList;
	private JTextField 	userInputField;
	private JButton 	goCreateList;

	/**
	 *	sortList - a JButton to set goSortList button visible
	 *	when goSortButton is clicked, if the current instruction is sorting
	 *	the it starts its animation.
	 *	But for binarySearchTree- it is used as button to call 
	 *		search element function
	 *	and for binaryHeap- it is used to call deleteMin function.
	 */
	private JButton 	sortList;
	private JButton 	goSortList;

	/**
	 *	text field to get the element to be searched.
	 */
	private JTextField 	searchField;
	
	/**
	 *	insertElement button sets the insertField and goInsertElement visible.
	 *	so when goInsertElement is clicked, the input element
	 *	to be inserted in the binarySearchTree or binaryHeap is read 
	 *	from the JTextField - insertField.
	 */
	private JButton 	insertElement;
	private JTextField 	insertField;
	private JButton 	goInsertElement;

	/**
	 *	deleteElement button sets the deleteField and goDeleteElement visible.
	 *	so when goDeleteElement is clicked, the input element
	 *	to be deleted in the binarySearchTree is read from deleteField.
	 */
	private JButton 	deleteElement;
	private JTextField 	deleteField;
	private JButton 	goDeleteElement;

	/**
	 *	JButton to go back to homePage.
	 */
	private JButton		back;

	/**
	 *	JButton to play/pause the animation.
	 */
	private JButton 	pause;

	/**
	 *	JButton to see the details of current algorithm/data-structure
	 *	being implemented.
	 */
	private JButton 	currentAlgo;

	/**
	 *	images of the right/left arrows on the buttons in right/left panels.
	 */
	private ImageIcon 	rightBlackArrow;
	private ImageIcon 	leftBlackArrow;

	protected BSTPanel 	bstPanel;
	protected BHPanel 	bhPanel;

	/**
	 *	int flag to keep track of the current algorithm/data-structure globally.
	 */
	int 	instruction;

	/**
	 *	global timer of the running animation.
	 */
	Timer 	globalTimer;

	/**
	 *	Constructor to call the GUI initialize function from the homePage.
	 */
	public GUI (boolean v)
	{
		initialize();
		mainFrame.setVisible(v);
	}
	
	public void initialize ()
	{
		rightBlackArrow = new ImageIcon ("Images/arrow_black_right.png");
		leftBlackArrow = new ImageIcon ("Images/arrow_black_left.png");
		
		mainFrame = new JFrame ("Algorithm Theatre");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setSize(screenSize);

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
	    topPanel.setLayout(null);
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
		gbc.fill 	= GridBagConstraints.HORIZONTAL;
		gbc.anchor 	= GridBagConstraints.PAGE_END;
		gbc.gridx 	= 0;
		gbc.gridy 	= 0;
		gbc.weighty	= 1.0;
		gbc.insets 	= new Insets(0,0,1,0);
		leftPanel.add (leftBottom, gbc);

		leftBottom.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = instruction;
				if (createList.isVisible())
				{
					leftBottom.setIcon(rightBlackArrow);
					createList.setVisible(false);
					userInputField.setVisible(false);
					goCreateList.setVisible(false);
					sortList.setVisible(false);
					goSortList.setVisible(false);
					insertElement.setVisible(false);
					deleteElement.setVisible(false);
				}
				else
				{
					leftBottom.setIcon(leftBlackArrow);
					createList.setVisible(true);
					sortList.setVisible(true);
					if (i == 5)
					{
						insertElement.setVisible(true);
						deleteElement.setVisible(true);
					}
					else if (i == 6)
					{
						insertElement.setVisible(true);
					}
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
		gbc.fill 	= GridBagConstraints.HORIZONTAL;
		gbc.anchor 	= GridBagConstraints.CENTER;
		gbc.gridx 	= 0;
		gbc.gridy 	= 0;
		gbc.weighty	= 0.5;
		gbc.insets 	= new Insets(424,0,0,0);
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
		gbc.fill 	= GridBagConstraints.HORIZONTAL;
		gbc.anchor 	= GridBagConstraints.PAGE_END;
		gbc.gridx 	= 0;
		gbc.gridy 	= 1;
		gbc.weighty	= 1.0;
		gbc.insets	= new Insets(0,0,1,0);
		rightPanel.add (rightBottom, gbc);

		rightBottom.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				if (codeTracePanel.isVisible())
				{
					rightBottom.setIcon(leftBlackArrow);
					codeTracePanel.setVisible (false);
				}
				else
				{
					rightBottom.setIcon(rightBlackArrow);
					codeTracePanel.setVisible (true);
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
		gbc.fill 	= GridBagConstraints.HORIZONTAL;
		gbc.anchor 	= GridBagConstraints.PAGE_START;
		gbc.gridx	= 0;
		gbc.gridy 	= 0;
		gbc.weightx	= 0.0;
		gbc.insets 	= new Insets(0,0,0,0);
		centrePanel.add (mainTopPanel, gbc);

		mainBottomPanel = new JPanel();
		mainBottomPanel.setOpaque(true);
		mainBottomPanel.setBackground(Color.white);
		mainBottomPanel.setPreferredSize(new Dimension(1213,239));
		mainBottomPanel.setLayout(null);
		gbc.fill 	= GridBagConstraints.HORIZONTAL;
		gbc.anchor 	= GridBagConstraints.PAGE_START;
		gbc.gridx	= 0;
		gbc.gridy 	= 0;
		gbc.insets 	= new Insets(424,0,0,0);
		centrePanel.add (mainBottomPanel, gbc);

		statusLabel = new JLabel();
		statusLabel.setOpaque(true);
		statusLabel.setBackground(Color.orange);
		statusLabel.setVisible(false);
		statusLabel.setBounds(850,1,370,65);
		mainBottomPanel.add (statusLabel);
		
		codeTracePanel = new JPanel();
		codeTracePanel.setOpaque(true);
		codeTracePanel.setBackground(Color.green);
		codeTracePanel.setVisible(false);
		codeTracePanel.setBounds(850,69,370,169);
		codeTracePanel.setLayout(new GridLayout(7,1));
		mainBottomPanel.add (codeTracePanel);

		codeTraceLabels = new JLabel[7];
		for (int i=0; i<7; i++)
		{
			codeTraceLabels[i] = new JLabel();
			codeTracePanel.add(codeTraceLabels[i]);
		}

		createList = new JButton(" Create");
		createList.setBackground(Color.cyan);
		createList.setFocusPainted(false);
		createList.setBorder(null);
		createList.setVisible(false);
		createList.setBounds(0,208,110,32);
		createList.setHorizontalAlignment(SwingConstants.LEFT);
		createList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainBottomPanel.add (createList);
		
		createList.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				goSortList.setVisible(false);
				insertField.setVisible(false);
				goInsertElement.setVisible(false);
				deleteField.setVisible(false);
				goDeleteElement.setVisible(false);
				searchField.setVisible(false);
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
		sortList.setBounds(0,176,110,32);
		sortList.setHorizontalAlignment(SwingConstants.LEFT);
		sortList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainBottomPanel.add (sortList);

		sortList.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				insertField.setVisible(false);
				goInsertElement.setVisible(false);
				deleteField.setVisible(false);
				goDeleteElement.setVisible(false);
				userInputField.setVisible(false);
				goCreateList.setVisible(false);
				if (instruction == 5)
				{
					goSortList.setBounds(262,176,32,32);
					searchField.setVisible(true);
				}
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

		insertElement = new JButton(" Insert");
		insertElement.setBackground(Color.cyan);
		insertElement.setFocusPainted(false);
		insertElement.setBorder(null);
		insertElement.setVisible(false);
		insertElement.setBounds(0,144,110,32);
		insertElement.setHorizontalAlignment(SwingConstants.LEFT);
		insertElement.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainBottomPanel.add (insertElement);

		insertElement.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				goSortList.setVisible(false);
				deleteField.setVisible(false);
				goDeleteElement.setVisible(false);
				userInputField.setVisible(false);
				goCreateList.setVisible(false);
				searchField.setVisible(false);
				insertField.setVisible(true);
				goInsertElement.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				insertElement.setBackground(Color.black);
				insertElement.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				insertElement.setBackground(Color.cyan);
				insertElement.setForeground(Color.black);
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}

		});

		deleteElement = new JButton(" Delete");
		deleteElement.setBackground(Color.cyan);
		deleteElement.setFocusPainted(false);
		deleteElement.setBorder(null);
		deleteElement.setVisible(false);
		deleteElement.setBounds(0,112,110,32);
		deleteElement.setHorizontalAlignment(SwingConstants.LEFT);
		deleteElement.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainBottomPanel.add (deleteElement);

		deleteElement.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				goSortList.setVisible(false);
				insertField.setVisible(false);
				goInsertElement.setVisible(false);
				userInputField.setVisible(false);
				goCreateList.setVisible(false);
				searchField.setVisible(false);
				deleteField.setVisible(true);
				goDeleteElement.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				deleteElement.setBackground(Color.black);
				deleteElement.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				deleteElement.setBackground(Color.cyan);
				deleteElement.setForeground(Color.black);
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}

		});

		userInputField = new JTextField();
		userInputField.setVisible(false);
		userInputField.setBounds(111,208,150,32);
		userInputField.setBackground(Color.black);
		userInputField.setForeground(Color.white);
		userInputField.setCaretColor(Color.white);
		mainBottomPanel.add(userInputField);

		insertField = new JTextField();
		insertField.setVisible(false);
		insertField.setBounds(111,144,150,32);
		insertField.setBackground(Color.black);
		insertField.setForeground(Color.white);
		insertField.setCaretColor(Color.white);
		mainBottomPanel.add(insertField);

		goInsertElement = new JButton("Go");
		goInsertElement.setBackground(Color.cyan);
		goInsertElement.setFocusPainted(false);
		goInsertElement.setBorder(null);
		goInsertElement.setVisible(false);
		goInsertElement.setBounds(262,144,32,32);
		goInsertElement.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainBottomPanel.add (goInsertElement);

		goInsertElement.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = instruction;
				ArrayList<Integer> a = getInputList(insertField);

				if (i == 5)
				{
					globalTimer = bstPanel.insert(a.get(0));
					String pseudoCode = new StringBuilder()
									.append("  if found insertion point\n")
									.append("     create new node\n")
									.append("  if value to be inserted < this key\n")
									.append("     go left\n")
									.append("  else go right\n")
									.toString();
					setCodeTraceLabel(pseudoCode);
					codeTracePanel.setVisible(true);
				}
				if (i == 6)
				{
					globalTimer = bhPanel.insert(a.get(0));
					String pseudoCode = new StringBuilder()
									.append("  A[A.length] = new key\n")
									.append("  i = A.length - 1\n")
									.append("  while (i>1 and A[parent(i)] < A[i])\n")
									.append("     swap A[i] and A[parent(i)]\n")
									.toString();
					setCodeTraceLabel(pseudoCode);
					codeTracePanel.setVisible(true);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				goInsertElement.setBackground(Color.black);
				goInsertElement.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				goInsertElement.setBackground(Color.cyan);
				goInsertElement.setForeground(Color.black);
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}

		});

		deleteField = new JTextField();
		deleteField.setVisible(false);
		deleteField.setBounds(111,112,150,32);
		deleteField.setBackground(Color.black);
		deleteField.setForeground(Color.white);
		deleteField.setCaretColor(Color.white);
		mainBottomPanel.add(deleteField);

		goDeleteElement = new JButton("Go");
		goDeleteElement.setBackground(Color.cyan);
		goDeleteElement.setFocusPainted(false);
		goDeleteElement.setBorder(null);
		goDeleteElement.setVisible(false);
		goDeleteElement.setBounds(262,112,32,32);
		goDeleteElement.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainBottomPanel.add (goDeleteElement);

		goDeleteElement.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = instruction;
				ArrayList<Integer> a = getInputList(deleteField);

				if (i == 5)
				{
					globalTimer = bstPanel.delete(a.get(0));
					String pseudoCode = new StringBuilder()
									.append("  search for v\n")
									.append("  if v is a leaf\n")
									.append("     delete leaf v\n")
									.append("  else if v has 1 child\n")
									.append("     bypass v\n")
									.append("  else replace v with successor\n")
									.toString();
					setCodeTraceLabel(pseudoCode);
					codeTracePanel.setVisible(true);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				goDeleteElement.setBackground(Color.black);
				goDeleteElement.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				goDeleteElement.setBackground(Color.cyan);
				goDeleteElement.setForeground(Color.black);
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}

		});

		goCreateList = new JButton("Go");
		goCreateList.setBackground(Color.cyan);
		goCreateList.setFocusPainted(false);
		goCreateList.setBorder(null);
		goCreateList.setVisible(false);
		goCreateList.setBounds(262,208,32,32);
		goCreateList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainBottomPanel.add (goCreateList);

		goCreateList.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = instruction;
				ArrayList<Integer> a = getInputList(userInputField);
				
				if (i >= 0 && i<5)
				{
					globalTimer.stop();
					SortingFunctions p = new SortingFunctions(a);
					p.setBackground(Color.white);
					p.setVisible (true);
					p.setBounds(0,0,1213,420);
					mainTopPanel.add (p);
				
					if (i == 0)
					{
						globalTimer = p.bubbleSort();
					}
					else if (i == 1)
					{
						globalTimer = p.selectionSort();
					}
					else if (i == 2)
					{
						globalTimer = p.insertionSort();
					}
					else if (i == 3)
					{
						globalTimer = p.mergeSort();
					}
					else
					{
						globalTimer = p.quickSort();
					}
				}
				else if (i == 5)
				{
					BinarySearchTree b = new BinarySearchTree(a);
					bstPanel.setVisible(false);
					bstPanel = new BSTPanel(b);
					bstPanel.setBackground(Color.white);
					bstPanel.setVisible(true);
					bstPanel.setBounds(0,0,1213,420);
					mainTopPanel.add (bstPanel);
				}
				else if (i == 6)
				{
					BinaryHeap b = new BinaryHeap(a);
					bhPanel.setVisible(false);
					bhPanel = new BHPanel(b);
					bhPanel.setBackground(Color.white);
					bhPanel.setVisible(true);
					bhPanel.setBounds(0,0,1213,420);
					mainTopPanel.add (bhPanel);
				}
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
		
		searchField = new JTextField();
		searchField.setVisible(false);
		searchField.setBounds(111,176,150,32);
		searchField.setBackground(Color.black);
		searchField.setForeground(Color.white);
		searchField.setCaretColor(Color.white);
		mainBottomPanel.add(searchField);

		goSortList = new JButton("Go");
		goSortList.setBackground(Color.cyan);
		goSortList.setFocusPainted(false);
		goSortList.setBorder(null);
		goSortList.setVisible(false);
		goSortList.setBounds(112,176,32,32);
		goSortList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainBottomPanel.add (goSortList);

		goSortList.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = instruction;
				if (i >= 0 && i<5)
				{
					globalTimer.start();
					codeTracePanel.setVisible(true);
				}
				else if (i == 5)
				{
					ArrayList<Integer> a = getInputList(searchField);
					globalTimer = bstPanel.search(a.get(0));
					String pseudoCode = new StringBuilder()
									.append("  if this == null\n")
									.append("     return null\n")
									.append("  else if this key == search value\n")
									.append("     return this\n")
									.append("  else if this key < search value\n")
									.append("     search right\n")
									.append("  else search left\n")
									.toString();
					setCodeTraceLabel(pseudoCode);
					codeTracePanel.setVisible(true);					
				}
				else if (i == 6)
				{
					globalTimer = bhPanel.deleteMin();
					String pseudoCode = new StringBuilder()
									.append("  swap A[1] and A[A.length]\n")
									.append("  delete A[A.length] and A.length--\n")
									.append("  i=1\n")
									.append("  while (i<A.length)\n")
									.append("     if A[i] < than the larger of its children\n")
									.append("        swap A[i] with that child\n")
									.toString();
					setCodeTraceLabel(pseudoCode);
					codeTracePanel.setVisible(true);
				}
			}
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

		developers = new JButton("Developers - Ishu Dharmendra Garg & Ujjawal Soni");
		developers.setPreferredSize(new Dimension(400,30));
		developers.setFocusPainted(false);
		developers.setBorder(null);
		developers.setBackground(Color.black);
		developers.setForeground(Color.white);
		developers.setContentAreaFilled(false);
		gbc.gridx = 0;	gbc.gridy = 0;	gbc.weightx = 1.0;
		gbc.insets = new Insets(0,0,0,0);
		bottomPanel.add (developers, gbc);
		developers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


		/**
		 *	JButton to pause the animation timer
		 */
		pause = new JButton (new ImageIcon("Images/pause.jpg"));
		pause.setBorder(null);
		pause.setFocusPainted(false);
		pause.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pause.setBackground(Color.black);
		pause.setForeground(Color.white);
		pause.setPreferredSize(new Dimension(40,40));
		pause.setBounds(585,190,40,40);
		mainBottomPanel.add(pause);

		pause.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				if (globalTimer.isRunning())
				{
					globalTimer.stop();
					pause.setIcon(new ImageIcon("Images/play.jpg"));
				}
				else
				{
					globalTimer.start();
					pause.setIcon(new ImageIcon("Images/pause.jpg"));
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

		/**
		 *	JButton to go back to homePage
		 */
		back = new JButton (new ImageIcon("Images/back.png"));
		back.setBorder(null);
		back.setFocusPainted(false);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setBackground(Color.black);
		back.setForeground(Color.white);
		back.setPreferredSize(new Dimension(65,40));
		back.setBounds(30,0,65,40);
		topPanel.add(back);

		back.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisibility(false);
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

		currentAlgo = new JButton ("");
		currentAlgo.setBorder(null);
		currentAlgo.setFocusPainted(false);
		currentAlgo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		currentAlgo.setBackground(Color.black);
		currentAlgo.setForeground(Color.white);
		currentAlgo.setPreferredSize(new Dimension(150,40));
		currentAlgo.setBounds(1100,0,150,40);
		topPanel.add(currentAlgo);

		mainFrame.pack();
	    mainFrame.setVisible(true);
	}

	
	/**
	 *	read the input from the input JTextField and convert 
	 *	to ArrayList and return.
	 */
	public ArrayList<Integer> getInputList (JTextField inputField)
	{
		String userInputString = inputField.getText();
		String[] splittedInput = userInputString.split(",");
		ArrayList<Integer> userInputList = new ArrayList<Integer>();

		for (int i=0; i<splittedInput.length; i++)
			userInputList.add(Integer.parseInt(splittedInput[i]));

		return userInputList;
	}
	
	public void setStatusLabel(String content)
	{
		statusLabel.setText(convertToMultiline(content));
	}

	/**
	 *	Set the pseudocode in the JLabel array of the codeTracePanel.
	 */
	public void setCodeTraceLabel (String content)
	{
		String lines[] = content.split("\\r?\\n");
		for (int i=0; i<lines.length; i++)
			codeTraceLabels[i].setText(lines[i]);
	}

	private static String convertToMultiline(String original)
	{	
	    return "<html>" + original.replaceAll("\n", "<br>");
	}
	
	public void setVisibility (boolean v)
	{
		mainFrame.setVisible(v);
	}


	/**
	 *	bubbleSortWindowInitialize() create the object of the 
	 *	SortingFunction class, and intialize array to be sorted
	 *	set the pseudocode for bubble sort in the CodeTracePanel
	 *	then call the bubbleSort() function of SortingFunction.
	 */
	public void bubbleSortWindowInitialize()
	{
		instruction = 0;
		currentAlgo.setText("Bubble Sort");
		leftBottom.setPreferredSize(new Dimension(40,64));
		String pseudoCode = new StringBuilder()
							.append("  do\n")
							.append("  swapped = false\n")
							.append("  for i=1 to numOfElements exclusive:\n")
							.append("     if leftElement > rightElement:\n")
							.append("        swap(leftElement, rightElement)\n")
							.append("        swapped = true\n")
							.append("  while swapped\n")
							.toString();
		setCodeTraceLabel(pseudoCode);
		
		final ArrayList<Integer> list = new ArrayList<Integer>();
		int[] a = {52,34,22,46,26,97,54,76,19,44,67,84,25,4,30,99,98,40,39,33,43,50};
		for (int i=0; i<a.length; i++)
			list.add(a[i]);

		SortingFunctions p = new SortingFunctions(list);
		p.setBackground(Color.white);
		p.setVisible (true);
		p.setBounds(0,0,1213,420);

		/**
		 *	add the SortingFunction panel object to the mainTopPanel.
		 */
		mainTopPanel.add (p);
		globalTimer = p.bubbleSort();
	}
	

	/**
	 *	selectionSortWindowInitialize() create the object of the 
	 *	SortingFunction class, and intialize array to be sorted
	 *	set the pseudocode for selection sort in the CodeTracePanel
	 *	then call the selectionSort() function of SortingFunction.
	 */
	public void selectionSortWindowInitialize()
	{
		instruction = 1;
		currentAlgo.setText("Selection Sort");
		leftBottom.setPreferredSize(new Dimension(40,64));
		String pseudoCode = new StringBuilder()
							.append("  repeat (numOfElements-1) times\n")
							.append("     set the first unsorted element as the minimum\n")
							.append("     for each of the unsorted elements:\n")
							.append("        if element < currentMinimum:\n")
							.append("           set element as minimum\n")
							.append("     swap minimum with first unswapped position\n")
							.toString();
		setCodeTraceLabel(pseudoCode);
		
		final ArrayList<Integer> list = new ArrayList<Integer>();
		int[] a = {52,34,22,46,26,97,54,76,19,44,67,84,25,4,30,99,98,40,39,33,43,50};
		for (int i=0; i<a.length; i++)
			list.add(a[i]);

		SortingFunctions p = new SortingFunctions(list);
		p.setBackground(Color.white);
		p.setVisible (true);
		p.setBounds(0,0,1213,420);
		mainTopPanel.add (p);
		globalTimer = p.selectionSort();
	}
	

	/**
	 *	insertionSortWindowInitialize() create the object of the 
	 *	SortingFunction class, and intialize array to be sorted
	 *	set the pseudocode for insertion sort in the CodeTracePanel
	 *	then call the insertionSort() function of SortingFunction.
	 */
	public void insertionSortWindowInitialize()
	{
		instruction = 2;
		currentAlgo.setText("Insertion Sort");
		leftBottom.setPreferredSize(new Dimension(40,64));
		String pseudoCode = new StringBuilder()
							.append("  mark first element as sorted\n")
							.append("  for each unsorted element\n")
							.append("     'extract' the element\n")
							.append("     for i = lastSortedIndex to 0\n")
							.append("        if currentSortedElement > extractedElement\n")
							.append("           move sorted element to the right by 1\n")
							.append("        else: insert extracted element\n")
							.toString();
		setCodeTraceLabel(pseudoCode);

		final ArrayList<Integer> list = new ArrayList<Integer>();
		int[] a = {52,34,22,46,26,97,54,76,19,44,67,84,25,4,30,99,98,40,39,33,43,50};
		for (int i=0; i<a.length; i++)
			list.add(a[i]);
		
		SortingFunctions p = new SortingFunctions(list);
		p.setBackground(Color.white);
		p.setVisible (true);
		p.setBounds(0,0,1213,420);
		mainTopPanel.add (p);
		globalTimer = p.insertionSort();
	}
	

	/**
	 *	mergeSortWindowInitialize() create the object of the 
	 *	SortingFunction class, and intialize array to be sorted
	 *	set the pseudocode for merge sort in the CodeTracePanel
	 *	then call the mergeSort() function of SortingFunction.
	 */
	public void mergeSortWindowInitialize()
	{
		instruction = 3;
		currentAlgo.setText("Merge Sort");
		leftBottom.setPreferredSize(new Dimension(40,64));
		String pseudoCode = new StringBuilder()
							.append("  split each element into partitions of size 1\n")
							.append("  recursively merge adjacent partitions\n")
							.append("     for i = leftStartIndex to rightLastIndex inclusive\n")
							.append("        if leftPartHeadValue <= rightPartHeadValue\n")
							.append("           copy leftPartHeadValue\n")
							.append("        else: copy rightPartHeadValue\n")
							.append("  copy elements back to original array\n")
							.toString();
		setCodeTraceLabel(pseudoCode);
		
		final ArrayList<Integer> list = new ArrayList<Integer>();
		int[] a = {52,34,22,46,26,97,54,76,19,44,67,84,25,4,30,99,98,40,39,33,43,50};
		for (int i=0; i<a.length; i++)
			list.add(a[i]);
		
		SortingFunctions p = new SortingFunctions(list);
		p.setBackground(Color.white);
		p.setVisible (true);
		p.setBounds(0,0,1213,420);
		mainTopPanel.add (p);
		globalTimer = p.mergeSort();
	}
	

	/**
	 *	quickSortWindowInitialize() create the object of the 
	 *	SortingFunction class, and intialize array to be sorted
	 *	set the pseudocode for quick sort in the CodeTracePanel
	 *	then call the quickSort() function of SortingFunction.
	 */
	public void quickSortWindowInitialize()
	{
		instruction = 4;
		currentAlgo.setText("Quick Sort");
		leftBottom.setPreferredSize(new Dimension(40,64));
		String pseudoCode = new StringBuilder()
							.append("  for each (unsorted) partition\n")
							.append("     set first element as pivot\n")
							.append("     storeIndex = pivotIndex + 1\n")
							.append("     for i = pivotIndex + 1 to rightmostIndex\n")
							.append("        if element[i] < element[pivot]\n")
							.append("           swap(i, storeIndex); storeIndex++\n")
							.append("     swap(pivot, storeIndex - 1)\n")
							.toString();
		setCodeTraceLabel(pseudoCode);
		
		final ArrayList<Integer> list = new ArrayList<Integer>();
		int[] a = {52,34,22,46,26,97,54,76,19,44,67,84,25,4,30,99,98,40,39,33,43,50};
		for (int i=0; i<a.length; i++)
			list.add(a[i]);
		
		SortingFunctions p = new SortingFunctions(list);
		p.setBackground(Color.white);
		p.setVisible (true);
		p.setBounds(0,0,1213,420);
		mainTopPanel.add (p);
		globalTimer = p.quickSort();
	}


	/**
	 *	binarySearchTreeWindowInitialize() create the object of the 
	 *	BSTPanel class, and intialize the binary search tree.
	 */
	public void binarySearchTreeWindowInitialize()
	{
		instruction = 5;
		currentAlgo.setText("Binary Search Tree");
		leftBottom.setPreferredSize(new Dimension(40,128));
		sortList.setText(" Search");

		final ArrayList<Integer> list = new ArrayList<Integer>();
		int[] a = {52,34,22,46,26,97,54,76,19,44,67,84,25,4,30,99,98,40,39,33,43,50};
		for (int i=0; i<a.length; i++)
			list.add(a[i]);

		BinarySearchTree b = new BinarySearchTree(list);
		bstPanel = new BSTPanel(b);
		bstPanel.setBackground(Color.white);
		bstPanel.setVisible (true);
		bstPanel.setBounds(0,0,1213,420);

		/**
		 *	add the BSTPanel object to the mainTopPanel.
		 */
		mainTopPanel.add (bstPanel);
	}
	

	/**
	 *	binaryHeapWindowInitialize() create the object of the 
	 *	BHPanel class, and intialize the binary heap.
	 */
	public void binaryHeapWindowInitialize()
	{
		instruction = 6;
		currentAlgo.setText("Binary Heap");
		leftBottom.setPreferredSize(new Dimension(40,96));

		deleteElement.setVisible(false);
		deleteField.setVisible(false);
		goDeleteElement.setVisible(false);
		sortList.setText(" Delete Min");

		final ArrayList<Integer> list = new ArrayList<Integer>();
		int[] a = {52,34,22,46,26,97,54,76,19,44,67,84,25,4,30,99,98,40,39,33,43,50};
		for (int i=0; i<a.length; i++)
			list.add(a[i]);

		BinaryHeap b = new BinaryHeap(list);
		bhPanel = new BHPanel(b);
		bhPanel.setBackground(Color.white);
		bhPanel.setVisible (true);
		bhPanel.setBounds(0,0,1213,420);

		/**
		 *	add the BHPanel object to the mainTopPanel.
		 */
		mainTopPanel.add (bhPanel);
	}

}