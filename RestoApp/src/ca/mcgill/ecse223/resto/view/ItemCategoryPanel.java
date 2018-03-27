package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.PricedMenuItem;

@SuppressWarnings("serial")
public class ItemCategoryPanel extends SidePanel implements ActionListener {
	private OrderItemPanel orderPanel = null;
	private ItemCategory aCategory = null;
	static ArrayList<JButton> buttonsList = new ArrayList<JButton>();

	public ItemCategoryPanel(Controller c, RestoAppPage p) {
		super(c, p);
		setSize(200, 100);
		this.orderPanel = (OrderItemPanel) this.page.getSidePanel(11);
		this.setBackground(Color.white);
		GridLayout display = new GridLayout(3, 2);
		this.setLayout(display);
		this.setVisible(true);
		this.setEnabled(true);
		display.setVgap(10);
		display.setHgap(10);

		try {
			this.createItemCategoryPanel(c.getMenuItems(aCategory));
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Create the panel.
	 * 
	 * @param <MenuItem>
	 */
	public void createItemCategoryPanel(ArrayList<MenuItem> selectedCategoryItems) {
		buttonsList.clear();
		for (MenuItem item : selectedCategoryItems) {
			JButton button = new JButton();
			button.setBackground(Color.WHITE);
			button.setFont((new Font("Comic Sans MS", Font.PLAIN, 24)));
			button.setText(item.getName() + " - $" + item.getCurrentPricedMenuItem().getPrice());
			buttonsList.add(button);
			button.addActionListener(this);
		}
	}

	public void setSelectedCategory(ItemCategory aCategory) {
		if (aCategory != null) {
			this.aCategory = aCategory;
		}

	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton b = (JButton) arg0.getSource();

		if (buttonsList.contains(b)) {
			try {
				PricedMenuItem p = this.controller.getPricedMenuItem(b.getText().split("@")[0]);
				this.orderPanel = (OrderItemPanel) this.page.getSidePanel(11);
				this.orderPanel.setPricedMenuItem(p);
				this.page.setRightIndex(11);
				this.page.updateSidePanels();
			} catch (InvalidInputException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
				e.printStackTrace();
			}
		}

	}
}
