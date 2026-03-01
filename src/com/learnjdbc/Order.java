package com.learnjdbc;
import java.util.Scanner;
import java.sql.*;

public class Order {
	//JDBC connection to database
    private Connection connection;
    private Scanner scanner;

    public Order(Connection connection, Scanner scanner) {
    	//they are declared to use in the further methods this keyword is used to get connected
        this.connection = connection;
        this.scanner = scanner;
    }

    public void createOrder() {
    	//method to create an order
        System.out.print("Enter Customer ID: ");
        int customerId = readInt(scanner);
        //readInt() is a method created because of the scanner to take input separately by a function call so that the code runs with out any error

        double totalAmount = 0.0;
        //Initialization of totalAmount to 0 because in further steps it gets incremented depended on price of the menu item

        try {
        	//Query to perform insertion operation based on the attributes order via giving them a place holders
            String query = "INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES (?, CURDATE(), ?)";
            //CURDATE() gives the current date or the present date and gets assigned to orderDate
            
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            //RETURN_GENERATED_KEYS is used to retrieve the value which got updated or incremented by themselves  
           
            preparedStatement.setInt(1, customerId);
            preparedStatement.setDouble(2, totalAmount);
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            //here getGeneratedKeys() gives the auto incremented values given to attribute declaration
            
            
            int orderId = 0;
            //Firstly orderId is initialized to 0 so next it will get incremented next()
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            while (true) {
                System.out.print("Enter Menu Item ID (0 to finish): ");
                //Click 0 to get out of the loop running continuosly
                
                int itemId = readInt(scanner);
                //function call takes the input which gets incrementing continuosly after 1 also 
                
                if (itemId == 0) break;
                //item id==0 then exits

                System.out.print("Enter Quantity: ");
                int qty = readInt(scanner);
                
                String query1="SELECT Price FROM Menu WHERE ItemID = ?";
                //gets and stores the value of price at that particular itemID position

                PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                preparedStatement1.setInt(1, itemId);
                ResultSet resultSet = preparedStatement1.executeQuery();

                if (resultSet.next()) {
                    double price = resultSet.getDouble("Price");
                    totalAmount += price * qty;
                    //total amount gets updated by the price value at that itemID position
                    
                    String Query="INSERT INTO OrderDetails (OrderID, ItemID, Quantity) VALUES (?, ?, ?)";
                    //Insertion of the details into orderDetails table by using the place holders-->?

                    PreparedStatement preparedStatement2 = connection.prepareStatement(Query);
                    //giving the inputs one after another
                    
                    preparedStatement2.setInt(1, orderId);
                    preparedStatement2.setInt(2, itemId);
                    preparedStatement2.setInt(3, qty);
                    preparedStatement2.executeUpdate();
                    //gets updated by the above given values
                } else {
                	//if the item is not found
                    System.out.println("Menu Item ID not found.");
                }
            }
            
            String query2="UPDATE Orders SET TotalAmount = ? WHERE OrderID = ?";
            //orders table will get updated with the totalAmount at the position of orderID given

            PreparedStatement psUpdate = connection.prepareStatement(query2);
            psUpdate.setDouble(1, totalAmount);
            psUpdate.setInt(2, orderId);
            psUpdate.executeUpdate();

            System.out.println("Order created successfully! Total: Rs." + totalAmount);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewOrders() {
        try {
            String query = "SELECT o.OrderID, c.Name, o.OrderDate, o.TotalAmount FROM Orders o JOIN Customers c ON o.CustomerID = c.CustomerID";
            Statement st = connection.createStatement();
            System.out.println("+---------+------------+----------+------------+");
            System.out.println("| OrderID | customerID | Date     | TotalAmount|");
            System.out.println("+---------+------------+----------+------------+");

            ResultSet resultset = st.executeQuery(query);
            while (resultset.next()) {
                int orderID = resultset.getInt("OrderID");
                String name = resultset.getString("Name");
                double totalAmount = resultset.getDouble("TotalAmount");
                String orderDate=resultset.getString("orderDate");

                System.out.printf("|%-9d|%-12s|%10s|%-12.2f|\n", orderID, name,orderDate, totalAmount);
                System.out.println("+---------+------------+----------+------------+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input, please enter a number");
            sc.next(); // discard invalid input
        }
        return sc.nextInt();
    }
}
