package test;

public class Flight {

    public final String category;
    public final String flightNumber;
    public final int noOfSeats;
    public final float priceOfSeat;
    public final String arrivalCity;
    public final String departureCity;

    // Constructor
    public Flight(String category, String fightNumber, int noOfSeats,
                  float priceOfSeat, String arrivalCity, String departureCity) {
        this.category = category;
        this.flightNumber = fightNumber;
        this.noOfSeats = noOfSeats;
        this.priceOfSeat = priceOfSeat;
        this.arrivalCity = arrivalCity;
        this.departureCity = departureCity;
    }
}
