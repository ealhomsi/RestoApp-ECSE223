/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

// line 33 "../../../../../model.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private Date orderDate;
  private Time orderTime;

  //Order Associations
  private List<OrderEntry> entries;
  private Waiter waiter;
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(Date aOrderDate, Time aOrderTime, Waiter aWaiter, Customer aCustomer)
  {
    orderDate = aOrderDate;
    orderTime = aOrderTime;
    entries = new ArrayList<OrderEntry>();
    boolean didAddWaiter = setWaiter(aWaiter);
    if (!didAddWaiter)
    {
      throw new RuntimeException("Unable to create order due to waiter");
    }
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create order due to customer");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setOrderDate(Date aOrderDate)
  {
    boolean wasSet = false;
    orderDate = aOrderDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setOrderTime(Time aOrderTime)
  {
    boolean wasSet = false;
    orderTime = aOrderTime;
    wasSet = true;
    return wasSet;
  }

  public Date getOrderDate()
  {
    return orderDate;
  }

  public Time getOrderTime()
  {
    return orderTime;
  }

  public OrderEntry getEntry(int index)
  {
    OrderEntry aEntry = entries.get(index);
    return aEntry;
  }

  public List<OrderEntry> getEntries()
  {
    List<OrderEntry> newEntries = Collections.unmodifiableList(entries);
    return newEntries;
  }

  public int numberOfEntries()
  {
    int number = entries.size();
    return number;
  }

  public boolean hasEntries()
  {
    boolean has = entries.size() > 0;
    return has;
  }

  public int indexOfEntry(OrderEntry aEntry)
  {
    int index = entries.indexOf(aEntry);
    return index;
  }

  public Waiter getWaiter()
  {
    return waiter;
  }

  public Customer getCustomer()
  {
    return customer;
  }

  public static int minimumNumberOfEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OrderEntry addEntry(int aCount, MenuEntry aItem)
  {
    return new OrderEntry(aCount, aItem, this);
  }

  public boolean addEntry(OrderEntry aEntry)
  {
    boolean wasAdded = false;
    if (entries.contains(aEntry)) { return false; }
    Order existingOrder = aEntry.getOrder();
    boolean isNewOrder = existingOrder != null && !this.equals(existingOrder);
    if (isNewOrder)
    {
      aEntry.setOrder(this);
    }
    else
    {
      entries.add(aEntry);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEntry(OrderEntry aEntry)
  {
    boolean wasRemoved = false;
    //Unable to remove aEntry, as it must always have a order
    if (!this.equals(aEntry.getOrder()))
    {
      entries.remove(aEntry);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEntryAt(OrderEntry aEntry, int index)
  {  
    boolean wasAdded = false;
    if(addEntry(aEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEntries()) { index = numberOfEntries() - 1; }
      entries.remove(aEntry);
      entries.add(index, aEntry);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEntryAt(OrderEntry aEntry, int index)
  {
    boolean wasAdded = false;
    if(entries.contains(aEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEntries()) { index = numberOfEntries() - 1; }
      entries.remove(aEntry);
      entries.add(index, aEntry);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEntryAt(aEntry, index);
    }
    return wasAdded;
  }

  public boolean setWaiter(Waiter aWaiter)
  {
    boolean wasSet = false;
    if (aWaiter == null)
    {
      return wasSet;
    }

    Waiter existingWaiter = waiter;
    waiter = aWaiter;
    if (existingWaiter != null && !existingWaiter.equals(aWaiter))
    {
      existingWaiter.removeOrder(this);
    }
    waiter.addOrder(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeOrder(this);
    }
    customer.addOrder(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (entries.size() > 0)
    {
      OrderEntry aEntry = entries.get(entries.size() - 1);
      aEntry.delete();
      entries.remove(aEntry);
    }
    
    Waiter placeholderWaiter = waiter;
    this.waiter = null;
    if(placeholderWaiter != null)
    {
      placeholderWaiter.removeOrder(this);
    }
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeOrder(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "orderDate" + "=" + (getOrderDate() != null ? !getOrderDate().equals(this)  ? getOrderDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "orderTime" + "=" + (getOrderTime() != null ? !getOrderTime().equals(this)  ? getOrderTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "waiter = "+(getWaiter()!=null?Integer.toHexString(System.identityHashCode(getWaiter())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}