package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.PricedMenuItem;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;

@SuppressWarnings("serial")
public class OrderItemPanel extends SidePanel implements ActionListener {
	private JLabel promptQuantity;
	private JLabel promptSeats;
	private JTextField quantity;
	private JTextField seats;
	private PricedMenuItem pricedMenuItem;
	private JButton submit;
	private JButton back;
	private JComboBox<OrderView> orders;

	public PricedMenuItem getPricedMenuItem() {
		return pricedMenuItem;
	}

	public void setPricedMenuItem(PricedMenuItem pricedMenuItem) {
		this.pricedMenuItem = pricedMenuItem;
		this.updateView();
	}

	public OrderItemPanel(Controller controller, RestoAppPage p) {
		super(controller, p);

		this.setLayout(null);

		JSeparator sep = new JSeparator();
		sep.setOrientation(SwingConstants.VERTICAL);
		sep.setBounds(0, 112, 2, 525);
		sep.setBackground(Color.BLACK);
		sep.setForeground(Color.BLACK);
		this.add(sep);

		promptQuantity = new JLabel("");
		promptQuantity.setBounds(120, 100, 200, 100);
		promptQuantity.setFont((new Font("Comic sans MS", Font.BOLD, 15)));
		this.add(promptQuantity);

		quantity = new JTextField();
		quantity.setBounds(300, 135, 50, 30);
		this.add(quantity);

		submit = new JButton("submit");
		submit.setBounds(100, 449, 200, 80);
		submit.addActionListener(this);
		submit.setBackground(Color.white);
		this.add(submit);

		back = new JButton("back");
		back.setBounds(400, 449, 200, 80);
		back.addActionListener(this);
		back.setBackground(Color.white);
		this.add(back);

		orders = new JComboBox<>();
		orders.setBackground(Color.white);
		orders.setBounds(120, 200, 400, 50);
		orders.addActionListener(this);
		this.add(orders);

		promptSeats = new JLabel("Enter which Seats format: #Table:#Seat, #Table:#Seat");
		promptSeats.setBounds(120, 245, 600, 100);
		promptSeats.setFont((new Font("Comic sans MS", Font.BOLD, 15)));
		this.add(promptSeats);
		seats = new JTextField("");
		seats.setBounds(120, 340, 500, 30);
		this.add(seats);

		// updateView
		updateView();

		// adding
		this.setBorder(new BorderUIResource.LineBorderUIResource(Color.BLACK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			// grab order
			OrderView ov = (OrderView) orders.getSelectedItem();
			Order o = ov.getOrder();

			// grab quantity
			int quantity = Integer.parseInt(this.quantity.getText());

			// grab seats and tables
			String input = seats.getText();
			input = input.trim();
			String[] seatTableCombination = input.split(",");
			ArrayList<Table> tables = new ArrayList<>();
			ArrayList<Seat> seats = new ArrayList<>();
			for (String item : seatTableCombination) {
				int tableNumber = Integer.parseInt(item.split(":")[0].trim());
				int seatNumber = Integer.parseInt(item.split(":")[1].trim());

				Table t = this.controller.getTableByNumber(tableNumber).getTable();
				tables.add(t);
				seats.add(t.getCurrentSeat(seatNumber));
			}

			try {
				this.controller.orderItem(quantity, o, seats, this.pricedMenuItem);
				this.page.updateSidePanels();
			} catch (InvalidInputException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			}
		} else if (e.getSource() == back) {
			this.page.setRightIndex(9);
			this.page.updateSidePanels();

		}
	}

	@Override
	public void updateView() {
		if (pricedMenuItem != null)
			this.promptQuantity.setText(this.pricedMenuItem.getMenuItem().getName() + " Quantity");

		this.orders.removeAllItems();
		for (OrderView t : controller.getAllCurrentOrders()) {
			orders.addItem(t);
		}

		this.revalidate();
		this.repaint();
	}

}
