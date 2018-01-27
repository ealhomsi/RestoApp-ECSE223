# Iteration one of group project ecse 223

This document would explain the design decisions of the classes and their role in the application
Also it will specify how to do some required usecases in the application


## Design Details
1. OrderEntry is a wrapper for MenuEntry which includes the multiplicity of the item ordered.
2. Every Order is composed of a list of OrderEntries. Therefore, deleting the order would delete all order entries related to it.
	⋅⋅1. Class Order is abstract in this sense
3. A Client is a potential customer that did not show up yet 
4. Once the Client shows up he/she becomes a Customer which includes multiple functionalities:
	⋅⋅1. Every Customer has a list of Orders
	⋅⋅2. Every Customer should have an assigned Seat (UsedSeat)
5. An Order could be either Individual or Shared
	..1. An Individual Order is processed normally
	..2. A Shared Order is added as a percentage for example 5 people would share (0.2) of the total.
6. Therefore A Bill could be assigned to a list of Customers solving the problem of customized billing (We did not include the payment information so far).
7. A ClientInfo (potential Client) could make one or more reservations and each reservation could target as many tables as necessary.
8. One ClientInfo could have multiple customers (the same client showing up multiple times)


## UseCases
1. Billing a list of customers given their UsedSeats
	⋅⋅1. for every used Seat get the current Customer
	..2. Create a bill by adding the customers
	..3. Get the total by a simple iteration on the customers and their orders

2. Deleting a table or a seat
	..1. Make sure there are no UsedSeats for the deleted seat or table
	..2. delete the requested item

3. Handling shared Order given a list of UsedSeats or Customers
	..1. add a sharedOrder with the right percentage to every Customer.

