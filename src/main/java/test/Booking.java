package test;

public class Booking {
    public String bookingName;
    public String flightNumber;
    public String seatCategory;
    public int numberOfSeats;
    public String paymentCardNumber;

    // constructor
    public Booking(String bookingName, String flightNumber, String seatCategory, int numberOfSeats,
                   String paymentCardNumber) {
        this.bookingName = bookingName;
        this.flightNumber = flightNumber;
        this.seatCategory = seatCategory;
        this.numberOfSeats = numberOfSeats;
        this.paymentCardNumber = paymentCardNumber;
    }
}

