package com.hms.dao;

import com.hms.model.Booking;
import com.hms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    // 🔹 Fetch all bookings for admin
    public List<Booking> getAllBookings() {

        List<Booking> list = new ArrayList<>();

        String sql = """
            SELECT b.id, b.check_in, b.check_out, b.adults, b.child, b.status,
                   u.full_name,
                   r.room_name, r.room_type, r.price
            FROM bookings b
            JOIN users u ON b.user_id = u.id
            JOIN rooms r ON b.room_id = r.id
            ORDER BY b.booked_at DESC
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Booking b = new Booking();
                b.setId(rs.getLong("id"));
                b.setUserName(rs.getString("full_name"));
                b.setRoomName(rs.getString("room_name"));
                b.setRoomType(rs.getString("room_type"));
                b.setPrice(rs.getBigDecimal("price"));
                b.setCheckIn(rs.getDate("check_in"));
                b.setCheckOut(rs.getDate("check_out"));
                b.setAdults(rs.getInt("adults"));
                b.setChild(rs.getInt("child"));
                b.setStatus(rs.getString("status"));
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 🔹 Update booking status (CONFIRM / CANCEL)
    public void updateBookingStatus(long bookingId, String status) {

        String sql = "UPDATE bookings SET status=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setLong(2, bookingId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 // 🔹 Fetch bookings for logged-in user
    public List<Booking> getBookingsByUser(int userId) {

        List<Booking> list = new ArrayList<>();

        String sql = """
            SELECT b.id, b.check_in, b.check_out, b.adults, b.child, b.status,
                   r.room_name, r.room_type, r.price
            FROM bookings b
            JOIN rooms r ON b.room_id = r.id
            WHERE b.user_id = ?
            ORDER BY b.booked_at DESC
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking b = new Booking();
                b.setId(rs.getLong("id"));
                b.setRoomName(rs.getString("room_name"));
                b.setRoomType(rs.getString("room_type"));
                b.setPrice(rs.getBigDecimal("price"));
                b.setCheckIn(rs.getDate("check_in"));
                b.setCheckOut(rs.getDate("check_out"));
                b.setAdults(rs.getInt("adults"));
                b.setChild(rs.getInt("child"));
                b.setStatus(rs.getString("status"));
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}