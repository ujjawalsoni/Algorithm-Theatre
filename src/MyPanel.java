/**
 *	Project:	Algorithm Theatre
 *
 * 	Authors:	Ishu Dharmendra Garg (CS13B060)
 *				Ujjawal Soni (CS13B053)
 *
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;


class MyPanel extends JPanel implements ActionListener
{
	protected ArrayRectangle box;

	public MyPanel(ArrayList<Integer> list)
	{
		box = new ArrayRectangle();
		box.setBaseX(200);
		box.setBaseY(200);
		box.initializeRectangle(list);
		this.paintBox();
		
	}
	
	public int getRectangleSpace()
	{
		if (this.box.getNumber() > 0)
			return (this.box.getRectangle(0).getWidth() + this.box.getGap());
		else
		{
			System.out.println("The list has not been initialized: error getRectangleSpace ");
			return 0;
		}
	}
	
	void paintBox()
	{
		repaint();
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(500, 500);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		box.paintArrayRectangle(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}