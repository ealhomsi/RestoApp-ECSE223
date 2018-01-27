/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

// line 85 "../../../../../model.ump"
public class Reservation
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reservation Attributes
  private Date reservationDate;
  private Time reservationTime;

  //Autounique Attributes
  private int id;

  //Reservation Associations
  private ClientInfo client;
  private List<Table> tables;
  private Waiter waiter;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(Date aReservationDate, Time aReservationTime, ClientInfo aClient, Waiter aWaiter)
  {
    reservationDate = aReservationDate;
    reservationTime = aReservationTime;
    id = nextId++;
    boolean didAddClient = setClient(aClient);
    if (!didAddClient)
    {
      throw new RuntimeException("Unable to create reservation due to client");
    }
    tables = new ArrayList<Table>();
    boolean didAddWaiter = setWaiter(aWaiter);
    if (!didAddWaiter)
    {
      throw new RuntimeException("Unable to create reservation due to waiter");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setReservationDate(Date aReservationDate)
  {
    boolean wasSet = false;
    reservationDate = aReservationDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setReservationTime(Time aReservationTime)
  {
    boolean wasSet = false;
    reservationTime = aReservationTime;
    wasSet = true;
    return wasSet;
  }

  public Date getReservationDate()
  {
    return reservationDate;
  }

  public Time getReservationTime()
  {
    return reservationTime;
  }

  public int getId()
  {
    return id;
  }

  public ClientInfo getClient()
  {
    return client;
  }

  public Table getTable(int index)
  {
    Table aTable = tables.get(index);
    return aTable;
  }

  public List<Table> getTables()
  {
    List<Table> newTables = Collections.unmodifiableList(tables);
    return newTables;
  }

  public int numberOfTables()
  {
    int number = tables.size();
    return number;
  }

  public boolean hasTables()
  {
    boolean has = tables.size() > 0;
    return has;
  }

  public int indexOfTable(Table aTable)
  {
    int index = tables.indexOf(aTable);
    return index;
  }

  public Waiter getWaiter()
  {
    return waiter;
  }

  public boolean setClient(ClientInfo aClient)
  {
    boolean wasSet = false;
    if (aClient == null)
    {
      return wasSet;
    }

    ClientInfo existingClient = client;
    client = aClient;
    if (existingClient != null && !existingClient.equals(aClient))
    {
      existingClient.removeReservation(this);
    }
    client.addReservation(this);
    wasSet = true;
    return wasSet;
  }

  public boolean isNumberOfTablesValid()
  {
    boolean isValid = numberOfTables() >= minimumNumberOfTables();
    return isValid;
  }

  public static int minimumNumberOfTables()
  {
    return 1;
  }

  public Table addTable(int aLocationX, int aLocationY, RestoApp aRestoApp)
  {
    Table aNewTable = new Table(aLocationX, aLocationY, aRestoApp, this);
    return aNewTable;
  }

  public boolean addTable(Table aTable)
  {
    boolean wasAdded = false;
    if (tables.contains(aTable)) { return false; }
    Reservation existingReservation = aTable.getReservation();
    boolean isNewReservation = existingReservation != null && !this.equals(existingReservation);

    if (isNewReservation && existingReservation.numberOfTables() <= minimumNumberOfTables())
    {
      return wasAdded;
    }
    if (isNewReservation)
    {
      aTable.setReservation(this);
    }
    else
    {
      tables.add(aTable);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTable(Table aTable)
  {
    boolean wasRemoved = false;
    //Unable to remove aTable, as it must always have a reservation
    if (this.equals(aTable.getReservation()))
    {
      return wasRemoved;
    }

    //reservation already at minimum (1)
    if (numberOfTables() <= minimumNumberOfTables())
    {
      return wasRemoved;
    }

    tables.remove(aTable);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean addTableAt(Table aTable, int index)
  {  
    boolean wasAdded = false;
    if(addTable(aTable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTables()) { index = numberOfTables() - 1; }
      tables.remove(aTable);
      tables.add(index, aTable);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTableAt(Table aTable, int index)
  {
    boolean wasAdded = false;
    if(tables.contains(aTable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTables()) { index = numberOfTables() - 1; }
      tables.remove(aTable);
      tables.add(index, aTable);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTableAt(aTable, index);
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
      existingWaiter.removeReservation(this);
    }
    waiter.addReservation(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ClientInfo placeholderClient = client;
    this.client = null;
    if(placeholderClient != null)
    {
      placeholderClient.removeReservation(this);
    }
    for(int i=tables.size(); i > 0; i--)
    {
      Table aTable = tables.get(i - 1);
      aTable.delete();
    }
    Waiter placeholderWaiter = waiter;
    this.waiter = null;
    if(placeholderWaiter != null)
    {
      placeholderWaiter.removeReservation(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reservationDate" + "=" + (getReservationDate() != null ? !getReservationDate().equals(this)  ? getReservationDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "reservationTime" + "=" + (getReservationTime() != null ? !getReservationTime().equals(this)  ? getReservationTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "client = "+(getClient()!=null?Integer.toHexString(System.identityHashCode(getClient())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "waiter = "+(getWaiter()!=null?Integer.toHexString(System.identityHashCode(getWaiter())):"null");
  }
}