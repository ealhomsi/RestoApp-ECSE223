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
		btnReserved.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		btnReserved.setBackground(Color.white);
		btnReserved.setBounds(100, 435, 240, 80);
		btnReserved.addActionListener(this);
		this.add(btnReserved);

		JButton btnBill = new JButton("BILL");
		btnBill.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		btnBill.setBackground(Color.white);
		btnBill.setBounds(380, 435, 240, 80);
		this.add(btnBill);

		comboBox = new JComboBox<String>();
		comboBox.setBackground(Color.white);
		comboBox.setBounds(200, 587, 400, 50);
		comboBox.addItem("");
		comboBox.addItem("Add Tables");
		comboBox.addItem("Remove Tables");
		comboBox.addItem("Update Table");
		comboBox.addItem("Change Table Location");
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
	// if the source is combo box
//		System.out.println(e.getActionCommand());
		if (e.getSource() == comboBox) {
			int option = comboBox.getSelectedIndex();
			if(option == 0)
				return;
			this.page.setRightIndex(++option);
			this.page.updateSidePanels();
		} else if(e.getSource() == btnMenu) {
			this.page.setRightIndex(6);
			this.page.updateSidePanels();
		}
		
		if(e.getActionCommand().equals("RESERVATION")){
//			System.out.print("hello");
			this.page.setRightIndex(8);
			this.page.updateSidePanels();
		}
	}

}
