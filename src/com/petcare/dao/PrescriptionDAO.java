package com.petcare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.petcare.model.Prescription;
import com.petcare.util.DatabaseConnection;

public class PrescriptionDAO {

    public boolean addPrescription(Prescription prescription) {

        String sql = "INSERT INTO prescriptions " +
                     "(record_id, medicine_name, dosage, frequency, duration, instructions) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, prescription.getRecordId());
            statement.setString(2, prescription.getMedicineName());
            statement.setString(3, prescription.getDosage());
            statement.setString(4, prescription.getFrequency());
            statement.setString(5, prescription.getDuration());
            statement.setString(6, prescription.getInstructions());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Prescription> getAllPrescriptions() {

        List<Prescription> prescriptions = new ArrayList<>();

        String sql = "SELECT * FROM prescriptions";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Prescription prescription = new Prescription();

                prescription.setPrescriptionId(
                        resultSet.getInt("prescription_id"));

                prescription.setRecordId(
                        resultSet.getInt("record_id"));

                prescription.setMedicineName(
                        resultSet.getString("medicine_name"));

                prescription.setDosage(
                        resultSet.getString("dosage"));

                prescription.setFrequency(
                        resultSet.getString("frequency"));

                prescription.setDuration(
                        resultSet.getString("duration"));

                prescription.setInstructions(
                        resultSet.getString("instructions"));

                prescriptions.add(prescription);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return prescriptions;
    }
    public boolean deletePrescription(int prescriptionId) {

        String sql = "DELETE FROM prescriptions WHERE prescription_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, prescriptionId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}