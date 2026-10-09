package com.petcare.model;

public class Bill {

    private int billId;
    private int appointmentId;
    private double consultationFee;
    private double medicineFee;
    private double otherCharges;
    private double totalAmount;
    private String paymentStatus;
    private String paymentDate;

    // Default constructor
    public Bill() {
    }

    // Parameterized constructor
    public Bill(int billId, int appointmentId,
                double consultationFee, double medicineFee,
                double otherCharges, double totalAmount,
                String paymentStatus, String paymentDate) {

        this.billId = billId;
        this.appointmentId = appointmentId;
        this.consultationFee = consultationFee;
        this.medicineFee = medicineFee;
        this.otherCharges = otherCharges;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }

    // Getters and Setters

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public double getMedicineFee() {
        return medicineFee;
    }

    public void setMedicineFee(double medicineFee) {
        this.medicineFee = medicineFee;
    }

    public double getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(double otherCharges) {
        this.otherCharges = otherCharges;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", appointmentId=" + appointmentId +
                ", consultationFee=" + consultationFee +
                ", medicineFee=" + medicineFee +
                ", otherCharges=" + otherCharges +
                ", totalAmount=" + totalAmount +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                '}';
    }
}