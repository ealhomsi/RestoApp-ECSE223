package ca.mcgill.ecse223.resto.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.LoyaltyCard;

public class LoyaltyCardDisplay extends SidePanel implements ActionListener {
	private static JScrollPane cardListScroll;
	private DefaultTableModel cardListFrameView;
	private JTable cardListView;
	private JButton backButton;
	private JTextField enterClientName;
	private JTextField enterPhone;
	private JTextField enterEmail;
	private JTextField removeCard;
	private JButton submitRegistrationButton;
	private JButton submitRemovalButton;

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public LoyaltyCardDisplay(Controller c, RestoAppPage p) {
		super(c, p);
		this.setBorder(new BorderUIResource.LineBorderUIResource(Color.BLACK));
		this.setLayout(null);
		// TODO Auto-generated constructor stub

		// LOYALTY DISPLAY
		// TITLE
		JLabel labelLoyaltyCards = new JLabel("LOYALTY CARDS");
		labelLoyaltyCards.setBounds(25, 5, 300, 100);
		labelLoyaltyCards.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		this.add(labelLoyaltyCards);

		// TABLE VIEW
		String tableTitles[] = { "Email Address", "Name", "Phone Number", "Points" };
		cardListFrameView = new DefaultTableModel();
		cardListFrameView.setColumnIdentifiers(tableTitles);

		cardListView = new JTable(cardListFrameView);
		cardListView.setBackground(Color.WHITE);
		cardListView.setFont(new Font("Comic sans MS", Font.PLAIN, 10));
		cardListView.setBounds(10, 80, 700, 600);

		// SCROLL PANE
		cardListScroll = new JScrollPane(cardListView);
		cardListScroll.setBackground(Color.WHITE);
		// cardListScroll.setViewportView();
		this.add(cardListScroll);
		cardListScroll.setBorder(new BorderUIResource.LineBorderUIResource(Color.BLACK));
		cardListScroll.setFont(new Font("Comic sans MS", Font.PLAIN, 30));
		cardListScroll.setBounds(10, 80, 700, 300);
		cardListScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// REMOVING A LOYALTY CARD
		// Label
		JLabel removeLabel = new JLabel("REMOVE using email");
		removeLabel.setBounds(10, 380, 300, 20);
		removeLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));

		this.add(removeLabel);

		// Textfield
		removeCard = new JTextField();
		removeCard.setBounds(310, 380, 300, 20);
		removeCard.setToolTipText("Please enter email address");
		add(removeCard);

		// SUBMIT REMOVAL
		submitRemovalButton = new JButton("SUBMIT");
		submitRemovalButton.setBounds(610, 380, 100, 20);
		submitRemovalButton.addActionListener(this);
		submitRemovalButton.setFont(new Font("Comic sans MS", Font.PLAIN, 10));
		add(submitRemovalButton);

		// REGISTRATION
		// TITLE
		JLabel cardRegistration = new JLabel("REGISTRATION");
		cardRegistration.setBounds(10, 390, 300, 50);
		cardRegistration.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		this.add(cardRegistration);

		// NAME
		// label
		JLabel clientName = new JLabel("NAME:");
		clientName.setBounds(10, 450, 300, 50);
		clientName.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		this.add(clientName);

		// Textfield
		enterClientName = new JTextField();
		enterClientName.setBounds(310, 450, 400, 50);
		add(enterClientName);

		// PHONE
		// label
		JLabel phoneNumber = new JLabel("PHONE NUMBER:");
		phoneNumber.setBounds(10, 510, 300, 50);
		phoneNumber.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		this.add(phoneNumber);

		// Textfield
		enterPhone = new JTextField();
		enterPhone.setBounds(310, 510, 400, 50);
		add(enterPhone);

		// EMAIL
		// label
		JLabel email = new JLabel("EMAIL:");
		email.setBounds(10, 580, 300, 50);
		email.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		this.add(email);

		// Textfield
		enterEmail = new JTextField();
		enterEmail.setBounds(310, 580, 400, 50);
		add(enterEmail);

		// SUBMIT REGISTRATION
		submitRegistrationButton = new JButton("SUBMIT");
		submitRegistrationButton.setBounds(600, 640, 100, 50);
		submitRegistrationButton.setFont(new Font("Comic sans MS", Font.PLAIN, 15));
		submitRegistrationButton.addActionListener(this);
		add(submitRegistrationButton);

		// BACK
		backButton = new JButton("Back");
		backButton.setBounds(10, 700, 100, 50);
		backButton.addActionListener(this);
		backButton.setFont(new Font("Comic sans MS", Font.PLAIN, 15));
		add(backButton);

		// displaying info
		this.updateView();
	}

	@Override
	public void updateView() {
		clearTable();
		updateCardList();
		this.revalidate();
		this.repaint();
		cardListView.revalidate();
		cardListView.updateUI();
	}

	public void clearRegistrationInputs() {
		enterClientName.setText(null);
		enterPhone.setText(null);
		enterEmail.setText(null);
		this.page.updateSidePanels();
	}

	public void clearRemovalInputs() {
		removeCard.setText(null);
		this.page.updateSidePanels();
	}

	public void clearTable() {
		cardListFrameView.setRowCount(0);
		cardListView.revalidate();
	}

	public void updateCardList() {
		List<LoyaltyCard> cardList = controller.displayLoyaltyCards();
		DecimalFormat df = new DecimalFormat("0.00");

		for (LoyaltyCard aCard : cardList) {
			Object[] cardInfo = { aCard.getEmailAddress(), aCard.getClientName(), aCard.getPhoneNumber(),
					df.format(aCard.getPoint()) };
			cardListFrameView.addRow(cardInfo);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == backButton) {
			this.page.setRightIndex(0);
			this.page.updateSidePanels();
		} else if (e.getSource() == submitRegistrationButton) {
			String enteredName = enterClientName.getText();
			String enteredPhone = enterPhone.getText();
			String enteredEmail = enterEmail.getText();

			try {
				controller.registrationForLoyaltyCard(enteredName, enteredPhone, enteredEmail);
				this.clearRegistrationInputs();
				this.updateView();
			} catch (InvalidInputException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
//				e1.printStackTrace();
			}
		} else if (e.getSource() == submitRemovalButton) {
			String emailToRemove = removeCard.getText();
			try {
				controller.removeExistingLoyaltyCard(emailToRemove);
				this.clearRemovalInputs();
				this.updateView();

			} catch (InvalidInputException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			}
		}

	}

}
