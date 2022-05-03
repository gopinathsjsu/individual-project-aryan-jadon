package test;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import java.nio.file.Paths;

public class BookingDirectoryTest {

    @Test
    public void testBookingDetailsClass() {

        FlightDirectory flightDirectory = new FlightDirectory();
        BookingDirectory bookingDirectory = new BookingDirectory();

        BookingEngine bookingEngine = new BookingEngine(flightDirectory,bookingDirectory);

        String userDirectory = Paths.get("").toAbsolutePath().toString();
        String filePath = userDirectory+ "/DemoFiles/Sample.csv";

        bookingEngine.initializeBookings(filePath);
        String value = String.valueOf(bookingDirectory.getAllBookingsDetails().getClass());
        
        assertEquals("class java.util.ArrayList",value);

    }

}
