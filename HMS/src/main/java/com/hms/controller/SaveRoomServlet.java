package com.hms.controller;

import com.hms.dao.RoomDAO;
import com.hms.model.Room;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/admin/room/save")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class SaveRoomServlet extends HttpServlet {

    private static final String IMAGE_DIR = "E:/HMS_UPLOADS/room_images";

    private RoomDAO roomDAO;

    @Override
    public void init() {
        roomDAO = new RoomDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminName") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/admin/login.jsp");
            return;
        }

        try {
            Room room = new Room();

            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                room.setId(Long.parseLong(id));
            }

            room.setRoomName(request.getParameter("roomName"));
            room.setRoomType(request.getParameter("roomType"));
            room.setLocation(request.getParameter("location"));
            room.setPrice(new BigDecimal(request.getParameter("price")));
            room.setCapacity(Integer.parseInt(request.getParameter("capacity")));
            room.setTotalRooms(Integer.parseInt(request.getParameter("totalRooms")));

            // ===== IMAGE UPLOAD (SAME NAME) =====
            Part imagePart = request.getPart("image");
            if (imagePart != null && imagePart.getSize() > 0) {

                String fileName = imagePart.getSubmittedFileName(); // SAME NAME
                File uploadDir = new File(IMAGE_DIR);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                imagePart.write(IMAGE_DIR + File.separator + fileName);

                room.setImage(fileName); // DB stores ONLY file name
            }

            roomDAO.saveOrUpdate(room);
            response.sendRedirect(request.getContextPath() + "/admin/rooms");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/rooms");
        }
    }
}