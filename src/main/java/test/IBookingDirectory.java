package test;

import java.util.List;
import java.util.Map;

public interface IBookingDirectory {
    void loadBookingDetails(String file);
    List<Map<String, String>> getAllBookingsDetails();
}
