package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RestoAppPage extends JFrame implements ActionListener{
	private JTextField txtSettings;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RestoAppPage frame = new RestoAppPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public RestoAppPage() {
		setSize(1500,800);
		getContentPane().setLayout(null);
		
		JButton btnMenu = new JButton("MENU");
		/*btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MenuCategories newMenuCategories = new MenuCategories();
					newMenuCategories.setVisible(true);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});*/
		btnMenu.setBounds(800, 249, 520, 160);
		btnMenu.setBackground(Color.white);
		btnMenu.setFont(new Font("Comic sans MS", Font.PLAIN, 40));
		getContentPane().add(btnMenu);
		
		JButton btnReserved = new JButton("RESERVED");
		btnReserved.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		btnReserved.setBackground(Color.white);
		btnReserved.setBounds(800, 435, 240, 80);
		getContentPane().add(btnReserved);
		
		JButton btnBill = new JButton("BILL");
		btnBill.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		btnBill.setBackground(Color.white);
		btnBill.setBounds(1080, 435, 240, 80);
		getContentPane().add(btnBill);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.white);
		comboBox.setBounds(920, 587, 400, 50);
		comboBox.addItem("");
		comboBox.addItem("Add tables and its seats");
		comboBox.addItem("Remove tables");
		comboBox.addItem("Update table number and number of seats");
		comboBox.addItem("Change location of a table");
		comboBox.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		getContentPane().add(comboBox);
		
		JSeparator sep = new JSeparator();
		sep.setOrientation(SwingConstants.VERTICAL);
		sep.setBounds(700, 112, 2, 525);
		sep.setBackground(Color.BLACK);
		sep.setForeground(Color.BLACK);
		getContentPane().add(sep);
		
		txtSettings = new JTextField();
		txtSettings.setText("Settings");
		txtSettings.setBackground(null);
		txtSettings.setBorder(null);
		txtSettings.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		txtSettings.setBounds(841, 587, 70, 50);
		getContentPane().add(txtSettings);
		txtSettings.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 702, 728);
		getContentPane().add(panel);
	
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
