/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 21 "../../../../../model.ump"
public class MenuItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MenuItem Attributes
  private String name;
  private String description;
  private double price;

  //MenuItem Associations
  private List<Menu> menus;
  private List<OrderEntry> orderEntries;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MenuItem(String aName, String aDescription, double aPrice)
  {
    name = aName;
    description = aDescription;
    price = aPrice;
    menus = new ArrayList<Menu>();
    orderEntries = new ArrayList<OrderEntry>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(double aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getDescription()
  {
    return description;
  }

  public double getPrice()
  {
    return price;
  }

  public Menu getMenus(int index)
  {
    Menu aMenus = menus.get(index);
    return aMenus;
  }

  public List<Menu> getMenus()
  {
    List<Menu> newMenus = Collections.unmodifiableList(menus);
    return newMenus;
  }

  public int numberOfMenus()
  {
    int number = menus.size();
    return number;
  }

  public boolean hasMenus()
  {
    boolean has = menus.size() > 0;
    return has;
  }

  public int indexOfMenus(Menu aMenus)
  {
    int index = menus.indexOf(aMenus);
    return index;
  }

  public OrderEntry getOrderEntry(int index)
  {
    OrderEntry aOrderEntry = orderEntries.get(index);
    return aOrderEntry;
  }

  public List<OrderEntry> getOrderEntries()
  {
    List<OrderEntry> newOrderEntries = Collections.unmodifiableList(orderEntries);
    return newOrderEntries;
  }

  public int numberOfOrderEntries()
  {
    int number = orderEntries.size();
    return number;
  }

  public boolean hasOrderEntries()
  {
    boolean has = orderEntries.size() > 0;
    return has;
  }

  public int indexOfOrderEntry(OrderEntry aOrderEntry)
  {
    int index = orderEntries.indexOf(aOrderEntry);
    return index;
  }

  public static int minimumNumberOfMenus()
  {
    return 0;
  }

  public boolean addMenus(Menu aMenus)
  {
    boolean wasAdded = false;
    if (menus.contains(aMenus)) { return false; }
    menus.add(aMenus);
    if (aMenus.indexOfItem(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMenus.addItem(this);
      if (!wasAdded)
      {
        menus.remove(aMenus);
      }
    }
    return wasAdded;
  }

  public boolean removeMenus(Menu aMenus)
  {
    boolean wasRemoved = false;
    if (!menus.contains(aMenus))
    {
      return wasRemoved;
    }

    int oldIndex = menus.indexOf(aMenus);
    menus.remove(oldIndex);
    if (aMenus.indexOfItem(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMenus.removeItem(this);
      if (!wasRemoved)
      {
        menus.add(oldIndex,aMenus);
      }
    }
    return wasRemoved;
  }

  public boolean addMenusAt(Menu aMenus, int index)
  {  
    boolean wasAdded = false;
    if(addMenus(aMenus))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenus()) { index = numberOfMenus() - 1; }
      menus.remove(aMenus);
      menus.add(index, aMenus);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMenusAt(Menu aMenus, int index)
  {
    boolean wasAdded = false;
    if(menus.contains(aMenus))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenus()) { index = numberOfMenus() - 1; }
      menus.remove(aMenus);
      menus.add(index, aMenus);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMenusAt(aMenus, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfOrderEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OrderEntry addOrderEntry(int aCount, Order aOrder)
  {
    return new OrderEntry(aCount, this, aOrder);
  }

  public boolean addOrderEntry(OrderEntry aOrderEntry)
  {
    boolean wasAdded = false;
    if (orderEntries.contains(aOrderEntry)) { return false; }
    MenuItem existingItem = aOrderEntry.getItem();
    boolean isNewItem = existingItem != null && !this.equals(existingItem);
    if (isNewItem)
    {
      aOrderEntry.setItem(this);
    }
    else
    {
      orderEntries.add(aOrderEntry);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrderEntry(OrderEntry aOrderEntry)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrderEntry, as it must always have a item
    if (!this.equals(aOrderEntry.getItem()))
    {
      orderEntries.remove(aOrderEntry);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addOrderEntryAt(OrderEntry aOrderEntry, int index)
  {  
    boolean wasAdded = false;
    if(addOrderEntry(aOrderEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderEntries()) { index = numberOfOrderEntries() - 1; }
      orderEntries.remove(aOrderEntry);
      orderEntries.add(index, aOrderEntry);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderEntryAt(OrderEntry aOrderEntry, int index)
  {
    boolean wasAdded = false;
    if(orderEntries.contains(aOrderEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderEntries()) { index = numberOfOrderEntries() - 1; }
      orderEntries.remove(aOrderEntry);
      orderEntries.add(index, aOrderEntry);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderEntryAt(aOrderEntry, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Menu> copyOfMenus = new ArrayList<Menu>(menus);
    menus.clear();
    for(Menu aMenus : copyOfMenus)
    {
      aMenus.removeItem(this);
    }
    for(int i=orderEntries.size(); i > 0; i--)
    {
      OrderEntry aOrderEntry = orderEntries.get(i - 1);
      aOrderEntry.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "description" + ":" + getDescription()+ "," +
            "price" + ":" + getPrice()+ "]";
  }
}