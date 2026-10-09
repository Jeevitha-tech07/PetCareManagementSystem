package com.petcare.service;

import java.util.List;

import com.petcare.dao.AppointmentDAO;
import com.petcare.dao.PetDAO;
import com.petcare.dao.PetOwnerDAO;
import com.petcare.dao.VeterinarianDAO;
import com.petcare.model.Appointment;
import com.petcare.model.Pet;
import com.petcare.model.PetOwner;
import com.petcare.model.Veterinarian;
import com.petcare.dao.MedicalRecordDAO;
import com.petcare.model.MedicalRecord;
import com.petcare.dao.VaccinationDAO;
import com.petcare.model.Vaccination;
import com.petcare.dao.PrescriptionDAO;
import com.petcare.model.Prescription;
import com.petcare.dao.BillingDAO;
import com.petcare.model.Bill;

public class PetCareService {

    private final PetOwnerDAO petOwnerDAO;
    private final PetDAO petDAO;
    private final VeterinarianDAO veterinarianDAO;
    private final AppointmentDAO appointmentDAO;
    private final MedicalRecordDAO medicalRecordDAO;
    private final VaccinationDAO vaccinationDAO;
    private final PrescriptionDAO prescriptionDAO;
    private final BillingDAO billingDAO;

    // Constructor
    public PetCareService() {

        petOwnerDAO = new PetOwnerDAO();
        petDAO = new PetDAO();
        veterinarianDAO = new VeterinarianDAO();
        appointmentDAO = new AppointmentDAO();
        medicalRecordDAO = new MedicalRecordDAO();
        vaccinationDAO = new VaccinationDAO();
        prescriptionDAO = new PrescriptionDAO();
        billingDAO = new BillingDAO();
    }

    // ---------------- PET OWNER ----------------

    public boolean addOwner(PetOwner owner) {
        return petOwnerDAO.addOwner(owner);
    }

    public List<PetOwner> getAllOwners() {
        return petOwnerDAO.getAllOwners();
    }
    public boolean deleteOwner(int ownerId) {
        return petOwnerDAO.deleteOwner(ownerId);
    }

    // ---------------- PET ----------------

    public boolean addPet(Pet pet) {
        return petDAO.addPet(pet);
    }

    public List<Pet> getAllPets() {
        return petDAO.getAllPets();
    }
    public boolean deletePet(int petId) {
        return petDAO.deletePet(petId);
    }

    // ---------------- VETERINARIAN ----------------

    public boolean addVeterinarian(Veterinarian veterinarian) {
        return veterinarianDAO.addVeterinarian(veterinarian);
    }

    public List<Veterinarian> getAllVeterinarians() {
        return veterinarianDAO.getAllVeterinarians();
    }
    public boolean deleteVeterinarian(int vetId) {
        return veterinarianDAO.deleteVeterinarian(vetId);
    }

    // ---------------- APPOINTMENT ----------------

    public boolean bookAppointment(Appointment appointment) {
        return appointmentDAO.addAppointment(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDAO.getAllAppointments();
    }
    public boolean deleteAppointment(int appointmentId) {
        return appointmentDAO.deleteAppointment(appointmentId);
    }
    public boolean addMedicalRecord(MedicalRecord record) {
        return medicalRecordDAO.addMedicalRecord(record);
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordDAO.getAllMedicalRecords();
    }
    public boolean deleteMedicalRecord(int recordId) {
        return medicalRecordDAO.deleteMedicalRecord(recordId);
    }
    public boolean addVaccination(Vaccination vaccination) {
        return vaccinationDAO.addVaccination(vaccination);
    }

    public List<Vaccination> getAllVaccinations() {
        return vaccinationDAO.getAllVaccinations();
    }
    public boolean deleteVaccination(int vaccinationId) {
        return vaccinationDAO.deleteVaccination(vaccinationId);
    }
    
    public boolean addPrescription(Prescription prescription) {
        return prescriptionDAO.addPrescription(prescription);
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionDAO.getAllPrescriptions();
    }
    public boolean deletePrescription(int prescriptionId) {
        return prescriptionDAO.deletePrescription(prescriptionId);
    }
    public boolean addBill(Bill bill) {
        return billingDAO.addBill(bill);
    }

    public List<Bill> getAllBills() {
        return billingDAO.getAllBills();
    }
    public boolean deleteBill(int billId) {
        return billingDAO.deleteBill(billId);
    }
    public int getTableCount(String tableName) {

        String sql = "SELECT COUNT(*) FROM " + tableName;

        try (java.sql.Connection connection =
                     com.petcare.util.DatabaseConnection.getConnection();
             java.sql.PreparedStatement statement =
                     connection.prepareStatement(sql);
             java.sql.ResultSet resultSet =
                     statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}