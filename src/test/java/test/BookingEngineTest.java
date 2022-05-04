package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BookingEngineTest {


    @Test
    public void testMasterCard() {
        Boolean result = BookingEngine.isValidCard("5431111111111301");
        assertEquals(true, result);
    }

    @Test
    public void testVisaCard() {
        Boolean result = BookingEngine.isValidCard("4012888888881881");
        assertEquals(true, result);
    }

    @Test
    public void testDiscoverCard() {
        Boolean result = BookingEngine.isValidCard("6011000990139424");
        assertEquals(true, result);
    }

    @Test
    public void testAmexCard() {
        Boolean result = BookingEngine.isValidCard("378282246310005");
        assertEquals(true, result);
    }

    @Test
    public void testValidCreditCard() {
        Boolean result = BookingEngine.isValidCard("3566002020360505");
        assertEquals(true, result);
    }

    @Test
    public void testInvalidCreditCard() {
        Boolean result = BookingEngine.isValidCard("356600202036050535663566");
        assertEquals(false, result);
    }

    @Test
    public void testWriteFileCSVMethod() {

        String userDirectory = Paths.get("").toAbsolutePath().toString();
        String filePath = userDirectory+ "/DemoFiles/test.csv";

        var reportHeader = "BookingName,FlightNumber,SeatCategory,NoOfSeatsBooked,TotalPrice" + "\n";
        ArrayList<Record> records = new ArrayList<>();
        Record record = new Record(
                "Aryan Jadon",
                "SJ456",
                "Economy",
                5,
                500
                );

        records.add(record);
        BookingEngine.writeIntoFile(filePath,records,reportHeader);

        Path outputPath = Paths.get(filePath);

        boolean outputFlag = Files.exists(outputPath);

        assertTrue(outputFlag);

        try {
            Files.deleteIfExists(outputPath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriteFileTXTMethod() {

        String userDirectory = Paths.get("").toAbsolutePath().toString();
        String filePath = userDirectory+ "/DemoFiles/test.txt";

        var errorHeader = "";
        ArrayList<String> errors = new ArrayList<>();
        errors.add("File Test");

        BookingEngine.writeIntoFile(filePath,errors,errorHeader);
        Path outputPath = Paths.get(filePath);

        boolean outputFlag = Files.exists(outputPath);

        assertTrue(outputFlag);

        try {
            Files.deleteIfExists(outputPath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
