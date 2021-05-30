package sait.bms.problemdomain;

public class Periodical extends Book {
	private String frequency;

	public Periodical(long isbn, String callNumber, int availableQty, int totalQty, String title, String frequency) {
		super(isbn, callNumber, availableQty, totalQty, title);
		switch(frequency) {
		case "D": this.frequency = "Daily"; break;
		case "W": this.frequency = "Weekly"; break;
		case "M": this.frequency = "Monthly"; break;
		case "B": this.frequency = "Bimonthly"; break;
		case "Q": this.frequency = "Quarterly"; break;
		}
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	//other methods

	@Override
	public String formatToFile() {
		return super.formatToFile()+";"+this.frequency.charAt(0);
	}
	
	@Override
	public String toString() {
		return String.format("%-18s%-13s%n%-18s%-13s%n%-18s%-13d%n%-18s%-13d%n%-18s%-30s%n%-18s%-30s%n", "ISBN:", getIsbn(), "Call Number:",
				getCallNumber(), "Available", getAvailableQty(), "Total:", getTotalQty(), "Title:", getTitle(), "Frequency:", getFrequency());
	}
	
	
}
