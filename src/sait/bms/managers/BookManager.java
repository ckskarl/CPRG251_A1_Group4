package sait.bms.managers;

import sait.bms.problemdomain.*;
import java.util.*;
import java.io.*;

/**
 * This class contains most of public methods to interact with user.
 * 
 * @author Scott Normore, Gao Liu, Christian Lay, Kin Shing Chong
 * 
 */
public class BookManager {
	private final static String PATH = "res\\books.txt";
	private ArrayList<Book> books = new ArrayList<>();

	public void run() throws IOException {
		this.readFile();
		while (true) {
			printMenu();
			Scanner in = new Scanner(System.in);
			String input = in.nextLine();
			if (input.equals("1")) {
				System.out.print("Enter ISBN of book: ");
				long inputISBN = Long.parseLong(in.nextLine());
				this.checkOut(inputISBN);
			} else if (input.equals("2")) {
				System.out.print("Enter title to search for: ");
				String searchInput = in.nextLine().toLowerCase();
				System.out.println("Matching books:");
				this.searchBook(searchInput);
			} else if (input.equals("3")) {
				// display a list of book by type
			} else if (input.equals("4")) {
				// produce random book list
			} else if (input.equals("5")) {
				// save new arrayList to txt file
				break;
			} else {
				System.out.println("ERROR: Invalid input, please try again!");
			}
		}

		System.out.println("Thanks for using! File has been updated. See you!");
	}

	/**
	 * a method to print menu
	 * 
	 * @author Gao Liu
	 */
	private void printMenu() {
		// TODO Auto-generated method stub
		System.out.println("Welcome in ABC Book Company: How May We Assist You?");
		System.out.println("1	Checkout Book");
		System.out.println("2	Find Books by Title");
		System.out.println("3	Display Books by Type");
		System.out.println("4	Produce Random Book List");
		System.out.println("5	Save & Exit");
		System.out.println();
		System.out.print("Enter option: ");
	}

	/**
	 * a method that parses the supplied “books.txt” file into a single array list.
	 * The array list will be able to contain all Book types
	 * 
	 * @throws IOException
	 * @author Scott Normore, Gao Liu, Christian Lay, Kin Shing Chong
	 */
	public void readFile() throws IOException {
		Scanner fileReader = new Scanner(new File(PATH));
		while (fileReader.hasNextLine()) {
			String[] record = fileReader.nextLine().split(";");
			int endingNum;
			endingNum = (int) (Long.parseLong(record[0]) % 10);
			switch (endingNum) {
			case 0:
			case 1: {
				ChildrensBook book = new ChildrensBook(Long.parseLong(record[0]), record[1],
						Integer.parseInt(record[2]), Integer.parseInt(record[3]), record[4], record[5], record[6]);
				this.books.add(book);
				break;
			}
			case 2:
			case 3: {
				CookBook book = new CookBook(Long.parseLong(record[0]), record[1], Integer.parseInt(record[2]),
						Integer.parseInt(record[3]), record[4], record[5], record[6]);
				this.books.add(book);
				break;
			}
			case 4:
			case 5:
			case 6:
			case 7: {
				Paperback book = new Paperback(Long.parseLong(record[0]), record[1], Integer.parseInt(record[2]),
						Integer.parseInt(record[3]), record[4], record[5], Integer.parseInt(record[6]), record[7]);
				this.books.add(book);
				break;
			}
			case 8:
			case 9: {
				Periodical book = new Periodical(Long.parseLong(record[0]), record[1], Integer.parseInt(record[2]),
						Integer.parseInt(record[3]), record[4], record[5]);
				this.books.add(book);
				break;
			}
			}
		}
		fileReader.close();
	}

	/**
	 * This method read the user input, then compare it with the ISBN of each book
	 * in the array list. If ISBN matches, it then check for the available quantity.
	 * If the quantity is non-zero, the quantity will be decremented and the book
	 * information will be displayed. The book is considered to be checked out. If
	 * the quantity is zero, it displays the book is not available. If there is no
	 * match result, it displays an error message.
	 * 
	 * @param inputISBN user input to compare with ISBN of books
	 * @author Kin Shing Chong
	 */
	public void checkOut(long inputISBN) {
		// System.out.println(inputISBN);
		int matchCount = 0;
		for (Book currentBook : this.books) {
			if (currentBook.getIsbn() == inputISBN) {
				matchCount++;
				if (currentBook.getAvailableQty() == 0) {
					System.out.println("The book is currently unavailable");
				} else {
					currentBook.setAvailableQty(currentBook.getAvailableQty() - 1);
					;
					System.out.println("The book \"" + currentBook.getTitle() + "\" has been checked out.");
					System.out.println("It can be located using a call number: " + currentBook.getCallNumber());
				}
			}
		}
		if (matchCount == 0) {
			System.out.println("ERROR: There is no match result.");
		}
		System.out.println();
	}

	/**
	 * This method read the user input, then performs a case-insensitive search with
	 * title of each book in the arrayList if the title containing the input, then
	 * its information will be displayed. otherwise, an ERROR will be displayed to
	 * show no match.
	 * 
	 * @param input user input
	 * @author Gao Liu
	 */
	public void searchBook(String input) {
		int matchCount = 0;
		for (Book currentBook : this.books) {
			if (currentBook.getTitle().toLowerCase().contains(input)) {
				matchCount++;
				System.out.println(currentBook.toString());
			}
		}
		if (matchCount == 0) {
			System.out.println("ERROR: There is no match result.");
		}
	}
}
