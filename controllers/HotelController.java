package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.example.dao.HotelDAO;
import org.example.dao.HotelDAOimplt;
import org.example.models.Flight;
import org.example.models.Hotel;

import java.io.IOException;
import java.util.List;

public class HotelController {

    @FXML
    private ListView<String> hotelList;

    private Flight selectedFlight;

    public void setSelectedFlight(Flight selectedFlight) {
        this.selectedFlight = selectedFlight;

        // Create an instance of HotelDAO
        HotelDAO hotelDAO = new HotelDAOimplt();

        // Fetch hotels based on flight destination
        List<Hotel> hotels = hotelDAO.findByLocation(selectedFlight.getDestination());

        ObservableList<String> hotelDetails = FXCollections.observableArrayList();
        for (Hotel hotel : hotels) {
            hotelDetails.add("Hotel ID: " + hotel.getHotelId() +
                    ", Name: " + hotel.getName() +
                    ", Price/Night: $" + hotel.getPricePerNight() +
                    ", Rating: " + hotel.getRating());
        }

        hotelList.setItems(hotelDetails);
    }

    @FXML
    private void nextPage() {
        int selectedIndex = hotelList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Navigate to Conference Location Selection Screen
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/conference.fxml"));
                Parent root = loader.load();

                ConferenceController conferenceController = loader.getController();
                conferenceController.setSelectedFlightAndHotel(selectedFlight, selectedIndex);

                Stage stage = (Stage) hotelList.getScene().getWindow();
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
