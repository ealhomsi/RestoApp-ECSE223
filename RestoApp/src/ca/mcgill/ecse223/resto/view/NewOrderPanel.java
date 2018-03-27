package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Table;

@SuppressWarnings("serial")

public class NewOrderPanel extends SidePanel implements ActionListener {
	private JButton addButton;
	private JButton backButton;
	private JButton clear;
	private JTextField tableNumbers;
	private JLabel TableNumbersLabel;

	public NewOrderPanel(Controller c, RestoAppPage p) {
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

		tableNumbers = new JTextField();
		tableNumbers.setBounds(250, 400, 300, 50);
		this.add(tableNumbers);

		TableNumbersLabel = new JLabel("Table Numbers Comma seperated");
		TableNumbersLabel.setBounds(75, 200, 700, 300);
		TableNumbersLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(TableNumbersLabel);

	}

	public void clearInputs() {
		tableNumbers.setText(null);
	}

	@Override
	public void updateView() {
		this.clearInputs();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == backButton) {
			this.page.setRightIndex(0);
			this.page.updateSidePanels();
		} else if (e.getSource() == clear) {
			this.clearInputs();

		}

		else if (e.getActionCommand().equals("Add")) {
			try {
				String input = tableNumbers.getText();
				int[] numbers = Arrays.asList(input.split(",")).stream().map(String::trim).mapToInt(Integer::parseInt)
						.toArray();

				ArrayList<Table> tables = new ArrayList<>();
				ArrayList<TableView> views = new ArrayList<>();
				for (int i : numbers) {
					TableView tv = this.controller.getTableByNumber(i);
					if (tv == null)
						throw new InvalidInputException("one of the tables was not found");
					Table t = tv.getTable();
					tables.add(t);
				}
				Controller.startOrder(tables);
				this.page.updateSidePanels();
			} catch (InvalidInputException exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				exception.printStackTrace();
			}
		}
	}
}
