package sait.bms.managers;
import sait.bms.problemdomain.*;
import java.util.*;
import java.io.*;

public class BookManager{
	private final static String PATH ="res\\books.txt";
	private ArrayList<Book> books=new ArrayList<>();
	
	public void run() throws IOException{
		this.readFile();
		
		//just for testing
		//for (int i=0;i<=10;i++) {
		//	System.out.println(books.get(i).toString());
		//}
		
		
		/*
		 * for checkOut method
		 */
		long inputISBN=9795333191914L; //pseudo input for ISBN
		this.checkOut(inputISBN);
		
	}
	
	public void readFile() throws IOException {
		Scanner fileReader = new Scanner(new File(PATH));
		while(fileReader.hasNextLine()){
			String[] record = fileReader.nextLine().split(";");
			if(record[0].charAt(record[0].length()-1)=='0' || record[0].charAt(record[0].length()-1)=='1') {
				ChildrensBook book=new ChildrensBook(Long.parseLong(record[0]),record[1],Integer.parseInt(record[2]),Integer.parseInt(record[3]),record[4],record[5],record[6]);
				this.books.add(book);
			}
			else if(record[0].charAt(record[0].length()-1)=='2' || record[0].charAt(record[0].length()-1)=='3') {
				CookBook book=new CookBook(Long.parseLong(record[0]),record[1],Integer.parseInt(record[2]),Integer.parseInt(record[3]),record[4],record[5],record[6]);
				this.books.add(book);
			}
			else if(record[0].charAt(record[0].length()-1)=='4' || record[0].charAt(record[0].length()-1)=='7') {
				Paperback book=new Paperback(Long.parseLong(record[0]),record[1],Integer.parseInt(record[2]),Integer.parseInt(record[3]),record[4],record[5],Integer.parseInt(record[6]),record[7]);
				this.books.add(book);
			}
			else if(record[0].charAt(record[0].length()-1)=='8' || record[0].charAt(record[0].length()-1)=='9') {
				Periodical book=new Periodical(Long.parseLong(record[0]),record[1],Integer.parseInt(record[2]),Integer.parseInt(record[3]),record[4],record[5]);
				this.books.add(book);
			}
		}
		fileReader.close();
	}
	
	/*
	 *This method read the user input, then compare it with the ISBN of each book in the array list. 
	 *If ISBN matches, it then check for the available quantity.
	 *If the quantity is non-zero, the quantity will be deccremented and the book information will be displayed. The book is considered to be checked out.
	 *If the qunatity is zero, it displays the book is not available.
	 *If there is no match result, it displays an error message. 
	 */
	public void checkOut(long inputISBN) {
		//System.out.println(inputISBN);
		int matchCount=0;
		for (Book currentBook : this.books) {
			if (currentBook.getIsbn()== inputISBN) {
				matchCount++;
				if (currentBook.getAvailableQty()==0) {
					System.out.println("The book is currently unavailable");
				} 
				else {
					currentBook.setAvailableQty(currentBook.getAvailableQty()-1);;
					System.out.println("Comfirmation: " + currentBook);
				}
			}
		}
			if (matchCount==0) {
				System.out.println("ERROR: There is no match result.");
			}
	}
}

