package ca.mcgill.ecse223.resto.view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.mcgill.ecse223.resto.controller.Controller;

@SuppressWarnings("serial")
public class RestoAppPage extends JFrame {
	private Controller c;
	private JPanel body;
	private SidePanel[] sidePanels;
	private int leftIndex;
	private int rightIndex;

	public RestoAppPage(Controller c) {
		this.c = c;

		// add body and never remove it
		this.body = new JPanel();
		this.add(body);

		// split into two
		setSize(1500, 800);
		this.setResizable(false);
		// init sidePanels
		initSidePanels();

		this.leftIndex = 1;
		this.rightIndex = 0;

		this.updateSidePanels();
	}

	/**
	 * this method would create all side panels needed
	 */
	public void initSidePanels() {
		sidePanels = new SidePanel[10];
		sidePanels[0] = new MainSidePanel(c, this);
		sidePanels[1] = new DrawingPanel(c, this);
		sidePanels[2] = new AddTablePanel(c, this);
		sidePanels[3] = new RemoveTablePanel(c, this);
		sidePanels[4] = new UpdateTablePanel(c, this);
		sidePanels[5] = new ChangeTableLocationPanel(c, this);
		sidePanels[6] = new MenuCategoriesPanel(c, this);
		sidePanels[7] = new ItemCategoryPanel(c, this);
		sidePanels[8] = new ReservationPanel(c, this);
		sidePanels[9] = new ViewOrderPanel(c, this);
	}

	public int getRightIndex() {
		return rightIndex;
	}

	public void setLeftIndex(int leftIndex) {
		this.leftIndex = leftIndex;
	}

	public void setRightIndex(int rightIndex) {
		this.rightIndex = rightIndex;
	}

	public int getLeftIndex() {
		return leftIndex;
	}

	// comment

	public void updateSidePanels() {
		this.sidePanels[5].updateView();
		this.sidePanels[4].updateView();
		this.sidePanels[3].updateView();
		body.removeAll();
		body.setLayout(new GridLayout(1, 2));
		body.add(sidePanels[leftIndex], 0, 0);
		body.add(sidePanels[rightIndex], 0, 1);
		this.validate();
		this.revalidate();
		this.repaint();
	}

}
