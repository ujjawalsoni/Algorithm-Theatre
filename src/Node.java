import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.geom.Line2D;


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
		color = Color.yellow;
		height = h;
	}

	public void drawNode (Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fillOval(x, y, 32, 32);
		g2.setColor(Color.BLACK);
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(2));
		g2.drawOval(x, y, 32, 32);
		g2.drawString (Integer.toString(data), x+((data/10) == 0 ? 12:8), y+19);

		if (parent != null)
		{
			double temp = Math.atan2(y-parent.y, x-parent.x);
			double x1 = (x+16) - 16*Math.cos(temp);
			double y1 = (y+16) - 16*Math.sin(temp);
			double x2 = (parent.x+16) + 16*Math.cos(temp);
			double y2 = (parent.y+16) + 16*Math.sin(temp);
			g2.draw (new Line2D.Double(x1, y1, x2, y2));
		}
		g2.setStroke(oldStroke);
		Toolkit.getDefaultToolkit().sync();
	}

}