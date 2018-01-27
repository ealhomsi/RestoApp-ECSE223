/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

// line 40 "../../../../../model.ump"
public class InvdividualOrder extends Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public InvdividualOrder(Date aOrderDate, Time aOrderTime, Waiter aWaiter, Customer aCustomer)
  {
    super(aOrderDate, aOrderTime, aWaiter, aCustomer);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}