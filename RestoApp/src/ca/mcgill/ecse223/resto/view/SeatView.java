package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import ca.mcgill.ecse223.resto.model.Seat;

/**
 * this class is a wrapper class for the seat
 * 
 * @author student
 *
 */
public class SeatView  {
	private Seat seat;
	private final static int RADIUS = 20;
	private int x;
	private int y;
	private Color color = Color.RED;

	public SeatView(Seat seat, int x, int y) {
		super();
		this.seat = seat;
		this.x = x;
		this.y = y;
	}

	public SeatView(Seat seat, int x, int y, Color color) {
		super();
		this.seat = seat;
		this.x = x;
		this.y = y;
		this.color = color;
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
		g.setColor(color);
		g.fillOval(x, y, RADIUS, RADIUS);
	}
}
