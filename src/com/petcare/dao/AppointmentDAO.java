package com.petcare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.petcare.model.Appointment;
import com.petcare.util.DatabaseConnection;

public class AppointmentDAO {

    // Book a new appointment
    public boolean addAppointment(Appointment appointment) {

        String sql = "INSERT INTO appointments " +
                     "(pet_id, vet_id, appointment_date, appointment_time, reason, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, appointment.getPetId());
            statement.setInt(2, appointment.getVetId());
            statement.setString(3, appointment.getAppointmentDate());
            statement.setString(4, appointment.getAppointmentTime());
            statement.setString(5, appointment.getReason());
            statement.setString(6, appointment.getStatus());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {

        List<Appointment> appointments = new ArrayList<>();

        String sql = "SELECT * FROM appointments";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Appointment appointment = new Appointment();

                appointment.setAppointmentId(
                        resultSet.getInt("appointment_id"));

                appointment.setPetId(
                        resultSet.getInt("pet_id"));

                appointment.setVetId(
                        resultSet.getInt("vet_id"));

                appointment.setAppointmentDate(
                        resultSet.getString("appointment_date"));

                appointment.setAppointmentTime(
                        resultSet.getString("appointment_time"));

                appointment.setReason(
                        resultSet.getString("reason"));

                appointment.setStatus(
                        resultSet.getString("status"));

                appointments.add(appointment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return appointments;
    }
    public boolean deleteAppointment(int appointmentId) {

        String sql = "DELETE FROM appointments WHERE appointment_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, appointmentId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}