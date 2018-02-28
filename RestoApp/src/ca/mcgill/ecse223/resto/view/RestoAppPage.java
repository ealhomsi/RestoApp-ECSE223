package ca.mcgill.ecse223.resto.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;

@SuppressWarnings("serial")
public class RestoAppPage extends JFrame implements ActionListener {
	private JMenuBar menuBar;
	private Controller controller;
	private JComboBox<String> options;
	private DrawingPanel drawPane;
	private SidePanel[] sidePanels = null;

	/**
	 * constructor of the main page
	 * 
	 * @param aRestoApp
	 *            the system
	 * @throws InvalidInputException
	 *             when controller get mad
	 */
	public RestoAppPage(Controller c) throws InvalidInputException {
		// set the controller
		this.controller = c;

		// set menu bar
		this.initMenuBar();

		// init the page
		this.setTitle("RestoPage");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200, 200);

		// content
		Container contentPane = this.getContentPane();
		contentPane.setBackground(Color.white);

		drawPane = new DrawingPanel(c);
		contentPane.add(drawPane, BorderLayout.CENTER);

		// combo box init
		String[] items = { "Add a table", "Remove a table", "Update the table number and number of seats of a table",
				"Change the location of a table", "Display the menu" };
		options = new JComboBox<String>(items);
		options.addActionListener(this);
		contentPane.add(options, BorderLayout.PAGE_START);
		
		// init side panels ADD YOURS HERE
		sidePanels = new SidePanel[5];
		sidePanels[3] = new ChangeTableLocationPanel(c);
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int optionCode = options.getSelectedIndex();
		sidePanels[3].updateView();
		try {
			switch (optionCode) {
			case 0:
				// Add A table
				controller.addTable(62, 1000, 600, 300, 200, 15); //test
				break;
			case 1:
				// Remove a table
				controller.addTable(621, 1000, 600, 300, 200, 4); //test
				break;
			case 2:
				// Update the table number and number of seats of a table
				break;
			case 3:
				// Change the location of a table
				sidePanels[3].updateView();
				this.getContentPane().add(sidePanels[3], BorderLayout.EAST);
				break;
			case 4:
				// Display the menu
				break;
			}
			
			this.getContentPane().revalidate();
			this.getContentPane().repaint();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	/**
	 * for now it is a template for MenuBar
	 */
	public void initMenuBar() {
		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		JMenu menu = new JMenu("A Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);

		// a group of JMenuItems
		JMenuItem menuItem = new JMenuItem("A text-only menu item", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(menuItem);

		menuItem = new JMenuItem("Both text and icon", new ImageIcon("images/middle.gif"));
		menuItem.setMnemonic(KeyEvent.VK_B);
		menu.add(menuItem);

		menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
		menuItem.setMnemonic(KeyEvent.VK_D);
		menu.add(menuItem);

		// a group of radio button menu items
		menu.addSeparator();
		ButtonGroup group = new ButtonGroup();
		JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Another one");
		rbMenuItem.setMnemonic(KeyEvent.VK_O);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		// a group of check box menu items
		menu.addSeparator();
		JCheckBoxMenuItem cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
		cbMenuItem.setMnemonic(KeyEvent.VK_C);
		menu.add(cbMenuItem);

		cbMenuItem = new JCheckBoxMenuItem("Another one");
		cbMenuItem.setMnemonic(KeyEvent.VK_H);
		menu.add(cbMenuItem);

		// a submenu
		menu.addSeparator();
		JMenu submenu = new JMenu("A submenu");
		submenu.setMnemonic(KeyEvent.VK_S);

		menuItem = new JMenuItem("An item in the submenu");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		submenu.add(menuItem);

		menuItem = new JMenuItem("Another item");
		submenu.add(menuItem);
		menu.add(submenu);

		// Build second menu in the menu bar.
		menu = new JMenu("Another Menu");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
		menuBar.add(menu);

		this.setJMenuBar(menuBar);
	}

}
