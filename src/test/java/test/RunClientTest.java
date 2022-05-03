package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.nio.file.*;

public class RunClientTest {
    @Test
    public void execution() {

        FlightDirectory flightDirectory = new FlightDirectory();
        BookingDirectory bookingDirectory = new BookingDirectory();

        BookingEngine bookingEngine = new BookingEngine(flightDirectory,bookingDirectory);

        String userDirectory = Paths.get("").toAbsolutePath().toString();
        String flightsfilePath = userDirectory+ "/DemoFiles/flights.csv";
        String bookingsfilePath = userDirectory+ "/DemoFiles/Sample.csv";

        String errorsfilePath = userDirectory+ "/Output.txt";
        String outputfilePath = userDirectory+ "/Output.csv";

        bookingEngine.intializeFlights(flightsfilePath);
        bookingEngine.initializeBookings(bookingsfilePath);

        bookingEngine.process();

        bookingEngine.printReport(outputfilePath);
        bookingEngine.printErrors(errorsfilePath);

        Path outputPath = Paths.get(outputfilePath);
        Path errorPath = Paths.get(errorsfilePath);

        boolean outputflag = false;
        boolean errorflag = false;

        if (Files.exists(outputPath)) {
            outputflag = true;
        }

        if (Files.exists(errorPath)) {
            errorflag = true;
        }

        assertEquals(true,errorflag);
        assertEquals(true,outputflag);

    }
}
