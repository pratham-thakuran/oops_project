import java.util.*;
//issue book funtion
abstract class issue {
	abstract void issueBook();
}
//book info function
class BookInfo extends issue{
	private int bookId;
	String bookName;
	String can_i_issue;
	Scanner sc = new Scanner(System.in);
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
		System.out.print("Is the book available: ");
		can_i_issue=sc.nextLine();
		if(can_i_issue.equalsIgnoreCase("no")){
			System.out.println("Not Available");
		}
		else if(can_i_issue.equalsIgnoreCase("yes")){
			System.out.println("Available");
		}
		else{
			System.out.println("Invalid");
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
				System.out.println("Book issued succesfully");
			}
			else if(y.equalsIgnoreCase("no")){
				System.out.println("Okay, thankyou for the interogation.");
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

//main function to create objects and to call all the methods
public class mainBook{
	public static void main(String[] args){
		BookInfo b = new BookInfo();
		CustomerInfo c = new CustomerInfo();
		Scanner sc = new Scanner(System.in);
		System.out.println("You are a customer or staff:");
		String z = sc.nextLine();
		//menu driven 
		if(z.equalsIgnoreCase("Customer")){
			c.setCustomerId();
			c.setCustomerName();
			c.ShowCustomer();
			c.issueBook();
		}
		else if(z.equalsIgnoreCase("Staff")){
			b.setBookId();
			b.setBookName();
			b.ShowBook();
			b.setisAvailable();
		}
		else{
			System.out.println("Invalid input!!");
		}
		System.out.println("Updated now will learn and apply more things on this");
	}
}