package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import ca.mcgill.ecse223.resto.controller.Controller;

/**
 * This class is a list of all tables that needs to be maintained and drawn
 * @author student
 *
 */
public class DrawingPanel extends SidePanel  {
	public DrawingPanel(Controller c) {
		super(c);
		this.setBackground(Color.white);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (TableView tv : controller.getAllTables()) {
			tv.drawTable(g);
			for (SeatView sv : tv.getSeats()) {
				sv.drawSeat(g);
			}
		}
	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}
}
