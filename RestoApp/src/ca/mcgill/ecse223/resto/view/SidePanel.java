package ca.mcgill.ecse223.resto.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ca.mcgill.ecse223.resto.controller.Controller;

public abstract class SidePanel extends JPanel {
	protected Controller controller;

	abstract public void updateView();

	public SidePanel(Controller c) {
		super();
		this.controller = c;
	}
	
	public void requestSetLeft(int i) {
		JFrame frame = (JFrame)SwingUtilities.getRoot(this);
		RestoAppPage page = (RestoAppPage) frame;
		page.setLeftIndex(i);
	}
	
	public void requestSetRight(int i) {
		JFrame frame = (JFrame)SwingUtilities.getRoot(this);
		RestoAppPage page = (RestoAppPage) frame;
		page.setRightIndex(i);
	}

}
