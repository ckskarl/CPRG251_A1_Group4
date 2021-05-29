package sait.bms.problemdomain;

public class ChildrensBook extends Book {
	private String authors;
	private String format;
	
	public ChildrensBook() {
		super();
	}
	public ChildrensBook(long isbn, String callNumber, int availableQty, int totalQty, String title,String authors, String format) {
		super(isbn, callNumber, availableQty, totalQty, title);
		this.authors = authors;
		switch(format) {
		case "P": this.format = "Picture Book"; break;
		case "E": this.format = "Early Readers"; break;
		case "C": this.format = "Chapter Book"; break;
		}
	}
	
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	@Override
	public String toString() {
		return String.format("%-18s%-13s%n%-18s%-13s%n%-18s%-13d%n%-18s%-13d%n%-18s%-30s%n%-18s%-30s%n%-18s%-30s%n", "ISBN:", getIsbn(), "Call Number:",
				getCallNumber(), "Available", getAvailableQty(), "Total:", getTotalQty(), "Title:", getTitle(), "Authors:", getAuthors(), "Format:", getFormat());
	}
	
	
}
