package com.petcare.model;

public class Vaccination {

    private int vaccinationId;
    private int petId;
    private String vaccineName;
    private String vaccinationDate;
    private String nextDueDate;
    private String notes;

    // Default constructor
    public Vaccination() {
    }

    // Parameterized constructor
    public Vaccination(int vaccinationId, int petId,
                       String vaccineName, String vaccinationDate,
                       String nextDueDate, String notes) {

        this.vaccinationId = vaccinationId;
        this.petId = petId;
        this.vaccineName = vaccineName;
        this.vaccinationDate = vaccinationDate;
        this.nextDueDate = nextDueDate;
        this.notes = notes;
    }

    // Getters and Setters

    public int getVaccinationId() {
        return vaccinationId;
    }

    public void setVaccinationId(int vaccinationId) {
        this.vaccinationId = vaccinationId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public String getNextDueDate() {
        return nextDueDate;
    }

    public void setNextDueDate(String nextDueDate) {
        this.nextDueDate = nextDueDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Vaccination{" +
                "vaccinationId=" + vaccinationId +
                ", petId=" + petId +
                ", vaccineName='" + vaccineName + '\'' +
                ", vaccinationDate='" + vaccinationDate + '\'' +
                ", nextDueDate='" + nextDueDate + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
