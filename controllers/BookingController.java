package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.dao.*;
import org.example.models.*;

import java.sql.Date;
import java.util.List;

public class BookingController {

    @FXML
    private Label confirmationLabel;

    private Flight selectedFlight;
    private Hotel selectedHotel;
    private ConferenceLocation selectedConference;
    private Transport selectedTransport;

    public void setBookingData(Flight selectedFlight, int selectedHotelIndex, int selectedConferenceIndex, int selectedTransportIndex) {
        this.selectedFlight = selectedFlight;

        // Create instances of DAO classes
        HotelDAO hotelDAO = new HotelDAOimplt();
        ConferenceLocationDAO conferenceLocationDAO = new ConferenceLocationDAO();
        TransportDAO transportDAO = new TransportDAO();

        // Fetch hotel, conference location, and transport based on indices
        List<Hotel> hotels = hotelDAO.findByLocation(selectedFlight.getDestination());
        List<ConferenceLocation> locations = conferenceLocationDAO.findByLocation(selectedFlight.getDestination());
        List<Transport> transports = transportDAO.findAll();

        this.selectedHotel = hotels.get(selectedHotelIndex);
        this.selectedConference = locations.get(selectedConferenceIndex);
        this.selectedTransport = transports.get(selectedTransportIndex);

        // Display confirmation details
        confirmationLabel.setText("Flight: " + selectedFlight.getAirline() +
                "\nHotel: " + selectedHotel.getName() +
                "\nConference Location: " + selectedConference.getName() +
                "\nTransport: " + selectedTransport.getType());
    }

    @FXML
    private void confirmBooking() {
        // Create an instance of BookingDAO
        BookingDAO bookingDAO = new Bookingimplt();

        // Save booking to the database
        Booking booking = new Booking();
        booking.setFlightId(selectedFlight.getFlightId());
        booking.setHotelId(selectedHotel.getHotelId());
        booking.setConferenceLocationId(selectedConference.getLocationId());
        booking.setTransportId(selectedTransport.getTransportId());
        booking.setUserName("JohnDoe"); // Replace with actual user input
        booking.setBookingDate(new java.sql.Date(System.currentTimeMillis()));
        booking.setStatus("wait");

        // Set the airline name
        booking.setAirlines(selectedFlight.getAirline());
        booking.setFlightPrice(selectedFlight.getPrice());
        booking.setDepartureTime(selectedFlight.getDepartureTime());

        // Log the booking details for debugging
        System.out.println("Attempting to save booking with details:");
        System.out.println("Flight ID: " + booking.getFlightId());
        System.out.println("Hotel ID: " + booking.getHotelId());
        System.out.println("Transport ID: " + booking.getTransportId());
        System.out.println("Conference Location ID: " + booking.getConferenceLocationId());
        System.out.println("User Name: " + booking.getUserName());
        System.out.println("Booking Date: " + booking.getBookingDate());
        System.out.println("Status: " + booking.getStatus());
        System.out.println("Airlines: " + booking.getAirlines());
        System.out.println("FlightPrice: " + booking.getFlightPrice());
        System.out.println("Departure Time: " + booking.getDepartureTime());

        // Save the booking
        bookingDAO.save(booking);

        // Update the UI
        confirmationLabel.setText("Booking Confirmed!");
    }


}
