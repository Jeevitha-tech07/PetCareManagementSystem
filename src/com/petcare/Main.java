package com.petcare;

import com.petcare.ui.OwnerView;
import com.petcare.ui.PetView;
import com.petcare.ui.VeterinarianView;
import com.petcare.ui.AppointmentView;
import com.petcare.ui.MedicalRecordView;
import com.petcare.ui.VaccinationView;
import com.petcare.ui.PrescriptionView;
import com.petcare.ui.BillingView;
import com.petcare.service.PetCareService;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        // Service object
        PetCareService service = new PetCareService();

        // ==============================
        // TITLE
        // ==============================

        Label title = new Label("🐾 PETCARE");

        title.setStyle(
                "-fx-font-size: 32px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1f2937;"
        );
        Label subtitle = new Label(
                "Pet Care & Veterinary Appointment Management System"
        );

        subtitle.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #6b7280;"
        );


        // ==============================
        // MENU BUTTONS
        // ==============================

        Button ownerButton = new Button("Pet Owners");

        ownerButton.setOnAction(event -> {
            OwnerView ownerView = new OwnerView();
            ownerView.show();
        });


        Button petButton = new Button("Pets");

        petButton.setOnAction(event -> {
            PetView petView = new PetView();
            petView.show();
        });


        Button vetButton = new Button("Veterinarians");

        vetButton.setOnAction(event -> {
            VeterinarianView veterinarianView = new VeterinarianView();
            veterinarianView.show();
        });


        Button appointmentButton = new Button("Appointments");

        appointmentButton.setOnAction(event -> {
            AppointmentView appointmentView = new AppointmentView();
            appointmentView.show();
        });


        Button medicalButton = new Button("Medical Records");

        medicalButton.setOnAction(event -> {
            MedicalRecordView medicalRecordView = new MedicalRecordView();
            medicalRecordView.show();
        });


        Button vaccinationButton = new Button("Vaccinations");

        vaccinationButton.setOnAction(event -> {
            VaccinationView vaccinationView = new VaccinationView();
            vaccinationView.show();
        });


        Button prescriptionButton = new Button("Prescriptions");

        prescriptionButton.setOnAction(event -> {
            PrescriptionView prescriptionView = new PrescriptionView();
            prescriptionView.show();
        });


        Button billingButton = new Button("Billing");

        billingButton.setOnAction(event -> {
            BillingView billingView = new BillingView();
            billingView.show();
        });


        // ==============================
        // BUTTON SIZE
        // ==============================

        Button[] buttons = {
                ownerButton,
                petButton,
                vetButton,
                appointmentButton,
                medicalButton,
                vaccinationButton,
                prescriptionButton,
                billingButton
        };

        for (Button button : buttons) {

            button.setPrefWidth(180);
            button.setPrefHeight(50);

            button.setStyle(
                    "-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 10;" +
                    "-fx-border-radius: 10;" +
                    "-fx-border-color: #dbe3ea;" +
                    "-fx-background-color: #ffffff;" +
                    "-fx-text-fill: #1f2937;"
            );
        }


        // ==============================
        // MENU GRID
        // ==============================

        GridPane menu = new GridPane();

        menu.setHgap(20);
        menu.setVgap(20);
        menu.setAlignment(Pos.CENTER);

        menu.add(ownerButton, 0, 0);
        menu.add(petButton, 1, 0);
        menu.add(vetButton, 2, 0);

        menu.add(appointmentButton, 0, 1);
        menu.add(medicalButton, 1, 1);
        menu.add(vaccinationButton, 2, 1);

        menu.add(prescriptionButton, 0, 2);
        menu.add(billingButton, 1, 2);


        // ==============================
        // LIVE DATABASE STATISTICS
        // ==============================

        GridPane statsGrid = new GridPane();

        statsGrid.setHgap(15);
        statsGrid.setVgap(15);
        statsGrid.setAlignment(Pos.CENTER);

        String[] tableNames = {
                "pet_owners",
                "pets",
                "veterinarians",
                "appointments",
                "medical_records",
                "vaccinations",
                "prescriptions",
                "billing"
        };

        String[] statNames = {
                "TOTAL OWNERS",
                "TOTAL PETS",
                "TOTAL VETERINARIANS",
                "TOTAL APPOINTMENTS",
                "MEDICAL RECORDS",
                "VACCINATIONS",
                "PRESCRIPTIONS",
                "BILLS"
        };


        for (int i = 0; i < tableNames.length; i++) {

            Label countLabel = new Label(
                    String.valueOf(
                            service.getTableCount(tableNames[i])
                    )
            );

            countLabel.setStyle(
                    "-fx-font-size: 24px;" +
                    "-fx-font-weight: bold;"
            );


            Label nameLabel = new Label(statNames[i]);

            nameLabel.setStyle(
                    "-fx-font-size: 12px;" +
                    "-fx-font-weight: bold;"
            );


            VBox card = new VBox(5);

            card.setAlignment(Pos.CENTER);

            card.setPrefSize(150, 75);

            card.setStyle(
                    "-fx-background-color: #f8fafc;" +
                    "-fx-border-color: #dbe3ea;" +
                    "-fx-border-radius: 10;" +
                    "-fx-background-radius: 10;"
            );

            card.getChildren().addAll(
                    countLabel,
                    nameLabel
            );


            statsGrid.add(
                    card,
                    i % 4,
                    i / 4
            );
        }


        // ==============================
        // MAIN DASHBOARD
        // ==============================

        VBox root = new VBox(25);

        root.setAlignment(Pos.CENTER);

        root.setPadding(new Insets(35));

        root.getChildren().addAll(
                title,
                subtitle,
                statsGrid,
                menu
        );


        // ==============================
        // SCENE
        // ==============================

        Scene scene = new Scene(
                root,
                900,
                750
        );


        stage.setTitle(
                "PetCare Management System"
        );

        stage.setScene(scene);

        stage.show();
    }


    // ==============================
    // MAIN METHOD
    // ==============================

    public static void main(String[] args) {

        launch(args);
    }
}