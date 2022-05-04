package test;

public class Record {

    public final int noOfSeatsBooked;
    public final float totalPrice;
    public final String bookingName;
    public final String flightNumber;
    public final String categoryName;

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
