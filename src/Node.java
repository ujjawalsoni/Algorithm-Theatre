import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;


public class Node
{
	int data;
	int x;
	int y;
	Node parent;
	Node leftChild;
	Node rightChild;
	Color color;
	int height;

	public Node (int d, Node p, int x, int y, int h)
	{
		data = d;
		this.x = x;
		this.y = y;
		parent = p;
		leftChild = null;
		rightChild = null;
		color = Color.red;
		height = h;
	}

	public void drawNode (Graphics g)
	{
		g.setColor(color);
		g.fillOval(x, y, 30, 30);
		g.setColor(Color.BLACK);
		g.drawOval(x, y, 30, 30);

		Toolkit.getDefaultToolkit().sync();
	}

}