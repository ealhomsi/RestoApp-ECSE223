package ca.mcgill.ecse223.resto.view;

import java.awt.GridLayout;

import javax.swing.JFrame;

import ca.mcgill.ecse223.resto.controller.Controller;

@SuppressWarnings("serial")
public class RestoAppPage extends JFrame {
	private Controller c;
	private SidePanel[] sidePanels;
	private int leftIndex;
	private int rightIndex;
	
	public RestoAppPage(Controller c) {
		this.c = c;

		// split into two
		setSize(1500, 800);
		this.setResizable(false);
		this.getContentPane().setLayout(new GridLayout(1, 2));
		
		//init sidePanels
		initSidePanels();
		
		this.leftIndex = 0;
		this.rightIndex = 1;

		this.add(sidePanels[leftIndex], 0, 0);
		this.add(sidePanels[rightIndex], 0, 1);
	}

	/**
	 * this method would create all side panels needed
	 */
	public void initSidePanels() {
		sidePanels = new SidePanel[6];
		sidePanels[0] = new MainSidePanel(c);
		sidePanels[1] = new DrawingPanel(c);
		sidePanels[2] = new AddTablePanel(c);
		sidePanels[3] = new ChangeTableLocationPanel(c);
	}

	
	public void setLeftIndex(int i) {
		this.leftIndex = i;
		this.add(sidePanels[leftIndex], 0, 0);
		this.add(sidePanels[rightIndex], 0, 1);
		this.repaint();
		this.revalidate();
	}
	
	public void setRightIndex(int i) {
		this.rightIndex = i;
		this.add(sidePanels[leftIndex], 0, 0);
		this.add(sidePanels[rightIndex], 0, 1);
		this.repaint();
		this.revalidate();
	}
	
}
