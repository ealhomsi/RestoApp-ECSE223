/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

// line 60 "../../../../../model.ump"
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
  private String name;
  private String emailAddress;
  private String phoneNumber;
  private Date reservationDate;
  private Time reservationTime;

  //Autounique Attributes
  private int id;

  //Reservation Associations
  private List<Table> tables;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(String aName, String aEmailAddress, String aPhoneNumber, Date aReservationDate, Time aReservationTime, RestoApp aRestoApp)
  {
    name = aName;
    emailAddress = aEmailAddress;
    phoneNumber = aPhoneNumber;
    reservationDate = aReservationDate;
    reservationTime = aReservationTime;
    id = nextId++;
    tables = new ArrayList<Table>();
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create reservation due to restoApp");
    }
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

  public boolean setEmailAddress(String aEmailAddress)
  {
    boolean wasSet = false;
    emailAddress = aEmailAddress;
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

  public String getName()
  {
    return name;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
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

  public RestoApp getRestoApp()
  {
    return restoApp;
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

  public Table addTable(boolean aIsTaken, int aLocationX, int aLocationY, RestoApp aRestoApp)
  {
    Table aNewTable = new Table(aIsTaken, aLocationX, aLocationY, aRestoApp, this);
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
      existingRestoApp.removeReservation(this);
    }
    restoApp.addReservation(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=tables.size(); i > 0; i--)
    {
      Table aTable = tables.get(i - 1);
      aTable.delete();
    }
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeReservation(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "name" + ":" + getName()+ "," +
            "emailAddress" + ":" + getEmailAddress()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reservationDate" + "=" + (getReservationDate() != null ? !getReservationDate().equals(this)  ? getReservationDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "reservationTime" + "=" + (getReservationTime() != null ? !getReservationTime().equals(this)  ? getReservationTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }
}