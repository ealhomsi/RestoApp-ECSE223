package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;

@SuppressWarnings("serial")

public class AddTablePanel extends SidePanel implements ActionListener {

	private JButton addButton;
	private JButton backButton;
	private JButton clear;
	private JTextField seatNumber;
	private JTextField tableNumber;
	private JTextField width;
	private JTextField length;
	private JTextField xPos;
	private JTextField yPos;
	private JLabel seatNum;
	private JLabel tableNum;
	private JLabel widthLabel;
	private JLabel lengthLabel;
	private JLabel xLabel;
	private JLabel yLabel;
	private JLabel title;
	private JSeparator divider;
	
	
	public AddTablePanel(Controller c, RestoAppPage p) {
		// TODO Auto-generated constructor stub
		super(c, p);
				
		this.setLayout(null);
		
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		addButton.setBounds(550,650,100,50);
		addButton.setBackground(Color.white);
		addButton.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(addButton);
		
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		backButton.setBounds(150,650,100,50); 
		backButton.setBackground(Color.white);
		backButton.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(backButton);
		
		clear = new JButton("Clear");
		clear.addActionListener(this);
		clear.setBounds(350, 650, 100, 50);
		clear.setBackground(Color.white);
		clear.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(clear);
		
		seatNumber = new JTextField();
		seatNumber.setBounds(250, 200, 300, 50);
		this.add(seatNumber);
		
		tableNumber = new JTextField();
		tableNumber.setBounds(250, 275, 300, 50);
		this.add(tableNumber);
		
		width = new JTextField();
		width.setBounds(250, 350, 300, 50);
		this.add(width);
		
		length = new JTextField();
		length.setBounds(250, 425, 300, 50);
		this.add(length);

		xPos = new JTextField();
		xPos.setBounds(250, 500, 300, 50);
		this.add(xPos);
		
		yPos = new JTextField();
		yPos.setBounds(250, 575, 300, 50);
		this.add(yPos);
		
		seatNum = new JLabel("Number of Seats: ");
		seatNum.setBounds(75, 200, 200, 50);
		seatNum.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(seatNum);
		
		tableNum = new JLabel("Table ID Number: ");
		tableNum.setBounds(75, 275, 200, 50);
		tableNum.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(tableNum);
		
		widthLabel = new JLabel("                Width: ");
		widthLabel.setBounds(75, 350, 200, 50);
		widthLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(widthLabel);
		
		lengthLabel = new JLabel("               Length: ");
		lengthLabel.setBounds(75, 425, 200, 50);
		lengthLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(lengthLabel);
		
		xLabel = new JLabel("          X Position: ");
		xLabel.setBounds(75, 500, 200, 50);
		xLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(xLabel);
		
		yLabel = new JLabel("          Y Position: ");
		yLabel.setBounds(75, 575, 200, 50);
		yLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		this.add(yLabel);
		
		title = new JLabel("    Parameters of New Table");
		title.setBounds(0, 0, 750, 100);
		title.setBackground(Color.decode("#FFCCC2"));
		title.setOpaque(true);
		title.setFont(new Font("Comic sans MS", Font.PLAIN, 50));
		this.add(title);
		
	}
	
	public void clearInputs() {
		tableNumber.setText(null);
		seatNumber.setText(null);
		xPos.setText(null);
		yPos.setText(null);
		width.setText(null);
		length.setText(null);
		this.page.updateSidePanels();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton) {
			int tableNum = Integer.parseInt(tableNumber.getText());
			int seatNum = Integer.parseInt(seatNumber.getText());
			int xCord = Integer.parseInt(xPos.getText());
			int yCord = Integer.parseInt(yPos.getText());
			int wid = Integer.parseInt(width.getText());
			int len = Integer.parseInt(length.getText());
			
			try {
				controller.addTable(tableNum, xCord, yCord, wid, len, seatNum);
				
				TableView t = controller.getTableByNumber(tableNum);
				
				if(t == null)
					throw new RuntimeException("Something is seriously wrong.");
				
				this.clearInputs();
				
				this.page.updateSidePanels();
				
			}
			catch(InvalidInputException e2){
				JOptionPane.showMessageDialog(this, e2.getMessage());
				e2.printStackTrace();
			}
			
		}
		else if(e.getSource() == backButton) {
			this.page.setRightIndex(0);
			this.page.updateSidePanels();
		}
		
		else if(e.getSource() == clear) {
			this.clearInputs();
		}
	}

	@Override
	public void updateView() {

	}
}
