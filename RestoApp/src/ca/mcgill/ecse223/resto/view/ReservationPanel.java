package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.model.Reservation;

@SuppressWarnings("serial")
public class ReservationPanel extends SidePanel implements ActionListener{
	
	private JTextField nameTextField;
	private JTextField numberOfPersonsTextField;
	private JTextField phoneNumberTextField;
	private JTextField emailTextField;
	private JTextField monthDateField;
	private JTextField dayDateField;
	private JTextField yearDateField;
	private JTextField tablesAssignedField;

	private JLabel reservationLabel;
	private JLabel nameLabel;
	private JLabel numberOfPersonsLabel;
	private JLabel dateLabel;
	private JLabel timeLabel;
	private JLabel phoneNumberLabel;
	private JLabel tablesAssignedLabel;
	private JLabel emailLabel;

	private JTextField hourTextField;
	private JTextField minuteTextField;

	private JButton addBtn;
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
		
		
		reservationLabel = new JLabel("RESERVATION");
		reservationLabel.setBounds(15, 16, 300, 90);
		reservationLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 30));
		add(reservationLabel);
		
		nameLabel = new JLabel("NAME:");
		nameLabel.setBounds(30, 261, 200, 40);
		nameLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		add(nameLabel);
		
		numberOfPersonsLabel = new JLabel("NUMBER OF PERSONS:");
		numberOfPersonsLabel.setBounds(30, 317, 210, 40);
		numberOfPersonsLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		add(numberOfPersonsLabel);
		
		dateLabel = new JLabel("DATE:");
		dateLabel.setBounds(30, 150, 200, 40);
		dateLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		add(dateLabel);
		
		timeLabel= new JLabel("TIME:");
		timeLabel.setBounds(30, 200, 200, 40);
		timeLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		add(timeLabel);
		
		phoneNumberLabel = new JLabel("PHONE NUMBER:");
		phoneNumberLabel.setBounds(30, 377, 200, 40);
		phoneNumberLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		add(phoneNumberLabel);
		
		tablesAssignedLabel = new JLabel("TABLE(S) ASSIGNED:");
		tablesAssignedLabel.setBounds(30, 481, 200, 40);
		tablesAssignedLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		add(tablesAssignedLabel);
		
		emailLabel = new JLabel("EMAIL:");
		emailLabel.setBounds(30, 425, 200, 40);
		emailLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		add(emailLabel);

		//month text field
		monthDateField = new JTextField();
		monthDateField.setBounds(300, 150, 61, 40);
		monthDateField.setColumns(10);
		monthDateField.setText("mm");
		add(monthDateField);

		//name text field
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(300, 261, 250, 40);
		add(nameTextField);

		//numberOfPersons text field
		numberOfPersonsTextField = new JTextField();
		numberOfPersonsTextField.setColumns(10);
		numberOfPersonsTextField.setBounds(300, 317, 250, 40);
		add(numberOfPersonsTextField);

		//phone number text field
		phoneNumberTextField = new JTextField();
		phoneNumberTextField.setColumns(10);
		phoneNumberTextField.setBounds(300, 373, 250, 40);
		add(phoneNumberTextField);

		//email text field
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(300, 433, 250, 40);
		add(emailTextField);

		//date field with day value
		dayDateField = new JTextField();
		dayDateField.setColumns(10);
		dayDateField.setBounds(396, 150, 61, 40);
		dayDateField.setText("dd");
		add(dayDateField);

		//date field with year value
		yearDateField = new JTextField();
		yearDateField.setColumns(10);
		yearDateField.setBounds(489, 150, 61, 40);
		yearDateField.setText("yy");
		add(yearDateField);
		
		//label for slash	(between month and day)
		JLabel firstSlash = new JLabel("/");
		firstSlash.setBounds(370, 140, 30, 50);
		firstSlash.setFont(new Font("Comic sans MS", Font.PLAIN, 40));
		add(firstSlash);

		//label for second slash (between day and year)
		JLabel secondSlash = new JLabel("/");
		secondSlash.setBounds(460, 140, 30, 50);
		secondSlash.setFont(new Font("Comic sans MS", Font.PLAIN, 40));
		add(secondSlash);

		//
		hourTextField = new JTextField();
		hourTextField.setBounds(300, 207, 100, 40);
		//hourCombo.setBackground(Color.white);
		hourTextField.setText("hh");
		add(hourTextField);
		
		minuteTextField = new JTextField();
		minuteTextField.setBounds(426, 207, 100, 40);
		//minuteTextField.setBackground(Color.white);
		minuteTextField.setText("mm");
		add(minuteTextField);
		
		JLabel colonLabel = new JLabel(":");
		colonLabel.setBounds(405, 205, 30, 30);
		colonLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 40));
		add(colonLabel);
		
		tablesAssignedField = new JTextField();
		tablesAssignedField.setColumns(10);
		tablesAssignedField.setBounds(300, 488, 250, 40);
		add(tablesAssignedField);
		
		addBtn = new JButton("ADD RESERVATION");
		addBtn.setBounds(330, 603, 220, 50);
		addBtn.setBackground(Color.white);
		addBtn.setFont(new Font("Comic sans MS", Font.PLAIN, 18));
		add(addBtn);
		
		backBtn = new JButton("BACK");
		backBtn.addActionListener(this);
		backBtn.setBounds(115, 603, 200, 50);
		backBtn.setBackground(Color.white);
		backBtn.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		add(backBtn);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//JButton selectedButton = (JButton) e.getSource();
		if(e.getSource() == addBtn){
			//new Reservation();
			Calendar eventDate = Calendar.getInstance();
			//event.set();

		}
		if(e.getSource() == backBtn) {
			System.out.println("e");
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
