package com.petcare.ui;

import com.petcare.model.Prescription;
import com.petcare.service.PetCareService;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrescriptionView {

    private final PetCareService service = new PetCareService();

    public void show() {

        Stage stage = new Stage();

        Label title = new Label("PRESCRIPTION MANAGEMENT");

        title.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;"
        );

        TextField recordIdField = new TextField();
        recordIdField.setPromptText("Medical Record ID");

        TextField medicineNameField = new TextField();
        medicineNameField.setPromptText("Medicine Name");

        TextField dosageField = new TextField();
        dosageField.setPromptText("Dosage");

        TextField frequencyField = new TextField();
        frequencyField.setPromptText("Frequency");

        TextField durationField = new TextField();
        durationField.setPromptText("Duration");

        TextField instructionsField = new TextField();
        instructionsField.setPromptText("Instructions");

        Button addButton = new Button("Add Prescription");
        Button clearButton = new Button("Clear");
        Button deleteButton = new Button("Delete Prescription");

        TableView<Prescription> table = new TableView<>();

        TableColumn<Prescription, Integer> idColumn =
                new TableColumn<>("ID");
        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("prescriptionId"));

        TableColumn<Prescription, Integer> recordColumn =
                new TableColumn<>("Record ID");
        recordColumn.setCellValueFactory(
                new PropertyValueFactory<>("recordId"));

        TableColumn<Prescription, String> medicineColumn =
                new TableColumn<>("Medicine");
        medicineColumn.setCellValueFactory(
                new PropertyValueFactory<>("medicineName"));

        TableColumn<Prescription, String> dosageColumn =
                new TableColumn<>("Dosage");
        dosageColumn.setCellValueFactory(
                new PropertyValueFactory<>("dosage"));

        TableColumn<Prescription, String> frequencyColumn =
                new TableColumn<>("Frequency");
        frequencyColumn.setCellValueFactory(
                new PropertyValueFactory<>("frequency"));

        TableColumn<Prescription, String> durationColumn =
                new TableColumn<>("Duration");
        durationColumn.setCellValueFactory(
                new PropertyValueFactory<>("duration"));

        TableColumn<Prescription, String> instructionsColumn =
                new TableColumn<>("Instructions");
        instructionsColumn.setCellValueFactory(
                new PropertyValueFactory<>("instructions"));

        table.getColumns().addAll(
                idColumn,
                recordColumn,
                medicineColumn,
                dosageColumn,
                frequencyColumn,
                durationColumn,
                instructionsColumn
        );

        table.setPrefHeight(300);

        GridPane form = new GridPane();

        form.setHgap(10);
        form.setVgap(10);
        form.setAlignment(Pos.CENTER);

        form.add(new Label("Record ID:"), 0, 0);
        form.add(recordIdField, 1, 0);

        form.add(new Label("Medicine:"), 2, 0);
        form.add(medicineNameField, 3, 0);

        form.add(new Label("Dosage:"), 0, 1);
        form.add(dosageField, 1, 1);

        form.add(new Label("Frequency:"), 2, 1);
        form.add(frequencyField, 3, 1);

        form.add(new Label("Duration:"), 0, 2);
        form.add(durationField, 1, 2);

        form.add(new Label("Instructions:"), 2, 2);
        form.add(instructionsField, 3, 2);

        form.add(addButton, 1, 3);
        form.add(clearButton, 2, 3);
        form.add(deleteButton, 3, 3);

        addButton.setOnAction(event -> {

            try {

                Prescription prescription = new Prescription(
                        0,
                        Integer.parseInt(recordIdField.getText()),
                        medicineNameField.getText(),
                        dosageField.getText(),
                        frequencyField.getText(),
                        durationField.getText(),
                        instructionsField.getText()
                );

                boolean success =
                        service.addPrescription(prescription);

                if (success) {

                    table.setItems(
                            FXCollections.observableArrayList(
                                    service.getAllPrescriptions()
                            )
                    );

                    recordIdField.clear();
                    medicineNameField.clear();
                    dosageField.clear();
                    frequencyField.clear();
                    durationField.clear();
                    instructionsField.clear();
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid Medical Record ID."
                );
            }
        });

        clearButton.setOnAction(event -> {

            recordIdField.clear();
            medicineNameField.clear();
            dosageField.clear();
            frequencyField.clear();
            durationField.clear();
            instructionsField.clear();
        });
        deleteButton.setOnAction(event -> {

            Prescription selectedPrescription =
                    table.getSelectionModel().getSelectedItem();

            if (selectedPrescription != null) {

                boolean success =
                        service.deletePrescription(
                                selectedPrescription.getPrescriptionId()
                        );

                if (success) {

                    table.setItems(
                            FXCollections.observableArrayList(
                                    service.getAllPrescriptions()
                            )
                    );
                }
            }
        });

        table.setItems(
                FXCollections.observableArrayList(
                        service.getAllPrescriptions()
                )
        );

        VBox root = new VBox(20);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));

        root.getChildren().addAll(
                title,
                form,
                table
        );

        Scene scene = new Scene(root, 1150, 650);

        stage.setTitle("PetCare - Prescription Management");
        stage.setScene(scene);
        stage.show();
    }
}