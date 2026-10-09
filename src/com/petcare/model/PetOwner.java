package com.petcare.model;

public class PetOwner {

    private int ownerId;
    private String ownerName;
    private String phone;
    private String email;
    private String address;

   
    public PetOwner() {
    }

   
    public PetOwner(int ownerId, String ownerName, String phone,
                    String email, String address) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    
    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PetOwner{" +
                "ownerId=" + ownerId +
                ", ownerName='" + ownerName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}