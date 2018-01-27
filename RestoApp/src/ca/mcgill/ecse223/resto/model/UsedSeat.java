/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.sql.Date;
import java.sql.Time;

// line 77 "../../../../../model.ump"
public class UsedSeat
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UsedSeat Attributes
  private Date date;
  private Time startTime;
  private Time endTime;

  //Autounique Attributes
  private int id;

  //UsedSeat Associations
  private Seat seat;
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UsedSeat(Date aDate, Time aStartTime, Time aEndTime, Seat aSeat, Customer aCustomer)
  {
    date = aDate;
    startTime = aStartTime;
    endTime = aEndTime;
    id = nextId++;
    boolean didAddSeat = setSeat(aSeat);
    if (!didAddSeat)
    {
      throw new RuntimeException("Unable to create usedSeat due to seat");
    }
    if (aCustomer == null || aCustomer.getUsedseat() != null)
    {
      throw new RuntimeException("Unable to create UsedSeat due to aCustomer");
    }
    customer = aCustomer;
  }

  public UsedSeat(Date aDate, Time aStartTime, Time aEndTime, Seat aSeat, ClientInfo aInfoForCustomer, Bill aBillForCustomer)
  {
    date = aDate;
    startTime = aStartTime;
    endTime = aEndTime;
    id = nextId++;
    boolean didAddSeat = setSeat(aSeat);
    if (!didAddSeat)
    {
      throw new RuntimeException("Unable to create usedSeat due to seat");
    }
    customer = new Customer(this, aInfoForCustomer, aBillForCustomer);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public int getId()
  {
    return id;
  }

  public Seat getSeat()
  {
    return seat;
  }

  public Customer getCustomer()
  {
    return customer;
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
      existingSeat.removeUsedSeat(this);
    }
    seat.addUsedSeat(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Seat placeholderSeat = seat;
    this.seat = null;
    if(placeholderSeat != null)
    {
      placeholderSeat.removeUsedSeat(this);
    }
    Customer existingCustomer = customer;
    customer = null;
    if (existingCustomer != null)
    {
      existingCustomer.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "seat = "+(getSeat()!=null?Integer.toHexString(System.identityHashCode(getSeat())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}