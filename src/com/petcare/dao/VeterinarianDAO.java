package com.petcare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.petcare.model.Veterinarian;
import com.petcare.util.DatabaseConnection;

public class VeterinarianDAO {

    // Add a new veterinarian
    public boolean addVeterinarian(Veterinarian veterinarian) {

        String sql = "INSERT INTO veterinarians " +
                     "(vet_name, specialization, phone, email, experience_years) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, veterinarian.getVetName());
            statement.setString(2, veterinarian.getSpecialization());
            statement.setString(3, veterinarian.getPhone());
            statement.setString(4, veterinarian.getEmail());
            statement.setInt(5, veterinarian.getExperienceYears());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all veterinarians
    public List<Veterinarian> getAllVeterinarians() {

        List<Veterinarian> veterinarians = new ArrayList<>();

        String sql = "SELECT * FROM veterinarians";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Veterinarian veterinarian = new Veterinarian();

                veterinarian.setVetId(resultSet.getInt("vet_id"));
                veterinarian.setVetName(resultSet.getString("vet_name"));
                veterinarian.setSpecialization(
                        resultSet.getString("specialization"));
                veterinarian.setPhone(resultSet.getString("phone"));
                veterinarian.setEmail(resultSet.getString("email"));
                veterinarian.setExperienceYears(
                        resultSet.getInt("experience_years"));

                veterinarians.add(veterinarian);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return veterinarians;
    }
    public boolean deleteVeterinarian(int vetId) {

        String sql = "DELETE FROM veterinarians WHERE vet_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, vetId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}