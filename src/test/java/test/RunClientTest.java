package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.nio.file.*;

public class RunClientTest {
    @Test
    public void execution() {

        FlightDirectory flightDirectory = new FlightDirectory();
        BookingDirectory bookingDirectory = new BookingDirectory();

        BookingEngine bookingEngine = new BookingEngine(flightDirectory,bookingDirectory);

        String userDirectory = Paths.get("").toAbsolutePath().toString();
        String flightsFilePath = userDirectory+ "/DemoFiles/flights.csv";
        String bookingsFilePath = userDirectory+ "/DemoFiles/Sample.csv";

        String errorsFilePath = userDirectory+ "/Output.txt";
        String outputFilePath = userDirectory+ "/Output.csv";

        bookingEngine.initializeFlights(flightsFilePath);
        bookingEngine.initializeBookings(bookingsFilePath);

        bookingEngine.process();

        bookingEngine.printReport(outputFilePath);
        bookingEngine.printErrors(errorsFilePath);

        Path outputPath = Paths.get(outputFilePath);
        Path errorPath = Paths.get(errorsFilePath);

        boolean outputFlag = false;
        boolean errorFlag = false;

        if (Files.exists(outputPath)) {
            outputFlag = true;
        }

        if (Files.exists(errorPath)) {
            errorFlag = true;
        }

        assertTrue(errorFlag);
        assertTrue(outputFlag);

    }
}
