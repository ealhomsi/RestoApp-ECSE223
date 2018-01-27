/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 66 "../../../../../model.ump"
public class Table
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Table Attributes
  private int locationX;
  private int locationY;

  //Autounique Attributes
  private int id;

  //Table Associations
  private List<Seat> seats;
  private RestoApp restoApp;
  private Reservation reservation;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Table(int aLocationX, int aLocationY, RestoApp aRestoApp, Reservation aReservation)
  {
    locationX = aLocationX;
    locationY = aLocationY;
    id = nextId++;
    seats = new ArrayList<Seat>();
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create table due to restoApp");
    }
    boolean didAddReservation = setReservation(aReservation);
    if (!didAddReservation)
    {
      throw new RuntimeException("Unable to create table due to reservation");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLocationX(int aLocationX)
  {
    boolean wasSet = false;
    locationX = aLocationX;
    wasSet = true;
    return wasSet;
  }

  public boolean setLocationY(int aLocationY)
  {
    boolean wasSet = false;
    locationY = aLocationY;
    wasSet = true;
    return wasSet;
  }

  public int getLocationX()
  {
    return locationX;
  }

  public int getLocationY()
  {
    return locationY;
  }

  public int getId()
  {
    return id;
  }

  public Seat getSeat(int index)
  {
    Seat aSeat = seats.get(index);
    return aSeat;
  }

  public List<Seat> getSeats()
  {
    List<Seat> newSeats = Collections.unmodifiableList(seats);
    return newSeats;
  }

  public int numberOfSeats()
  {
    int number = seats.size();
    return number;
  }

  public boolean hasSeats()
  {
    boolean has = seats.size() > 0;
    return has;
  }

  public int indexOfSeat(Seat aSeat)
  {
    int index = seats.indexOf(aSeat);
    return index;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public Reservation getReservation()
  {
    return reservation;
  }

  public boolean isNumberOfSeatsValid()
  {
    boolean isValid = numberOfSeats() >= minimumNumberOfSeats();
    return isValid;
  }

  public static int minimumNumberOfSeats()
  {
    return 1;
  }

  public Seat addSeat()
  {
    Seat aNewSeat = new Seat(this);
    return aNewSeat;
  }

  public boolean addSeat(Seat aSeat)
  {
    boolean wasAdded = false;
    if (seats.contains(aSeat)) { return false; }
    Table existingTable = aSeat.getTable();
    boolean isNewTable = existingTable != null && !this.equals(existingTable);

    if (isNewTable && existingTable.numberOfSeats() <= minimumNumberOfSeats())
    {
      return wasAdded;
    }
    if (isNewTable)
    {
      aSeat.setTable(this);
    }
    else
    {
      seats.add(aSeat);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSeat(Seat aSeat)
  {
    boolean wasRemoved = false;
    //Unable to remove aSeat, as it must always have a table
    if (this.equals(aSeat.getTable()))
    {
      return wasRemoved;
    }

    //table already at minimum (1)
    if (numberOfSeats() <= minimumNumberOfSeats())
    {
      return wasRemoved;
    }

    seats.remove(aSeat);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean addSeatAt(Seat aSeat, int index)
  {  
    boolean wasAdded = false;
    if(addSeat(aSeat))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeats()) { index = numberOfSeats() - 1; }
      seats.remove(aSeat);
      seats.add(index, aSeat);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSeatAt(Seat aSeat, int index)
  {
    boolean wasAdded = false;
    if(seats.contains(aSeat))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeats()) { index = numberOfSeats() - 1; }
      seats.remove(aSeat);
      seats.add(index, aSeat);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSeatAt(aSeat, index);
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
      existingRestoApp.removeTable(this);
    }
    restoApp.addTable(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setReservation(Reservation aReservation)
  {
    boolean wasSet = false;
    //Must provide reservation to table
    if (aReservation == null)
    {
      return wasSet;
    }

    if (reservation != null && reservation.numberOfTables() <= Reservation.minimumNumberOfTables())
    {
      return wasSet;
    }

    Reservation existingReservation = reservation;
    reservation = aReservation;
    if (existingReservation != null && !existingReservation.equals(aReservation))
    {
      boolean didRemove = existingReservation.removeTable(this);
      if (!didRemove)
      {
        reservation = existingReservation;
        return wasSet;
      }
    }
    reservation.addTable(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=seats.size(); i > 0; i--)
    {
      Seat aSeat = seats.get(i - 1);
      aSeat.delete();
    }
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeTable(this);
    }
    Reservation placeholderReservation = reservation;
    this.reservation = null;
    if(placeholderReservation != null)
    {
      placeholderReservation.removeTable(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "locationX" + ":" + getLocationX()+ "," +
            "locationY" + ":" + getLocationY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "reservation = "+(getReservation()!=null?Integer.toHexString(System.identityHashCode(getReservation())):"null");
  }
}