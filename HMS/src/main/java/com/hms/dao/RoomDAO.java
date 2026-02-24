package com.hms.dao;

import com.hms.model.Room;
import com.hms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    // ===== GET ALL ROOMS =====
    public List<Room> getAllRooms() {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM rooms ORDER BY id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Room r = new Room();
                r.setId(rs.getLong("id"));
                r.setRoomName(rs.getString("room_name"));
                r.setRoomType(rs.getString("room_type"));
                r.setImage(rs.getString("image"));
                r.setLocation(rs.getString("location"));
                r.setPrice(rs.getBigDecimal("price"));
                r.setCapacity(rs.getInt("capacity"));
                r.setTotalRooms(rs.getInt("total_rooms"));
                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ===== GET ROOM BY ID =====
    public Room getRoomById(long id) {
        Room r = null;
        String sql = "SELECT * FROM rooms WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                r = new Room();
                r.setId(rs.getLong("id"));
                r.setRoomName(rs.getString("room_name"));
                r.setRoomType(rs.getString("room_type"));
                r.setImage(rs.getString("image"));
                r.setLocation(rs.getString("location"));
                r.setPrice(rs.getBigDecimal("price"));
                r.setCapacity(rs.getInt("capacity"));
                r.setTotalRooms(rs.getInt("total_rooms"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    // ===== SAVE OR UPDATE =====
    public void saveOrUpdate(Room r) {

        String sql = (r.getId() == 0)
                ? "INSERT INTO rooms (room_name, room_type, image, location, price, capacity, total_rooms) VALUES (?,?,?,?,?,?,?)"
                : "UPDATE rooms SET room_name=?, room_type=?, image=?, location=?, price=?, capacity=?, total_rooms=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getRoomName());
            ps.setString(2, r.getRoomType());
            ps.setString(3, r.getImage());
            ps.setString(4, r.getLocation());
            ps.setBigDecimal(5, r.getPrice());
            ps.setInt(6, r.getCapacity());
            ps.setInt(7, r.getTotalRooms());

            if (r.getId() != 0) {
                ps.setLong(8, r.getId());
            }

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== DELETE =====
    public void delete(long id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM rooms WHERE id=?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}