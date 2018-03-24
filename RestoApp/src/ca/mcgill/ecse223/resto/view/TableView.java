package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
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
		for (Seat s : table.getCurrentSeats()) {
			this.seats.add(new SeatView(s, points.get(i).x, points.get(i).y, i));
			i++;
		}
		
		switch(table.getStatus()) {
		case NothingOrdered:
			this.color = Color.lightGray;
			break;
		case Ordered:
			this.color = Color.BLUE;
			break;
		default:
			break;
		}
	}

	public int getNumber() {
		return table.getNumber();
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

		// draw text in the middle of the table
		Rectangle rect = new Rectangle(table.getX(), table.getY(), table.getWidth(), table.getLength());
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		String text = table.getNumber() + " ";
		// Determine the X coordinate for the text
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		// Determine the Y coordinate for the text
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setColor(Color.WHITE);
		// Draw the String
		g.drawString(text , x, y);
	}

	/** Helper methods for automatic seats placing **/
	private List<Point> getListOfSeatCoordinates() throws InvalidInputException {
		int seatSize = SeatView.getRadius() * 2;
		int numberOfSeats = this.table.numberOfCurrentSeats();
		if(numberOfSeats == 1)
		{
			List<Point> tmmp = new ArrayList<Point>();
			tmmp.add(new Point(table.getX() - seatSize/2, table.getY()));
			return tmmp;

		}
			
		int a = table.getWidth();
		int b = table.getLength();
		int step = SeatView.getRadius() * 2;
		double ratio = a * 1.0 / (a + b);
		int aSeats = (int) Math.floor(ratio * numberOfSeats);
		int bSeats = (int) Math.floor((1 - ratio) * numberOfSeats);

		if (aSeats + bSeats < numberOfSeats)
			aSeats += numberOfSeats - aSeats - bSeats;

		if ((a / seatSize) < aSeats / 2 || (b / seatSize) < bSeats / 2)
			throw new InvalidInputException("Too many seats for table number" + table.getNumber());

		List<Point> points = new ArrayList<Point>();

		for (int i = 0; i <= aSeats / 2; i++) {
			points.add(new Point(table.getX() + 2 * i * (a / aSeats), table.getY() - step / 2));
			points.add(new Point(table.getX() + 2 * i * (a / aSeats), table.getY() + table.getLength()));
		}

		for (int i = 0; i <= bSeats / 2; i++) {
			points.add(new Point(table.getX() - step / 2, step / 2 + table.getY() + 2 * i * (b / bSeats)));
			points.add(new Point(table.getX() + table.getWidth(), step / 2 + table.getY() + 2 * i * (b / bSeats)));
		}
		return points;
	}

	@Override
	public String toString() {
		return table.getNumber()+ "";
	}
	
	

}
