package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.example.dao.FlightDAO;
import org.example.dao.FlightDAOimplt;
import org.example.models.Flight;

import java.io.IOException;
import java.util.List;

public class FlightController {

    @FXML
    private ListView<String> flightList;

    private List<Flight> flights;

    public void setDestination(String destination) {
        // Create an instance of FlightDAO
        FlightDAO flightDAO = new FlightDAOimplt();

        // Fetch flights from the database using the instance
        flights = flightDAO.findByDestination(destination);

        ObservableList<String> flightDetails = FXCollections.observableArrayList();
        for (Flight flight : flights) {
            flightDetails.add("Flight ID: " + flight.getFlightId() +
                    ", Airline: " + flight.getAirline() +
                    ", Departure: " + flight.getDepartureTime() +
                    ", Price: $" + flight.getPrice());
        }

        flightList.setItems(flightDetails);
    }

    @FXML
    private void nextPage() {
        int selectedIndex = flightList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Navigate to Hotel Selection Screen
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/hotels.fxml"));
                Parent root = loader.load();

                HotelController hotelController = loader.getController();
                hotelController.setSelectedFlight(flights.get(selectedIndex));

                Stage stage = (Stage) flightList.getScene().getWindow();
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}