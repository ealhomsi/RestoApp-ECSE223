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
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;

@SuppressWarnings("serial")
public class RemoveMenuItem2 extends SidePanel implements ActionListener{

	private JLabel name;
	private JLabel price;
	private JTextField nameTxt;
	private JTextField priceTxt;
	private JButton change;
	private JButton cancel;
	private MenuItem menuItem;
	private ItemCategory category;
	private JLabel item;
	private JLabel newCategory;
	private JComboBox<String> comboBox;
	
	public void clearTxt() {
		nameTxt.setText(null);
		priceTxt.setText(null);
		this.page.updateSidePanels();
	}
	
	public RemoveMenuItem2(Controller c, RestoAppPage p) {
		super(c, p);
		setLayout(null);
		
		
		item = new JLabel("");
		item.setBounds(250, 200, 300, 100);
		item.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		item.setBackground(Color.white);
		add(item);
		
		
		name = new JLabel("New Name");
		name.setBounds(120, 350, 150, 60);
		name.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		add(name);
		
		price = new JLabel("New price");
		price.setBounds(120, 450, 150, 60);
		price.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		add(price);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(300, 350, 300, 60);
		add(nameTxt);
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		priceTxt.setBounds(300, 450, 300, 60);
		add(priceTxt);
		
		change = new JButton("Submit change");
		change.setBounds(450, 600, 200, 60);
		change.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		change.setBackground(Color.white);
		change.addActionListener(this);
		add(change);
		
		cancel = new JButton("cancel");
		cancel.setBounds(250, 600, 150, 60);
		cancel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		cancel.setBackground(Color.WHITE);
		cancel.addActionListener(this);
		add(cancel);
		
		comboBox = new JComboBox<String> ();
		for(ItemCategory s: ItemCategory.values()) {
			comboBox.addItem(s.toString());
		}
		comboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		comboBox.setBounds(300, 523, 300, 60);
		add(comboBox);
		
		newCategory = new JLabel("New category");
		newCategory.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		newCategory.setBounds(120, 510, 150, 60);
		add(newCategory);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == cancel) {
			this.page.setLeftIndex(1);
			this.page.setRightIndex(9);
			this.page.updateSidePanels();
		}
		
		else if(e.getSource() == change) {
			try {
				String name = nameTxt.getText();
				double price = Double.parseDouble(priceTxt.getText());
				String category = comboBox.getSelectedItem().toString();
				
				Controller.updateMenuItem(menuItem, name, ItemCategory.valueOf(category), price);
				this.page.updateSidePanels();
				this.clearTxt();
				
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "Please enter an integer value");
			} catch (InvalidInputException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void updateView() {
		if (MenuCategoriesPanel.selectedMenu != null) {
			try {
				this.menuItem = this.controller.getPricedMenuItem(MenuCategoriesPanel.selectedMenu).getMenuItem();
				item.setText(this.menuItem.getName().split("-")[0].trim());
				this.item.setText(item.getText());
				
			} catch (Exception e) {
				throw new Error("we are dommed");
			}
			MenuCategoriesPanel.selectedMenu = null;
		}

		this.revalidate();
		this.repaint();
		
	}

}

