package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ca.mcgill.ecse223.resto.controller.Controller;

@SuppressWarnings("serial")
public class ReservationPanel extends SidePanel implements ActionListener{
	
	private JTextField nameTextField;
	private JTextField dateTextField;
	private JTextField timeTextField;
	private JTextField phoneNumberTextField;
	private JTextField tableAssignedTextField;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton backBtn;
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public ReservationPanel(Controller c, RestoAppPage p) {
		super(c,p);

		setLayout(null);
		
		
		JLabel reservationLabel = new JLabel("RESERVATION");
		reservationLabel.setBounds(15, 16, 300, 90);
		reservationLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 30));
		add(reservationLabel);
		
		JLabel label = new JLabel("NAME:");
		label.setBounds(30, 261, 200, 40);
		label.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		add(label);
		
		JLabel label_1 = new JLabel("NUMBER OF PERSONS:");
		label_1.setBounds(30, 317, 210, 40);
		label_1.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		add(label_1);
		
		JLabel label_2 = new JLabel("DATE :");
		label_2.setBounds(30, 150, 200, 40);
		label_2.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		add(label_2);
		
		JLabel label_3 = new JLabel("TIME:");
		label_3.setBounds(30, 200, 200, 40);
		label_3.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		add(label_3);
		
		JLabel label_4 = new JLabel("PHONE NUMBER:");
		label_4.setBounds(30, 377, 200, 40);
		label_4.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		add(label_4);
		
		JLabel label_5 = new JLabel("TABLE(S) ASSIGNED:");
		label_5.setBounds(30, 481, 200, 40);
		label_5.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		add(label_5);
		
		JLabel label_6 = new JLabel("EMAIL:");
		label_6.setBounds(30, 425, 200, 40);
		label_6.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		add(label_6);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(300, 150, 61, 40);
		add(nameTextField);
		nameTextField.setColumns(10);
		
		dateTextField = new JTextField();
		dateTextField.setColumns(10);
		dateTextField.setBounds(300, 261, 250, 40);
		add(dateTextField);
		
		timeTextField = new JTextField();
		timeTextField.setColumns(10);
		timeTextField.setBounds(300, 317, 250, 40);
		add(timeTextField);
		
		phoneNumberTextField = new JTextField();
		phoneNumberTextField.setColumns(10);
		phoneNumberTextField.setBounds(300, 373, 250, 40);
		add(phoneNumberTextField);
		
		tableAssignedTextField = new JTextField();
		tableAssignedTextField.setColumns(10);
		tableAssignedTextField.setBounds(300, 433, 250, 40);
		add(tableAssignedTextField);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(396, 150, 61, 40);
		add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(489, 150, 61, 40);
		add(textField_1);
		
		JLabel lbl1 = new JLabel("/");
		lbl1.setBounds(370, 140, 30, 50);
		lbl1.setFont(new Font("Comic sans MS", Font.PLAIN, 40));
		add(lbl1);
		
		JLabel lbl2 = new JLabel("/");
		lbl2.setBounds(460, 140, 30, 50);
		lbl2.setFont(new Font("Comic sans MS", Font.PLAIN, 40));
		add(lbl2);
		
		JComboBox hourCombo = new JComboBox();
		hourCombo.setBounds(300, 207, 100, 40);
		hourCombo.setBackground(Color.white);
		add(hourCombo);
		
		JComboBox minCombo = new JComboBox();
		minCombo.setBounds(426, 207, 100, 40);
		minCombo.setBackground(Color.white);
		add(minCombo);
		
		JLabel lbl3 = new JLabel(":");
		lbl3.setBounds(405, 205, 30, 30);
		lbl3.setFont(new Font("Comic sans MS", Font.PLAIN, 40));
		add(lbl3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(300, 488, 250, 40);
		add(textField_2);
		
		JButton addBtn = new JButton("ADD RESERVATION");
		addBtn.setBounds(330, 603, 220, 50);
		addBtn.setBackground(Color.white);
		addBtn.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		add(addBtn);
		
		JButton backBtn = new JButton("BACK");
		backBtn.setBounds(115, 603, 200, 50);
		backBtn.setBackground(Color.white);
		backBtn.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		add(backBtn);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton selectedButton = (JButton) e.getSource();
		
		if(selectedButton == backBtn) {
			this.page.setRightIndex(0);
			this.page.updateSidePanels();
		}	
		updateView();
	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}
	

	
}
