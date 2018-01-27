/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

// line 60 "../../../../../model.ump"
public class Bill
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bill Attributes
  private Date billDate;
  private Time billTime;

  //Bill Associations
  private List<Customer> customers;
  private Waiter waiter;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bill(Date aBillDate, Time aBillTime, Waiter aWaiter)
  {
    billDate = aBillDate;
    billTime = aBillTime;
    customers = new ArrayList<Customer>();
    boolean didAddWaiter = setWaiter(aWaiter);
    if (!didAddWaiter)
    {
      throw new RuntimeException("Unable to create bill due to waiter");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBillDate(Date aBillDate)
  {
    boolean wasSet = false;
    billDate = aBillDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setBillTime(Time aBillTime)
  {
    boolean wasSet = false;
    billTime = aBillTime;
    wasSet = true;
    return wasSet;
  }

  public Date getBillDate()
  {
    return billDate;
  }

  public Time getBillTime()
  {
    return billTime;
  }

  public Customer getCustomer(int index)
  {
    Customer aCustomer = customers.get(index);
    return aCustomer;
  }

  public List<Customer> getCustomers()
  {
    List<Customer> newCustomers = Collections.unmodifiableList(customers);
    return newCustomers;
  }

  public int numberOfCustomers()
  {
    int number = customers.size();
    return number;
  }

  public boolean hasCustomers()
  {
    boolean has = customers.size() > 0;
    return has;
  }

  public int indexOfCustomer(Customer aCustomer)
  {
    int index = customers.indexOf(aCustomer);
    return index;
  }

  public Waiter getWaiter()
  {
    return waiter;
  }

  public static int minimumNumberOfCustomers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Customer addCustomer(UsedSeat aUsedseat, ClientInfo aInfo)
  {
    return new Customer(aUsedseat, aInfo, this);
  }

  public boolean addCustomer(Customer aCustomer)
  {
    boolean wasAdded = false;
    if (customers.contains(aCustomer)) { return false; }
    Bill existingBill = aCustomer.getBill();
    boolean isNewBill = existingBill != null && !this.equals(existingBill);
    if (isNewBill)
    {
      aCustomer.setBill(this);
    }
    else
    {
      customers.add(aCustomer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCustomer(Customer aCustomer)
  {
    boolean wasRemoved = false;
    //Unable to remove aCustomer, as it must always have a bill
    if (!this.equals(aCustomer.getBill()))
    {
      customers.remove(aCustomer);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCustomerAt(Customer aCustomer, int index)
  {  
    boolean wasAdded = false;
    if(addCustomer(aCustomer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomers()) { index = numberOfCustomers() - 1; }
      customers.remove(aCustomer);
      customers.add(index, aCustomer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCustomerAt(Customer aCustomer, int index)
  {
    boolean wasAdded = false;
    if(customers.contains(aCustomer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomers()) { index = numberOfCustomers() - 1; }
      customers.remove(aCustomer);
      customers.add(index, aCustomer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCustomerAt(aCustomer, index);
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
      existingWaiter.removeBill(this);
    }
    waiter.addBill(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=customers.size(); i > 0; i--)
    {
      Customer aCustomer = customers.get(i - 1);
      aCustomer.delete();
    }
    Waiter placeholderWaiter = waiter;
    this.waiter = null;
    if(placeholderWaiter != null)
    {
      placeholderWaiter.removeBill(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "billDate" + "=" + (getBillDate() != null ? !getBillDate().equals(this)  ? getBillDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "billTime" + "=" + (getBillTime() != null ? !getBillTime().equals(this)  ? getBillTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "waiter = "+(getWaiter()!=null?Integer.toHexString(System.identityHashCode(getWaiter())):"null");
  }
}