package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import ca.mcgill.ecse223.resto.controller.Controller;

@SuppressWarnings("serial")
public class UpdateMenuItem extends SidePanel implements ActionListener{
private JButton cancel;
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public UpdateMenuItem (Controller c, RestoAppPage p) {
		super(c,p);
		setLayout(null);
		
		JLabel label = new JLabel("Please select a menu item to update");
		label.setBounds(150, 250, 450, 250);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		add(label);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(300, 500, 150, 50);
		cancel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		cancel.setBackground(Color.WHITE);
		add(cancel);
		cancel.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == cancel) {
			this.page.setLeftIndex(1);
			this.page.updateSidePanels();
		}
	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

}
