package com.petcare.model;

public class Veterinarian {

    private int vetId;
    private String vetName;
    private String specialization;
    private String phone;
    private String email;
    private int experienceYears;

    // Default constructor
    public Veterinarian() {
    }

    // Parameterized constructor
    public Veterinarian(int vetId, String vetName,
                        String specialization, String phone,
                        String email, int experienceYears) {

        this.vetId = vetId;
        this.vetName = vetName;
        this.specialization = specialization;
        this.phone = phone;
        this.email = email;
        this.experienceYears = experienceYears;
    }

    // Getters and Setters

    public int getVetId() {
        return vetId;
    }

    public void setVetId(int vetId) {
        this.vetId = vetId;
    }

    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    @Override
    public String toString() {
        return "Veterinarian{" +
                "vetId=" + vetId +
                ", vetName='" + vetName + '\'' +
                ", specialization='" + specialization + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", experienceYears=" + experienceYears +
                '}';
    }
}