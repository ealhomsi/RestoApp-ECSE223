package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Graphics;

import ca.mcgill.ecse223.resto.controller.Controller;

/**
 * This class is a list of all tables that needs to be maintained and drawn
 * 
 * @author student
 *
 */
@SuppressWarnings("serial")
public class DrawingPanel extends SidePanel {
	/**
	 * 
	 */

	public DrawingPanel(Controller c, RestoAppPage p) {
		super(c, p);
		this.setBackground(Color.white);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (TableView tv : controller.getAllCurrentTables()) {
			tv.drawTable(g);
			for (SeatView sv : tv.getSeats()) {
				sv.drawSeat(g);
			}
		}
	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		this.revalidate();
		this.repaint();

	}
}
