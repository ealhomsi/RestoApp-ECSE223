package ca.mcgill.ecse223.resto.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.model.OrderItem;

import javax.swing.*;

public class ViewOrderPanel extends SidePanel implements ActionListener {

	private final String htmlOpeningTag = "<html><p>";
	private final String htmlClosingTag = "</p></html>";
	private JComboBox<Integer> currentTables;
	private JScrollPane listOfOrderItems;
	private JPanel embeddedPanel;
	private JLabel titlePage;
	private JLabel comboBoxLabel;
	private GridLayout layout;
	private JButton back;
	private JButton submit;

	public ViewOrderPanel(Controller controller, RestoAppPage page) {
		super(controller, page);
		this.setLayout(null);

		// variable initialisations
		currentTables = new JComboBox<>();
		titlePage = new JLabel("Table Orders", SwingConstants.CENTER);
		// embeddedPanel = new JPanel();
		// listOfOrderItems = new JScrollPane();
		comboBoxLabel = new JLabel("select table");
		back = new JButton("back");
		submit = new JButton("submit");

		// titlePage JLabel properties
		titlePage.setBounds(0, 0, 750, 50);
		titlePage.setFont(new Font("Comic sans MS", Font.BOLD, 50));
		titlePage.setForeground(new Color(0, 255, 127));

		// currentTables combo box properties
		for (Integer tableId : controller.getAllCurrentTableNumbers()) {
			currentTables.addItem(tableId);
		}

		comboBoxLabel.setBounds(750 / 2 - 50, 800 - 150, 100, 50);
		currentTables.setBounds(750 / 2 - 50, 800 - 100, 100, 50);

		// back and submit JButton properties
		back.setBounds(750 / 2 - 200, 800 - 100, 100, 50);
		submit.setBounds(750 / 2 + 100, 800 - 100, 100, 50);
		back.addActionListener(this);
		submit.addActionListener(this);

		this.add(currentTables);
		this.add(titlePage);
		this.add(back);
		this.add(submit);

	}

	public void updateView() {
		currentTables.removeAllItems();

		// adds every table ID to the combo box
		for (Integer tableId : controller.getAllCurrentTableNumbers()) {
			currentTables.addItem(tableId);
		}
		revalidate();
		repaint();

	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == back) {
			if (listOfOrderItems != null)
				this.remove(listOfOrderItems);
			page.setRightIndex(0);
			page.updateSidePanels();
		} else if (event.getSource() == submit) {
			// if selected item is null, do nothing
			if (currentTables.getSelectedItem() == null) {
				return;
			}
			try {
				if (listOfOrderItems != null)
					this.remove(listOfOrderItems);
				// get the selected integer from the combo box -> retrieve the table from the
				// hashmap -> retrieve orderItems from table
				Integer tableId = (Integer) currentTables.getSelectedItem();
				Table table = Table.getWithNumber(tableId);
				List<OrderItem> orderItems = controller.getOrderItems(table);

				// calculate the number of rows with constant column size of 4. Gridlayout used
				// with JScrollPane
				int numberOfRows = orderItems.size() / 4 + 1;
				layout = new GridLayout(numberOfRows, 4);
				embeddedPanel = new JPanel(layout);
				JLabel[] labels = new JLabel[orderItems.size()];

				// create a JLabel for every OrderItem and add it to the grid layout
				int index = 0;
				for (OrderItem orderItem : orderItems) {
					System.out.println(orderItem);
					JLabel label = new JLabel(htmlOpeningTag + "Quantity: " + orderItem.getQuantity() + "<br>Price: "
							+ orderItem.getPricedMenuItem().getPrice() + "<br>Menu Item: "
							+ orderItem.getPricedMenuItem().getMenuItem().getName() + htmlClosingTag);
					labels[index++] = label;
				}
				for (JLabel label : labels) {
					embeddedPanel.add(label);
				}
				listOfOrderItems = new JScrollPane(embeddedPanel);
				listOfOrderItems.setBounds(25, 100, 700, 800 - 250);
				this.add(listOfOrderItems);

			} catch (InvalidInputException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
				ex.printStackTrace();
			}
			this.repaint();
			this.revalidate();

		}
	}
}
