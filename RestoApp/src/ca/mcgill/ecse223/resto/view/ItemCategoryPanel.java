package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;

@SuppressWarnings("serial")
public class ItemCategoryPanel extends SidePanel {
	
	private ItemCategory aCategory = null;
	static ArrayList<JButton> buttonsList = new ArrayList<JButton>();
	
	public ItemCategoryPanel(Controller c, RestoAppPage p) {
		super(c, p);
		setSize(200,100);
		
		this.setBackground(Color.white);
		GridLayout display = new GridLayout(3,2);
		this.setLayout(display);
		this.setVisible(true);
		this.setEnabled(true);
		display.setVgap(10);
		display.setHgap(10);
		
		
		try {
			ItemCategoryPanel.createItemCategoryPanel(c.getMenuItems(aCategory));
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	/**
	 * Create the panel.
	 * @param <MenuItem>
	 */
	public static void createItemCategoryPanel(ArrayList<MenuItem> selectedCategoryItems)
	{
		buttonsList.clear();
		
		for(MenuItem item : selectedCategoryItems ) {
		    JButton button = new JButton();
		    button.setBackground(Color.WHITE);
		    button.setFont((new Font("Comic Sans MS", Font.PLAIN, 24)));
		    button.setText(item.getName());
		    buttonsList.add(button);
		}		
	}
		
	public void setSelectedCategory(ItemCategory aCategory)
	{
		if(aCategory != null) {
		this.aCategory= aCategory;
		}

	}


	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}
}
