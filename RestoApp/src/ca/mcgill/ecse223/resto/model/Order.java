/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.sql.Time;
import java.util.*;

// line 32 "../../../../../model.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private Time orderTime;
  private double percentage;

  //Order Associations
  private List<OrderEntry> entries;
  private RestoApp restoApp;
  private Seat seat;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(Time aOrderTime, double aPercentage, RestoApp aRestoApp, Seat aSeat)
  {
    orderTime = aOrderTime;
    percentage = aPercentage;
    entries = new ArrayList<OrderEntry>();
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create order due to restoApp");
    }
    boolean didAddSeat = setSeat(aSeat);
    if (!didAddSeat)
    {
      throw new RuntimeException("Unable to create order due to seat");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setOrderTime(Time aOrderTime)
  {
    boolean wasSet = false;
    orderTime = aOrderTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setPercentage(double aPercentage)
  {
    boolean wasSet = false;
    percentage = aPercentage;
    wasSet = true;
    return wasSet;
  }

  public Time getOrderTime()
  {
    return orderTime;
  }

  public double getPercentage()
  {
    return percentage;
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

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public Seat getSeat()
  {
    return seat;
  }

  public static int minimumNumberOfEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OrderEntry addEntry(int aQuantity, MenuItem aItem)
  {
    return new OrderEntry(aQuantity, aItem, this);
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

  public boolean setSeat(Seat aSeat)
  {
    boolean wasSet = false;
    if (aSeat == null)
    {
      return wasSet;
    }

    Seat existingSeat = seat;
    seat = aSeat;
    if (existingSeat != null && !existingSeat.equals(aSeat))
    {
      existingSeat.removeOrder(this);
    }
    seat.addOrder(this);
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
    
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeOrder(this);
    }
    Seat placeholderSeat = seat;
    this.seat = null;
    if(placeholderSeat != null)
    {
      placeholderSeat.removeOrder(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "percentage" + ":" + getPercentage()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "orderTime" + "=" + (getOrderTime() != null ? !getOrderTime().equals(this)  ? getOrderTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "seat = "+(getSeat()!=null?Integer.toHexString(System.identityHashCode(getSeat())):"null");
  }
}