package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.model.Table;

@SuppressWarnings("serial")
public class UpdateTablePanel extends SidePanel implements ActionListener {
	private JButton button;
	private JButton back;
	private Controller c;
	private JTextField oldTableTitle;
	private JComboBox<Integer> selectedTable;
	private JTextField newTableNumber;
	private JTextField newNumberOfSeats;
	private JTextField mainTitle;
	private JTextField tableTitle;
	private JTextField seatTitle;

	public UpdateTablePanel(Controller controller, RestoAppPage page) {
		super(controller, page);
		c = controller;

		this.setLayout(null);

		button = new JButton("Apply");
		button.setBackground(Color.white);
		button.setBounds(445, 600, 200, 75);
		button.setFont(new Font("Comic sans MS", Font.PLAIN, 30));
		this.add(button);
		button.addActionListener(this);

		back = new JButton("Back");
		back.setBackground(Color.white);
		back.setBounds(75, 600, 200, 75);
		back.setFont(new Font("Comic sans MS", Font.PLAIN, 30));
		this.add(back);
		back.addActionListener(this);

		newTableNumber = new JTextField("");
		newTableNumber.setEditable(true);
		newTableNumber.setFont(new Font("Comic sans MS", Font.PLAIN, 25));
		newTableNumber.setBounds(85, 470, 175, 75);
		newTableNumber.setBackground(Color.white);
		newTableNumber.setHorizontalAlignment(JTextField.CENTER);
		this.add(newTableNumber);

		newNumberOfSeats = new JTextField("");
		newNumberOfSeats.setEditable(true);
		newNumberOfSeats.setFont(new Font("Comic sans MS", Font.PLAIN, 25));
		newNumberOfSeats.setBounds(455, 470, 175, 75);
		newNumberOfSeats.setBackground(Color.white);
		newNumberOfSeats.setHorizontalAlignment(JTextField.CENTER);
		this.add(newNumberOfSeats);

		selectedTable = new JComboBox<Integer>();
		selectedTable.setEditable(true);
		selectedTable.setFont(new Font("Comic sans MS", Font.PLAIN, 25));
		selectedTable.setBounds(260, 275, 175, 75);
		selectedTable.setBackground(Color.white);
		this.updateView();
		this.add(selectedTable);

		oldTableTitle = new JTextField("Number Of Selected Table");
		oldTableTitle.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		oldTableTitle.setBounds(225, 180, 260, 75);
		oldTableTitle.setEditable(false);
		oldTableTitle.setHorizontalAlignment(JTextField.CENTER);
		oldTableTitle.setBackground(Color.lightGray);
		this.add(oldTableTitle);

		mainTitle = new JTextField("Update Table Number And Number Of Seats");
		mainTitle.setFont(new Font("Comic sans MS", Font.PLAIN, 30));
		mainTitle.setBounds(25, 50, 700, 100);
		mainTitle.setEditable(false);
		mainTitle.setHorizontalAlignment(JTextField.CENTER);
		mainTitle.setBackground(Color.pink);
		this.add(mainTitle);

		tableTitle = new JTextField("Enter new Table Number");
		tableTitle.setEditable(false);
		tableTitle.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		tableTitle.setHorizontalAlignment(JTextField.CENTER);
		tableTitle.setBackground(Color.lightGray);
		tableTitle.setBounds(50, 370, 250, 75);
		this.add(tableTitle);

		seatTitle = new JTextField("Enter new Number Of Seats");
		seatTitle.setEditable(false);
		seatTitle.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		seatTitle.setHorizontalAlignment(JTextField.CENTER);
		seatTitle.setBackground(Color.lightGray);
		seatTitle.setBounds(400, 370, 300, 75);
		this.add(seatTitle);

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
		if (e.getActionCommand().equals("Apply")) {
			int newNumber;
			int newNumberSeats;

			if (!newTableNumber.getText().equals("") && !newNumberOfSeats.getText().equals("")) {
				try {
					newNumber = Integer.parseInt(newTableNumber.getText());
					newNumberSeats = Integer.parseInt(newNumberOfSeats.getText());
				} catch (Exception j) {
					JOptionPane.showMessageDialog(this, "Wrong Input");
					return;
				}
			} else if (!newTableNumber.getText().equals("") && newNumberOfSeats.getText().equals("")) {
				Table table;
				try {
					newNumber = Integer.parseInt(newTableNumber.getText());
					table = Table.getWithNumber((int) selectedTable.getSelectedItem());
				} catch (Exception j) {
					JOptionPane.showMessageDialog(this, "Wrong Input");
					return;
				}
				newNumberSeats = table.numberOfCurrentSeats();
			} else if (newTableNumber.getText().equals("") && !newNumberOfSeats.getText().equals("")) {
				Table table;
				try {
					table = Table.getWithNumber((int) selectedTable.getSelectedItem());
					newNumberSeats = Integer.parseInt(newNumberOfSeats.getText());
				} catch (Exception j) {
					JOptionPane.showMessageDialog(this, "Wrong Input");
					return;
				}
				newNumber = table.getNumber();
			} else {
				JOptionPane.showMessageDialog(this, "Wrong Input");
				return;
			}
			
			try {
				Table table = Table.getWithNumber((int) selectedTable.getSelectedItem());

				if(tooManySeats(newNumberSeats, table.getWidth(), table.getLength())) {
					throw new Exception("Too many seats");
				}
				c.updateTable(table, newNumber, newNumberSeats);
			} catch (Exception j) {
				JOptionPane.showMessageDialog(this, j.getMessage());
				return;
			}
			
			updateView();
			newTableNumber.setText("");
			newNumberOfSeats.setText("");
			this.page.updateSidePanels();

		} else {
			if (e.getActionCommand().equals("Back")) {
				this.page.setRightIndex(0);
				this.page.updateSidePanels();
				return;
			}
			System.out.println(e.getActionCommand());
		}
	}

	@Override
	public void updateView() {
		selectedTable.removeAllItems();
		for (Integer item : controller.getAllCurrentTableNumbers()) {
			selectedTable.addItem(item);
		}
		this.revalidate();
		this.repaint();
	}
}