package com.petcare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.petcare.model.PetOwner;
import com.petcare.util.DatabaseConnection;

public class PetOwnerDAO {

    // Add a new pet owner
    public boolean addOwner(PetOwner owner) {

        String sql = "INSERT INTO pet_owners " +
                     "(owner_name, phone, email, address) " +
                     "VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, owner.getOwnerName());
            statement.setString(2, owner.getPhone());
            statement.setString(3, owner.getEmail());
            statement.setString(4, owner.getAddress());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // Get all pet owners
    public List<PetOwner> getAllOwners() {

        List<PetOwner> owners = new ArrayList<>();

        String sql = "SELECT * FROM pet_owners";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                PetOwner owner = new PetOwner();

                owner.setOwnerId(resultSet.getInt("owner_id"));
                owner.setOwnerName(resultSet.getString("owner_name"));
                owner.setPhone(resultSet.getString("phone"));
                owner.setEmail(resultSet.getString("email"));
                owner.setAddress(resultSet.getString("address"));

                owners.add(owner);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return owners;
    }


    // Delete a pet owner
    public boolean deleteOwner(int ownerId) {

        String sql = "DELETE FROM pet_owners WHERE owner_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, ownerId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}