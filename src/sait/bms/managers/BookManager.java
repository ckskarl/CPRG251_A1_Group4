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
				this.displayBookType();
			} else if (input.equals("4")) {
				System.out.print("Enter number of books: ");
				this.produceRandomList(in.nextInt());
			} else if (input.equals("5")) {
				this.updateAndSave();
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

	/**
	 * displayBookType is a method that prompts the user to enter a number from 1-4 to determine which
	 * type of book they want to look up. When the user decides which type of book they want to lookup
	 *  they can also look up the format, genre, diet, and frequency so that the display is even more
	 *  specific.
	 *  @author Christian Lay
	 */
	public void displayBookType() throws IOException {
		Scanner in = new Scanner(System.in);
		boolean flag=false; //The flag is used for checking if there is any books that match the category later.
		String userChoice;
		System.out.println("#     Type");
		System.out.println("1     Children's Books");
		System.out.println("2     Cookbooks");
		System.out.println("3     Paperbacks");
		System.out.println("4     Periodicals");
		System.out.print("Enter type of book: ");
		userChoice = in.next();
		
		if(userChoice.equals("1")) {
			System.out.print("Enter a format (P for Picture book, E for Early Readers, or C for Chapter book): ");
			userChoice = in.next();
			System.out.println("Matching books:");
			for(int i = 0; i < books.size(); i++) {
				if(books.get(i).getIsbn()%10 ==1 || books.get(i).getIsbn()%10 ==0 ) {
					ChildrensBook childrensBook = (ChildrensBook)books.get(i);
					if(childrensBook.getFormat().charAt(0) == userChoice.charAt(0)) {
						System.out.println(childrensBook.toString());
						flag=true;
					}				
				}
			}
			if(!flag) {
				System.out.println("ERROR: There is no match result.");
			}
		}
		
		else if (userChoice.equals("2")) {
			System.out.print("Enter a diet (D for Diabetic, V for Vegetarian, G for Gluten-free, I for International, or N for None): ");
			userChoice = in.next();
			System.out.println("Matching books:");
			for(int i = 0; i < books.size(); i++) {
				if(books.get(i).getIsbn()%10 ==2 || books.get(i).getIsbn()%10 ==3 ) {
					CookBook cookBook = (CookBook)books.get(i);
					if(cookBook.getDiet().charAt(0) == userChoice.charAt(0)) {
						System.out.println(cookBook.toString());
						flag=true;
					}		
				}
			}
			if(!flag) {
				System.out.println("ERROR: There is no match result.");
			}
		}
		
		else if (userChoice.equals("3")) {
			System.out.print("Enter a genre (A for Adventure, D for Drama, E for Education, C for Classic, F for Fantasy, or S for Science Fiction): ");
			userChoice = in.next();
			System.out.println("Matching books:");	
			for(int i = 0; i < books.size(); i++) {
				if(books.get(i).getIsbn()%10 ==4 || books.get(i).getIsbn()%10 ==5 || books.get(i).getIsbn()%10 ==6 || books.get(i).getIsbn()%10 ==7 ) {
					Paperback paperback = (Paperback)books.get(i);
					if(paperback.getGenre().charAt(0) == userChoice.charAt(0)) {
						System.out.println(paperback.toString());
						flag=true;
					}				
				}
			}
			if(!flag) {
				System.out.println("ERROR: There is no match result.");
			}
		}
		
		else if (userChoice.equals("4")) {
			System.out.print("Enter a frequency (D for Daily, W for Weekly, M for Monthly, B for Bimonthly, and Q for Quarterly): ");
			userChoice = in.next();
			System.out.println("Matching books:");
			for(int i = 0; i < books.size(); i++) {
				if(books.get(i).getIsbn()%10 ==8 || books.get(i).getIsbn()%10 ==9 ) {
					Periodical periodical = (Periodical)books.get(i);
					if(periodical.getFrequency().charAt(0) == userChoice.charAt(0)) {
						System.out.println(periodical.toString());
						flag=true;
					}					
				}
			}
			if(!flag) {
				System.out.println("ERROR: There is no match result.");
			}
		}
		else {
			System.out.println("ERROR: Invalid input, please try again!");
		}
	}	

	/**
	 * This method takes a number, then produces a randomly generated list of books with
	 * size based on the number given in the input. It does this by selecting the first
	 * number of books in the books ArrayList after shuffling the contents using 
	 * Collections.shuffle()
	 * 
	 * @param num number of books desired in list
	 * @author Scott Normore
	 */
	public void produceRandomList(int num) {
		Collections.shuffle(books);
		for(int i=0; i<num; i++) {
			System.out.println(books.get(i).toString());
		}
	}
	
	/**
	 * This method updates the books list with the checked out/newly added books.
	 * This method is called whenever one uses the exit input to exit the program.
	 * @author Scott Normore
	 */
	public void updateAndSave() throws IOException {
		PrintWriter writer = new PrintWriter(new File(PATH));
		for(int i=0; i<books.size(); i++) {
			writer.println(books.get(i).formatToFile());
		}
		writer.close();
	}
	
}
