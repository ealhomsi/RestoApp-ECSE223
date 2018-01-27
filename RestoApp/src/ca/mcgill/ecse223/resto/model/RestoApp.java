/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 4 "../../../../../model.ump"
public class RestoApp
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //RestoApp Associations
  private List<Table> tables;
  private List<Menu> menus;
  private List<Waiter> waiters;
  private List<Reservation> reservations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RestoApp()
  {
    tables = new ArrayList<Table>();
    menus = new ArrayList<Menu>();
    waiters = new ArrayList<Waiter>();
    reservations = new ArrayList<Reservation>();
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public Menu getMenus(int index)
  {
    Menu aMenus = menus.get(index);
    return aMenus;
  }

  public List<Menu> getMenus()
  {
    List<Menu> newMenus = Collections.unmodifiableList(menus);
    return newMenus;
  }

  public int numberOfMenus()
  {
    int number = menus.size();
    return number;
  }

  public boolean hasMenus()
  {
    boolean has = menus.size() > 0;
    return has;
  }

  public int indexOfMenus(Menu aMenus)
  {
    int index = menus.indexOf(aMenus);
    return index;
  }

  public Waiter getWaiter(int index)
  {
    Waiter aWaiter = waiters.get(index);
    return aWaiter;
  }

  public List<Waiter> getWaiters()
  {
    List<Waiter> newWaiters = Collections.unmodifiableList(waiters);
    return newWaiters;
  }

  public int numberOfWaiters()
  {
    int number = waiters.size();
    return number;
  }

  public boolean hasWaiters()
  {
    boolean has = waiters.size() > 0;
    return has;
  }

  public int indexOfWaiter(Waiter aWaiter)
  {
    int index = waiters.indexOf(aWaiter);
    return index;
  }

  public Reservation getReservation(int index)
  {
    Reservation aReservation = reservations.get(index);
    return aReservation;
  }

  public List<Reservation> getReservations()
  {
    List<Reservation> newReservations = Collections.unmodifiableList(reservations);
    return newReservations;
  }

  public int numberOfReservations()
  {
    int number = reservations.size();
    return number;
  }

  public boolean hasReservations()
  {
    boolean has = reservations.size() > 0;
    return has;
  }

  public int indexOfReservation(Reservation aReservation)
  {
    int index = reservations.indexOf(aReservation);
    return index;
  }

  public static int minimumNumberOfTables()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Table addTable(int aLocationX, int aLocationY, Reservation aReservation)
  {
    return new Table(aLocationX, aLocationY, this, aReservation);
  }

  public boolean addTable(Table aTable)
  {
    boolean wasAdded = false;
    if (tables.contains(aTable)) { return false; }
    RestoApp existingRestoApp = aTable.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aTable.setRestoApp(this);
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
    //Unable to remove aTable, as it must always have a restoApp
    if (!this.equals(aTable.getRestoApp()))
    {
      tables.remove(aTable);
      wasRemoved = true;
    }
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

  public static int minimumNumberOfMenus()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Menu addMenus(String aTitle)
  {
    return new Menu(aTitle, this);
  }

  public boolean addMenus(Menu aMenus)
  {
    boolean wasAdded = false;
    if (menus.contains(aMenus)) { return false; }
    RestoApp existingRestoApp = aMenus.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aMenus.setRestoApp(this);
    }
    else
    {
      menus.add(aMenus);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMenus(Menu aMenus)
  {
    boolean wasRemoved = false;
    //Unable to remove aMenus, as it must always have a restoApp
    if (!this.equals(aMenus.getRestoApp()))
    {
      menus.remove(aMenus);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addMenusAt(Menu aMenus, int index)
  {  
    boolean wasAdded = false;
    if(addMenus(aMenus))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenus()) { index = numberOfMenus() - 1; }
      menus.remove(aMenus);
      menus.add(index, aMenus);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMenusAt(Menu aMenus, int index)
  {
    boolean wasAdded = false;
    if(menus.contains(aMenus))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenus()) { index = numberOfMenus() - 1; }
      menus.remove(aMenus);
      menus.add(index, aMenus);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMenusAt(aMenus, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfWaiters()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Waiter addWaiter(String aName)
  {
    return new Waiter(aName, this);
  }

  public boolean addWaiter(Waiter aWaiter)
  {
    boolean wasAdded = false;
    if (waiters.contains(aWaiter)) { return false; }
    RestoApp existingRestoApp = aWaiter.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aWaiter.setRestoApp(this);
    }
    else
    {
      waiters.add(aWaiter);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWaiter(Waiter aWaiter)
  {
    boolean wasRemoved = false;
    //Unable to remove aWaiter, as it must always have a restoApp
    if (!this.equals(aWaiter.getRestoApp()))
    {
      waiters.remove(aWaiter);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addWaiterAt(Waiter aWaiter, int index)
  {  
    boolean wasAdded = false;
    if(addWaiter(aWaiter))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWaiters()) { index = numberOfWaiters() - 1; }
      waiters.remove(aWaiter);
      waiters.add(index, aWaiter);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWaiterAt(Waiter aWaiter, int index)
  {
    boolean wasAdded = false;
    if(waiters.contains(aWaiter))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWaiters()) { index = numberOfWaiters() - 1; }
      waiters.remove(aWaiter);
      waiters.add(index, aWaiter);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWaiterAt(aWaiter, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfReservations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Reservation addReservation(Date aReservationDate, Time aReservationTime, ClientInfo aClient)
  {
    return new Reservation(aReservationDate, aReservationTime, aClient, this);
  }

  public boolean addReservation(Reservation aReservation)
  {
    boolean wasAdded = false;
    if (reservations.contains(aReservation)) { return false; }
    RestoApp existingRestoApp = aReservation.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aReservation.setRestoApp(this);
    }
    else
    {
      reservations.add(aReservation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReservation(Reservation aReservation)
  {
    boolean wasRemoved = false;
    //Unable to remove aReservation, as it must always have a restoApp
    if (!this.equals(aReservation.getRestoApp()))
    {
      reservations.remove(aReservation);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addReservationAt(Reservation aReservation, int index)
  {  
    boolean wasAdded = false;
    if(addReservation(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReservationAt(Reservation aReservation, int index)
  {
    boolean wasAdded = false;
    if(reservations.contains(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReservationAt(aReservation, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (tables.size() > 0)
    {
      Table aTable = tables.get(tables.size() - 1);
      aTable.delete();
      tables.remove(aTable);
    }
    
    while (menus.size() > 0)
    {
      Menu aMenus = menus.get(menus.size() - 1);
      aMenus.delete();
      menus.remove(aMenus);
    }
    
    while (waiters.size() > 0)
    {
      Waiter aWaiter = waiters.get(waiters.size() - 1);
      aWaiter.delete();
      waiters.remove(aWaiter);
    }
    
    for(int i=reservations.size(); i > 0; i--)
    {
      Reservation aReservation = reservations.get(i - 1);
      aReservation.delete();
    }
  }

}