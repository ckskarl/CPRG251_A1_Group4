package sait.bms.problemdomain;

public class CookBook extends Book {
	private String publisher;
	private String diet;
	public CookBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CookBook(long isbn, String callNumber, int availableQty, int totalQty, String title, String publisher, String diet) {
		super(isbn, callNumber, availableQty, totalQty, title);
		this.publisher=publisher;
		switch(diet) {
		case "D": this.diet = "Diabetic"; break;
		case "V": this.diet = "Vegetarian"; break;
		case "G": this.diet = "Gluten-free"; break;
		case "I": this.diet = "International"; break;
		case "N": this.diet = "None"; break;
		}
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getDiet() {
		return diet;
	}
	public void setDiet(String diet) {
		this.diet = diet;
	}
	@Override
	public String toString() {
		return String.format("%-18s%-13s%n%-18s%-13s%n%-18s%-13d%n%-18s%-13d%n%-18s%-30s%n%-18s%-30s%n%-18s%-30s%n", "ISBN:", getIsbn(), "Call Number:",
				getCallNumber(), "Available", getAvailableQty(), "Total:", getTotalQty(), "Title:", getTitle(), "Publisher:", getPublisher() , "Diet:", getDiet());
	}
	
}
