/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 20 "../../../../../model.ump"
public class PricedMenuItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PricedMenuItem Attributes
  private double price;

  //PricedMenuItem Associations
  private List<OrderItem> orderitems;
  private MenuItem menuItem;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PricedMenuItem(double aPrice, MenuItem aMenuItem, RestoApp aRestoApp)
  {
    price = aPrice;
    orderitems = new ArrayList<OrderItem>();
    boolean didAddMenuItem = setMenuItem(aMenuItem);
    if (!didAddMenuItem)
    {
      throw new RuntimeException("Unable to create pricedMenuItem due to menuItem");
    }
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create pricedMenuItem due to restoApp");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPrice(double aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public double getPrice()
  {
    return price;
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

  public MenuItem getMenuItem()
  {
    return menuItem;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
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
    if (aOrderitem.indexOfPricedMenuItem(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOrderitem.addPricedMenuItem(this);
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
    if (aOrderitem.indexOfPricedMenuItem(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOrderitem.removePricedMenuItem(this);
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

  public boolean setMenuItem(MenuItem aMenuItem)
  {
    boolean wasSet = false;
    //Must provide menuItem to pricedMenuItem
    if (aMenuItem == null)
    {
      return wasSet;
    }

    if (menuItem != null && menuItem.numberOfPricedMenuItems() <= MenuItem.minimumNumberOfPricedMenuItems())
    {
      return wasSet;
    }

    MenuItem existingMenuItem = menuItem;
    menuItem = aMenuItem;
    if (existingMenuItem != null && !existingMenuItem.equals(aMenuItem))
    {
      boolean didRemove = existingMenuItem.removePricedMenuItem(this);
      if (!didRemove)
      {
        menuItem = existingMenuItem;
        return wasSet;
      }
    }
    menuItem.addPricedMenuItem(this);
    wasSet = true;
    return wasSet;
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
      existingRestoApp.removePricedMenuItem(this);
    }
    restoApp.addPricedMenuItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<OrderItem> copyOfOrderitems = new ArrayList<OrderItem>(orderitems);
    orderitems.clear();
    for(OrderItem aOrderitem : copyOfOrderitems)
    {
      aOrderitem.removePricedMenuItem(this);
    }
    MenuItem placeholderMenuItem = menuItem;
    this.menuItem = null;
    if(placeholderMenuItem != null)
    {
      placeholderMenuItem.removePricedMenuItem(this);
    }
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removePricedMenuItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "menuItem = "+(getMenuItem()!=null?Integer.toHexString(System.identityHashCode(getMenuItem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }
}