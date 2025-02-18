package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.dao.Bookingimplt;
import org.example.models.Booking;

public class HistoryDetails {

    @FXML
    private TextField bookingIdField;

    @FXML
    private TextField userNameField;

    @FXML
    private TextField flightIdField;

    @FXML
    private TextField hotelIdField;

    @FXML
    private TextField transportIdField;

    @FXML
    private TextField conferenceLocationIdField;

    @FXML
    private TextField airlinesField;

    @FXML
    private TextField flightPriceField;

    @FXML
    private TextField departureTimeField;

    private Booking selectedBooking;
    private HistoryController historyController;

    public void setBooking(Booking booking, HistoryController historyController) {
        this.selectedBooking = booking;
        this.historyController = historyController;

        // Populate the fields with booking details
        bookingIdField.setText(String.valueOf(booking.getBookingId()));
        userNameField.setText(booking.getUserName());
        flightIdField.setText(String.valueOf(booking.getFlightId()));
        hotelIdField.setText(String.valueOf(booking.getHotelId()));
        transportIdField.setText(String.valueOf(booking.getTransportId()));
        conferenceLocationIdField.setText(String.valueOf(booking.getConferenceLocationId()));
        airlinesField.setText(booking.getAirlines());
        flightPriceField.setText(String.valueOf(booking.getFlightPrice()));
        departureTimeField.setText(booking.getDepartureTime() != null ? booking.getDepartureTime().toString() : "N/A");
    }

    @FXML
    private void navigateToPayment() {
        try {
            // Load the payment page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/payment.fxml"));
            Parent root = loader.load();

            // Pass the booking ID to the PaymentController
            PaymentController paymentController = loader.getController();
            paymentController.setBookingId(selectedBooking.getBookingId());

            // Show the payment page
            Stage stage = new Stage();
            stage.setTitle("Payment");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void confirmBooking() {
        updateStatus("payment_confirmed");
    }

    @FXML
    private void notConfirmBooking() {
        updateStatus("not_payment");
    }

    private void updateStatus(String status) {
        // Update the status in the database
        Bookingimplt bookingDAO = new Bookingimplt();
        bookingDAO.updateStatus(selectedBooking.getBookingId(), status);
        System.out.println("Booking ID " + selectedBooking.getBookingId() + " status updated to: " + status);

        // Refresh the admin table
        if (historyController != null) {
            historyController.refreshTable();
        }

        // Close the current window
        bookingIdField.getScene().getWindow().hide();
    }
}