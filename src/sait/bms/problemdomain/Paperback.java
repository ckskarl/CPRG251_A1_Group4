package sait.bms.problemdomain;

public class Paperback extends Book {
	private String authors;
	private int year;
	private String genre;

	// Constructors
	public Paperback() {
		super();
	}

	public Paperback(long isbn, String callNumber, int availableQty, int totalQty, String title, String authors,
			int year, String genre) {
		super(isbn, callNumber, availableQty, totalQty, title);
		this.authors = authors;
		this.year = year;
		switch(genre) {
		case "A": this.genre = "Adventure"; break;
		case "D": this.genre = "Drama"; break;
		case "E": this.genre = "Education"; break;
		case "C": this.genre = "Classic"; break;
		case "F": this.genre = "Fantasy"; break;
		case "S": this.genre = "Science Fiction"; break;
		}
	}

	// Getters and Setters
	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	//other methods
	
	@Override
	public String formatToFile() {
		return super.formatToFile()+";"+this.authors+";"+this.year+";"+this.genre.charAt(0);
	}
	
	@Override
	public String toString() {
		 return String.format(
					"%-18s%-13s%n%-18s%-13s%n%-18s%-13d%n%-18s%-13d%n%-18s%-30s%n%-18s%-30s%n%-18s%-13d%n%-18s%-30s%n",
					"ISBN:", getIsbn(), "Call Number:", getCallNumber(), "Available", getAvailableQty(), "Total:",
					getTotalQty(), "Title:", getTitle(), "Authors:", getAuthors(), "Year:", getYear(), "Genre:",
					getGenre());

	}

}
