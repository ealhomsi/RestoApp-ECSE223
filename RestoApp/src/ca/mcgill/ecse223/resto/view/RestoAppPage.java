package ca.mcgill.ecse223.resto.view;

import java.awt.GridLayout;

import javax.swing.JButton;
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
		sidePanels = new SidePanel[6];
		sidePanels[0] = new MainSidePanel(c, this);
		sidePanels[1] = new DrawingPanel(c, this);
		sidePanels[2] = new AddTablePanel(c, this);
		sidePanels[3] = new ChangeTableLocationPanel(c, this);
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

	public void updateSidePanels() {
		body.removeAll();
		body.setLayout(new GridLayout(1, 2));
		body.add(sidePanels[leftIndex], 0, 0);
		body.add(sidePanels[rightIndex], 0, 1);
		this.validate();
		this.revalidate();
		this.repaint();
	}

}