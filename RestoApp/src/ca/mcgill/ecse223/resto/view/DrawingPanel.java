package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import ca.mcgill.ecse223.resto.controller.Controller;

/**
 * This class is a list of all tables that needs to be maintained and drawn
 * 
 * @author student
 *
 */
@SuppressWarnings("serial")
public class DrawingPanel extends SidePanel implements ActionListener {
	/**
	 * 
	 */
	Calendar now = null;

	public DrawingPanel(Controller c, RestoAppPage p) {
		super(c, p);
		this.setBackground(Color.white);
		now = Calendar.getInstance();
		int interval = 3000; // 3000 ms
		new Timer(interval, this).start();
}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// draw date
		now = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String text = dateFormat.format(now.getTime());
		int x = 600;
		int y = 40;
		g.setColor(Color.BLACK);
		g.drawString(text, x, y);
		for (TableView tv : controller.getAllCurrentTables()) {
			tv.drawTable(g, now);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		now = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(dateFormat.format(now.getTime()));
		this.updateView();
	}
}
