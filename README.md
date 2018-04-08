# Project 233 Group 05 Winter 2018 McGill University

## Purpose (Problem Statement)
You will create the RestoApp application that first allows a restaurant owner to specify the number and
locations of tables and seats in a restaurant as well as the menu and then allows waiters to reserve
tables, place orders, and issue bills. The application will also be able to load a restaurant menu from a file
given to you.
A restaurant owner first specifies the number and locations of tables and seats as shown on the left side
in the illustration below. The restaurant owner places the tables and seats according to the layout of the
restaurant to make it easier for waiters to identify tables. You may assume that the restaurant has only
on level. Note that you do not have to implement the exact user interface as shown below, but your
application must be able to perform all required tasks. Furthermore, the shown user interface is not
complete and needs to be extended by you to support all required tasks.

![Goal solution](https://image.ibb.co/kazUux/Capture.png)


## Team Members
* Al Homsi, Elias

* Brodeur-Urbas, Max

*Damlaj, Hani

*El Khoury, Carl

*Vu, Marie

*Zhou, Mia


## General Architecture
MVC Architecture was chosen for this project with the controller coded as the backend in java. The front end was done using the Swing library and Java-2D. The model is saved locally as serializable objects which is part of the main java library for serilizable interface.
Once the application is loaded, it remembers its last state by reading the file. If the file does not exists it would generate a new file. However, to get the menuItems preconfigured it is preferable to use the menu.resto that is outside the project's root folder.

### View 
1. The main interface is a single page os size (1500, 800) and it is divided in half. Each half is occupied by a class called "SidePanel". The interactions would simply exchange those SidePanels and therefore, no new Frames are required.

2. To Create a new SidePanel Simply create a new Class that extends the SidePanel Class. See: MainSidePanel, DrawingPanel, ChangeTableLocationPanel.

3. Add your sidePanel in the RestoApp page by storing it in the array of sidepanels. Also, give it an appropriate index for later use.

4. to display your sidePanel simply call page(RestoAppPage).setRightIndex(<YourIndex>) and then page().updateSidePanels(), similarly methods are avialable to update the left part.

5. the sidePanel class is abstract and it has the method updateView which should be called to refresh the content of the side panel

6. Many DataViewObjects similar to (DTO) were created to help displaying the model. For example, TableView and SeatView.

7. Automatic displaying of the seats arround the table is related to the table size and seat size. The algorithm behind it is to check if it is possible to display the number of seats requrested in a "nice way", otherwise, it would complain that there are too many seats.

### Controller
1. The Controller is stateless, that is it is a "Static Class" that provides methods to connect the view and the model.

2. Although some methods are not static inside the controller, this is only a bug that needs fixing

3. Controller is passed to the side panel (initial design non-static controller) so that it would be able to communicate with the model

4. Controller checks for bad user input 

### Model
1. The model is done in umple

2. The model contains three main parts the class association, persistance and the state machine for table class.


## References:
1. Mussbacher, G. (2018). ECSE223 Project - Overview.pdf. McGill's MyCourses: retrieved on 8th of April 2018.

Thank you,
Sincerely,

Group 05