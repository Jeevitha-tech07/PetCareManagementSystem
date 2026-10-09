package com.petcare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.petcare.model.Bill;
import com.petcare.util.DatabaseConnection;

public class BillingDAO {

    public boolean addBill(Bill bill) {

        String sql = "INSERT INTO billing " +
                     "(appointment_id, consultation_fee, medicine_fee, " +
                     "other_charges, payment_status, payment_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bill.getAppointmentId());
            statement.setDouble(2, bill.getConsultationFee());
            statement.setDouble(3, bill.getMedicineFee());
            statement.setDouble(4, bill.getOtherCharges());
            statement.setString(5, bill.getPaymentStatus());
            statement.setString(6, bill.getPaymentDate());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Bill> getAllBills() {

        List<Bill> bills = new ArrayList<>();

        String sql = "SELECT * FROM billing";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Bill bill = new Bill();

                bill.setBillId(
                        resultSet.getInt("bill_id"));

                bill.setAppointmentId(
                        resultSet.getInt("appointment_id"));

                bill.setConsultationFee(
                        resultSet.getDouble("consultation_fee"));

                bill.setMedicineFee(
                        resultSet.getDouble("medicine_fee"));

                bill.setOtherCharges(
                        resultSet.getDouble("other_charges"));

                bill.setTotalAmount(
                        resultSet.getDouble("total_amount"));

                bill.setPaymentStatus(
                        resultSet.getString("payment_status"));

                bill.setPaymentDate(
                        resultSet.getString("payment_date"));

                bills.add(bill);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bills;
    }
    public boolean deleteBill(int billId) {

        String sql = "DELETE FROM billing WHERE bill_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, billId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}