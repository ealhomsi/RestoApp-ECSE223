/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 17 "../../../../../model.ump"
public class Menu
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Menu Attributes
  private String title;

  //Menu Associations
  private List<MenuEntry> entries;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Menu(String aTitle, RestoApp aRestoApp)
  {
    title = aTitle;
    entries = new ArrayList<MenuEntry>();
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create menus due to restoApp");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public String getTitle()
  {
    return title;
  }

  public MenuEntry getEntry(int index)
  {
    MenuEntry aEntry = entries.get(index);
    return aEntry;
  }

  public List<MenuEntry> getEntries()
  {
    List<MenuEntry> newEntries = Collections.unmodifiableList(entries);
    return newEntries;
  }

  public int numberOfEntries()
  {
    int number = entries.size();
    return number;
  }

  public boolean hasEntries()
  {
    boolean has = entries.size() > 0;
    return has;
  }

  public int indexOfEntry(MenuEntry aEntry)
  {
    int index = entries.indexOf(aEntry);
    return index;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public static int minimumNumberOfEntries()
  {
    return 0;
  }

  public boolean addEntry(MenuEntry aEntry)
  {
    boolean wasAdded = false;
    if (entries.contains(aEntry)) { return false; }
    entries.add(aEntry);
    if (aEntry.indexOfMenus(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aEntry.addMenus(this);
      if (!wasAdded)
      {
        entries.remove(aEntry);
      }
    }
    return wasAdded;
  }

  public boolean removeEntry(MenuEntry aEntry)
  {
    boolean wasRemoved = false;
    if (!entries.contains(aEntry))
    {
      return wasRemoved;
    }

    int oldIndex = entries.indexOf(aEntry);
    entries.remove(oldIndex);
    if (aEntry.indexOfMenus(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aEntry.removeMenus(this);
      if (!wasRemoved)
      {
        entries.add(oldIndex,aEntry);
      }
    }
    return wasRemoved;
  }

  public boolean addEntryAt(MenuEntry aEntry, int index)
  {  
    boolean wasAdded = false;
    if(addEntry(aEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEntries()) { index = numberOfEntries() - 1; }
      entries.remove(aEntry);
      entries.add(index, aEntry);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEntryAt(MenuEntry aEntry, int index)
  {
    boolean wasAdded = false;
    if(entries.contains(aEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEntries()) { index = numberOfEntries() - 1; }
      entries.remove(aEntry);
      entries.add(index, aEntry);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEntryAt(aEntry, index);
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
      existingRestoApp.removeMenus(this);
    }
    restoApp.addMenus(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<MenuEntry> copyOfEntries = new ArrayList<MenuEntry>(entries);
    entries.clear();
    for(MenuEntry aEntry : copyOfEntries)
    {
      aEntry.removeMenus(this);
    }
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeMenus(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }
}