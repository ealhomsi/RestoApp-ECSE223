package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ca.mcgill.ecse223.resto.controller.Controller;

public class AddMenuItem extends SidePanel implements ActionListener{
	private JTextField nameTxt;
	private JTextField priceTxt;
	private JComboBox<String> comboBox;

	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public AddMenuItem(Controller c, RestoAppPage p) {
		super(c, p);
		
		setLayout(null);
		setSize(800,1500);
		
		JLabel newMenuLabel = new JLabel("  NEW MENU ITEM");
		newMenuLabel.setBounds(200, 150, 400, 100);
		newMenuLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		newMenuLabel.setBackground(Color.decode("#FFCCC2"));
		newMenuLabel.setOpaque(true);
		add(newMenuLabel);
		
		JLabel name = new JLabel("NAME:");
		name.setBounds(140, 350, 200, 50);
		name.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		add(name);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(350, 350, 300, 50);
		add(nameTxt);
		nameTxt.setColumns(10);
		
		JLabel price = new JLabel("PRICE:");
		price.setBounds(140, 450, 200, 50);
		price.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		add(price);
		
		priceTxt = new JTextField();
		priceTxt.setBounds(350, 450, 300, 50);
		add(priceTxt);
		priceTxt.setColumns(10);
		
		JLabel category = new JLabel("CATEGORY:");
		category.setBounds(140, 550, 200, 50);
		category.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		add(category);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(350, 550, 300, 50);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		comboBox.addItem("Appetizer");
		comboBox.addItem("Main");
		comboBox.addItem("Dessert");
		comboBox.addItem("Alcoholic Beverage");
		comboBox.addItem("Non Alcoholic Beverage");
		add(comboBox);
		comboBox.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		this.comboBox.setSelectedIndex(0);
	}
}
