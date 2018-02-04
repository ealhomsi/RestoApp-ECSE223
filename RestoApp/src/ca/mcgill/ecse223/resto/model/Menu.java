/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 32 "../../../../../model.ump"
public class Menu
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Menu Associations
  private List<MenuItem> menuitems;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Menu(RestoApp aRestoApp)
  {
    menuitems = new ArrayList<MenuItem>();
    if (aRestoApp == null || aRestoApp.getMenu() != null)
    {
      throw new RuntimeException("Unable to create Menu due to aRestoApp");
    }
    restoApp = aRestoApp;
  }

  public Menu()
  {
    menuitems = new ArrayList<MenuItem>();
    restoApp = new RestoApp(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public MenuItem getMenuitem(int index)
  {
    MenuItem aMenuitem = menuitems.get(index);
    return aMenuitem;
  }

  public List<MenuItem> getMenuitems()
  {
    List<MenuItem> newMenuitems = Collections.unmodifiableList(menuitems);
    return newMenuitems;
  }

  public int numberOfMenuitems()
  {
    int number = menuitems.size();
    return number;
  }

  public boolean hasMenuitems()
  {
    boolean has = menuitems.size() > 0;
    return has;
  }

  public int indexOfMenuitem(MenuItem aMenuitem)
  {
    int index = menuitems.indexOf(aMenuitem);
    return index;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public static int minimumNumberOfMenuitems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MenuItem addMenuitem(String aName)
  {
    return new MenuItem(aName, this);
  }

  public boolean addMenuitem(MenuItem aMenuitem)
  {
    boolean wasAdded = false;
    if (menuitems.contains(aMenuitem)) { return false; }
    Menu existingMenu = aMenuitem.getMenu();
    boolean isNewMenu = existingMenu != null && !this.equals(existingMenu);
    if (isNewMenu)
    {
      aMenuitem.setMenu(this);
    }
    else
    {
      menuitems.add(aMenuitem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMenuitem(MenuItem aMenuitem)
  {
    boolean wasRemoved = false;
    //Unable to remove aMenuitem, as it must always have a menu
    if (!this.equals(aMenuitem.getMenu()))
    {
      menuitems.remove(aMenuitem);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addMenuitemAt(MenuItem aMenuitem, int index)
  {  
    boolean wasAdded = false;
    if(addMenuitem(aMenuitem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenuitems()) { index = numberOfMenuitems() - 1; }
      menuitems.remove(aMenuitem);
      menuitems.add(index, aMenuitem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMenuitemAt(MenuItem aMenuitem, int index)
  {
    boolean wasAdded = false;
    if(menuitems.contains(aMenuitem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenuitems()) { index = numberOfMenuitems() - 1; }
      menuitems.remove(aMenuitem);
      menuitems.add(index, aMenuitem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMenuitemAt(aMenuitem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (menuitems.size() > 0)
    {
      MenuItem aMenuitem = menuitems.get(menuitems.size() - 1);
      aMenuitem.delete();
      menuitems.remove(aMenuitem);
    }
    
    RestoApp existingRestoApp = restoApp;
    restoApp = null;
    if (existingRestoApp != null)
    {
      existingRestoApp.delete();
    }
  }

}