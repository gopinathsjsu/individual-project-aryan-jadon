package test;

// Importing All Packages
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opencsv.CSVWriter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import static test.CreditCards.checkCardDetails;

public class RunClient {

    public static void main(String[] args) throws IOException {

        // Reading CSV File
        String directoryFile = "/Users/aryanjadon/Desktop/Java-Personal-Project/files/flights.csv";
        String queryFile = "/Users/aryanjadon/Desktop/Java-Personal-Project/files/Sample.csv";

        // Writing Output.txt Errors File
        FileWriter errorWriter = new FileWriter("Output.txt");

        // Flight Directory
        Multimap<String, HashMap> FlightDirectoryMap = ArrayListMultimap.create();

        // Array List of Query Records
        List<Map<String, String>> queryRecords = new ArrayList<Map<String, String>>();

        // Array List of Errors
        ArrayList<String> errorList = new ArrayList<String>();

        List<String[]> outputList = new ArrayList<>();
        String[] header = {
                "Booking Name",
                "Flight Number",
                "Category",
                "Number of Seats Booked",
                "Total Price"
        };

        outputList.add(header);

        // File Reader
        BufferedReader reader = null ;
        String line = "";

        // ************************************************************************************************************
        // ************************************************************************************************************

        // Handling Flight Directory Records
        try{
            reader = new BufferedReader(new FileReader(directoryFile));
            int k=0;

            while((line = reader.readLine()) != null){
                String[] row = line.split(",");
                HashMap<String,String> record = new HashMap<String, String>();

                // Skipping Headers of File
                if(k == 0){
                    k++;
                    continue;
                }

                // Adding Records in HashMap
                record.put("Category",row[0]);
                record.put("AvailableSeats",row[2]);
                record.put("Price",row[3]);
                record.put("Arrival",row[4]);
                record.put("Departure",row[5]);

                // Adding Hashmap in to Array List
                FlightDirectoryMap.put(row[1],record);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            reader.close();
        }

        System.out.println("Fight Directory Data");
        System.out.println(FlightDirectoryMap);
        System.out.println("**********************************************************");

        // ************************************************************************************************************
        // ************************************************************************************************************

        // Handling Query Records
        try{
            reader = new BufferedReader(new FileReader(queryFile));
            int k=0;
            while((line = reader.readLine()) != null){
                String[] row = line.split(",");
                HashMap<String,String> record = new HashMap<String, String>();

                // Skipping Headers of File
                if(k == 0){
                    k++;
                    continue;
                }

                // Adding Records in HashMap
                record.put("BookingName",row[0]);
                record.put("flightNumber",row[1]);
                record.put("seatCategory",row[2]);
                record.put("numberOfSeats",row[3]);
                record.put("paymentCardNumber",row[4]);

                // Adding Hashmap in to Array List
                queryRecords.add(record);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            reader.close();
        }

        System.out.println("Query Data");
        System.out.println(queryRecords);

        System.out.println("**********************************************************");

        // ************************************************************************************************************
        // ************************************************************************************************************

        // Input Validation

        // 1. Check Requested Flight Exists
        for (int i = 0; i < queryRecords.size(); i++) {

            System.out.println("//***************************************************//");
            String queryFlightNumber = queryRecords.get(i).get("flightNumber");
            String queryCustomerName = queryRecords.get(i).get("BookingName");
            String queryCategory = queryRecords.get(i).get("seatCategory");

            boolean Flag = false;
            List<Map.Entry<String, String>> currentRecord = new ArrayList(FlightDirectoryMap.get(queryFlightNumber));

            if (currentRecord.size() == 0) {
                System.out.println("Flight Validation Failed");
                errorList.add("Please enter correct booking details for " + queryCustomerName + ": invalid flight number");
            } else {
                System.out.println("Flight Validation Completed -- Processing Seats");
                // 2. Validate the Number of Seats Requested for the Category
                for (int j = 0; j < currentRecord.size(); j++) {
                    HashMap<String,String> record = new HashMap<String, String>();
                    record = (HashMap<String, String>) currentRecord.get(j);

                    if(record.get("Category").equalsIgnoreCase(queryCategory)){
                        Flag = true;

                        int availableSeats = Integer.parseInt(record.get("AvailableSeats"));
                        int requiredSeats = Integer.parseInt(queryRecords.get(i).get("numberOfSeats"));

                        if (availableSeats > requiredSeats) {
                            System.out.println("Seats Validation Completed -- Processing Price");
                            int totalPrice = requiredSeats*Integer.parseInt(record.get("Price"));
                            String cardNumber = queryRecords.get(i).get("paymentCardNumber");

                            // 3. Validate the Card
                            boolean isValid = checkCardDetails(cardNumber);

                            if (!isValid) {
                                System.out.println("Card Validation Failed");
                                errorList.add("Please enter correct booking details for " + queryCustomerName + ": invalid card number");
                                continue;
                            }

                            // 4. Validate the Seats in Records

                            // update the seats
                            int deltaSeats = availableSeats - requiredSeats;
                            // updating the record
                            FlightDirectoryMap.get(queryFlightNumber).remove(record);
                            record.put("AvailableSeats",String.valueOf(deltaSeats));
                            FlightDirectoryMap.get(queryFlightNumber).add(record);

                            String[] tempRecord = {
                                    queryCustomerName,
                                    queryFlightNumber,
                                    queryCategory,
                                    String.valueOf(requiredSeats),
                                    String.valueOf(totalPrice)
                            };

                            outputList.add(tempRecord);

                        } else {
                            System.out.println("Seat Validation Failed");
                            errorList.add("Please enter correct booking details for " + queryCustomerName + ": seats not available");
                        }
                    }
                }

                if (!Flag) {
                    System.out.println("Flight Validation Failed");
                    errorList.add("Please enter correct booking details for " + queryCustomerName + ": invalid flight number");
                }
            }
        }

        // writing errors
        for(String str: errorList) {
            errorWriter.write(str + System.lineSeparator());
        }
        errorWriter.close();

        // writing Output
        try (CSVWriter writer = new CSVWriter(new FileWriter("Output.csv"))) {
            writer.writeAll(outputList);
        }
    }
}