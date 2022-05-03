package test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightDirectory implements IFlightDirectory {

    // Flight Directory
    Multimap<String, HashMap> FlightDirectoryMap = ArrayListMultimap.create();

    private static Flight createFlight(String[] attributes) {
        String category = attributes[0];
        String flightNumber = attributes[1];
        int noOfSeats = Integer.parseInt(attributes[2]);
        float priceOfSeat = Float.parseFloat(attributes[3]);
        String arrivalCity = attributes[4];
        String departureCity = attributes[5];

        return new Flight(category, flightNumber, noOfSeats, priceOfSeat, arrivalCity, departureCity);
    }


    @Override
    public void loadFlightDetails(String givenFile) {
        // File Reader
        BufferedReader reader = null;

        // Handling Flight Directory Records
        try {
            reader = new BufferedReader(new FileReader(givenFile));
            reader.readLine();
            String line = reader.readLine();

            System.out.println("Creating Flight Records");

            while (line != null) {
                String[] row = line.split(",");
                HashMap<String, String> record = new HashMap<String, String>();
                Flight flight = createFlight(row);
                System.out.println(flight);

                // Adding Records in HashMap
                record.put("Category", flight.category);
                record.put("AvailableSeats", String.valueOf(flight.noOfSeats));
                record.put("Price", String.valueOf(flight.priceOfSeat));
                record.put("Arrival", flight.arrivalCity);
                record.put("Departure", flight.departureCity);

                // Adding Hashmap in to Array List
                FlightDirectoryMap.put(flight.flightNumber, record);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    @Override
    public Multimap<String, HashMap> getAllFlightsDetails() {
        return FlightDirectoryMap;
    }

    @Override
    public Boolean DoesFlightExist(String flightNumber, String seatCategory) {

        List<Map.Entry<String, String>> currentRecord = new ArrayList(FlightDirectoryMap.get(flightNumber));

        if (currentRecord.size() == 0) {
            System.out.println("Flight Validation Failed");
        } else {
            System.out.println("Flight Validation Completed -- Processing Seats");
            // 2. Validate the Number of Seats Requested for the Category

            for (int j = 0; j < currentRecord.size(); j++) {
                HashMap<String, String> record = new HashMap<String, String>();
                record = (HashMap<String, String>) currentRecord.get(j);

                if (record.get("Category").equalsIgnoreCase(seatCategory)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public float CalculateTotalPrice(String flightNumber, String seatCategory, String numberOfSeats) {
        List<Map.Entry<String, String>> currentRecord = new ArrayList(FlightDirectoryMap.get(flightNumber));

        for (int j = 0; j < currentRecord.size(); j++) {
            HashMap<String, String> record = new HashMap<String, String>();
            record = (HashMap<String, String>) currentRecord.get(j);

            if (record.get("Category").equalsIgnoreCase(seatCategory)) {
                int availableSeats = Integer.parseInt(record.get("AvailableSeats"));
                int requiredSeats = Integer.parseInt(numberOfSeats);

                if (availableSeats > requiredSeats) {
                    System.out.println("Seats Validation Completed -- Processing Price");
                    float totalPrice = requiredSeats * Float.valueOf(record.get("Price"));
                    return totalPrice;
                }
            }
        }
        return 0;
    }

    @Override
    public void modifySeats(String flightNumber, String seatCategory, String numberOfSeats) {
        List<Map.Entry<String, String>> currentRecord = new ArrayList(FlightDirectoryMap.get(flightNumber));

        for (int j = 0; j < currentRecord.size(); j++) {
            HashMap<String, String> record = new HashMap<String, String>();
            record = (HashMap<String, String>) currentRecord.get(j);

            if (record.get("Category").equalsIgnoreCase(seatCategory)) {
                int availableSeats = Integer.parseInt(record.get("AvailableSeats"));
                int requiredSeats = Integer.parseInt(numberOfSeats);

                // update the seats
                int deltaSeats = availableSeats - requiredSeats;

                // updating the record
                FlightDirectoryMap.get(flightNumber).remove(record);
                record.put("AvailableSeats",String.valueOf(deltaSeats));
                FlightDirectoryMap.get(flightNumber).add(record);
            }
        }

    }
}
