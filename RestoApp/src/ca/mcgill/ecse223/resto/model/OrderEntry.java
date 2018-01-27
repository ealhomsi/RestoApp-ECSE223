/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;

// line 28 "../../../../../model.ump"
public class OrderEntry
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OrderEntry Attributes
  private int count;

  //OrderEntry Associations
  private MenuEntry item;
  private Order order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OrderEntry(int aCount, MenuEntry aItem, Order aOrder)
  {
    count = aCount;
    boolean didAddItem = setItem(aItem);
    if (!didAddItem)
    {
      throw new RuntimeException("Unable to create orderEntry due to item");
    }
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create entry due to order");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCount(int aCount)
  {
    boolean wasSet = false;
    count = aCount;
    wasSet = true;
    return wasSet;
  }

  public int getCount()
  {
    return count;
  }

  public MenuEntry getItem()
  {
    return item;
  }

  public Order getOrder()
  {
    return order;
  }

  public boolean setItem(MenuEntry aItem)
  {
    boolean wasSet = false;
    if (aItem == null)
    {
      return wasSet;
    }

    MenuEntry existingItem = item;
    item = aItem;
    if (existingItem != null && !existingItem.equals(aItem))
    {
      existingItem.removeOrderEntry(this);
    }
    item.addOrderEntry(this);
    wasSet = true;
    return wasSet;
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
      existingOrder.removeEntry(this);
    }
    order.addEntry(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    MenuEntry placeholderItem = item;
    this.item = null;
    if(placeholderItem != null)
    {
      placeholderItem.removeOrderEntry(this);
    }
    Order placeholderOrder = order;
    this.order = null;
    if(placeholderOrder != null)
    {
      placeholderOrder.removeEntry(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "count" + ":" + getCount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "item = "+(getItem()!=null?Integer.toHexString(System.identityHashCode(getItem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null");
  }
}