package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
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
		btnMenu.addActionListener(this);;

		JButton btnReserved = new JButton("RESERVED");
		btnReserved.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		btnReserved.setBackground(Color.white);
		btnReserved.setBounds(100, 435, 240, 80);
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
		comboBox.addItem("Add tables and its seats");
		comboBox.addItem("Remove tables");
		comboBox.addItem("Update table number and number of seats");
		comboBox.addItem("Change location of a table");
		comboBox.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
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
		
	//if the source is combobox
		if (e.getSource() == comboBox) {
			int option = comboBox.getSelectedIndex();
			this.page.setRightIndex(--option);
			this.page.updateSidePanels();
		}
		if (e.getSource() == btnMenu) {
			
			this.setVisible(false);
			MenuCategoriesPanel newMenuCat = new MenuCategoriesPanel(controller, page);
			newMenuCat.setVisible(true);
			this.page.updateSidePanels();
			
			        /*this.setVisible(false);
			        new MenuCategoriesPanel(c, page).setVisible(true);
			        this.page.updateSidePanels();
*/			    }
	}

}
