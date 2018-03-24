package ca.mcgill.ecse223.resto.view;

import javax.swing.plaf.BorderUIResource;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MenuCategoriesPanel extends SidePanel implements ActionListener {
	private ItemCategoryPanel itemPanel;
	private static JScrollPane itemDisplay;
	private HashMap<JButton, ItemCategory> itemCategories = new HashMap<JButton, ItemCategory>();
	private JButton btnBack;

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public MenuCategoriesPanel(Controller c, RestoAppPage p) {
		super(c, p);
		this.setBorder(new BorderUIResource.LineBorderUIResource(Color.BLACK));

		ArrayList<ItemCategory> categories = (ArrayList<ItemCategory>) controller.getItemCategories();

		// creating the buttons and modyfing its appearance
		JButton btnAppetizer = new JButton(categories.get(0).toString().toUpperCase());
		btnAppetizer.setBounds(50, 140, 200, 60);
		btnAppetizer.setFont(new Font("Comic sans MS", Font.PLAIN, 26));
		btnAppetizer.setBackground(Color.decode("#FF6666"));
		btnAppetizer.addActionListener(this);
		itemCategories.put(btnAppetizer, categories.get(0));

		JButton btnMains = new JButton(categories.get(1).toString().toUpperCase());
		btnMains.setBounds(270, 140, 200, 60);
		btnMains.setFont(new Font("Comic sans MS", Font.PLAIN, 26));
		btnMains.setBackground(Color.decode("#CC66CC"));
		btnMains.addActionListener(this);
		itemCategories.put(btnMains, categories.get(1));

		JButton btnDesserts = new JButton(categories.get(2).toString().toUpperCase());
		btnDesserts.setBounds(490, 140, 200, 60);
		btnDesserts.setFont(new Font("Comic sans MS", Font.PLAIN, 26));
		btnDesserts.setBackground(Color.decode("#66FF99"));
		btnDesserts.addActionListener(this);
		itemCategories.put(btnDesserts, categories.get(2));

		JButton btnAlcohol = new JButton(categories.get(3).toString().toUpperCase());
		btnAlcohol.setText("<html>ALCOHOLIC<BR />BEVERAGE</HTML>");
		btnAlcohol.setBounds(125, 220, 200, 60);
		btnAlcohol.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		btnAlcohol.setBackground(Color.decode("#FFCC00"));
		btnAlcohol.addActionListener(this);
		itemCategories.put(btnAlcohol, categories.get(3));

		JButton btnNonAlcohol = new JButton(categories.get(4).toString().toUpperCase());
		btnNonAlcohol.setText("<html>NON ALCOHOLIC<br />BEVERAGE</html>");
		btnNonAlcohol.setBounds(350, 220, 200, 60);
		btnNonAlcohol.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		btnNonAlcohol.setBackground(Color.decode("#33CCFF"));
		btnNonAlcohol.addActionListener(this);
		itemCategories.put(btnNonAlcohol, categories.get(4));

		JLabel labelMenu = new JLabel("MENU");
		labelMenu.setBounds(25, 30, 300, 100);
		labelMenu.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

		btnBack = new JButton("Back");
		btnBack.setBackground(Color.WHITE);
		btnBack.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnBack.setBounds(25, 600, 115, 50);
		btnBack.addActionListener(this);

		// creating a panel to display the items of a category
		itemPanel = new ItemCategoryPanel(c, p);
		itemDisplay = new JScrollPane(itemPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		itemDisplay.setViewportView(itemPanel);
		itemDisplay.setBorder(new BorderUIResource.LineBorderUIResource(Color.BLACK));

		// adding the buttons to a Group Layout
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(labelMenu)
				.addGroup(layout.createSequentialGroup().addComponent(btnAppetizer, 240, 240, 400)
						.addComponent(btnMains).addComponent(btnDesserts))
				.addGroup(layout.createSequentialGroup().addComponent(btnAlcohol).addComponent(btnNonAlcohol))
				.addComponent(itemDisplay, 500, 700, 700).addComponent(btnBack));
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(labelMenu)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(btnAppetizer)
						.addComponent(btnMains).addComponent(btnDesserts))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(btnAlcohol)
						.addComponent(btnNonAlcohol))
				.addComponent(itemDisplay, 500, 700, 700).addComponent(btnBack));
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { btnAppetizer, btnMains, btnDesserts });
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { btnAlcohol, btnNonAlcohol });

	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		itemDisplay.setViewportView(itemPanel);
		itemDisplay.setVisible(true);
		itemDisplay.validate();
		this.revalidate();
		this.repaint();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ArrayList<MenuItem> selectedCategoryItems = new ArrayList<MenuItem>();
		JButton selectedButton = (JButton) e.getSource();
		ItemCategory selectedCategory = itemCategories.get(selectedButton);
		try {
			selectedCategoryItems = (ArrayList<MenuItem>) controller.getMenuItems(selectedCategory);
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (selectedButton == btnBack) {
			this.page.setRightIndex(0);
			this.page.updateSidePanels();
		} else {
			itemPanel.setSelectedCategory(selectedCategory);
			itemPanel.createItemCategoryPanel(selectedCategoryItems);
			itemPanel.removeAll();
			for (JButton button : ItemCategoryPanel.buttonsList) {
				itemPanel.add(button);
			}
			updateView();
		}
	}
}
