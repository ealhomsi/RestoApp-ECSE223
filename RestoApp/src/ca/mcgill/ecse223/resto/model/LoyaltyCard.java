/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.io.Serializable;
import java.util.*;

// line 109 "../../../../../RestoAppPersistence.ump"
// line 81 "../../../../../RestoApp v3.ump"
public class LoyaltyCard implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, LoyaltyCard> loyaltycardsByEmailAddress = new HashMap<String, LoyaltyCard>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LoyaltyCard Attributes
  private double point;
  private String clientName;
  private String phoneNumber;
  private String emailAddress;

  //LoyaltyCard Associations
  private List<Order> orders;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LoyaltyCard(double aPoint, String aClientName, String aPhoneNumber, String aEmailAddress, RestoApp aRestoApp)
  {
    point = aPoint;
    clientName = aClientName;
    phoneNumber = aPhoneNumber;
    if (!setEmailAddress(aEmailAddress))
    {
      throw new RuntimeException("Cannot create due to duplicate emailAddress");
    }
    orders = new ArrayList<Order>();
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create loyaltyCard due to restoApp");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPoint(double aPoint)
  {
    boolean wasSet = false;
    point = aPoint;
    wasSet = true;
    return wasSet;
  }

  public boolean setClientName(String aClientName)
  {
    boolean wasSet = false;
    clientName = aClientName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmailAddress(String aEmailAddress)
  {
    boolean wasSet = false;
    String anOldEmailAddress = getEmailAddress();
    if (hasWithEmailAddress(aEmailAddress)) {
      return wasSet;
    }
    emailAddress = aEmailAddress;
    wasSet = true;
    if (anOldEmailAddress != null) {
      loyaltycardsByEmailAddress.remove(anOldEmailAddress);
    }
    loyaltycardsByEmailAddress.put(aEmailAddress, this);
    return wasSet;
  }

  public double getPoint()
  {
    return point;
  }

  public String getClientName()
  {
    return clientName;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }

  public static LoyaltyCard getWithEmailAddress(String aEmailAddress)
  {
    return loyaltycardsByEmailAddress.get(aEmailAddress);
  }

  public static boolean hasWithEmailAddress(String aEmailAddress)
  {
    return getWithEmailAddress(aEmailAddress) != null;
  }

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

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public static int minimumNumberOfOrders()
  {
    return 0;
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    orders.add(aOrder);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    if (orders.contains(aOrder))
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
      existingRestoApp.removeLoyaltyCard(this);
    }
    restoApp.addLoyaltyCard(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    loyaltycardsByEmailAddress.remove(getEmailAddress());
    orders.clear();
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    placeholderRestoApp.removeLoyaltyCard(this);
  }

  // line 114 "../../../../../RestoAppPersistence.ump"
   public static  void reinitializeUniqueEmailAddress(List<LoyaltyCard> loyaltyCards){
    loyaltycardsByEmailAddress = new HashMap<String, LoyaltyCard>();
	for (LoyaltyCard loyaltyCard : loyaltyCards) {
	loyaltycardsByEmailAddress.put(loyaltyCard.getEmailAddress(), loyaltyCard); 
	}
  }


  public String toString()
  {
    return super.toString() + "["+
            "point" + ":" + getPoint()+ "," +
            "clientName" + ":" + getClientName()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "emailAddress" + ":" + getEmailAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 112 ../../../../../RestoAppPersistence.ump
  private static final long serialVersionUID = 5837274928374635219L ;

  
}