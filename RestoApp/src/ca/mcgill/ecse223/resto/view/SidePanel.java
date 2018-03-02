package ca.mcgill.ecse223.resto.view;

import javax.swing.JPanel;

import ca.mcgill.ecse223.resto.controller.Controller;

@SuppressWarnings("serial")
public abstract class SidePanel extends JPanel {
	protected Controller controller;
	protected RestoAppPage page;

	abstract public void updateView();

	public SidePanel(Controller controller, RestoAppPage page) {
		super();
		this.controller = controller;
		this.page = page;
	}

}