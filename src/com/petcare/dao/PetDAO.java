package com.petcare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.petcare.model.Pet;
import com.petcare.util.DatabaseConnection;

public class PetDAO {

    // Add a new pet
    public boolean addPet(Pet pet) {

        String sql = "INSERT INTO pets " +
                     "(owner_id, pet_name, species, breed, gender, age, weight) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, pet.getOwnerId());
            statement.setString(2, pet.getPetName());
            statement.setString(3, pet.getSpecies());
            statement.setString(4, pet.getBreed());
            statement.setString(5, pet.getGender());
            statement.setInt(6, pet.getAge());
            statement.setDouble(7, pet.getWeight());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // Get all pets
    public List<Pet> getAllPets() {

        List<Pet> pets = new ArrayList<>();

        String sql = "SELECT * FROM pets";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Pet pet = new Pet();

                pet.setPetId(resultSet.getInt("pet_id"));
                pet.setOwnerId(resultSet.getInt("owner_id"));
                pet.setPetName(resultSet.getString("pet_name"));
                pet.setSpecies(resultSet.getString("species"));
                pet.setBreed(resultSet.getString("breed"));
                pet.setGender(resultSet.getString("gender"));
                pet.setAge(resultSet.getInt("age"));
                pet.setWeight(resultSet.getDouble("weight"));

                pets.add(pet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pets;
    }


    // Delete a pet
    public boolean deletePet(int petId) {

        String sql = "DELETE FROM pets WHERE pet_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, petId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}