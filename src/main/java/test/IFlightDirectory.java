package test;

import com.google.common.collect.Multimap;
import java.util.HashMap;

public interface IFlightDirectory {

    void loadFlightDetails(String arg);
    Multimap<String, HashMap> getAllFlightsDetails();
    Boolean DoesFlightExist(String flightNumber,String seatCategory);
    float CalculateTotalPrice(String flightNumber, String seatCategory, String numberOfSeats);
    void modifySeats(String flightNumber, String seatCategory, String numberOfSeats);
}
