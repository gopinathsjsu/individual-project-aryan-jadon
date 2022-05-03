package test;

public class RunClient {

    public static void main(String[] args){

        // Creating Flight Directory Object
        FlightDirectory flightDirectory = new FlightDirectory();

        // Creating Booking Directory Object
        BookingDirectory bookingDirectory = new BookingDirectory();

        // Creating Booking Engine Object
        BookingEngine bookingEngine = new BookingEngine(flightDirectory,bookingDirectory);

        // Creating Entries of Flights and Bookings
        bookingEngine.intializeFlights(args[0]);
        bookingEngine.initializeBookings(args[1]);

        // Process Records
        bookingEngine.process();

        // Add Records to CSV File
        bookingEngine.printReport(args[2]);

        // Add Errors to txt File
        bookingEngine.printErrors(args[3]);
    }
}