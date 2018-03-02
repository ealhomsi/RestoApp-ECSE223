import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollBar;

import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppetizersPanel extends JPanel {
	private JTextField txtAppetizers;

	/**
	 * Create the panel.
	 */
	public AppetizersPanel() {
		setSize(1500,800);
		setLayout(null);
		
		txtAppetizers = new JTextField();
		txtAppetizers.setText("APPETIZERS");
		txtAppetizers.setBounds(119, 145, 300, 80);
		txtAppetizers.setBorder(null);
		txtAppetizers.setBackground(null);
		txtAppetizers.setFont(new Font("Comic sans MS", Font.PLAIN, 40));
		add(txtAppetizers);
		txtAppetizers.setColumns(10);
		
		/*JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(1235, 293, 53, 197);
		add(scrollBar);*/
		
		JButton btn = new JButton("New button");
		btn.setBounds(119, 293, 200, 60);
		btn.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		add(btn);
		
		JButton btn1 = new JButton("New button");
		btn1.setBounds(679, 293, 200, 60);
		btn1.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		add(btn1);
		
		JButton btn2 = new JButton("New button");
		btn2.setBounds(957, 293, 200, 60);
		btn2.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		add(btn2);
		
		JButton btn3 = new JButton("New button");
		btn3.setBounds(119, 430, 200, 60);
		btn3.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		add(btn3);
		
		JButton btn4 = new JButton("New button");
		btn4.setBounds(393, 430, 200, 60);
		btn4.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		add(btn4);
		
		JButton btn5 = new JButton("New button");
		btn5.setBounds(679, 430, 200, 60);
		btn5.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		add(btn5);
		
		JButton btn6 = new JButton("New button");
		btn6.setBounds(957, 430, 200, 60);
		btn6.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		add(btn6);
		
		JButton btn7 = new JButton("New button");
		btn7.setBounds(393, 293, 200, 60);
		btn7.setFont(new Font("Comic sans MS", Font.PLAIN, 20));
		add(btn7);
		
	}
}
