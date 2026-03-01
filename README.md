
# 🍽️ Restaurant Management System (JDBC + MySQL)

A **console-based Restaurant Management System** built using **Java, JDBC, and MySQL**.
This project demonstrates database connectivity, CRUD operations, and order management using a structured menu-driven program.

---

## 🚀 Technologies Used

* ☕ Java
* 🔌 JDBC
* 🗄️ MySQL
* 🖥️ Eclipse IDE

---

## 📌 Features

### 👤 Customer Management

* Add new customer
* View all customers

(Implemented in `Customer.java` )

---

### 🍴 Menu Management

* Add menu items
* View menu items

(Implemented in `Menu.java` )

---

### 🧾 Order Management

* Create new order
* Add multiple items to order
* Automatically calculate total amount
* View all orders

(Implemented in `Order.java` )

---

### 🖥️ Main Application

* Menu-driven console interface
* Database connection handling
* User input handling

(Main file: `RestaurantManagementSystem.java` )

---

## 🗄️ Database Schema

Create a MySQL database:

```sql
CREATE DATABASE restaurant;
```

### Customers Table

```sql
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100),
    Phone VARCHAR(15),
    Address VARCHAR(255)
);
```

### Menu Table

```sql
CREATE TABLE Menu (
    ItemID INT PRIMARY KEY AUTO_INCREMENT,
    ItemName VARCHAR(100),
    Category VARCHAR(50),
    Price DOUBLE
);
```

### Orders Table

```sql
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    CustomerID INT,
    OrderDate DATE,
    TotalAmount DOUBLE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);
```

### OrderDetails Table

```sql
CREATE TABLE OrderDetails (
    OrderDetailID INT PRIMARY KEY AUTO_INCREMENT,
    OrderID INT,
    ItemID INT,
    Quantity INT,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ItemID) REFERENCES Menu(ItemID)
);
```

---

## 🔧 How to Run

1. Install MySQL and create the database.
2. Add MySQL Connector JAR to Eclipse build path.
3. Update database credentials in:

```java
RestaurantManagementSystem.java
```

4. Run the project.
5. Use console menu to manage restaurant operations.

---

## 📂 Project Structure

```
com.learnJDBC
│
├── Customer.java
├── Menu.java
├── Order.java
└── RestaurantManagementSystem.java
```

---

## 🎯 Learning Outcomes

* JDBC connection handling
* PreparedStatement usage
* ResultSet processing
* SQL JOIN operations
* Auto-generated keys handling
* Console-based system design

---

## 👨‍💻 Author

**Swaranjith Reddy**
Backend Developer (Java + JDBC + MySQL)

---

## ⭐ Future Improvements

* Add update & delete operations
* Implement DAO layer
* Add transaction management
* Convert to Spring Boot REST API
* Add GUI (JavaFX / Web Interface)

---


