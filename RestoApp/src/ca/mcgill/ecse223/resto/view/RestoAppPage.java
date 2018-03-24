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
	private int sidePanelsCount;
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
		this.sidePanelsCount = 12;
		sidePanels = new SidePanel[sidePanelsCount];
		sidePanels[0] = new MainSidePanel(c, this);
		sidePanels[1] = new DrawingPanel(c, this);
		sidePanels[2] = new AddTablePanel(c, this);
		sidePanels[3] = new RemoveTablePanel(c, this);
		sidePanels[4] = new UpdateTablePanel(c, this);
		sidePanels[5] = new ChangeTableLocationPanel(c, this);
		sidePanels[6] = new NewOrderPanel(c, this);
		sidePanels[7] = new EndOrderPanel(c, this);
		sidePanels[8] = new ItemCategoryPanel(c, this);
		sidePanels[9] = new MenuCategoriesPanel(c, this);
		sidePanels[10] = new ReservationPanel(c, this);
		sidePanels[11] = new OrderItemPanel(c, this);
	}

	public int getRightIndex() {
		return rightIndex;
	}

	public void setLeftIndex(int leftIndex) {
		this.leftIndex = leftIndex;
	}

	public SidePanel getSidePanel(int index) {
		return this.sidePanels[index];
	}

	public void setRightIndex(int rightIndex) {
		this.rightIndex = rightIndex;
	}

	public int getLeftIndex() {
		return leftIndex;
	}

	// comment

	public void updateSidePanels() {
		for (int i = 1; i < sidePanelsCount; i++) {
			this.sidePanels[i].updateView();
		}

		body.removeAll();
		body.setLayout(new GridLayout(1, 2));
		body.add(sidePanels[leftIndex], 0, 0);
		body.add(sidePanels[rightIndex], 0, 1);
		this.validate();
		this.revalidate();
		this.repaint();
	}

}
