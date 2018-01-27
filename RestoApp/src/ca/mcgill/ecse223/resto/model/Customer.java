/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 54 "../../../../../model.ump"
public class Customer
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Associations
  private List<Order> orders;
  private UsedSeat usedseat;
  private ClientInfo info;
  private Bill bill;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(UsedSeat aUsedseat, ClientInfo aInfo, Bill aBill)
  {
    orders = new ArrayList<Order>();
    if (aUsedseat == null || aUsedseat.getCustomer() != null)
    {
      throw new RuntimeException("Unable to create Customer due to aUsedseat");
    }
    usedseat = aUsedseat;
    if (!setInfo(aInfo))
    {
      throw new RuntimeException("Unable to create Customer due to aInfo");
    }
    boolean didAddBill = setBill(aBill);
    if (!didAddBill)
    {
      throw new RuntimeException("Unable to create customer due to bill");
    }
  }

  public Customer(Date aDateForUsedseat, Time aStartTimeForUsedseat, Time aEndTimeForUsedseat, Seat aSeatForUsedseat, ClientInfo aInfo, Bill aBill)
  {
    orders = new ArrayList<Order>();
    usedseat = new UsedSeat(aDateForUsedseat, aStartTimeForUsedseat, aEndTimeForUsedseat, aSeatForUsedseat, this);
    boolean didAddInfo = setInfo(aInfo);
    if (!didAddInfo)
    {
      throw new RuntimeException("Unable to create customer due to info");
    }
    boolean didAddBill = setBill(aBill);
    if (!didAddBill)
    {
      throw new RuntimeException("Unable to create customer due to bill");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Order getOrder(int index)
  {
    Order aOrder = orders.get(index);
    return aOrder;
  }

  public List<Order> getOrders()
  {
    List<Order> newOrders = Collections.unmodifiableList(orders);
    return newOrders;
  }

  public int numberOfOrders()
  {
    int number = orders.size();
    return number;
  }

  public boolean hasOrders()
  {
    boolean has = orders.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = orders.indexOf(aOrder);
    return index;
  }

  public UsedSeat getUsedseat()
  {
    return usedseat;
  }

  public ClientInfo getInfo()
  {
    return info;
  }

  public Bill getBill()
  {
    return bill;
  }

  public static int minimumNumberOfOrders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Order addOrder(Date aOrderDate, Time aOrderTime, Waiter aWaiter)
  {
    return new Order(aOrderDate, aOrderTime, aWaiter, this);
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    Customer existingCustomer = aOrder.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aOrder.setCustomer(this);
    }
    else
    {
      orders.add(aOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrder, as it must always have a customer
    if (!this.equals(aOrder.getCustomer()))
    {
      orders.remove(aOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(orders.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }

  public boolean setInfo(ClientInfo aNewInfo)
  {
    boolean wasSet = false;
    if (aNewInfo != null)
    {
      info = aNewInfo;
      wasSet = true;
    }
    return wasSet;
  }

  public boolean setBill(Bill aBill)
  {
    boolean wasSet = false;
    if (aBill == null)
    {
      return wasSet;
    }

    Bill existingBill = bill;
    bill = aBill;
    if (existingBill != null && !existingBill.equals(aBill))
    {
      existingBill.removeCustomer(this);
    }
    bill.addCustomer(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (orders.size() > 0)
    {
      Order aOrder = orders.get(orders.size() - 1);
      aOrder.delete();
      orders.remove(aOrder);
    }
    
    UsedSeat existingUsedseat = usedseat;
    usedseat = null;
    if (existingUsedseat != null)
    {
      existingUsedseat.delete();
    }
    info = null;
    Bill placeholderBill = bill;
    this.bill = null;
    if(placeholderBill != null)
    {
      placeholderBill.removeCustomer(this);
    }
  }

}