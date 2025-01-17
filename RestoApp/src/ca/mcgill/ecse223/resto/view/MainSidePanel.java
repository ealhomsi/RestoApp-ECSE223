package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ca.mcgill.ecse223.resto.controller.Controller;

@SuppressWarnings("serial")
public class MainSidePanel extends SidePanel implements ActionListener {
	private JButton btnMenu;
	private JTextField txtSettings;
	private JComboBox<String> comboBox;
	private JButton btnStartOrder;
	private JButton btnEndOrder;
	private JButton btnLoyaltyCard;

	public MainSidePanel(Controller c, RestoAppPage p) {
		super(c, p);

		this.setLayout(null);

		btnMenu = new JButton("MENU");
		btnMenu.setBounds(100, 249, 520, 160);
		btnMenu.setBackground(Color.white);
		btnMenu.setFont(new Font("Comic sans MS", Font.PLAIN, 40));
		this.add(btnMenu);
		btnMenu.addActionListener(this);

		JButton btnReserved = new JButton("RESERVATION");
		btnReserved.setFont(new Font("Comic sans MS", Font.PLAIN, 17));
		btnReserved.setBackground(Color.white);
		btnReserved.setBounds(100, 435, 173, 80);
		btnReserved.addActionListener(this);
		this.add(btnReserved);

		btnStartOrder = new JButton("START ORDER");
		btnStartOrder.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		btnStartOrder.setBackground(Color.white);
		btnStartOrder.setBounds(100, 135, 240, 80);
		btnStartOrder.addActionListener(this);
		this.add(btnStartOrder);

		JButton btnBill = new JButton("BILL");
		btnBill.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		btnBill.setBackground(Color.white);
		btnBill.setBounds(275, 435, 173, 80);
		btnBill.addActionListener(this);
		this.add(btnBill);

		btnEndOrder = new JButton("END ORDER");
		btnEndOrder.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		btnEndOrder.setBackground(Color.white);
		btnEndOrder.setBounds(380, 135, 240, 80);
		this.add(btnEndOrder);
		btnEndOrder.addActionListener(this);

		btnLoyaltyCard = new JButton("LOYALTY CARD");
		btnLoyaltyCard.setBounds(450, 435, 173, 80);
		btnLoyaltyCard.setBackground(Color.white);
		btnLoyaltyCard.setFont(new Font("Comic sans MS", Font.PLAIN, 17));
		this.add(btnLoyaltyCard);
		btnLoyaltyCard.addActionListener(this);

		comboBox = new JComboBox<String>();
		comboBox.setBackground(Color.white);
		comboBox.setBounds(200, 587, 400, 50);
		comboBox.addItem("");
		comboBox.addItem("Add Tables");
		comboBox.addItem("Remove Tables");
		comboBox.addItem("Update Table");
		comboBox.addItem("Change Table Location");
		comboBox.addItem("View Orders");
		comboBox.addItem("Cancel Orders/OrderItems");
		comboBox.setFont(new Font("Comiec sans MS", Font.PLAIN, 20));
		this.add(comboBox);
		comboBox.addActionListener(this);

		JSeparator sep = new JSeparator();
		sep.setOrientation(SwingConstants.VERTICAL);
		sep.setBounds(0, 112, 2, 525);
		sep.setBackground(Color.BLACK);
		sep.setForeground(Color.BLACK);
		this.add(sep);

		txtSettings = new JTextField();
		txtSettings.setEditable(false);
		txtSettings.setText("Settings");
		txtSettings.setBackground(null);
		txtSettings.setBorder(null);
		txtSettings.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		txtSettings.setBounds(100, 587, 70, 50);
		this.add(txtSettings);
		txtSettings.setColumns(10);
	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		this.comboBox.setSelectedIndex(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == comboBox) {
			int option = comboBox.getSelectedIndex();
			if (option == 0)
				return;
			else if (option == 5) {
				this.page.setRightIndex(12);
				this.page.updateSidePanels();
			} else if (option == 6) {
				this.page.setRightIndex(20);
				this.page.updateSidePanels();
			} else {
				this.page.setRightIndex(++option);
				this.page.updateSidePanels();
			}
		} else if (e.getSource() == btnMenu) {
			this.page.setRightIndex(9);
			this.page.updateSidePanels();
		} else if (e.getSource() == btnStartOrder) {
			this.page.setRightIndex(6);
			this.page.updateSidePanels();
		} else if (e.getSource() == btnEndOrder) {
			this.page.setRightIndex(7);
			this.page.updateSidePanels();
		} else if (e.getActionCommand().equals("RESERVATION")) {
			this.page.setRightIndex(10);
			this.page.updateSidePanels();
		} else if (e.getSource() == btnLoyaltyCard) {
			this.page.setRightIndex(16);
			this.page.updateSidePanels();
		} else if (e.getActionCommand().equals("BILL")) {
			this.page.setRightIndex(19);
			this.page.updateSidePanels();
		}

	}
}
