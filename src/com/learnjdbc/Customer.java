package com.learnjdbc;
import java.util.Scanner;
import java.sql.*;

public class Customer {
	//JDBC Connection to database
	private Connection connection;
	private Scanner scanner;
	public Customer(Connection connection,Scanner scanner)
	{
		//Initializes both of them to use in the methods using this keyword
		this.connection=connection;
		this.scanner=scanner;
	}	
	public void addCustomer()
	//Method used for adding a customer 
	{
		//Giving inputs for Name,phone number,address
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter Customer Name");
		String name=scanner.nextLine();
		System.out.println("Enter Customer Phone Number");
		String phone=scanner.nextLine();
		System.out.println("Enter Customer address");
		String address=scanner.nextLine();
		try
		{
			//Inserting the values of the customer's attributes using placeholders
			String query="Insert into customers(name,phone,address) values (?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, phone);
			preparedStatement.setString(3, address);
			int affectedRows=preparedStatement.executeUpdate();
			//executeUpdate is used to check whether any of the rows updated in database 
			if(affectedRows>0)
			{
				//if !=0 adds successfully
				System.out.println("Customer Added Succussfully");
			}
			else
			{
				//if ==0 returns failed to add
				System.out.println("Failed to add Customer");
			}
			
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		
	    }
	}
	public void viewCustomer()
	//Method to view the customers
	{
		String query="Select * from customers";
		//Displays all the customers recorded
		try
		{
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			System.out.println("+-----------+---------------+--------------+---------------------+");
			System.out.println("| CustomerID| Name          | Phone        | Address             |");
			System.out.println("+-----------+---------------+--------------+---------------------+");
			while(resultSet.next())
			{
				//Displaying the output as given formatted tables
				int customerid=resultSet.getInt("customerID");
		    	String name=resultSet.getString("name");
		    	String phoneNumber=resultSet.getString("phone");
		    	String address=resultSet.getString("address");
		    	
		    	System.out.printf("|%-11s|%-15s|%-14s|%-21s|\n",customerid,name,phoneNumber,address);
		    	System.out.println("+-----------+---------------+--------------+---------------------+");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	


}
