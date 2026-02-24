package com.hms.controller;

import com.hms.dao.RoomDAO;
import com.hms.model.Room;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/admin/room/save")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,      // 1 MB
        maxFileSize = 5 * 1024 * 1024,         // 5 MB
        maxRequestSize = 10 * 1024 * 1024      // 10 MB
)
public class SaveRoomServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // ✅ Ensure this folder exists and is writable
    private static final String IMAGE_DIR = "E:/HMS_UPLOADS/room_images";

    private RoomDAO roomDAO;

    @Override
    public void init() {
        roomDAO = new RoomDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ===== ADMIN SESSION CHECK =====
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminName") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/admin/login.jsp");
            return;
        }

        try {
            Room room = new Room();

            // ===== ID (NEW vs EDIT) =====
            String id = request.getParameter("id");
            if (id != null && !id.trim().isEmpty()) {
                room.setId(Long.parseLong(id)); // EDIT
            } else {
                room.setId(0);                  // NEW
            }

            // ===== FORM DATA =====
            room.setRoomName(request.getParameter("roomName"));
            room.setRoomType(request.getParameter("roomType"));
            room.setLocation(request.getParameter("location"));
            room.setPrice(new BigDecimal(request.getParameter("price")));
            room.setCapacity(Integer.parseInt(request.getParameter("capacity")));
            room.setTotalRooms(Integer.parseInt(request.getParameter("totalRooms")));

            // ===== IMAGE UPLOAD =====
            Part imagePart = request.getPart("image");

            if (imagePart != null && imagePart.getSize() > 0) {

                String originalName = imagePart.getSubmittedFileName();
                String fileName = System.currentTimeMillis() + "_" + originalName;

                File uploadDir = new File(IMAGE_DIR);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                imagePart.write(IMAGE_DIR + File.separator + fileName);
                room.setImage(fileName);

            } else if (room.getId() > 0) {
                // KEEP OLD IMAGE WHEN EDITING
                Room existing = roomDAO.getRoomById(room.getId());
                if (existing != null) {
                    room.setImage(existing.getImage());
                }
            } else {
                // NEW ROOM WITHOUT IMAGE
                room.setImage(null);
            }

            // ===== SAVE / UPDATE =====
            roomDAO.saveOrUpdate(room);

            // ===== REDIRECT =====
            response.sendRedirect(request.getContextPath() + "/admin/rooms");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error saving room", e);
        }
    }
}