/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 73 "../../../../../model.ump"
public class Seat
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Autounique Attributes
  private int id;

  //Seat Associations
  private Table table;
  private List<UsedSeat> usedSeats;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Seat(Table aTable)
  {
    id = nextId++;
    boolean didAddTable = setTable(aTable);
    if (!didAddTable)
    {
      throw new RuntimeException("Unable to create seat due to table");
    }
    usedSeats = new ArrayList<UsedSeat>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getId()
  {
    return id;
  }

  public Table getTable()
  {
    return table;
  }

  public UsedSeat getUsedSeat(int index)
  {
    UsedSeat aUsedSeat = usedSeats.get(index);
    return aUsedSeat;
  }

  public List<UsedSeat> getUsedSeats()
  {
    List<UsedSeat> newUsedSeats = Collections.unmodifiableList(usedSeats);
    return newUsedSeats;
  }

  public int numberOfUsedSeats()
  {
    int number = usedSeats.size();
    return number;
  }

  public boolean hasUsedSeats()
  {
    boolean has = usedSeats.size() > 0;
    return has;
  }

  public int indexOfUsedSeat(UsedSeat aUsedSeat)
  {
    int index = usedSeats.indexOf(aUsedSeat);
    return index;
  }

  public boolean setTable(Table aTable)
  {
    boolean wasSet = false;
    //Must provide table to seat
    if (aTable == null)
    {
      return wasSet;
    }

    if (table != null && table.numberOfSeats() <= Table.minimumNumberOfSeats())
    {
      return wasSet;
    }

    Table existingTable = table;
    table = aTable;
    if (existingTable != null && !existingTable.equals(aTable))
    {
      boolean didRemove = existingTable.removeSeat(this);
      if (!didRemove)
      {
        table = existingTable;
        return wasSet;
      }
    }
    table.addSeat(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfUsedSeats()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public UsedSeat addUsedSeat(Date aDate, Time aStartTime, Time aEndTime, Customer aCustomer)
  {
    return new UsedSeat(aDate, aStartTime, aEndTime, this, aCustomer);
  }

  public boolean addUsedSeat(UsedSeat aUsedSeat)
  {
    boolean wasAdded = false;
    if (usedSeats.contains(aUsedSeat)) { return false; }
    Seat existingSeat = aUsedSeat.getSeat();
    boolean isNewSeat = existingSeat != null && !this.equals(existingSeat);
    if (isNewSeat)
    {
      aUsedSeat.setSeat(this);
    }
    else
    {
      usedSeats.add(aUsedSeat);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUsedSeat(UsedSeat aUsedSeat)
  {
    boolean wasRemoved = false;
    //Unable to remove aUsedSeat, as it must always have a seat
    if (!this.equals(aUsedSeat.getSeat()))
    {
      usedSeats.remove(aUsedSeat);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addUsedSeatAt(UsedSeat aUsedSeat, int index)
  {  
    boolean wasAdded = false;
    if(addUsedSeat(aUsedSeat))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsedSeats()) { index = numberOfUsedSeats() - 1; }
      usedSeats.remove(aUsedSeat);
      usedSeats.add(index, aUsedSeat);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUsedSeatAt(UsedSeat aUsedSeat, int index)
  {
    boolean wasAdded = false;
    if(usedSeats.contains(aUsedSeat))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsedSeats()) { index = numberOfUsedSeats() - 1; }
      usedSeats.remove(aUsedSeat);
      usedSeats.add(index, aUsedSeat);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUsedSeatAt(aUsedSeat, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Table placeholderTable = table;
    this.table = null;
    if(placeholderTable != null)
    {
      placeholderTable.removeSeat(this);
    }
    for(int i=usedSeats.size(); i > 0; i--)
    {
      UsedSeat aUsedSeat = usedSeats.get(i - 1);
      aUsedSeat.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "table = "+(getTable()!=null?Integer.toHexString(System.identityHashCode(getTable())):"null");
  }
}