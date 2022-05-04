package test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;

public class BookingEngine {

    private final IFlightDirectory flightDirectory;
    private final IBookingDirectory bookingDirectory;

    final ArrayList<String> errors = new ArrayList<>();
    final ArrayList<Record> records = new ArrayList<>();

    BookingEngine(IFlightDirectory flightDirectory,IBookingDirectory bookingDirectory) {
        this.flightDirectory = flightDirectory;
        this.bookingDirectory = bookingDirectory;
    }

    public void initializeFlights(String file) {
        this.flightDirectory.loadFlightDetails(file);
    }
    public void initializeBookings(String file) {
        this.bookingDirectory.loadBookingDetails(file);
    }

    public static Boolean isValidCard(String paymentCardNumber) {
        boolean isValid = false;

        // Patterns for -
        // 1. checking for Visa Card
        // 2. checking for Master Card and Discover
        // 3. checking for Master Card and Discover

        String regex = "^(?:(4[0-9]{12}(?:[0-9]{3})?)|" +
                "([1-5]{1}[0-9]{15})|" +
                "(6(?:011|5[0-9]{2})[0-9]{12})|" +
                "(3[47][0-9]{13}))$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(paymentCardNumber);

        if(matcher.matches()){
            isValid = true;
        }

        return isValid;
    }

    public void process() {
        var bookings = bookingDirectory.getAllBookingsDetails();

        System.out.println("Booking Data");
        System.out.println(bookings);
        System.out.println();

        // go through each records
        for(Map<String, String> booking:bookings) {
            System.out.println();
            var flights = flightDirectory.getAllFlightsDetails();
            System.out.println("Processing For "+booking.get("flightNumber"));

            // check flight exists
            var result = flightDirectory.DoesFlightExist(
                    booking.get("flightNumber"), booking.get("seatCategory"));

            if(!result) {
                errors.add("Please enter correct booking details for "+
                        booking.get("bookingName")+": invalid flight number\n");
            }
            else{

                float totalPrice = flightDirectory.CalculateTotalPrice(
                        booking.get("flightNumber"), booking.get("seatCategory"), booking.get("numberOfSeats"));

                if (totalPrice == 0){
                    System.out.println("Seats Validation Failed");

                    errors.add("Please enter correct booking details for " +
                            booking.get("bookingName")+": no seats available in "
                            +booking.get("seatCategory")+"\n");
                    continue;
                }

                if(!isValidCard(booking.get("paymentCardNumber"))){
                    System.out.println("Payment Validation Failed");
                    errors.add("Please enter correct booking details for "+ booking.get("bookingName")+
                            ": Invalid Card\n");
                    continue;
                }

                System.out.println("Payment Validation Completed");

                flightDirectory.modifySeats(
                        booking.get("flightNumber"),booking.get("seatCategory"), booking.get("numberOfSeats"));

                System.out.println(flights);

                Record record = new Record(
                        booking.get("bookingName"),
                        booking.get("flightNumber"),
                        booking.get("seatCategory"),
                        Integer.parseInt(booking.get("numberOfSeats")),
                        totalPrice);

                records.add(record);
            }
        }
    }

    public void printReport(String file) {
        var reportHeader = "BookingName,FlightNumber,SeatCategory,NoOfSeatsBooked,TotalPrice" + "\n";
        writeIntoFile(file, records, reportHeader);

    }

    public void printErrors(String file) {
        var errorHeader = "";
        writeIntoFile(file,errors,errorHeader);
    }

    public static <T> void writeIntoFile(String arg, ArrayList<T> records,String header) {
        FileWriter fw;
        try {
            fw = new FileWriter(arg);
            if(!header.isEmpty())
                fw.write(header);
            for (T record: records) {
                fw.write(record.toString());
            }
            fw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
