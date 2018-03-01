package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.BorderUIResource;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;

public class ChangeTableLocationPanel extends SidePanel implements ActionListener  {
	private Controller controller;
	private JComboBox<Integer> tables;
	private JLabel promptLabel;
	private JLabel newXLabel;
	private JLabel newYLabel;
	private JButton submit;
	private JTextField newXField;
	private JTextField newYField;
	private int selected = 0;
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public ChangeTableLocationPanel(Controller controller, RestoAppPage p) {
		super(controller, p);
		
		// init
		promptLabel = new JLabel("Please enter the new coordiantes");
		newXLabel = new JLabel("newX");
		newYLabel = new JLabel("newY");
		submit = new JButton("submit");
		submit.addActionListener(this);
		newXField = new JTextField();
		newYField = new JTextField();
		tables = new JComboBox<Integer>(controller.getAllTableNumbers());
		
		
		// updateView
		updateView();
		
		
		//adding
		this.setBorder(new BorderUIResource.LineBorderUIResource(Color.BLACK));
		this.setLayout(new GridLayout(10, 10));
		this.add(tables, 0,0);
		this.add(promptLabel);
		
		this.add(newXLabel);
		this.add(newXField);
		
		
		this.add(newYLabel);
		this.add(newYField);
		
		this.add(submit);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		int newX = Integer.parseInt(newXField.getText());
		int newY = Integer.parseInt(newYField.getText());

		try {
			controller.changeTableLocation((Integer) controller.getAllTableNumbers()[tables.getSelectedIndex()], newX, newY);
		} catch (InvalidInputException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
		this.getParent().repaint();
		this.repaint();
		this.revalidate();
	}

	@Override
	public void updateView() {
		if(tables != null && controller != null && tables.getItemCount() < controller.getAllTables().size()) {
			tables = new JComboBox<Integer>(controller.getAllTableNumbers());
			tables.setSelectedIndex(0);
		}
		this.newXField.setText("");
		this.newYField.setText("");
		this.revalidate();
		this.repaint();
	}

}
