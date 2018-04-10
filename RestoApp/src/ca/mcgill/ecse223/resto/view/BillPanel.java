package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Bill;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.OrderItem;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;

public class BillPanel extends SidePanel implements ActionListener{
	private Controller c;
	private DefaultTableModel seatOverview;
	private DefaultTableModel selectedSeats;
	private JTable box;
	private JScrollPane overviewScrollPane;
	private JComboBox<String> seatCombo;
	private JComboBox<String> order;
	private JScrollPane selected;
	private JTable box1;
	private JButton adderButton;
	private JButton clearButton;
	private JButton submitButton;
	private DefaultTableModel currentBills;
	private JTable box2;
	private JScrollPane zaha2;
	private JLabel title1;
	private JLabel title2;
	private JButton backButton;
	private List<Seat> selectedSeat;

	
	public BillPanel(Controller controller, RestoAppPage page) {
		super(controller, page);
		c=controller;
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		selectedSeat = new ArrayList<Seat>();
		
		String titlesItems[] = {"Name of Item","Quantity"};
		seatOverview =new DefaultTableModel();
		seatOverview.setColumnIdentifiers(titlesItems);
		
		box = new JTable(seatOverview);
		box.setBackground(Color.WHITE);
		box.setFont(new Font("Comic sans MS", Font.PLAIN, 10));
		box.setBounds(10,30,200,500);
		
		overviewScrollPane = new JScrollPane(box);
		overviewScrollPane.setFont(new Font("Comic sans MS", Font.PLAIN, 30));
		add(overviewScrollPane);
		overviewScrollPane.setBackground(Color.white);
		overviewScrollPane.setBounds(10,30,200,500);
		overviewScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		String titlesBill[] = {"Bill issued to seats:"," amount"};
		currentBills =new DefaultTableModel();
		currentBills.setColumnIdentifiers(titlesBill);
		
		box2 = new JTable(currentBills);
		box2.setBackground(Color.WHITE);
		box2.setFont(new Font("Comic sans MS", Font.PLAIN, 10));
		box2.setBounds(10,600,600,400);
		
		zaha2 = new JScrollPane(box2);
		zaha2.setFont(new Font("Comic sans MS", Font.PLAIN, 30));
		add(zaha2);
		zaha2.setBackground(Color.white);
		zaha2.setBounds(10,550,715,200);
		zaha2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		seatCombo = new JComboBox<String>();
		seatCombo.addItem("");
		seatCombo.setBounds(220,150, 250, 50);
		add(seatCombo);
		seatCombo.setFont(new Font("Comic sans MS", Font.PLAIN, 10));
		seatCombo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (seatOverview.getRowCount() > 0) {
				    for (int i = seatOverview.getRowCount() - 1; i > -1; i--) {
				        seatOverview.removeRow(i);
				    }
				}
				String selectedItem = (String) seatCombo.getSelectedItem();
				if(selectedItem == null)
					return ;
				String[] splited = selectedItem.split(" ");
				int num = Integer.parseInt(splited[1]);
				Table a = Table.getWithNumber(num);
				num = Integer.parseInt(splited[3]);
				Seat b = a.getCurrentSeat(num);
				
				
				for(OrderItem o: b.getOrderItems()){
					Object[] obj= {o.getPricedMenuItem().getMenuItem().getName(),o.getQuantity()};
					
					seatOverview.addRow(obj);
				}
			}
		});
		order = new JComboBox<String>();
		order.setBounds(220,100, 250, 50);
		add(order);
		order.setFont(new Font("Comic sans MS", Font.PLAIN, 10));
		order.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				seatCombo.removeAll();
				// TODO Auto-generated method stub
				List<OrderView> a = c.getAllCurrentOrders();
				seatCombo.removeAllItems();
				int num=order.getSelectedIndex();
				if(num<0)
					return;
				Order current = a.get(num).getOrder();
				List<Table> tables = current.getTables();
				for(Table t: tables){
					int i =0;
					List<Seat> seats = t.getCurrentSeats();
					for(Seat s : seats){
						
						if(s.hasOrderItems()){
							updateCombo(s,i);
						}
						i++;
					}
				}
				if (currentBills.getRowCount() > 0) {
				    for (int i = currentBills.getRowCount() - 1; i > -1; i--) {
				        currentBills.removeRow(i);
				    }
				}
				List<Bill> currentBill = current.getBills();
				for (Bill bill : currentBill){
					List<Seat> seats = bill.getIssuedForSeats();
					String result = "";
					double amount = 0;
					for(Seat seat: seats){
						result += "id "+seat.getTable().getNumber()+ "Index: "+seat.getTable().indexOfSeat(seat);
						List<OrderItem> orderItems = seat.getOrderItems();
						for(OrderItem aOrderItem: orderItems){
							amount += aOrderItem.getPricedMenuItem().getPrice();
						}
					}
					Object[] toAdd = {result,amount};
					currentBills.addRow(toAdd);
					
				}
			}
			
		});
		String titlesSeats[] = {"Table Id","Index"};
		selectedSeats =new DefaultTableModel();
		selectedSeats.setColumnIdentifiers(titlesSeats);
		
		box1 = new JTable(selectedSeats);
		box1.setBackground(Color.WHITE);
