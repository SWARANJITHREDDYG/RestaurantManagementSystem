package com.learnjdbc;
import java.sql.*;
import java.util.Scanner;

public class RestaurantManagementSystem {
	//URL,USERNAME,PASSWORD are declared and used to connect to our desired database
	private static final String Url="jdbc:mysql://localhost:3306/restaurant";
	private static final String Username="root";
	private static final String Password="Swaran@123";

	public static void main(String[] args) {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		Scanner scanner=new Scanner(System.in);
		try
		{
			Connection connection=DriverManager.getConnection(Url,Username,Password);
			//creating objects for already defined methods
			Customer customer=new Customer(connection, scanner);
			Menu menu=new Menu(connection,scanner);
			Order order=new Order(connection,scanner);
			while(true)
			{
				//Displaying output
				System.out.println("Restaurant Management System");
				System.out.println("1.Add Customer");
				System.out.println("2.View Customers");
				System.out.println("3.Add Menu Item");
				System.out.println("4.View Menu");
				System.out.println("5.Create Order");
				System.out.println("6.View Orders");
				System.out.println("7.Exit");
				System.out.println("Please Enter your Choice:");
				//Giving input of your choice to perform any operation
				int choice=scanner.nextInt();
				switch(choice)
				{
				case 1:
				
					//Adding Customer to customer table with all the mentioned attributes
					customer.addCustomer();
					System.out.println();
					break;
				case 2:
					//function call which is used to view all the customers in the table 
					customer.viewCustomer();
					System.out.println();
					break;
				case 3:
					//Adding Menu Items to the menu table
					menu.addMenu();
					System.out.println();
					break;
				case 4:
					//Viewing menu items present in the table menu
					menu.viewMenu();
					System.out.println();
					break;
				case 5:
					//Creating order to be stored in orders table
					order.createOrder();
					System.out.println("");
					break;
				case 6:
					order.viewOrders();
					System.out.println("");
					break;
				case 7:
					//Exit
					System.out.println("THANK YOU! FOR USING RESTAURANT MANAGEMENT SYSTEM!!");
					return;
					//return -->terminates from execution 
				default:
					//After each and every case you select the default statement will be executed
					System.out.println("Please Enter Valid Input");
					break;
				}
				
			}
			

	}catch(SQLException e)
		{
		   e.printStackTrace();
		}
		
		}

}
