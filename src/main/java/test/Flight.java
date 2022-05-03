package test;

public class Flight {

    public String category;
    public String flightNumber;
    public int noOfSeats;
    public float priceOfSeat;
    public String arrivalCity;
    public String departureCity;

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
