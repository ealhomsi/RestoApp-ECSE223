package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.BorderUIResource;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;

@SuppressWarnings("serial")
public class ChangeTableLocationPanel extends SidePanel implements ActionListener {
	private JComboBox<Integer> tables;
	private JLabel promptLabel;
	private JLabel newXLabel;
	private JLabel newYLabel;
	private JButton submit;
	private JButton back;
	private JTextField newXField;
	private JTextField newYField;
	private JLabel currentTableLocX;
	private JLabel currentTableLocY;
	private JLabel currentTableWidth;
	private JLabel currentTableHeight;

	public ChangeTableLocationPanel(Controller controller, RestoAppPage p) {
		super(controller, p);

		this.setLayout(null);

		JSeparator sep = new JSeparator();
		sep.setOrientation(SwingConstants.VERTICAL);
		sep.setBounds(0, 112, 2, 525);
		sep.setBackground(Color.BLACK);
		sep.setForeground(Color.BLACK);
		this.add(sep);

		promptLabel = new JLabel("Please enter the new coordiantes (max 750, 400)");
		promptLabel.setBounds(50, 20, 520, 160);
		promptLabel.setFont((new Font("Comic sans MS", Font.PLAIN, 20)));

		newXLabel = new JLabel("newX:");
		newXLabel.setBounds(120, 100, 100, 100);
		newXLabel.setFont((new Font("Comic sans MS", Font.BOLD, 15)));

		newXField = new JTextField();
		newXField.setBounds(200, 135, 50, 30);

		newYLabel = new JLabel("newY:");
		newYLabel.setFont((new Font("Comic sans MS", Font.BOLD, 15)));
		newYLabel.setBounds(120, 160, 100, 100);

		newYField = new JTextField();
		newYField.setBounds(200, 195, 50, 30);

		submit = new JButton("submit");
		submit.setBounds(100, 449, 200, 80);
		submit.addActionListener(this);
		submit.setBackground(Color.white);

		back = new JButton("back");
		back.setBounds(400, 449, 200, 80);
		back.addActionListener(this);
		back.setBackground(Color.white);

		tables = new JComboBox<Integer>();
		tables.setBackground(Color.white);
		tables.setBounds(120, 240, 400, 50);
		tables.addActionListener(this);

		currentTableLocX = new JLabel("X: ");
		currentTableLocY = new JLabel("Y: ");
		currentTableWidth = new JLabel("W: ");
		currentTableHeight = new JLabel("H: ");
		JLabel currentTable = new JLabel("Current Table Data:");
		currentTable.setFont((new Font("Comic sans MS", Font.BOLD, 15)));
		currentTableLocX.setFont((new Font("Comic sans MS", Font.BOLD, 12)));
		currentTableLocY.setFont((new Font("Comic sans MS", Font.BOLD, 12)));
		currentTableWidth.setFont((new Font("Comic sans MS", Font.BOLD, 12)));
		currentTableLocX.setFont((new Font("Comic sans MS", Font.BOLD, 12)));
		currentTable.setBounds(120, 290, 400, 50);
		currentTableLocX.setBounds(120, 330, 400, 50);
		currentTableLocY.setBounds(190, 330, 400, 50);
		currentTableWidth.setBounds(120, 360, 400, 50);
		currentTableHeight.setBounds(190, 360, 400, 50);

		// updateView
		updateView();

		// adding
		this.setBorder(new BorderUIResource.LineBorderUIResource(Color.BLACK));

		this.add(tables);
		this.add(promptLabel);
		this.add(currentTable);
		this.add(newXLabel);
		this.add(newXField);

		this.add(newYLabel);
		this.add(newYField);

		this.add(submit);
		this.add(back);
		this.add(currentTableHeight);
		this.add(currentTableLocX);
		this.add(currentTableLocY);
		this.add(currentTableWidth);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			int newX = Integer.parseInt(newXField.getText());
			int newY = Integer.parseInt(newYField.getText());
			try {
				controller.changeTableLocation((Integer) tables.getSelectedItem(), newX, newY);

				TableView t = controller.getTableByNumber((Integer) tables.getSelectedItem());

				if (t == null)
					throw new RuntimeException("Something is seriously wrong");

				currentTableLocX.setText("X: " + t.getTable().getX());
				currentTableLocY.setText("Y: " + t.getTable().getY());
				currentTableWidth.setText("W: " + t.getTable().getWidth());
				currentTableHeight.setText("H: " + t.getTable().getLength());
				this.page.updateSidePanels();
			} catch (InvalidInputException e2) {
				JOptionPane.showMessageDialog(this, e2.getMessage());
				e2.printStackTrace();
			}
		} else if (e.getSource() == back) {
			this.page.setRightIndex(0);
			this.page.updateSidePanels();
		} else if (e.getSource() == tables) {
			if (tables.getSelectedItem() == null)
				return;
			int tableNumber = (Integer) tables.getSelectedItem();
			TableView t = controller.getTableByNumber(tableNumber);

			if (t == null)
				throw new RuntimeException("Something is seriously wrong");

			currentTableLocX.setText("X: " + t.getTable().getX());
			currentTableLocY.setText("Y: " + t.getTable().getY());
			currentTableWidth.setText("W: " + t.getTable().getWidth());
			currentTableHeight.setText("H: " + t.getTable().getLength());

		}

	}

	@Override
	public void updateView() {

		this.tables.removeAllItems();
		for (Integer item : controller.getAllCurrentTableNumbers()) {
			tables.addItem(item);
		}
		
		// null check => hani
		if (tables.getSelectedItem() == null)
			return;

		int tableNumber = (Integer) tables.getSelectedItem();

		TableView t = controller.getTableByNumber(tableNumber);

		if (t == null)
			throw new RuntimeException("Something is seriously wrong");

		currentTableLocX.setText("X: " + t.getTable().getX());
		currentTableLocY.setText("Y: " + t.getTable().getY());
		currentTableWidth.setText("W: " + t.getTable().getWidth());
		currentTableHeight.setText("H: " + t.getTable().getLength());
		this.newXField.setText("");
		this.newYField.setText("");
		this.revalidate();
		this.repaint();
	}

}
