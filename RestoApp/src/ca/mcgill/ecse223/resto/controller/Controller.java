package ca.mcgill.ecse223.resto.controller;

import java.awt.Rectangle;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.OrderItem;
import ca.mcgill.ecse223.resto.model.PricedMenuItem;
import ca.mcgill.ecse223.resto.model.Reservation;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.model.Table.Status;
import ca.mcgill.ecse223.resto.view.OrderView;
import ca.mcgill.ecse223.resto.view.TableView;

public class Controller {
	private RestoApp service;

	/**
	 * constructor for the controller
	 * 
	 * @param service
	 *            the model system
	 */
	public Controller(RestoApp service) {
		super();
		this.service = service;
	}

	/**
	 * This method adds a new table to the system
	 *
	 * @param number:
	 *            table number
	 * @param x:
	 *            table location
	 * @param y:
	 *            table location
	 * @param width:
	 *            table width
	 * @param length:
	 *            table length
	 * @param numberOfSeats:
	 *            number of seats on that table
	 * @return a new table view
	 * 
	 * @throws InvalidInputException
	 */
	public TableView addTable(int number, int x, int y, int width, int length, int numberOfSeats)
			throws InvalidInputException {
		// check input
		if (number < 0 || x < 0 || y < 0 || width <= 0 || length <= 0 || numberOfSeats < 0) {
			throw new InvalidInputException("one of the inputs is zero or negative");
		}

		// check if another table has the same number
		for (Table t : service.getCurrentTables()) {
			if (t.getNumber() == number)
				throw new InvalidInputException("the table number already exists");
		}

		Rectangle rect = new Rectangle(x, y, width, length);

		// check if the table clash
		for (Table t : service.getCurrentTables()) {
			if (tableOverLap(tableToRectangle(t), rect)) {
				throw new InvalidInputException("the table overlaps with another table");
			}
		}

		// passwed all checks
		Table newt = new Table(number, x, y, width, length, service);
		for (int i = 0; i < numberOfSeats; i++) {
			newt.addCurrentSeat(new Seat(newt));
		}

		// add table to currentTable
		service.addCurrentTable(newt);
		// saving
		RestoApplication.save();

		return convertToViewObject(newt);
	}

	/**
	 * This method would add an order item to a list of seats
	 * 
	 * @param quantity
	 *            the quantity must be strictly positive
	 * @param o
	 *            the order that parents the order item
	 * @param seats
	 *            the list of seats for that order item
	 * @param i
	 *            the priced menuitem
	 * @throws InvalidInputException
	 *             exceptions are thrown on different occasions.
	 */

	public void orderItem(int quantity, Order o, List<Seat> seats, PricedMenuItem i) throws InvalidInputException {
		// get restoapp
		RestoApp r = RestoApplication.getRestoApp();

		// check for current tables in order
		if (!r.getCurrentTables().contains(o.getTables()))
			throw new InvalidInputException("Some of the tables in the order are not current");

		// checking quantity
		if (quantity <= 0)
			throw new InvalidInputException("quantity has to be > 0");

		// checking for nulls
		if (o == null || seats == null || i == null)
			throw new InvalidInputException("Order or seat or menu item is null");

		// check if all seats are contained in one of the tables
		if (!areSeatsInTables(seats, o.getTables())) {
			throw new InvalidInputException("One or more of the seats does not belong to a table of that order");
		}

		new OrderItem(quantity, i, o, seats.toArray(new Seat[seats.size()]));

		// saving
		RestoApplication.save();
	}

