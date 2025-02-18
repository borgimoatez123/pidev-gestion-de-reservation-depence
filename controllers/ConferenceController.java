package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.example.dao.ConferenceLocationDAO;
import org.example.models.ConferenceLocation;
import org.example.models.Flight;

import java.io.IOException;
import java.util.List;

public class ConferenceController {

    @FXML
    private ListView<String> conferenceList;

    private Flight selectedFlight;
    private int selectedHotelIndex;

    public void setSelectedFlightAndHotel(Flight selectedFlight, int selectedHotelIndex) {
        this.selectedFlight = selectedFlight;
        this.selectedHotelIndex = selectedHotelIndex;

        // Create an instance of ConferenceLocationDAO
        ConferenceLocationDAO conferenceLocationDAO = new ConferenceLocationDAO();

        // Fetch conference locations based on flight destination
        List<ConferenceLocation> locations = conferenceLocationDAO.findByLocation(selectedFlight.getDestination());

        ObservableList<String> locationDetails = FXCollections.observableArrayList();
        for (ConferenceLocation location : locations) {
            locationDetails.add("Location ID: " + location.getLocationId() +
                    ", Name: " + location.getName() +
                    ", Address: " + location.getAddress() +
                    ", Price/Day: $" + location.getPricePerDay());
        }

        conferenceList.setItems(locationDetails);
    }

    @FXML
    private void nextPage() {
        int selectedIndex = conferenceList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Navigate to Transport Selection Screen
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/transport.fxml"));
                Parent root = loader.load();

                TransportController transportController = loader.getController();
                transportController.setSelectedData(selectedFlight, selectedHotelIndex, selectedIndex);

                Stage stage = (Stage) conferenceList.getScene().getWindow();
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
