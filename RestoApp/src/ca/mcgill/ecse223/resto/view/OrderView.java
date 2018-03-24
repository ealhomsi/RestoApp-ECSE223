package ca.mcgill.ecse223.resto.view;

import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.Table;

public class OrderView {
	private Order order;

	public OrderView(Order order) {
		super();
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		String tables = "";
		
		for(Table t: order.getTables()) {
			tables += t.getNumber() + " ";
		}
		return "Order [ number=" + order.getNumber() + ", tables=" + tables + "]";
	}
	
	
	
}
