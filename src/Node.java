/**
 *	Project:	Algorithm Theatre
 *
 * 	Authors:	Ishu Dharmendra Garg (CS13B060)
 *				Ujjawal Soni (CS13B053)
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.geom.Line2D;


public class Node
{
	/**
	 *	A Node object stores its data, its x and y - coordinates
	 *	its parent, left child, right child
	 *	Node color, node background color, node text color,
	 *	edge color (color of edge from node to its parent),
	 *	and its height in the tree.
	 */
	int 	data;
	int 	x;
	int 	y;
	Node 	parent;
	Node 	leftChild;
	Node 	rightChild;
	Color 	nodeColor;
	Color 	nodeBGColor;
	Color 	edgeColor;
	Color 	textColor;
	int 	height;

	public Node (int d, Node p, int x, int y, int h)
	{
		data 			= d;
		this.x 			= x;
		this.y 			= y;
		parent 			= p;
		leftChild 		= null;
		rightChild 		= null;
		nodeColor 		= Color.black;
		nodeBGColor 	= Color.white;
		edgeColor 		= Color.black;
		textColor 		= Color.black;
		height 			= h;
	}
	
	public void printNodeDetails ()
	{
		System.out.println("Data: " + data);
		System.out.println("x: " + x);
		System.out.println("y: " + y);
		System.out.println("Parent: " + (parent==null ? "null" : parent.data));
		System.out.println("Left: " + (leftChild==null ? "null" : leftChild.data));
		System.out.println("Right: " + (rightChild==null ? "null" : rightChild.data));
		System.out.println("Height: " + height);
		System.out.println();
	}

	/**
	 *	Draw a node.
	 */
	public void drawNode (Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(
			    RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(nodeBGColor);
		g2.fillOval(x, y, 32, 32);
		g2.setColor(nodeColor);
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(2));
		g2.drawOval(x, y, 32, 32);
		g2.setColor(textColor);
		g2.drawString (Integer.toString(data), x+((data/10) == 0 ? 12:8), y+20);

		g2.setColor(edgeColor);
		if (parent != null)
		{
			double temp = Math.atan2(y-parent.y, x-parent.x);
			double x1 	= (x+16) - 16*Math.cos(temp);
			double y1 	= (y+16) - 16*Math.sin(temp);
			double x2 	= (parent.x+16) + 16*Math.cos(temp);
			double y2 	= (parent.y+16) + 16*Math.sin(temp);

			g2.draw (new Line2D.Double(x1, y1, x2, y2));
		}
		g2.setStroke(oldStroke);
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void changeNodeColor (Color c)
	{
		nodeColor = c;
	}

	public void changeNodeBackgroundColor (Color c)
	{
		nodeBGColor = c;
	}
	
	public void changeEdgeColor (Color c)
	{
		edgeColor = c;
	}
	
	public void changeTextColor (Color c)
	{
		textColor = c;
	}
	
	public void changeNodeData (int d)
	{
		data = d;
	}
}