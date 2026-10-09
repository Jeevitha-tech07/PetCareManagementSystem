package com.petcare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.petcare.model.Vaccination;
import com.petcare.util.DatabaseConnection;

public class VaccinationDAO {

    public boolean addVaccination(Vaccination vaccination) {

        String sql = "INSERT INTO vaccinations " +
                     "(pet_id, vaccine_name, vaccination_date, next_due_date, notes) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, vaccination.getPetId());
            statement.setString(2, vaccination.getVaccineName());
            statement.setString(3, vaccination.getVaccinationDate());
            statement.setString(4, vaccination.getNextDueDate());
            statement.setString(5, vaccination.getNotes());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Vaccination> getAllVaccinations() {

        List<Vaccination> vaccinations = new ArrayList<>();

        String sql = "SELECT * FROM vaccinations";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Vaccination vaccination = new Vaccination();

                vaccination.setVaccinationId(
                        resultSet.getInt("vaccination_id"));

                vaccination.setPetId(
                        resultSet.getInt("pet_id"));

                vaccination.setVaccineName(
                        resultSet.getString("vaccine_name"));

                vaccination.setVaccinationDate(
                        resultSet.getString("vaccination_date"));

                vaccination.setNextDueDate(
                        resultSet.getString("next_due_date"));

                vaccination.setNotes(
                        resultSet.getString("notes"));

                vaccinations.add(vaccination);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return vaccinations;
    }
    public boolean deleteVaccination(int vaccinationId) {

        String sql = "DELETE FROM vaccinations WHERE vaccination_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, vaccinationId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}