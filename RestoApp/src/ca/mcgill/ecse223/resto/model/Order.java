/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.sql.Date;
import java.util.*;

// line 3 "../../../../../model.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private Date dateTime;
  private int number;

  //Order Associations
  private List<OrderItem> orderitems;
  private List<Bill> bills;
  private List<Table> tables;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(Date aDateTime, int aNumber, RestoApp aRestoApp, Table... allTables)
  {
    dateTime = aDateTime;
    number = aNumber;
    orderitems = new ArrayList<OrderItem>();
    bills = new ArrayList<Bill>();
    tables = new ArrayList<Table>();
    boolean didAddTables = setTables(allTables);
    if (!didAddTables)
    {
      throw new RuntimeException("Unable to create Order, must have at least 1 tables");
    }
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create order due to restoApp");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDateTime(Date aDateTime)
  {
    boolean wasSet = false;
    dateTime = aDateTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public Date getDateTime()
  {
    return dateTime;
  }

  public int getNumber()
  {
    return number;
  }

  public OrderItem getOrderitem(int index)
  {
    OrderItem aOrderitem = orderitems.get(index);
    return aOrderitem;
  }

  public List<OrderItem> getOrderitems()
  {
    List<OrderItem> newOrderitems = Collections.unmodifiableList(orderitems);
    return newOrderitems;
  }

  public int numberOfOrderitems()
  {
    int number = orderitems.size();
    return number;
  }

  public boolean hasOrderitems()
  {
    boolean has = orderitems.size() > 0;
    return has;
  }

  public int indexOfOrderitem(OrderItem aOrderitem)
  {
    int index = orderitems.indexOf(aOrderitem);
    return index;
  }

  public Bill getBill(int index)
  {
    Bill aBill = bills.get(index);
    return aBill;
  }

  public List<Bill> getBills()
  {
    List<Bill> newBills = Collections.unmodifiableList(bills);
    return newBills;
  }

  public int numberOfBills()
  {
    int number = bills.size();
    return number;
  }

  public boolean hasBills()
  {
    boolean has = bills.size() > 0;
    return has;
  }

  public int indexOfBill(Bill aBill)
  {
    int index = bills.indexOf(aBill);
    return index;
  }

  public Table getTable(int index)
  {
    Table aTable = tables.get(index);
    return aTable;
  }

  public List<Table> getTables()
  {
    List<Table> newTables = Collections.unmodifiableList(tables);
    return newTables;
  }

  public int numberOfTables()
  {
    int number = tables.size();
    return number;
  }

  public boolean hasTables()
  {
    boolean has = tables.size() > 0;
    return has;
  }

  public int indexOfTable(Table aTable)
  {
    int index = tables.indexOf(aTable);
    return index;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public static int minimumNumberOfOrderitems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OrderItem addOrderitem(int aQuantity, Seat... allSeats)
  {
    return new OrderItem(aQuantity, this, allSeats);
  }

  public boolean addOrderitem(OrderItem aOrderitem)
  {
    boolean wasAdded = false;
    if (orderitems.contains(aOrderitem)) { return false; }
    Order existingOrder = aOrderitem.getOrder();
    boolean isNewOrder = existingOrder != null && !this.equals(existingOrder);
    if (isNewOrder)
    {
      aOrderitem.setOrder(this);
    }
    else
    {
      orderitems.add(aOrderitem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrderitem(OrderItem aOrderitem)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrderitem, as it must always have a order
    if (!this.equals(aOrderitem.getOrder()))
    {
      orderitems.remove(aOrderitem);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addOrderitemAt(OrderItem aOrderitem, int index)
  {  
    boolean wasAdded = false;
    if(addOrderitem(aOrderitem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderitems()) { index = numberOfOrderitems() - 1; }
      orderitems.remove(aOrderitem);
      orderitems.add(index, aOrderitem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderitemAt(OrderItem aOrderitem, int index)
  {
    boolean wasAdded = false;
    if(orderitems.contains(aOrderitem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderitems()) { index = numberOfOrderitems() - 1; }
      orderitems.remove(aOrderitem);
      orderitems.add(index, aOrderitem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderitemAt(aOrderitem, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfBills()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Bill addBill(RestoApp aRestoApp, Seat... allIssuedForSeats)
  {
    return new Bill(this, aRestoApp, allIssuedForSeats);
  }

  public boolean addBill(Bill aBill)
  {
    boolean wasAdded = false;
    if (bills.contains(aBill)) { return false; }
    Order existingOrder = aBill.getOrder();
    boolean isNewOrder = existingOrder != null && !this.equals(existingOrder);
    if (isNewOrder)
    {
      aBill.setOrder(this);
    }
    else
    {
      bills.add(aBill);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBill(Bill aBill)
  {
    boolean wasRemoved = false;
    //Unable to remove aBill, as it must always have a order
    if (!this.equals(aBill.getOrder()))
    {
      bills.remove(aBill);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addBillAt(Bill aBill, int index)
  {  
    boolean wasAdded = false;
    if(addBill(aBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBills()) { index = numberOfBills() - 1; }
      bills.remove(aBill);
      bills.add(index, aBill);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBillAt(Bill aBill, int index)
  {
    boolean wasAdded = false;
    if(bills.contains(aBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBills()) { index = numberOfBills() - 1; }
      bills.remove(aBill);
      bills.add(index, aBill);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBillAt(aBill, index);
    }
    return wasAdded;
  }

  public boolean isNumberOfTablesValid()
  {
    boolean isValid = numberOfTables() >= minimumNumberOfTables();
    return isValid;
  }

  public static int minimumNumberOfTables()
  {
    return 1;
  }

  public boolean addTable(Table aTable)
  {
    boolean wasAdded = false;
    if (tables.contains(aTable)) { return false; }
    tables.add(aTable);
    if (aTable.indexOfOrder(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTable.addOrder(this);
      if (!wasAdded)
      {
        tables.remove(aTable);
      }
    }
    return wasAdded;
  }

  public boolean removeTable(Table aTable)
  {
    boolean wasRemoved = false;
    if (!tables.contains(aTable))
    {
      return wasRemoved;
    }

    if (numberOfTables() <= minimumNumberOfTables())
    {
      return wasRemoved;
    }

    int oldIndex = tables.indexOf(aTable);
    tables.remove(oldIndex);
    if (aTable.indexOfOrder(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTable.removeOrder(this);
      if (!wasRemoved)
      {
        tables.add(oldIndex,aTable);
      }
    }
    return wasRemoved;
  }

  public boolean setTables(Table... newTables)
  {
    boolean wasSet = false;
    ArrayList<Table> verifiedTables = new ArrayList<Table>();
    for (Table aTable : newTables)
    {
      if (verifiedTables.contains(aTable))
      {
        continue;
      }
      verifiedTables.add(aTable);
    }

    if (verifiedTables.size() != newTables.length || verifiedTables.size() < minimumNumberOfTables())
    {
      return wasSet;
    }

    ArrayList<Table> oldTables = new ArrayList<Table>(tables);
    tables.clear();
    for (Table aNewTable : verifiedTables)
    {
      tables.add(aNewTable);
      if (oldTables.contains(aNewTable))
      {
        oldTables.remove(aNewTable);
      }
      else
      {
        aNewTable.addOrder(this);
      }
    }

    for (Table anOldTable : oldTables)
    {
      anOldTable.removeOrder(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addTableAt(Table aTable, int index)
  {  
    boolean wasAdded = false;
    if(addTable(aTable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTables()) { index = numberOfTables() - 1; }
      tables.remove(aTable);
      tables.add(index, aTable);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTableAt(Table aTable, int index)
  {
    boolean wasAdded = false;
    if(tables.contains(aTable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTables()) { index = numberOfTables() - 1; }
      tables.remove(aTable);
      tables.add(index, aTable);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTableAt(aTable, index);
    }
    return wasAdded;
  }

  public boolean setRestoApp(RestoApp aRestoApp)
  {
    boolean wasSet = false;
    if (aRestoApp == null)
    {
      return wasSet;
    }

    RestoApp existingRestoApp = restoApp;
    restoApp = aRestoApp;
    if (existingRestoApp != null && !existingRestoApp.equals(aRestoApp))
    {
      existingRestoApp.removeOrder(this);
    }
    restoApp.addOrder(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (orderitems.size() > 0)
    {
      OrderItem aOrderitem = orderitems.get(orderitems.size() - 1);
      aOrderitem.delete();
      orderitems.remove(aOrderitem);
    }
    
    for(int i=bills.size(); i > 0; i--)
    {
      Bill aBill = bills.get(i - 1);
      aBill.delete();
    }
    ArrayList<Table> copyOfTables = new ArrayList<Table>(tables);
    tables.clear();
    for(Table aTable : copyOfTables)
    {
      aTable.removeOrder(this);
    }
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeOrder(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "dateTime" + "=" + (getDateTime() != null ? !getDateTime().equals(this)  ? getDateTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }
}