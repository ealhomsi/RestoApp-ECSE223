/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

// line 44 "../../../../../model.ump"
public class SharedOrder extends Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SharedOrder Attributes
  private double percentage;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SharedOrder(Date aOrderDate, Time aOrderTime, Waiter aWaiter, Customer aCustomer, double aPercentage)
  {
    super(aOrderDate, aOrderTime, aWaiter, aCustomer);
    percentage = aPercentage;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPercentage(double aPercentage)
  {
    boolean wasSet = false;
    percentage = aPercentage;
    wasSet = true;
    return wasSet;
  }

  public double getPercentage()
  {
    return percentage;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "percentage" + ":" + getPercentage()+ "]";
  }
}