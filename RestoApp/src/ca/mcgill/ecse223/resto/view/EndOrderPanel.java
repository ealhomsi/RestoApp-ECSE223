package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.Table;

@SuppressWarnings("serial")

public class EndOrderPanel extends SidePanel implements ActionListener {

	private JButton SubmitButton;
	private JButton backButton;
	private JComboBox<OrderView> orders;
	private JLabel promptLabel;
	private JComboBox<String> email;

	public EndOrderPanel(Controller c, RestoAppPage p) {
		// TODO Auto-generated constructor stub
		super(c, p);

		this.setLayout(null);
		email = new JComboBox<>();
		email.setBounds(120, 350, 400, 50);
		email.setBackground(Color.white);
		email.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(email);

		SubmitButton = new JButton("End Order");
		SubmitButton.setBounds(330, 650, 200, 50);
		SubmitButton.setBackground(Color.white);
		SubmitButton.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(SubmitButton);
		SubmitButton.addActionListener(this);

		backButton = new JButton("Back");
		backButton.setBounds(200, 650, 100, 50);
		backButton.setBackground(Color.white);
		backButton.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		backButton.addActionListener(this);
		this.add(backButton);
		backButton.addActionListener(this);

		promptLabel = new JLabel("Select which order to end");
		promptLabel.setBounds(50, 20, 520, 160);
		promptLabel.setFont((new Font("Comic sans MS", Font.PLAIN, 20)));
		this.add(promptLabel);

		orders = new JComboBox<>();
		orders.setBackground(Color.white);
		orders.setBounds(120, 240, 400, 50);
		orders.addActionListener(this);
		this.add(orders);
	}

	@Override
	public void updateView() {
		if (this.page.getRightIndex() != 7)
			return;
		this.orders.removeAllItems();
		for (OrderView t : controller.getAllCurrentOrders()) {
			orders.addItem(t);
		}
		this.email.removeAllItems();
		this.email.addItem("Assign it to none");
		for(String item: Controller.getAListOfAllEmails())
			email.addItem(item);

		this.revalidate();
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// git test comment
		if (e.getSource() == backButton) {
			this.page.setRightIndex(0);
			this.page.updateSidePanels();
		} else if (e.getSource() == SubmitButton) {
			String emailAddress = (String) email.getSelectedItem();
			OrderView ov = (OrderView) this.orders.getSelectedItem();
			Order o = ov.getOrder();
			try {
				if(this.email.getSelectedIndex() == 0) {
					Controller.endOrder(o);
				}else {
					Controller.endOrder(o, emailAddress);
				}
				
				this.page.updateSidePanels();
			} catch (InvalidInputException exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				exception.printStackTrace();
			}
		}

	}
}
