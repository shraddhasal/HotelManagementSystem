package com.hms.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Booking {

    private long id;
    private String userName;
    private String roomName;
    private String roomType;
    private BigDecimal price;
    private Date checkIn;
    private Date checkOut;
    private int adults;
    private int child;
    private String status;

    // ===== Getters & Setters =====

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCheckIn() {
        return checkIn;
    }
    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }
    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public int getAdults() {
        return adults;
    }
    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChild() {
        return child;
    }
    public void setChild(int child) {
        this.child = child;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}