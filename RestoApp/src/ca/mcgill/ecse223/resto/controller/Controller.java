package ca.mcgill.ecse223.resto.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.view.TableView;

public class Controller {
	private RestoApp service;

	/**
	 * constructor for the controller
	 * @param service the model system
	 */
	public Controller(RestoApp service) {
		super();
		this.service = service;
	}
	
	/**
	 * This method adds a new table to the system
	 * @param number: table number
	 * @param x: table location
	 * @param y: table location
	 * @param width: table width
	 * @param length: table length
	 * @param numberOfSeats: number of seats on that table
	 * @return a new table view
	 * 
	 * @throws InvalidInputException
	 */
	public TableView addTable(int number, int x, int y, int width, int length, int numberOfSeats) throws InvalidInputException {
		//check input
		if(number < 0 || x < 0 || y < 0 || width <= 0 || length <= 0 || numberOfSeats < 0) {
			throw new InvalidInputException("one of the inputs is zero or negative");
		}
		
		//check if another table has the same number
		for(Table t: service.getTables()) {
			if(t.getNumber() == number)
				throw new InvalidInputException("the table number already exsits");
		}
		
		Table newt = new Table(number, x, y, width, length, service);
		

		//check if the table clash
		for(Table t: service.getTables()) {
			if(tableOverLap(t, newt)) {
				newt.delete(); //remove the added table
				throw new InvalidInputException("the table overlaps with another table");
			}
		}
		
		for(int i=0; i<numberOfSeats; i++) {
			newt.addSeat();
		}
		
		//saving
		RestoApplication.save();
		
		return convertToViewObject(newt);
	}
	
	public void clearTables() throws InvalidInputException {
		for(Table t: service.getTables())
			t.delete();
		
		if(service.getTables().size() != 0)
			throw new InvalidInputException("something is wrong");
	}
	
	public List<TableView> getAllTables() {
		List<TableView> tvs = new ArrayList<TableView>();
		for(Table t: service.getTables()) {
			System.out.println(t.getNumber());
			tvs.add(convertToViewObject(t));
		}
		return tvs;
	}
	
	/** CONVERSION METHODS **/

	TableView convertToViewObject(Table t) {
		return new TableView(t);
	}
	
	/** HELPER METHODS **/

	/**
	 * This method checks if two line segments specified by the starting point and length
	 * do intersect or not
	 * @param first: coordinate of the first point
	 * @param firstWidth: the length of the first segement
	 * @param second: coordinate of the second point
	 * @param secondWidth: coordinate of the second segement
	 * @return boolean: overlap or not
	 */
	private boolean linearIntersection(int first, int firstWidth, int second, int secondWidth) {
		//make sure first is smaller
		if(first > second) {
			int tmp = first;
			first = second;
			second = tmp;
			
			tmp = firstWidth;
			firstWidth = secondWidth;
			secondWidth = tmp;
		}
		
		if(first + firstWidth > second)
			return true;
		
		return false;
	}

	/**
	 * This method checks if two tables overlap or not
	 * @param table1 the first table
	 * @param table2 the second table
	 * @return boolean: overlap or not
	 */
	private boolean tableOverLap(Table table1, Table table2) {
		if(table1.getNumber() == table2.getNumber())
			return false;
		
		return linearIntersection(table1.getX(), table1.getWidth(), table2.getX(), table2.getWidth()) || 
				linearIntersection(table1.getY(), table1.getLength(), table2.getY(), table2.getLength());
	}
}
