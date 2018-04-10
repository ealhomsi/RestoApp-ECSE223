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
import ca.mcgill.ecse223.resto.model.PricedMenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;

@SuppressWarnings("serial")
public class AddMenuItem extends SidePanel implements ActionListener{
	private JTextField nameTxt;
	private JTextField priceTxt;
	private JComboBox<String> comboBox;
	private JButton back;
	private JButton add;
	private ItemCategory itemCategory;
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void clearTxt() {
		nameTxt.setText(null);
		priceTxt.setText(null);
		this.page.updateSidePanels();
	}
	
	public ItemCategory getItemCategory()
	  {
	    return itemCategory;
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
		
		back = new JButton("Back");
		back.setBounds(300, 650, 147, 50);
		back.setBackground(Color.WHITE);
		back.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		add(back);
		back.addActionListener(this);
		
		add = new JButton("Add Item");
		add.setBounds(500, 650, 147, 50);
		add.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		add.setBackground(Color.WHITE);
		add(add);
		add.addActionListener(this);
		
		
		comboBox = new JComboBox<String> ();
		for(ItemCategory s: ItemCategory.values()) {
			comboBox.addItem(s.toString());
		}
		comboBox.setBounds(350, 550, 300, 50);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		add(comboBox);
		comboBox.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == back) {
			this.page.setRightIndex(9);
			this.page.updateSidePanels();
		}

		else if(e.getSource() == add) {
			try {
				String name = nameTxt.getText();
				double price = Double.parseDouble(priceTxt.getText());
				String category = comboBox.getSelectedItem().toString();
				
				Controller.addMenuItem(name, ItemCategory.valueOf(category), price);
				this.clearTxt();
				page.updateSidePanels();
				
			}catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "Please enter an integer value");
			} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, e2.getMessage());
			}
		}
	}

	@Override
	public void updateView() {
		
	}
}