//		box1.setFont(new Font("Comic sans MS", Font.PLAIN, 30));
		box1.setBounds(600,30,150,500);
		
		selected = new JScrollPane(box1);
//		selected.setFont(new Font("Comic sans MS", Font.PLAIN, 30));
		add(selected);
		selected.setBackground(Color.white);
		selected.setBounds(475,30,250,500);
		selected.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(selected);
		
		adderButton = new JButton("Add");
		adderButton.setBounds(220, 250, 70, 50);
		adderButton.setFont(new Font("Comic sans MS", Font.PLAIN, 15));
		adderButton.addActionListener(this);
		add(adderButton);
		
		clearButton = new JButton("Clear");
		clearButton.setBounds(290, 250, 70, 50);
		clearButton.setFont(new Font("Comic sans MS", Font.PLAIN, 15));
		clearButton.addActionListener(this);
		add(clearButton);
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(360, 250, 100, 50);
		submitButton.setFont(new Font("Comic sans MS", Font.PLAIN, 15));
		submitButton.addActionListener(this);
		add(submitButton);
		
		title1 = new JLabel("Items Of Selected Seat");
		title2= new JLabel("Selected Seats for a Bill");
		title1.setBounds(10, 10, 200, 20);
		title2.setBounds(475, 10, 200, 20);
		add(title1);
		add(title2);
		
		backButton = new JButton("Back");
		backButton.setBounds(225, 450 , 100, 50);
		backButton.addActionListener(this);
		backButton.setFont(new Font("Comic sans MS", Font.PLAIN, 15));
		add(backButton);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Add")){
			if(seatCombo.getSelectedObjects() == null)
				return;
			Object[] addStr = seatCombo.getSelectedObjects();
			String str = addStr[0].toString();
			String[] addSt = str.split(" ");
			Object[] adds = {addSt[1],addSt[3]};
			selectedSeats.addRow(adds);
			Seat addSeat = Table.getWithNumber(Integer.parseInt(addSt[1])).getCurrentSeat(Integer.parseInt(addSt[3]));
			selectedSeat.add(addSeat);
		}
		if(arg0.getActionCommand().equals("Clear")){
			if (selectedSeats.getRowCount() > 0) {
			    for (int i = selectedSeats.getRowCount() - 1; i > -1; i--) {
			        selectedSeats.removeRow(i);
			    }
			}
			selectedSeat.clear();
		}
		if(arg0.getActionCommand().equals("Back")){
			page.setRightIndex(0);
			page.updateSidePanels();
		}
		if(arg0.getActionCommand().equals("Submit")){
			if(selectedSeat.size() == 0)
				return;
			try {
				c.issueBill(selectedSeat);
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void updateView() {
		order.removeAllItems();
		List<OrderView> a = c.getAllCurrentOrders();
//		System.out.println(a.size());
		for(OrderView o: a){
			List<Table> t = o.getOrder().getTables();
			String toAdd="Order for: ";
			for(Table b: t){
				toAdd += " id" + b.getNumber();
			}
			order.addItem(toAdd);
		}
	}
	public void updateCombo(Seat s , int i){
		seatCombo.addItem("Id: "+s.getTable().getNumber()+" Index: "+i);
	}

	
}
