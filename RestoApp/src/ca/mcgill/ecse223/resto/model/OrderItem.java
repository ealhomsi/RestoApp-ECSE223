/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 11 "../../../../../model.ump"
public class OrderItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OrderItem Attributes
  private int quantity;

  //OrderItem Associations
  private Order order;
  private List<PricedMenuItem> pricedMenuItems;
  private List<Seat> seats;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OrderItem(int aQuantity, Order aOrder, Seat... allSeats)
  {
    quantity = aQuantity;
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create orderitem due to order");
    }
    pricedMenuItems = new ArrayList<PricedMenuItem>();
    seats = new ArrayList<Seat>();
    boolean didAddSeats = setSeats(allSeats);
    if (!didAddSeats)
    {
      throw new RuntimeException("Unable to create OrderItem, must have at least 1 seats");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public Order getOrder()
  {
    return order;
  }

  public PricedMenuItem getPricedMenuItem(int index)
  {
    PricedMenuItem aPricedMenuItem = pricedMenuItems.get(index);
    return aPricedMenuItem;
  }

  public List<PricedMenuItem> getPricedMenuItems()
  {
    List<PricedMenuItem> newPricedMenuItems = Collections.unmodifiableList(pricedMenuItems);
    return newPricedMenuItems;
  }

  public int numberOfPricedMenuItems()
  {
    int number = pricedMenuItems.size();
    return number;
  }

  public boolean hasPricedMenuItems()
  {
    boolean has = pricedMenuItems.size() > 0;
    return has;
  }

  public int indexOfPricedMenuItem(PricedMenuItem aPricedMenuItem)
  {
    int index = pricedMenuItems.indexOf(aPricedMenuItem);
    return index;
  }

  public Seat getSeat(int index)
  {
    Seat aSeat = seats.get(index);
    return aSeat;
  }

  public List<Seat> getSeats()
  {
    List<Seat> newSeats = Collections.unmodifiableList(seats);
    return newSeats;
  }

  public int numberOfSeats()
  {
    int number = seats.size();
    return number;
  }

  public boolean hasSeats()
  {
    boolean has = seats.size() > 0;
    return has;
  }

  public int indexOfSeat(Seat aSeat)
  {
    int index = seats.indexOf(aSeat);
    return index;
  }

  public boolean setOrder(Order aOrder)
  {
    boolean wasSet = false;
    if (aOrder == null)
    {
      return wasSet;
    }

    Order existingOrder = order;
    order = aOrder;
    if (existingOrder != null && !existingOrder.equals(aOrder))
    {
      existingOrder.removeOrderitem(this);
    }
    order.addOrderitem(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfPricedMenuItems()
  {
    return 0;
  }

  public boolean addPricedMenuItem(PricedMenuItem aPricedMenuItem)
  {
    boolean wasAdded = false;
    if (pricedMenuItems.contains(aPricedMenuItem)) { return false; }
    pricedMenuItems.add(aPricedMenuItem);
    if (aPricedMenuItem.indexOfOrderitem(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPricedMenuItem.addOrderitem(this);
      if (!wasAdded)
      {
        pricedMenuItems.remove(aPricedMenuItem);
      }
    }
    return wasAdded;
  }

  public boolean removePricedMenuItem(PricedMenuItem aPricedMenuItem)
  {
    boolean wasRemoved = false;
    if (!pricedMenuItems.contains(aPricedMenuItem))
    {
      return wasRemoved;
    }

    int oldIndex = pricedMenuItems.indexOf(aPricedMenuItem);
    pricedMenuItems.remove(oldIndex);
    if (aPricedMenuItem.indexOfOrderitem(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPricedMenuItem.removeOrderitem(this);
      if (!wasRemoved)
      {
        pricedMenuItems.add(oldIndex,aPricedMenuItem);
      }
    }
    return wasRemoved;
  }

  public boolean addPricedMenuItemAt(PricedMenuItem aPricedMenuItem, int index)
  {  
    boolean wasAdded = false;
    if(addPricedMenuItem(aPricedMenuItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPricedMenuItems()) { index = numberOfPricedMenuItems() - 1; }
      pricedMenuItems.remove(aPricedMenuItem);
      pricedMenuItems.add(index, aPricedMenuItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePricedMenuItemAt(PricedMenuItem aPricedMenuItem, int index)
  {
    boolean wasAdded = false;
    if(pricedMenuItems.contains(aPricedMenuItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPricedMenuItems()) { index = numberOfPricedMenuItems() - 1; }
      pricedMenuItems.remove(aPricedMenuItem);
      pricedMenuItems.add(index, aPricedMenuItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPricedMenuItemAt(aPricedMenuItem, index);
    }
    return wasAdded;
  }

  public boolean isNumberOfSeatsValid()
  {
    boolean isValid = numberOfSeats() >= minimumNumberOfSeats();
    return isValid;
  }

  public static int minimumNumberOfSeats()
  {
    return 1;
  }

  public boolean addSeat(Seat aSeat)
  {
    boolean wasAdded = false;
    if (seats.contains(aSeat)) { return false; }
    seats.add(aSeat);
    if (aSeat.indexOfOrderitem(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSeat.addOrderitem(this);
      if (!wasAdded)
      {
        seats.remove(aSeat);
      }
    }
    return wasAdded;
  }

  public boolean removeSeat(Seat aSeat)
  {
    boolean wasRemoved = false;
    if (!seats.contains(aSeat))
    {
      return wasRemoved;
    }

    if (numberOfSeats() <= minimumNumberOfSeats())
    {
      return wasRemoved;
    }

    int oldIndex = seats.indexOf(aSeat);
    seats.remove(oldIndex);
    if (aSeat.indexOfOrderitem(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSeat.removeOrderitem(this);
      if (!wasRemoved)
      {
        seats.add(oldIndex,aSeat);
      }
    }
    return wasRemoved;
  }

  public boolean setSeats(Seat... newSeats)
  {
    boolean wasSet = false;
    ArrayList<Seat> verifiedSeats = new ArrayList<Seat>();
    for (Seat aSeat : newSeats)
    {
      if (verifiedSeats.contains(aSeat))
      {
        continue;
      }
      verifiedSeats.add(aSeat);
    }

    if (verifiedSeats.size() != newSeats.length || verifiedSeats.size() < minimumNumberOfSeats())
    {
      return wasSet;
    }

    ArrayList<Seat> oldSeats = new ArrayList<Seat>(seats);
    seats.clear();
    for (Seat aNewSeat : verifiedSeats)
    {
      seats.add(aNewSeat);
      if (oldSeats.contains(aNewSeat))
      {
        oldSeats.remove(aNewSeat);
      }
      else
      {
        aNewSeat.addOrderitem(this);
      }
    }

    for (Seat anOldSeat : oldSeats)
    {
      anOldSeat.removeOrderitem(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addSeatAt(Seat aSeat, int index)
  {  
    boolean wasAdded = false;
    if(addSeat(aSeat))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeats()) { index = numberOfSeats() - 1; }
      seats.remove(aSeat);
      seats.add(index, aSeat);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSeatAt(Seat aSeat, int index)
  {
    boolean wasAdded = false;
    if(seats.contains(aSeat))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeats()) { index = numberOfSeats() - 1; }
      seats.remove(aSeat);
      seats.add(index, aSeat);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSeatAt(aSeat, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Order placeholderOrder = order;
    this.order = null;
    if(placeholderOrder != null)
    {
      placeholderOrder.removeOrderitem(this);
    }
    ArrayList<PricedMenuItem> copyOfPricedMenuItems = new ArrayList<PricedMenuItem>(pricedMenuItems);
    pricedMenuItems.clear();
    for(PricedMenuItem aPricedMenuItem : copyOfPricedMenuItems)
    {
      aPricedMenuItem.removeOrderitem(this);
    }
    ArrayList<Seat> copyOfSeats = new ArrayList<Seat>(seats);
    seats.clear();
    for(Seat aSeat : copyOfSeats)
    {
      aSeat.removeOrderitem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null");
  }
}