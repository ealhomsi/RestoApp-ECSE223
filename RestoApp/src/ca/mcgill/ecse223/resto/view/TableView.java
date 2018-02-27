package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;

/**
 * This class is a wrapper for the model table
 * 
 * @author student
 *
 */
public class TableView {
	private Table table;
	private Color color;
	private List<SeatView> seats;

	public TableView(Table table) {
		super();
		this.seats = new ArrayList<SeatView>();
		this.table = table;
		this.color = Color.DARK_GRAY;

		// add seats
		List<Point> points = null;
		try {
			points = getListOfSeatCoordinates();
		} catch (InvalidInputException e) {
			System.err.println("There was an error in generating seats " + e.getMessage());
			e.printStackTrace();
		}

		int i = 0;
		for (Seat s : table.getSeats()) {
			this.seats.add(new SeatView(s, points.get(i).x, points.get(i).y));
			i++;
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public List<SeatView> getSeats() {
		return seats;
	}

	public void setSeats(List<SeatView> seats) {
		this.seats = seats;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public TableView(Table table, Color color) {
		super();
		this.table = table;
		this.color = color;
	}

	public void drawTable(Graphics g) {
		g.setColor(color);
		g.fillRect(table.getX(), table.getY(), table.getWidth(), table.getLength());
		g.setColor(Color.white);
	
		//draw text in the middle of the table	
		Rectangle rect = new Rectangle(table.getX(), table.getY(), table.getWidth(), table.getLength());
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		String text = table.getNumber() + " ";
		// Determine the X coordinate for the text
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setColor(Color.WHITE);
	    // Draw the String
	    g.drawString(text, x, y);
	}

	/** Helper methods for automatic seats placing **/
	private static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	private List<Point> getListOfSeatCoordinates() throws InvalidInputException {
		int numberOfSeats = this.table.numberOfSeats();
		int a = this.table.getWidth();
		int b = this.table.getLength();

		int g = gcd(a, b);

		int aUnits = a / g;
		int bUnits = b / g;

		int total = aUnits + bUnits;

		int crowdFactor = numberOfSeats / (total * 2);
		if (crowdFactor == 0)
			crowdFactor++;
		if (crowdFactor < g) {
			// use crowdFactor as seats per unit length
			List<Point> points = new ArrayList<>();

			for (int aIndex = 0; aIndex < aUnits; aIndex++) {
				for (int c = 0; c <= crowdFactor; c++) {
					points.add(new Point(table.getX() + c * (g / crowdFactor) + aIndex * g - c * SeatView.getRadius(),
							table.getY() - SeatView.getRadius()));
					points.add(new Point(table.getX() + c * (g / crowdFactor) + aIndex * g - c * SeatView.getRadius(),
							table.getY() + table.getLength()));
				}
			}

			for (int bIndex = 0; bIndex < bUnits; bIndex++) {
				for (int c = 0; c <= crowdFactor; c++) {
					points.add(new Point(table.getX() - SeatView.getRadius(),
							table.getY() + c * (g / crowdFactor) + bIndex * g));
					points.add(new Point(table.getX() + table.getWidth(),
							table.getY() + c * (g / crowdFactor) + bIndex * g));
				}
			}

			return points;
		} else {
			throw new InvalidInputException("too many seats for visual display");
		}
	}

	private int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}
}
