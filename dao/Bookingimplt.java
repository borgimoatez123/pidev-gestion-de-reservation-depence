package org.example.dao;/*package org.example.dao;



import org.example.models.Booking;

import java.sql.*;
        import java.util.ArrayList;
import java.util.List;

public class Bookingimplt implements BookingDAO {

    @Override
    public List<Booking> findAll() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection conn = DBconnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("flight_id"),
                        rs.getInt("hotel_id"),
                        rs.getInt("transport_id"),
                        rs.getInt("conference_location_id"),
                        rs.getString("user_name"),
                        rs.getDate("booking_date")
                );
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public Booking findById(int id) {
        String sql = "SELECT * FROM bookings WHERE booking_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("flight_id"),
                        rs.getInt("hotel_id"),
                        rs.getInt("transport_id"),
                        rs.getInt("conference_location_id"),
                        rs.getString("user_name"),
                        rs.getDate("booking_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Booking booking) {
        String sql = "INSERT INTO bookings (flight_id, hotel_id, transport_id, conference_location_id, user_name, booking_date) VALUES (?, ?, ?, ?, ?, CURDATE())";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, booking.getFlightId());
            stmt.setInt(2, booking.getHotelId());
            stmt.setInt(3, booking.getTransportId());
            stmt.setInt(4, booking.getConferenceLocationId());
            stmt.setString(5, booking.getUserName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM bookings WHERE booking_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

 */
import org.example.dao.BookingDAO;
import org.example.dao.DBconnection;
import org.example.models.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bookingimplt  implements BookingDAO {

    @Override
    public List<Booking> findAll() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection conn = DBconnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("flight_id"),
                        rs.getInt("hotel_id"),

                        rs.getInt("transport_id"),
                        rs.getInt("conference_location_id"),
                        rs.getString("user_name"),
                        rs.getDate("booking_date"),
                        rs.getString("status"), //
                        rs.getString("airlines"),
                        rs.getTimestamp("departure_time"), // Retrieve departure time
                        rs.getDouble("flight_price")// Include status

// Include status
                );
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public Booking findById(int id) {
        String sql = "SELECT * FROM bookings WHERE booking_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("flight_id"),
                        rs.getInt("hotel_id"),
                        rs.getInt("transport_id"),
                        rs.getInt("conference_location_id"),
                        rs.getString("user_name"),
                        rs.getDate("booking_date"),
                        rs.getString("status") ,
                        rs.getString("airlines"),
                        rs.getTimestamp("departure_time"), // Retrieve departure time
                        rs.getDouble("flight_price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Booking booking) {
        String sql = "INSERT INTO bookings (flight_id, hotel_id, transport_id, conference_location_id, user_name, booking_date, status, airlines,departure_time,flight_price,) VALUES (?, ?, ?, ?, ?, CURDATE(), ?, ?, ?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters
            stmt.setInt(1, booking.getFlightId());
            stmt.setInt(2, booking.getHotelId());
            stmt.setInt(3, booking.getTransportId());
            stmt.setInt(4, booking.getConferenceLocationId());
            stmt.setString(5, booking.getUserName());
            stmt.setString(6, booking.getStatus());
            stmt.setString(7, booking.getAirlines());
            stmt.setTimestamp(8, booking.getDepartureTime());
            stmt.setDouble(9,booking.getFlightPrice());// Default status is 'wait'

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM bookings WHERE booking_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatus(int bookingId, String status) {
        String sql = "UPDATE bookings SET status = ? WHERE booking_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, bookingId);
            stmt.executeUpdate();

            System.out.println("Booking ID " + bookingId + " status updated to: " + status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Add this to your Bookingimplt class
    public List<Booking> findByStatus(String status) {
        List<Booking> filtered = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE status = ?";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                filtered.add(new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("flight_id"),
                        rs.getInt("hotel_id"),
                        rs.getInt("transport_id"),
                        rs.getInt("conference_location_id"),
                        rs.getString("user_name"),
                        rs.getDate("booking_date"),
                        rs.getString("status"),
                        rs.getString("airlines"),
                        rs.getTimestamp("departure_time"), // Retrieve departure time
                        rs.getDouble("flight_price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filtered;
    }
}