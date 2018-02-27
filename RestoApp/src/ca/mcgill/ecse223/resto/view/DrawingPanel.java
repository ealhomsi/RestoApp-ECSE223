package ca.mcgill.ecse223.resto.view;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel{
	private List<TableView> tvs;

	
	public DrawingPanel(List<TableView> tvs) {
		super();
		this.tvs = tvs;
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(TableView tv: tvs) {
			tv.drawTable(g);
			for(SeatView sv: tv.getSeats()) {
				sv.drawSeat(g);
			}
		}
	}
}
