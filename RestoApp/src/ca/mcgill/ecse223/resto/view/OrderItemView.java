package ca.mcgill.ecse223.resto.view;

import ca.mcgill.ecse223.resto.model.OrderItem;

public class OrderItemView {
	private OrderItem orderItem;

	public OrderItemView(OrderItem orderItem) {
		super();
		this.orderItem = orderItem;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	@Override
	public String toString() {
		
		return "OrderItem [ name = " + orderItem.getPricedMenuItem().getMenuItem().getName() + ", Quantity = " + orderItem.getQuantity() + " ]";
	}
}
