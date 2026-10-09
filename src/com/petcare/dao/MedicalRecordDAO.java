package com.petcare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.petcare.model.MedicalRecord;
import com.petcare.util.DatabaseConnection;

public class MedicalRecordDAO {

    public boolean addMedicalRecord(MedicalRecord record) {

        String sql = "INSERT INTO medical_records " +
                     "(pet_id, vet_id, visit_date, symptoms, diagnosis, treatment, notes) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, record.getPetId());
            statement.setInt(2, record.getVetId());
            statement.setString(3, record.getVisitDate());
            statement.setString(4, record.getSymptoms());
            statement.setString(5, record.getDiagnosis());
            statement.setString(6, record.getTreatment());
            statement.setString(7, record.getNotes());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MedicalRecord> getAllMedicalRecords() {

        List<MedicalRecord> records = new ArrayList<>();

        String sql = "SELECT * FROM medical_records";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                MedicalRecord record = new MedicalRecord();

                record.setRecordId(
                        resultSet.getInt("record_id"));

                record.setPetId(
                        resultSet.getInt("pet_id"));

                record.setVetId(
                        resultSet.getInt("vet_id"));

                record.setVisitDate(
                        resultSet.getString("visit_date"));

                record.setSymptoms(
                        resultSet.getString("symptoms"));

                record.setDiagnosis(
                        resultSet.getString("diagnosis"));

                record.setTreatment(
                        resultSet.getString("treatment"));

                record.setNotes(
                        resultSet.getString("notes"));

                records.add(record);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }
    public boolean deleteMedicalRecord(int recordId) {

        String sql = "DELETE FROM medical_records WHERE record_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, recordId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}
