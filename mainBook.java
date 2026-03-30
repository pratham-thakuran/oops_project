import java.util.*;
class Input{
	Scanner sc = new Scanner(System.in);
}
class BookInfo extends Input{
	int bookId;
	String bookName;
	String can_i_issue;
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
		if(can_i_issue.equals("no")){
			System.out.println("You cannot borrow this book.");
		}
		else if(can_i_issue.equals("yes")){
			System.out.println("Yes you can borrow it.");
		}
		else{
			System.out.println("Invalid");
		}
	}
	// public void bookissue(){
	// 	if(can_i_issue.equals("yes")){
	// 		System.out.println("Yes the book is available.");
	// 	}
	// 	else{
	// 		System.out.println("No the book is not available");
	// 	}
	// }
	public void ShowBook(){
		System.out.println(bookId+"-"+bookName+"-"+can_i_issue);
	}
}

// interface BInfo{
// 	void ShowBInfo();
// }

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
	@Override
	public void setisAvailable(){
		if(can_i_issue.equals("no")){
			System.out.println("No this book can not be borrowed.");
		}
		else if (can_i_issue.equals("yes")){
			System.out.println("yes you can borrow this book.");
		}
	}
}

// interface CInfo{
// 	void ShowCInfo();
// }


public class mainBook{
	public static void main(String[] args){
		BookInfo b = new BookInfo();
		CustomerInfo c = new CustomerInfo();
		b.setBookId();
		b.setBookName();
		b.setisAvailable();
		c.setCustomerId();
		c.setCustomerName();
		b.ShowBook();
		c.ShowCustomer();

		

	}
}