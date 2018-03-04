package ca.mcgill.ecse223.resto.view;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class RemoveTablePanel extends SidePanel implements ActionListener{
	//JButtons
	private JButton back;
	private JButton submit;

	//JLabels
	private JLabel title;
	private JLabel removeTableLabel;
	private JLabel removeCurrentTableLabel;
	private JComboBox<Integer> tables;
	private JComboBox<Integer> currentTables;

	public RemoveTablePanel(Controller controller, RestoAppPage page) {
		super(controller, page);
		this.setLayout(null);

		//title label properties
		title = new JLabel("Remove Table", SwingConstants.CENTER);
		title.setFont(new Font("Comic sans MS", Font.BOLD, 50));
		title.setForeground(Color.decode("#00BF9A"));
		title.setBounds(50, 0, 650, 200);


		//removeTableLabel properties
		removeTableLabel = new JLabel("select the current table to remove", SwingConstants.CENTER);
		removeTableLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		removeTableLabel.setBounds(0, 300, 750, 50);

		removeCurrentTableLabel = new JLabel("select the table to remove", SwingConstants.CENTER);
		removeCurrentTableLabel.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		removeCurrentTableLabel.setBounds(0, 475, 750, 50);

		//back button properties
		back = new JButton("back");
		back.setFont(new Font("Comic sans MS", Font.BOLD, 20));
		back.setBounds(450, 600, 150, 50);
		back.addActionListener(this);

		//submit button properties
		submit = new JButton("submit");
		submit.setFont(new Font("Comic sans MS", Font.BOLD, 20));
		submit.setBounds(200, 600, 150, 50);
		submit.addActionListener(this);

		//tables ComboBox properties
		tables = new JComboBox<Integer>();
		tables.setBounds(300, 500, 200, 50);
		tables.addActionListener(this);

		//currentTables ComboBox properties
		currentTables = new JComboBox<Integer>();
		currentTables.setBounds(300, 375, 200, 50);
		currentTables.addActionListener(this);

		//adding components to JPanel
		this.add(title);
		this.add(removeTableLabel);
		this.add(submit);
		this.add(back);
		this.add(tables);

		this.setVisible(true);

		updateView();


	}

	@Override
	public void updateView() {
		tables.removeAllItems();
		for(Integer tableId : controller.getAllCurrentTableNumbers()){
			currentTables.addItem(tableId);
		}
		for(Integer tableId : controller.getAllTableNumbers()){
			tables.addItem(tableId);
		}
		revalidate();
		repaint();
	}

	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("back")) {
			page.setRightIndex(0);
			page.updateSidePanels();
		}else if(e.getActionCommand().equals("submit")){
			int tableId = (Integer)tables.getSelectedItem();
			try {
				controller.removeTable(tableId);
				updateView();
				page.updateSidePanels();
			}catch(InvalidInputException exception){
				JOptionPane.showMessageDialog(this, exception.getMessage());
				exception.printStackTrace();
			}
		}
	}

}