	public boolean areSeatsInTables(List<Seat> seats, List<Table> tables) {
		for (Seat s : seats) {
			boolean found = false;
			for (Table t : tables) {
				if (t.getCurrentSeats().contains(s)) {
					found = true;
					break;
				}
			}

			if (found == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Give a list of all tables views
	 * 
	 * @return list of all table views
	 */
	public List<TableView> getAllCurrentTables() {
		List<TableView> tvs = new ArrayList<TableView>();
		for (Table t : service.getCurrentTables()) {
			System.out.println(t.getNumber());
			tvs.add(convertToViewObject(t));
		}
		return tvs;
	}

	/**
	 * Give a list of all Orders views
	 * 
	 * @return list of all table views
	 */
	public List<OrderView> getAllCurrentOrders() {
		List<OrderView> orders = new ArrayList<OrderView>();
		for (Order order : service.getCurrentOrders()) {
			if (order.getTables().size() == 0)
				continue;
			orders.add(convertToViewObject(order));
		}
		return orders;
	}

	/**
	 * Changing a table location
	 * 
	 * @param number:
	 *            number of the table
	 * @param newX:
	 *            new coordinates
	 * @param newY:
	 *            new coordinates
	 * @throws InvalidInputException
	 *             thrown when arguments are wrong or on overlap
	 */
	public void changeTableLocation(int number, int newX, int newY) throws InvalidInputException {

		if (newX < 0 || newY < 0)
			throw new InvalidInputException("arguments are wrong");

		Table found = null;
		for (Table t : service.getCurrentTables()) {
			if (t.getNumber() == number) {
				found = t;
				break;
			}
		}

		if (found == null)
			throw new InvalidInputException("table was not found");

		// dimensions with the new location
		Rectangle rect = new Rectangle(newX, newY, found.getX(), found.getY());

		// check for collision
		for (Table t : service.getCurrentTables()) {
			if (t == found)
				continue;
			if (tableOverLap(rect, tableToRectangle(t)))
				throw new InvalidInputException("table will collide with table number " + t.getNumber());
		}

		// all good set new coordinates
		found.setX(newX);
		found.setY(newY);

		// saving
		RestoApplication.save();

	}

	/**
	 * returns a list of all table numbers
	 * 
	 * @return an array of Integers
	 */
	public List<Integer> getAllTableNumbers() {
		List<Integer> list = new ArrayList<>();
		for (Table t : service.getTables())
			list.add(t.getNumber());

		return list;
	}

	// corrected method
	public List<Integer> getAllCurrentTableNumbers() {
		List<Integer> list = new ArrayList<>();
		for (Table t : service.getCurrentTables()) {
			list.add(t.getNumber());
			System.out.println(t.getNumber());
		}
		return list;
	}

	/**
	 * get a table by number
	 * 
	 * @param number
	 *            the number of the table
	 * @return a table view object or null if not found
	 */
	public TableView getTableByNumber(int number) {
		for (Table t : service.getTables()) {
			if (t.getNumber() == number)
				return convertToViewObject(t);
		}
		return null;
	}

	/** CONVERSION METHODS **/

	TableView convertToViewObject(Table t) {
		return new TableView(t);
	}

	OrderView convertToViewObject(Order t) {
		return new OrderView(t);
	}

	/** HELPER METHODS **/

	/**
	 * This method checks if two line segments specified by the starting point and
	 * length do intersect or not
	 * 
	 * @param first:
	 *            coordinate of the first point
	 * @param firstWidth:
	 *            the length of the first line segment
	 * @param second:
	 *            coordinate of the second point
	 * @param secondWidth:
	 *            coordinate of the second line segment
	 * @return boolean: overlap or not
	 */
	private boolean linearIntersection(double first, double firstWidth, double second, double secondWidth) {
		// make sure first is smaller
		if (first > second) {
			double tmp = first;
			first = second;
			second = tmp;

			tmp = firstWidth;
			firstWidth = secondWidth;
			secondWidth = tmp;
		}

		if (first + firstWidth > second)
			return true;

		return false;
	}

	/**
	 * This method checks if two tables overlap or not
	 * 
	 * @param rect1
	 *            the first rectangle
	 * @param rect2
	 *            the second rectangle
	 * @return boolean: overlap or not
	 */
	private boolean tableOverLap(Rectangle rect1, Rectangle rect2) {
		return linearIntersection(rect1.getX(), rect1.getWidth(), rect2.getX(), rect2.getWidth())
				&& linearIntersection(rect1.getY(), rect1.getHeight(), rect2.getY(), rect2.getHeight());
	}

	private Rectangle tableToRectangle(Table t) {
		return new Rectangle(t.getX(), t.getY(), t.getWidth(), t.getLength());
	}

	/**
	 * Method for displaying the menu categories and the menu items
	 */

	public List<ItemCategory> getItemCategories() {
		ArrayList<ItemCategory> menuCategories = new ArrayList<ItemCategory>();
		ItemCategory[] itemCategories = ItemCategory.values();
		for (int i = 0; i < itemCategories.length; i++) {
			ItemCategory category = itemCategories[i];
			menuCategories.add(category);
		}
		return menuCategories;
	}

	public ArrayList<MenuItem> getMenuItems(ItemCategory selectedItemCategory) throws InvalidInputException {
		ArrayList<MenuItem> categoryItemsList = new ArrayList<MenuItem>();
		RestoApp restoApp = RestoApplication.getRestoApp();

		for (MenuItem menuItem : restoApp.getMenu().getMenuItems()) {
			String error = "";

			if (menuItem.getItemCategory() == null) {
				error = "This item does not belong to any category";
				throw new InvalidInputException(error.trim());
			} else if (menuItem.getItemCategory().equals(selectedItemCategory) && menuItem.hasCurrentPricedMenuItem()) {
				categoryItemsList.add(menuItem);
			}

		}
		return categoryItemsList;
	}

	public void updateTable(Table table, int newNumber, int numberOfSeats) throws InvalidInputException {
		if (table == null || newNumber < 0 || numberOfSeats < 0 || table.hasReservations())
			throw new InvalidInputException("Wrong Input");
		List<Order> currentOrders = service.getCurrentOrders();
		for (Order t : currentOrders) {
			List<Table> tables = t.getTables();
			if (tables.contains(table))
				throw new InvalidInputException("Table has current orders");
		}

		if (!table.setNumber(newNumber) && table.getNumber() != newNumber)
			throw new InvalidInputException("Duplicate number");

		int currentNumberOfSeats = table.numberOfCurrentSeats();

		for (int count = 0; count < numberOfSeats - currentNumberOfSeats; count++) {
			Seat seat = table.addSeat();
			table.addCurrentSeat(seat);
		}
		for (int count = 0; count < currentNumberOfSeats - numberOfSeats; count++) {
			Seat seat = table.getCurrentSeat(0);
			table.removeCurrentSeat(seat);
		}
		RestoApplication.save();
	}

	public void removeTable(int number) throws InvalidInputException {
		// retrieve table by its unique id
		Table foundTable = Table.getWithNumber(number);

		// if no table by specified id was found => throw exception
		if (foundTable == null) {
			throw new InvalidInputException("no such table exists");
		}

		// table cannot be removed if it has reservations => throw exception
		if (foundTable.hasReservations()) {
			throw new InvalidInputException("cannot remove table due to existing reservations");
		}

		List<Order> orders = service.getCurrentOrders();
		List<Table> tables;

		// iterate through all orders to check if table is in use (has orders) => throw
		// exception
		for (Order order : orders) {
			tables = order.getTables();
			if (tables.contains(foundTable))
				throw new InvalidInputException("table contains orders");
		}

		// remove table completely, temp solution
		service.removeCurrentTable(foundTable);
		RestoApplication.save();

	}

	/**
	 * this method starts an order to a list of tables
	 * 
	 * @param tables
	 *            is the list of tables in question
	 * @throws InvalidInputException
	 *             thorws exception on different error cases
	 */
	public static void startOrder(List<Table> tables) throws InvalidInputException {
		RestoApp service = RestoApplication.getRestoApp();
		if (tables == null)
			throw new InvalidInputException("No tables given");

		List<Table> currentTables = service.getCurrentTables();
		for (Table t : tables) {
			boolean contains = currentTables.contains(t);
			if (contains == false)
				throw new InvalidInputException("Table given not in currentTables");
		}

		boolean orderCreated = false;
		Order newOrder = null;
		for (Table t : tables) {
			if (orderCreated) {
				t.addToOrder(newOrder);
			} else {
				Order lastOrder = null;
				if (t.numberOfOrders() > 0) {
					lastOrder = t.getOrder(t.numberOfOrders() - 1);
				}
				t.startOrder();
				if (t.numberOfOrders() > 0 && !t.getOrder(t.numberOfOrders() - 1).equals(lastOrder)) {
					orderCreated = true;
					newOrder = t.getOrder(t.numberOfOrders() - 1);
				}
			}
		}

		if (!orderCreated) {
			throw new InvalidInputException("The order couldn't be created");
		}

		service.addCurrentOrder(newOrder);
		RestoApplication.save();
	}

	public static void endOrder(Order order) throws InvalidInputException {
		RestoApp service = RestoApplication.getRestoApp();
		List<Order> currentOrders = service.getCurrentOrders();

		if (order == null) {
			throw new InvalidInputException("Order is invalid");
		}

		if (!currentOrders.contains(order)) {
			throw new InvalidInputException("Order being ended does not exist");
		}

		List<Table> tables = order.getTables();

		for (int i = 0; i < tables.size(); i++) {
			Table table = tables.get(i);
			if (table != null && table.numberOfOrders() > 0
					&& table.getOrder(table.numberOfOrders() - 1).equals(order)) {
				table.endOrder(order);
			}
		}

		if (allTablesAvailableOrDifferentCurrentOrder(tables, order))
			service.removeCurrentOrder(order);

		RestoApplication.save();
	}

	public static boolean allTablesAvailableOrDifferentCurrentOrder(List<Table> tables, Order order) {
		boolean result = false;

		for (Table table : tables) {
			if (table.getStatus() == Status.Available || !table.getOrder(table.numberOfOrders() - 1).equals(order)) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * this method does reservation
	 * 
	 * @param date
	 * @param time
	 * @param numberInParty
	 * @param contactName
	 * @param contactEmailAddress
	 * @param contactPhoneNumber
	 * @param tables
	 * @throws Exception
	 */
	public static void reserve(Date date, Time time, int numberInParty, String contactName, String contactEmailAddress,
			String contactPhoneNumber, List<Table> tables) throws Exception {
		// check for date and time for null values
		if (date == null || time == null) {
			throw new InvalidInputException("date/time values might be null");
		}

		// adds all strings to a list of input to be validated
		List<String> inputs = new ArrayList<>();
		inputs.add(contactName);
		inputs.add(contactEmailAddress);
		inputs.add(contactPhoneNumber);

		// checks for negative quantity
		if (numberInParty < 0) {
			throw new InvalidInputException("negative quantity");
		}

		// checks a list of input for an empty/null Strings
		checkInput(inputs);

		RestoApp restoApp = RestoApplication.getRestoApp();

		int seatCapacity = 0;
		List<Table> currentTables = restoApp.getCurrentTables();

		for (Table table : tables) {
			if (!currentTables.contains(table)) {
				throw new InvalidInputException("invalid table parameter");
			}

			seatCapacity += table.numberOfCurrentSeats();
			List<Reservation> reservations = table.getReservations();

			for (Reservation reservation : reservations) {
				if (reservation.doesOverlap(date, time)) {
					throw new InvalidInputException("time/date overlap");
				}
			}
		}
		if (seatCapacity < numberInParty) {
			throw new InvalidInputException("seat capactiy is less than the number in party");
		}
		new Reservation(date, time, numberInParty, contactName, contactEmailAddress, contactPhoneNumber, restoApp,
				tables.toArray(new Table[tables.size()]));
		RestoApplication.save();

	}

	/**
	 * this method checks input
	 * 
	 * @param inputs
	 * @throws InvalidInputException
	 */
	private static void checkInput(List<String> inputs) throws InvalidInputException {
		for (String input : inputs) {
			if (input == null || input.length() == 0)
				throw new InvalidInputException("null or empty values");
		}
	}

	public PricedMenuItem getPricedMenuItem(String name) throws InvalidInputException {

		for (PricedMenuItem m : this.service.getPricedMenuItems()) {
			if (m.getMenuItem().getName().equals(name)) {
				return m;
			}
		}

		throw new InvalidInputException("Priced Menu Item " + name + " was not found");
	}

}
