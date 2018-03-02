package ca.mcgill.ecse223.resto.view;

import javax.swing.JPanel;
import javax.swing.plaf.BorderUIResource;

import ca.mcgill.ecse223.resto.controller.Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MenuCategoriesPanel extends SidePanel implements ActionListener{
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public MenuCategoriesPanel(Controller c, RestoAppPage p) {
		super(c,p);
		setLayout(null); 
		this.setBorder(new BorderUIResource.LineBorderUIResource(Color.BLACK));
		
		
		JButton btnAppetizer = new JButton("APPETIZERS");
		btnAppetizer.setBounds(220, 300, 200, 90);
		btnAppetizer.setFont(new Font("Comic sans MS", Font.PLAIN, 26));
		btnAppetizer.setBackground(Color.decode("#FF6666"));
		this.add(btnAppetizer);
		
		JButton btnMains = new JButton("MAINS");
		btnMains.setBounds(509, 300, 200, 90);
		btnMains.setFont(new Font("Comic sans MS", Font.PLAIN, 26));
		btnMains.setBackground(Color.decode("#CC66CC"));
		this.add(btnMains);
		
		JButton btnDesserts = new JButton("DESSERTS");
		btnDesserts.setBounds(822, 300, 200, 90);
		btnDesserts.setFont(new Font("Comic sans MS", Font.PLAIN, 26));
		btnDesserts.setBackground(Color.decode("#66FF99"));
		this.add(btnDesserts);
		
		JButton btnAlcohol = new JButton("ALCOHOLIC BEVERAGE");
		btnAlcohol.setBounds(409, 458, 200, 90);
		btnAlcohol.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnAlcohol.setBackground(Color.decode("#FFCC00"));
		this.add(btnAlcohol);
		
		JButton btnNonAlcohol = new JButton("NON ALCOHOLIC BEVERAGE");
		btnNonAlcohol.setBounds(692, 460, 200, 90);
		btnNonAlcohol.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		btnNonAlcohol.setBackground(Color.decode("#33CCFF"));
		this.add(btnNonAlcohol);
		
		JLabel labelMenu = new JLabel("MENU");
		labelMenu.setBounds(150, 163, 300, 100);
		labelMenu.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
		this.add(labelMenu);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(Color.WHITE);
		btnBack.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnBack.setBounds(150, 97, 115, 50);
		this.add(btnBack);
	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
