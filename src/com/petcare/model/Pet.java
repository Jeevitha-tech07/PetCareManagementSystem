package com.petcare.model;

public class Pet {

    private int petId;
    private int ownerId;
    private String petName;
    private String species;
    private String breed;
    private String gender;
    private int age;
    private double weight;

    // Default constructor
    public Pet() {
    }

    // Parameterized constructor
    public Pet(int petId, int ownerId, String petName,
               String species, String breed, String gender,
               int age, double weight) {

        this.petId = petId;
        this.ownerId = ownerId;
        this.petName = petName;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
    }

    // Getters and Setters

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", ownerId=" + ownerId +
                ", petName='" + petName + '\'' +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}