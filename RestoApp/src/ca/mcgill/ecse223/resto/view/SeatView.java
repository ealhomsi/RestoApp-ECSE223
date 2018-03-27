package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

import ca.mcgill.ecse223.resto.model.Seat;

/**
 * this class is a wrapper class for the seat
 * 
 * @author student
 *
 */
public class SeatView {
	private int id;
private Seat seat;
	private final static int RADIUS = 20;
	private int x;
	private int y;
	private Color color = Color.RED;

	public SeatView(Seat seat, int x, int y, int id) {
		super();
		this.seat = seat;
		this.x = x;
		this.y = y;
		this.id = id;
	}

	private void hasAtLeastOneOrderItem() {
		if(this.getSeat().getOrderItems().size() > 0) {
			this.setColor(Color.PINK);
		}
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SeatView(Seat seat, int x, int y, Color color, int id) {
		super();
		this.seat = seat;
		this.x = x;
		this.y = y;
		this.color = color;
		this.id = id;
		this.hasAtLeastOneOrderItem();
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public static int getRadius() {
		return RADIUS;
	}

	public void drawSeat(Graphics g) {
		this.hasAtLeastOneOrderItem();
		g.setColor(color);
		g.fillOval(x, y, RADIUS, RADIUS);

		// draw text in the middle of the Seat
		Rectangle rect = new Rectangle(getX(), getY(), getRadius(), getRadius());
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		String text = this.getId() + " ";
		// Determine the X coordinate for the text
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2 + SeatView.getRadius()/8;
		// Determine the Y coordinate for the text
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setColor(Color.WHITE);
		// Draw the String
		g.drawString(text, x, y);
	}
}
