package com.learnjdbc;
import java.sql.*;
import java.util.Scanner;

public class Menu {
	private Connection connection;
	private Scanner scanner;
	public Menu(Connection connection,Scanner scanner)
	{
		this.connection=connection;
		this.scanner=scanner;
	}
	public void addMenu()
	//method created to add menu into the menu table
	{
		//Giving inputs for the attributes in the menu table 
		//menu ID is not given because it's already in AUTO_INCREMENT
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter Item name");
		String itemName=scanner.nextLine();
		System.out.println("Enter Category(Starters/Main Courses/Beverages)");
		String category=scanner.nextLine();
		System.out.println("Enter Price");
		double price=scanner.nextDouble();
		try
		{
			//placeholders are given to insert the given given data into their desired attributes
			String query="Insert into menu(itemName,category,price) values (?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, itemName);
			preparedStatement.setString(2, category);
			preparedStatement.setDouble(3, price);
			//executeUpdate() checks whether any rows in the table got updated or changed and gets the no.of rows got updated or changed
			int affectedRows=preparedStatement.executeUpdate();
			
			if(affectedRows>0)
			{
				System.out.println("Menu Added Successfully");
			}
			else
			{
				System.out.println("Failed to add Menu");
			}
			
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		
	    }
	}
	public void viewMenu()
	//Method to view the data in menu table
	{
		//displays the data in menu table
		String query="select * from menu";
		try
		{
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			ResultSet resultset=preparedStatement.executeQuery();
		
			System.out.println("+-----------+------------------------+--------------+------------+");
			System.out.println("| itemID    | itemName               | category     | price      |");
			System.out.println("+-----------+------------------------+--------------+------------+");
			
		
		    while(resultset.next())
		    {
		    	int itemID=resultset.getInt("itemID");
		    	String name=resultset.getString("itemName");
		    	
		    	String category=resultset.getString("category");
		    	Double price=resultset.getDouble("price");
		    	
		    	System.out.printf("|%-11s|%-24s|%-14s|%-12s|\n",itemID,name,category,price);
		    	System.out.println("+-----------+------------------------+--------------+------------+");
		    }
		}
		
	   catch(SQLException e)
	   {
		  e.printStackTrace(); 
	   }
		
	}

	

}
