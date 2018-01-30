# Iteration one of group project ecse 223

This document would explain the design decisions of the classes and their role in the application
Also it will specify how to do some required usecases in the application


## Design Details
1. OrderEntry is a wrapper for MenuEntry which includes the multiplicity of the item ordered.
2. Every Order is composed of a list of OrderEntries. Therefore, deleting the order would delete all order entries related to it.
3. A Shared Order is added as a percentage for example 5 people would share (0.2) of the total.
4. An Individual Order is handled by setting the percentage to 1.0
5. Therefore A Bill could be assigned to a list of Seats solving the problem of customized billing 
6. The AppResto Class handles and manages all components of the App

## UseCases
1. Billing a list of seats given their Table/Seats
	* Iterate over the orders of each seat and sum them up
2. Deleting a table or a seat
	* Make sure there are the table/seat is not used. In case of a table we need to check all seats.
3. Handling shared Order given a list of Seats 
	* add the same Order with a percentage 0.2 to all 5 seats for example.  "with the right percentage to every Seat"

Thank you,
Sincerely,

Group 05