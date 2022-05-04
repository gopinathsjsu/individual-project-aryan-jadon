package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingDirectory implements IBookingDirectory{

    // Array List of Query Records
    final List<Map<String, String>> queryRecords = new ArrayList<Map<String, String>>();

    private static Booking createBooking(String[] attributes) {
        String bookingName = attributes[0];
        String flightNumber = attributes[1];
        String seatCategory = attributes[2];
        int noOfSeats = Integer.parseInt(attributes[3]);
        String paymentCardNumber = attributes[4];
        return new Booking(bookingName,flightNumber,seatCategory,noOfSeats,paymentCardNumber);
    }

    @Override
    public void loadBookingDetails(String givenFile) {
        // File Reader
        BufferedReader reader;

        // Handling Flight Directory Records
        try{
            reader = new BufferedReader(new FileReader(givenFile));
            reader.readLine();
            String line = reader.readLine();

            System.out.println("Creating Query Records");

            while( line!= null)
            {
                String[] row = line.split(",");
                HashMap<String,String> record = new HashMap<String, String>();
                Booking booking = createBooking(row);
                System.out.println(booking);

                // Adding Records in HashMap
                record.put("bookingName",booking.bookingName);
                record.put("flightNumber",booking.flightNumber);
                record.put("seatCategory",booking.seatCategory);
                record.put("numberOfSeats", String.valueOf(booking.numberOfSeats));
                record.put("paymentCardNumber",booking.paymentCardNumber);

                queryRecords.add(record);
                line = reader.readLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println();
    }

    @Override
    public List<Map<String, String>> getAllBookingsDetails() {
        return queryRecords;
    }

}
