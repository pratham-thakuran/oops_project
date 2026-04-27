import java.sql.*;
import java.util.*;
//issue book funtion
abstract class issue {
	abstract void issueBook();
}
class caniissue{
	String can_i_issue(){
		String can_i_issue;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter if book is available or not:");
		can_i_issue=sc.nextLine();
		return can_i_issue;
	}
}
//book info function
abstract class BookInfo extends issue{   // <-- made abstract
	private int bookId;
	String bookName;
	String can_i_issue;
	Scanner sc = new Scanner(System.in);

	

	public boolean verifyStaff() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter Staff ID: ");
    int staffId = sc.nextInt();

    String url = "jdbc:mysql://localhost:3306/Staff";
    String user = "root";
    String password = "0864297531@Lichi";

    try {
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);

        String query = "SELECT Name FROM staffinfo WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, staffId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String name = rs.getString("name");
            System.out.println("Welcome " + name);
            con.close();
            return true;
        } else {
            System.out.println("Staff not found");
            con.close();
            return false;
        }

    } catch (Exception e) {
        System.out.println("Error: " + e);
        return false;
    }
	
}
	public void saveBookToDB() {
    String url = "jdbc:mysql://localhost:3306/Staff";
    String user = "root";
    String password = "0864297531@Lichi";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);

        String query = "INSERT INTO bookinfo (bookId, bookName, available) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, bookId);
        ps.setString(2, bookName);
        ps.setString(3, can_i_issue);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Book inserted into database");
        }

        con.close();

    } catch (Exception e) {
        System.out.println("DB Error: " + e);
    }
}

	public void setBookId(){
		System.out.print("Enter book id: ");
		bookId = sc.nextInt();
	}
	public void setBookName(){
		sc.nextLine();
		System.out.print("enter the name of the book: ");
		bookName = sc.nextLine();
	}
	public void setisAvailable(){
		caniissue a = new caniissue();
		while(true){
			can_i_issue = a.can_i_issue(); 
			if(can_i_issue.equalsIgnoreCase("no")){
				System.out.println("Not Available");
				break;
			}
			else if(can_i_issue.equalsIgnoreCase("yes")){
				System.out.println("Available");
				break;
			}
			else{
				System.out.println("Invalid");
				continue;
			}
		}
	}
	//book information display
	
	public void ShowBook(){
		System.out.println(bookId+"-"+bookName+"-"+can_i_issue);
	}
}
//customer info function
class CustomerInfo extends BookInfo{
	int Customer_id;
	String Customer_name;
	public void setCustomerId(){
		System.out.print("Enter the id of the customer: ");
		Customer_id= sc.nextInt();
	}
	public boolean checkBook(int id) {
    String url = "jdbc:mysql://localhost:3306/Staff";
    String user = "root";
    String password = "0864297531@Lichi";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);

        String query = "SELECT bookName, available FROM bookinfo WHERE bookId = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String name = rs.getString("bookName");
            String availability = rs.getString("available");

            System.out.println("Book Found: " + name);

            if (availability.equalsIgnoreCase("yes")) {
                System.out.println("Book is available");
                con.close();
                return true;
            } else {
                System.out.println("Book is NOT available");
                con.close();
                return false;
            }
        } else {
            System.out.println("Book not found in database");
            con.close();
            return false;
        }

    } catch (Exception e) {
        System.out.println("DB Error: " + e);
        return false;
    }
}
	public void setCustomerName(){
		sc.nextLine();
		System.out.print("Enter the name of the customer: ");
		Customer_name=sc.nextLine();
	}
	public void ShowCustomer(){
		System.out.println(Customer_id+"-"+Customer_name);
	}
	//abstract method overrided here using polymorphism
	@Override
	void issueBook(){
    	if(can_i_issue.equalsIgnoreCase("yes")){
        	System.out.println("Do you want to borrow it: ");
        	String y = sc.nextLine();
        	if(y.equals("yes")){
            	System.out.println("Book issued successfully");
        	}
        	else if(y.equalsIgnoreCase("no")){
            	System.out.println("Okay, thankyou for the interaction.");
        	}
        	else{
            	System.out.println("invalid input!");
        	}
    	}
    	else if(can_i_issue.equalsIgnoreCase("no")){
        	System.out.println("this book is not available");
    	}
    	else{
        	System.out.println("Invalid input!");
    	}
} 
}  
	public void saveBookToDB2() {
    String url = "jdbc:mysql://localhost:3306/Staff";
    String user = "root";
    String password = "0864297531@Lichi";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);

        String query = "INSERT INTO customerinfo (id, name, password) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, Customer_id);
        ps.setString(2, Customer_name);
        ps.setString(3, password);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Signup Successful");
        }

        con.close();

    } catch (Exception e) {
        System.out.println("DB Error: " + e);
    }
}


//main function to create objects and to call all the methods
public class mainBook{
	public static void main(String[] args){
		BookInfo b = new CustomerInfo();   // <-- changed object creation
		CustomerInfo c = new CustomerInfo();
		Scanner sc = new Scanner(System.in);
		System.out.println("Heyyy!!! welcome to the Library..");
		System.out.print("You are a customer or staff:");
		String z = sc.nextLine();
		//menu-driven
		if(z.equalsIgnoreCase("Customer")){
			System.out.println("You want to login or you are new here:\n1)Login\n2)SignUp");
			int k = sc.nextInt();
			switch(k){
				case 1:
					System.out.print("Enter Book ID you want: ");
					int id = sc.nextInt();

					if(c.checkBook(id)){
						sc.nextLine(); // clear buffer
					} else {
						System.out.println("Cannot issue book");
					}
				case 2:
					c.setCustomerId();
					c.setCustomerName();
					c.ShowCustomer();
			}

		}
		else if(z.equalsIgnoreCase("Staff")){
			if(b.verifyStaff()){
				b.setBookId();
				b.setBookName();
				b.setisAvailable();
				b.saveBookToDB();
				b.ShowBook();
			}
			else{
				System.out.println("access denied!!");
			}	
		}
		else{
			System.out.println("Invalid input!!");
		}
		System.out.println("Updated now will learn and apply more things on this");
		System.out.println("Updated now will learn and apply more things on this");

	}
}