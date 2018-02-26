package ca.mcgill.ecse.resto.application;

import ca.mcgill.ecse.resto.model.RestoApp;
import ca.mcgill.ecse.resto.persistence.PersistenceObjectStream;
import ca.mcgill.ecse.resto.view.RestoAppPage;

public class RestoApplication {
	
	private static RestoApp restoApp;
	private static String filename = "menu.resto";
	
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RestoAppPage().setVisible(true);
            }
        });
        
	}*/
	

	public static RestoApp getRestoApp() {
		if (restoApp == null) {
			// load model
			restoApp= load();
		}
 		return restoApp;
	}
	
	public static void save() {
		PersistenceObjectStream.serialize(restoApp);
	}
	
	public static RestoApp load() {
		PersistenceObjectStream.setFilename(filename);
		restoApp = (RestoApp) PersistenceObjectStream.deserialize();
		// model cannot be loaded - create empty RestoApp
		if (restoApp == null) {
			restoApp = new RestoApp();
		}
		else {
			restoApp.reinitialize();
		}
		return restoApp;
	}
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}

}

