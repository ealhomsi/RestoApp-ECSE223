package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.OrderItem;
import ca.mcgill.ecse223.resto.model.Table;

@SuppressWarnings("serial")

public class CancelOrderPanel extends SidePanel implements ActionListener {
	private JComboBox<OrderView> orders;
	private JComboBox<OrderItemView> orderItems;
	private JButton CancelOrder;
	private JButton CancelItem;
	private JButton back;
	private JLabel title;
	private JLabel orderLabel;
	private JLabel orderItemLabel;
	private Controller controller;

	public CancelOrderPanel(Controller c, RestoAppPage p) {
		super(c, p);
		controller = c;

		this.setLayout(null);

		orderLabel = new JLabel("Existing Orders");
		orderLabel.setBackground(Color.white);
		orderLabel.setBounds(100, 200, 800, 50);
		orderLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(orderLabel);

		orderItemLabel = new JLabel("OrderItems In Selected Order");
		orderItemLabel.setBackground(Color.white);
		orderItemLabel.setBounds(100, 350, 800, 50);
		orderItemLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(orderItemLabel);

		title = new JLabel("Cancel Order or OrderItem");
		title.setBackground(Color.white);
		title.setBounds(20, 20, 800, 50);
		title.setFont(new Font("Comic sans MS", Font.PLAIN, 50));
		this.add(title);

		orders = new JComboBox<OrderView>();
		orders.setBackground(Color.white);
		orders.setBounds(100, 250, 400, 50);
		orders.addActionListener(this);
		orders.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				orderItems.removeAllItems();
				int index = orders.getSelectedIndex();
				OrderView selectedOrderView;
				try {
					if (controller.getAllCurrentOrders().size() != 0) {
						selectedOrderView = controller.getAllCurrentOrders().get(index);
						Order selectedOrder = selectedOrderView.getOrder();
						List<OrderItem> OrderItems = selectedOrder.getOrderItems();
						for (OrderItem t : OrderItems) {
							orderItems.addItem(new OrderItemView(t));
						}
					}
				} catch (Exception a) {
					return;
				}

			}
		});
		this.add(orders);

		orderItems = new JComboBox<OrderItemView>();
		orderItems.setBackground(Color.white);
		orderItems.setBounds(100, 400, 400, 50);
		orderItems.addActionListener(this);
		this.add(orderItems);

		CancelOrder = new JButton("Cancel Order");
		CancelOrder.setBounds(500, 650, 200, 50);
		CancelOrder.setBackground(Color.white);
		CancelOrder.setFont(new Font("Comic sans MS", Font.PLAIN, 15));
		this.add(CancelOrder);
		CancelOrder.addActionListener(this);

		CancelItem = new JButton("Cancel OrderItem");
		CancelItem.setBounds(275, 650, 200, 50);
		CancelItem.setBackground(Color.white);
		CancelItem.setFont(new Font("Comic sans MS", Font.PLAIN, 15));
		this.add(CancelItem);
		CancelItem.addActionListener(this);

		back = new JButton("Back");
		back.setBounds(50, 650, 200, 50);
		back.setBackground(Color.white);
		back.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(back);
		back.addActionListener(this);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == back) {
			this.page.setRightIndex(0);
			this.page.updateSidePanels();
		}

		if (e.getSource() == CancelOrder) {
			int index = orders.getSelectedIndex();
			Order selectedOrder;
			try {
				selectedOrder = controller.getAllCurrentOrders().get(index).getOrder();
				List<Table> tables = selectedOrder.getTables();
				for (Table t : tables) {
					controller.cancelOrder(t);
				}
			} catch (Exception a) {
				JOptionPane.showMessageDialog(this, "error : " + a.getMessage());
				a.printStackTrace();
				return;
			}

		}

		if (e.getSource() == CancelItem) {

			int index = orders.getSelectedIndex();
			Order selectedOrder;
			try {
				selectedOrder = controller.getAllCurrentOrders().get(index).getOrder();

			} catch (Exception a) {
				JOptionPane.showMessageDialog(this, "error : " + a.getMessage());
//				a.printStackTrace();
				return;
			}

			int index2 = orderItems.getSelectedIndex();
			OrderItem selectedOrderItem;

			try {
				selectedOrderItem = selectedOrder.getOrderItem(index2);
				controller.cancelOrderItem(selectedOrderItem);
			} catch (Exception a1) {
				JOptionPane.showMessageDialog(this, "error : " + a1.getMessage());
//				a1.printStackTrace();
				return;

			}
		}
//		this.page.updateSidePanels();
	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		this.orders.removeAllItems();
		this.orderItems.removeAllItems();

		for (OrderView t : controller.getAllCurrentOrders()) {
			orders.addItem(t);
		}

		orders.revalidate();
		orders.repaint();
		this.revalidate();
		this.repaint();
	}

}
