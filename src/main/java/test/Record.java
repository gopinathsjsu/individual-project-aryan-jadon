package test;

public class Record {

    public int noOfSeatsBooked;
    public float totalPrice;
    public String bookingName;
    public String flightNumber;
    public String categoryName;

    // Constructor
    public Record(String bookingName, String flightNumber, String categoryName, int noOfSeatsBooked, float totalPrice) {

        this.bookingName = bookingName;
        this.flightNumber = flightNumber;
        this.categoryName = categoryName;
        this.noOfSeatsBooked = noOfSeatsBooked;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        // Creating CSV Row Record
        return bookingName + ',' + flightNumber + ',' + categoryName + ',' + noOfSeatsBooked + ',' + totalPrice + "\n";
    }

}
