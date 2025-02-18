/*package org.example.models;

import java.sql.Date;

public class Booking {
    private int bookingId;
    private int flightId;
    private int hotelId;
    private String userName;
    private Date bookingDate;

    public Booking() {}

    public Booking(int bookingId, int flightId, int hotelId, String userName, Date bookingDate) {
        this.bookingId = bookingId;
        this.flightId = flightId;
        this.hotelId = hotelId;
        this.userName = userName;
        this.bookingDate = bookingDate;
    }

    // Getters and Setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getFlightId() { return flightId; }
    public void setFlightId(int flightId) { this.flightId = flightId; }

    public int getHotelId() { return hotelId; }
    public void setHotelId(int hotelId) { this.hotelId = hotelId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }
}

 */

package org.example.models;

import java.sql.Date;
import java.sql.Timestamp;

public class Booking {
    private int bookingId;
    private int flightId;
    private int hotelId;
    private int transportId;
    private int conferenceLocationId;
    private String userName;
    private Date bookingDate;
    private String status;
    private String airlines; //
    private Timestamp departureTime; // New field for departure time
    private double flightPrice;


    public Booking() {}

    public Booking(int bookingId, int flightId, int hotelId, int transportId, int conferenceLocationId, String userName, Date bookingDate, String status, String airlines,Timestamp departureTime, double flightPrice) {
        this.bookingId = bookingId;
        this.flightId = flightId;
        this.hotelId = hotelId;
        this.transportId = transportId;
        this.conferenceLocationId = conferenceLocationId;
        this.userName = userName;
        this.bookingDate = bookingDate;
        this.status = status;
        this.airlines = airlines;
        this.departureTime = departureTime;
        this.flightPrice = flightPrice;
    }

    // Getters and Setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getFlightId() { return flightId; }
    public void setFlightId(int flightId) { this.flightId = flightId; }

    public int getHotelId() { return hotelId; }
    public void setHotelId(int hotelId) { this.hotelId = hotelId; }

    public int getTransportId() { return transportId; }
    public void setTransportId(int transportId) { this.transportId = transportId; }

    public int getConferenceLocationId() { return conferenceLocationId; }
    public void setConferenceLocationId(int conferenceLocationId) { this.conferenceLocationId = conferenceLocationId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAirlines() {
        return airlines;
    }

    public void setAirlines(String airlines) {
        this.airlines = airlines;
    }

    public int getConferenceId() {
        return conferenceLocationId;
    }

    public void setFlightDetails(String flightDestination, String airline, Timestamp departureTime) {
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public double getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(double flightPrice) {
        this.flightPrice = flightPrice;
    }


}
