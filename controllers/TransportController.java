package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.example.dao.TransportDAO;
import org.example.models.Flight;
import org.example.models.Transport;

import java.io.IOException;
import java.util.List;

public class TransportController {

    @FXML
    private ListView<String> transportList;

    private Flight selectedFlight;
    private int selectedHotelIndex;
    private int selectedConferenceIndex;

    public void setSelectedData(Flight selectedFlight, int selectedHotelIndex, int selectedConferenceIndex) {
        this.selectedFlight = selectedFlight;
        this.selectedHotelIndex = selectedHotelIndex;
        this.selectedConferenceIndex = selectedConferenceIndex;

        // Create an instance of TransportDAO
        TransportDAO transportDAO = new TransportDAO();

        // Fetch transport options using the instance
        List<Transport> transports = transportDAO.findAll();

        ObservableList<String> transportDetails = FXCollections.observableArrayList();
        for (Transport transport : transports) {
            transportDetails.add("Transport ID: " + transport.getTransportId() +
                    ", Type: " + transport.getType() +
                    ", Price: $" + transport.getPrice());
        }

        transportList.setItems(transportDetails);
    }

    @FXML
    private void nextPage() {
        int selectedIndex = transportList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Navigate to Booking Confirmation Screen
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/booking.fxml"));
                Parent root = loader.load();

                BookingController bookingController = loader.getController();
                bookingController.setBookingData(selectedFlight, selectedHotelIndex, selectedConferenceIndex, selectedIndex);

                Stage stage = (Stage) transportList.getScene().getWindow();
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

