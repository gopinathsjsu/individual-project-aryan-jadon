package test;

import java.nio.file.Paths;

import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class FlightDirectoryTest {

    @Test
    public void testFlightDetailsClass() {

        FlightDirectory flightDirectory = new FlightDirectory();
        BookingDirectory bookingDirectory = new BookingDirectory();

        BookingEngine bookingEngine = new BookingEngine(flightDirectory,bookingDirectory);

        String userDirectory = Paths.get("").toAbsolutePath().toString();
        String filePath = userDirectory+ "/DemoFiles/flights.csv";

        bookingEngine.initializeFlights(filePath);
        String value = String.valueOf(flightDirectory.getAllFlightsDetails().getClass());

        assertEquals("class com.google.common.collect.ArrayListMultimap",value);

    }

    @Test
    public void testFlightDataMethod1() {

        FlightDirectory flightDirectory = new FlightDirectory();
        BookingDirectory bookingDirectory = new BookingDirectory();

        BookingEngine bookingEngine = new BookingEngine(flightDirectory,bookingDirectory);

        String userDirectory = Paths.get("").toAbsolutePath().toString();
        String filePath = userDirectory+ "/DemoFiles/flights.csv";

        bookingEngine.initializeFlights(filePath);
        Multimap<String, HashMap> flightEntries = flightDirectory.getAllFlightsDetails();

        assertEquals(0,flightEntries.get("WrongEntry").size());

    }

    @Test
    public void testFlightDataMethod2() {

        FlightDirectory flightDirectory = new FlightDirectory();
        BookingDirectory bookingDirectory = new BookingDirectory();

        BookingEngine bookingEngine = new BookingEngine(flightDirectory,bookingDirectory);

        String userDirectory = Paths.get("").toAbsolutePath().toString();
        String filePath = userDirectory+ "/DemoFiles/flights.csv";

        bookingEngine.initializeFlights(filePath);
        Multimap<String, HashMap> flightEntries = flightDirectory.getAllFlightsDetails();

        assertEquals(2,flightEntries.get("BY110").size());

    }

    @Test
    public void testFlightExistsValid() {

        FlightDirectory flightDirectory = new FlightDirectory();
        BookingDirectory bookingDirectory = new BookingDirectory();

        BookingEngine bookingEngine = new BookingEngine(flightDirectory,bookingDirectory);

        String userDirectory = Paths.get("").toAbsolutePath().toString();
        String filePath = userDirectory+ "/DemoFiles/flights.csv";

        bookingEngine.initializeFlights(filePath);

        Boolean flightFlag = flightDirectory.DoesFlightExist("BY110","Business");

        assertEquals(true,flightFlag);

    }

    @Test
    public void testFlightExistsInValid() {

        FlightDirectory flightDirectory = new FlightDirectory();
        BookingDirectory bookingDirectory = new BookingDirectory();

        BookingEngine bookingEngine = new BookingEngine(flightDirectory,bookingDirectory);

        String userDirectory = Paths.get("").toAbsolutePath().toString();
        String filePath = userDirectory+ "/DemoFiles/flights.csv";

        bookingEngine.initializeFlights(filePath);

        Boolean flightFlag = flightDirectory.DoesFlightExist("BY210","Business");

        assertEquals(false,flightFlag);

    }

    @Test
    public void testFlightPriceValid() {

        FlightDirectory flightDirectory = new FlightDirectory();
        BookingDirectory bookingDirectory = new BookingDirectory();

        BookingEngine bookingEngine = new BookingEngine(flightDirectory,bookingDirectory);

        String userDirectory = Paths.get("").toAbsolutePath().toString();
        String filePath = userDirectory+ "/DemoFiles/flights.csv";

        bookingEngine.initializeFlights(filePath);

        float flightPrice = flightDirectory.CalculateTotalPrice("SJ456","Economy","1");
        assertEquals(250.0,flightPrice,0.0);

    }

    @Test
    public void testFlightPriceInValid() {

        FlightDirectory flightDirectory = new FlightDirectory();
        BookingDirectory bookingDirectory = new BookingDirectory();

        BookingEngine bookingEngine = new BookingEngine(flightDirectory,bookingDirectory);

        String userDirectory = Paths.get("").toAbsolutePath().toString();
        String filePath = userDirectory+ "/DemoFiles/flights.csv";

        bookingEngine.initializeFlights(filePath);

        float flightPrice = flightDirectory.CalculateTotalPrice("SJ456","Economy","1");

        assertNotEquals(255.0,flightPrice,0.0);

    }


    @Test
    public void testModifySeats() {

        FlightDirectory flightDirectory = new FlightDirectory();
        BookingDirectory bookingDirectory = new BookingDirectory();

        BookingEngine bookingEngine = new BookingEngine(flightDirectory,bookingDirectory);

        String userDirectory = Paths.get("").toAbsolutePath().toString();
        String filePath = userDirectory+ "/DemoFiles/flights.csv";

        bookingEngine.initializeFlights(filePath);

        flightDirectory.modifySeats("SJ456","Economy","1");

        List<Map.Entry<String, String>> currentRecord = new ArrayList(flightDirectory.FlightDirectoryMap.get("SJ456"));
        HashMap<String, String> record;
        record = (HashMap<String, String>) currentRecord.get(0);

        assertEquals(4, Integer.parseInt(record.get("AvailableSeats")));

    }

}
