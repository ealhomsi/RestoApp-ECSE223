package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import ca.mcgill.ecse223.resto.controller.Controller;

@SuppressWarnings("serial")

public class AddTablePanel extends SidePanel implements ActionListener {

	private JButton addButton;
	private JButton backButton;
	private JButton clear;
	private JTextField seatNumber;
	private JTextField tableNumber;
	private JTextField width;
	private JTextField length;
	private JTextField xPos;
	private JTextField yPos;
	private JLabel seatNum;
	private JLabel tableNum;
	private JLabel widthLabel;
	private JLabel lengthLabel;
	private JLabel xLabel;
	private JLabel yLabel;
	private JLabel title;
	private JSeparator divider;

	public AddTablePanel(Controller c, RestoAppPage p) {
		// TODO Auto-generated constructor stub
		super(c, p);

		this.setLayout(null);

		addButton = new JButton("Add");
		addButton.setBounds(550, 650, 100, 50);
		addButton.setBackground(Color.white);
		addButton.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		addButton.addActionListener(this);
		this.add(addButton);

		backButton = new JButton("Back");
		backButton.setBounds(150, 650, 100, 50);
		backButton.setBackground(Color.white);
		backButton.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		backButton.addActionListener(this);
		this.add(backButton);

		clear = new JButton("Clear");
		clear.setBounds(350, 650, 100, 50);
		clear.setBackground(Color.white);
		clear.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		clear.addActionListener(this);
		this.add(clear);

		seatNumber = new JTextField();
		seatNumber.setBounds(250, 200, 300, 50);
		this.add(seatNumber);

		tableNumber = new JTextField();
		tableNumber.setBounds(250, 275, 300, 50);
		this.add(tableNumber);

		width = new JTextField();
		width.setBounds(250, 350, 300, 50);
		this.add(width);

		length = new JTextField();
		length.setBounds(250, 425, 300, 50);
		this.add(length);

		xPos = new JTextField();
		xPos.setBounds(250, 500, 300, 50);
		this.add(xPos);

		yPos = new JTextField();
		yPos.setBounds(250, 575, 300, 50);
		this.add(yPos);

		seatNum = new JLabel("Number of Seats: ");
		seatNum.setBounds(75, 200, 200, 50);
		seatNum.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(seatNum);

		tableNum = new JLabel("Table ID Number: ");
		tableNum.setBounds(75, 275, 200, 50);
		tableNum.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(tableNum);

		widthLabel = new JLabel("                Width: ");
		widthLabel.setBounds(75, 350, 200, 50);
		widthLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(widthLabel);

		lengthLabel = new JLabel("               Length: ");
		lengthLabel.setBounds(75, 425, 200, 50);
		lengthLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(lengthLabel);

		xLabel = new JLabel("          X Position: ");
		xLabel.setBounds(75, 500, 200, 50);
		xLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(xLabel);

		yLabel = new JLabel("          Y Position: ");
		yLabel.setBounds(75, 575, 200, 50);
		yLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(yLabel);

		title = new JLabel("    Parameters of New Table");
		title.setBounds(0, 0, 750, 100);
		title.setBackground(Color.decode("#FFCCC2"));
		title.setOpaque(true);
		title.setFont(new Font("Comic sans MS", Font.PLAIN, 50));
		this.add(title);

	}

	public void clearInputs() {
		tableNumber.setText(null);
		seatNumber.setText(null);
		xPos.setText(null);
		yPos.setText(null);
		width.setText(null);
		length.setText(null);
		this.page.updateSidePanels();
	}

	@Override
	public void updateView() {

	}

	private boolean tooManySeats(int numberOfSeats, int a, int b) {
		int seatSize = SeatView.getRadius() * 2;
		int step = SeatView.getRadius() * 2;
		double ratio = a * 1.0 / (a + b);
		int aSeats = (int) Math.floor(ratio * numberOfSeats);
		int bSeats = (int) Math.floor((1 - ratio) * numberOfSeats);

		if (aSeats + bSeats < numberOfSeats)
			aSeats += numberOfSeats - aSeats - bSeats;

		return ((a / seatSize) < aSeats / 2 || (b / seatSize) < bSeats / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// git test comment
		if (e.getSource() == backButton) {
			this.page.setRightIndex(0);
			this.page.updateSidePanels();
		} else if (e.getSource() == clear) {
			this.clearInputs();

		}

		else if (e.getActionCommand().equals("Add")) {
			try {
				int tableId = Integer.parseInt(tableNumber.getText());
				int xCoordinate = Integer.parseInt(xPos.getText());
				int yCoordinate = Integer.parseInt(yPos.getText());
				int widthValue = Integer.parseInt(width.getText());
				int lengthValue = Integer.parseInt(length.getText());
				int numberOfSeats = Integer.parseInt(seatNumber.getText());
				if (numberOfSeats <= 0)
					throw new Exception();

				// checking if there are too many seats
				if(tooManySeats(numberOfSeats, widthValue, lengthValue)) {
					throw new Exception("We can't put that many seats arround the table. Please try lowering the number of seats");
				}
				controller.addTable(tableId, xCoordinate, yCoordinate, widthValue, lengthValue, numberOfSeats);
				this.clearInputs();
				page.updateSidePanels();

				for (Integer i : controller.getAllCurrentTableNumbers())
					System.out.println(i);

			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(this, "please enter integer values");
			} catch (Exception e3) {
				JOptionPane.showMessageDialog(this, e3.getMessage());
			}
		}

	}
}
