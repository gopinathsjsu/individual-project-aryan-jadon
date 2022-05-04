package test;

public class Booking {
    public final String bookingName;
    public final String flightNumber;
    public final String seatCategory;
    public final int numberOfSeats;
    public final String paymentCardNumber;

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

