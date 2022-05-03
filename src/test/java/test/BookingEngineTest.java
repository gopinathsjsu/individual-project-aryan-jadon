package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BookingEngineTest {

    @InjectMocks
    private BookingEngine bookingEngine;

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

}
