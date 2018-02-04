/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 58 "../../../../../model.ump"
public class Seat
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Seat Associations
  private List<OrderItem> orderitems;
  private List<Bill> bills;
  private Table table;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Seat(Table aTable)
  {
    orderitems = new ArrayList<OrderItem>();
    bills = new ArrayList<Bill>();
    boolean didAddTable = setTable(aTable);
    if (!didAddTable)
    {
      throw new RuntimeException("Unable to create seat due to table");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public Table getTable()
  {
    return table;
  }

  public static int minimumNumberOfOrderitems()
  {
    return 0;
  }

  public boolean addOrderitem(OrderItem aOrderitem)
  {
    boolean wasAdded = false;
    if (orderitems.contains(aOrderitem)) { return false; }
    orderitems.add(aOrderitem);
    if (aOrderitem.indexOfSeat(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOrderitem.addSeat(this);
      if (!wasAdded)
      {
        orderitems.remove(aOrderitem);
      }
    }
    return wasAdded;
  }

  public boolean removeOrderitem(OrderItem aOrderitem)
  {
    boolean wasRemoved = false;
    if (!orderitems.contains(aOrderitem))
    {
      return wasRemoved;
    }

    int oldIndex = orderitems.indexOf(aOrderitem);
    orderitems.remove(oldIndex);
    if (aOrderitem.indexOfSeat(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOrderitem.removeSeat(this);
      if (!wasRemoved)
      {
        orderitems.add(oldIndex,aOrderitem);
      }
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

  public boolean addBill(Bill aBill)
  {
    boolean wasAdded = false;
    if (bills.contains(aBill)) { return false; }
    bills.add(aBill);
    if (aBill.indexOfIssuedForSeat(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBill.addIssuedForSeat(this);
      if (!wasAdded)
      {
        bills.remove(aBill);
      }
    }
    return wasAdded;
  }

  public boolean removeBill(Bill aBill)
  {
    boolean wasRemoved = false;
    if (!bills.contains(aBill))
    {
      return wasRemoved;
    }

    int oldIndex = bills.indexOf(aBill);
    bills.remove(oldIndex);
    if (aBill.indexOfIssuedForSeat(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBill.removeIssuedForSeat(this);
      if (!wasRemoved)
      {
        bills.add(oldIndex,aBill);
      }
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

  public boolean setTable(Table aTable)
  {
    boolean wasSet = false;
    //Must provide table to seat
    if (aTable == null)
    {
      return wasSet;
    }

    if (table != null && table.numberOfSeats() <= Table.minimumNumberOfSeats())
    {
      return wasSet;
    }

    Table existingTable = table;
    table = aTable;
    if (existingTable != null && !existingTable.equals(aTable))
    {
      boolean didRemove = existingTable.removeSeat(this);
      if (!didRemove)
      {
        table = existingTable;
        return wasSet;
      }
    }
    table.addSeat(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<OrderItem> copyOfOrderitems = new ArrayList<OrderItem>(orderitems);
    orderitems.clear();
    for(OrderItem aOrderitem : copyOfOrderitems)
    {
      if (aOrderitem.numberOfSeats() <= OrderItem.minimumNumberOfSeats())
      {
        aOrderitem.delete();
      }
      else
      {
        aOrderitem.removeSeat(this);
      }
    }
    ArrayList<Bill> copyOfBills = new ArrayList<Bill>(bills);
    bills.clear();
    for(Bill aBill : copyOfBills)
    {
      if (aBill.numberOfIssuedForSeats() <= Bill.minimumNumberOfIssuedForSeats())
      {
        aBill.delete();
      }
      else
      {
        aBill.removeIssuedForSeat(this);
      }
    }
    Table placeholderTable = table;
    this.table = null;
    if(placeholderTable != null)
    {
      placeholderTable.removeSeat(this);
    }
  }

}