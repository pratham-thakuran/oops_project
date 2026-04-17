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
		return can_i_issue();
	}
}
//book info function
abstract class BookInfo extends issue{   // <-- made abstract
	private int bookId;
	String bookName;
	String can_i_issue;
	Scanner sc = new Scanner(System.in);

	public boolean checkid() {
    System.out.print("Enter the staff id: ");
    int staffId = sc.nextInt();

    try {
        // 1. Load Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. Create Connection
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/projectdb", "root", "0864297531@Lichi"
        );

        // 3. Prepare Query
        String query = "SELECT * FROM Staff WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, staffId);

        // 4. Execute Query
        ResultSet rs = ps.executeQuery();

        // 5. Check Result
        if (rs.next()) {
            System.out.println("✅ Staff verified!");
            con.close();
            return true;
        } else {
            System.out.println("❌ Invalid staff ID!");
            con.close();
            return false;
        }

    } catch (Exception e) {
        System.out.println("Error: " + e);
        return false;
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
		System.out.println(bookId+"-"+bookName+"-"+can_i_issue+"-"+bookId);
	}
}
//customer info function
class CustomerInfo extends BookInfo{
	int Customer_id;
	String Customer_name;
	String can_i_issue;
	public void setCustomerId(){
		System.out.print("Enter the id of the customer: ");
		Customer_id= sc.nextInt();
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


//main function to create objects and to call all the methods
public class mainBook{
	public static void main(String[] args){
		BookInfo b = new CustomerInfo();   // <-- changed object creation
		CustomerInfo c = new CustomerInfo();
		Scanner sc = new Scanner(System.in);
		System.out.println("Heyyy!!! welcome to the Library..");
		System.out.println("You are a customer or staff:");
		String z = sc.nextLine();
		//menu-driven
		if(z.equalsIgnoreCase("Customer")){
			c.setCustomerId();
			c.setCustomerName();
			c.ShowCustomer();
			c.issueBook();
		}
		else if(z.equalsIgnoreCase("Staff")){
			if(b.checkid()){
				b.setBookId();
				b.setBookName();
				b.setisAvailable();
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