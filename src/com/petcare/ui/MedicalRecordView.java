package com.petcare.ui;

import com.petcare.model.MedicalRecord;
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

public class MedicalRecordView {

    private final PetCareService service = new PetCareService();

    public void show() {

        Stage stage = new Stage();

        Label title = new Label("MEDICAL RECORD MANAGEMENT");

        title.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;"
        );

        TextField petIdField = new TextField();
        petIdField.setPromptText("Pet ID");

        TextField vetIdField = new TextField();
        vetIdField.setPromptText("Veterinarian ID");

        TextField visitDateField = new TextField();
        visitDateField.setPromptText("YYYY-MM-DD");

        TextField symptomsField = new TextField();
        symptomsField.setPromptText("Symptoms");

        TextField diagnosisField = new TextField();
        diagnosisField.setPromptText("Diagnosis");

        TextField treatmentField = new TextField();
        treatmentField.setPromptText("Treatment");

        TextField notesField = new TextField();
        notesField.setPromptText("Notes");

        Button addButton = new Button("Add Record");
        Button clearButton = new Button("Clear");
        Button deleteButton = new Button("Delete Record");

        TableView<MedicalRecord> table = new TableView<>();

        TableColumn<MedicalRecord, Integer> idColumn =
                new TableColumn<>("ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("recordId")
        );

        TableColumn<MedicalRecord, Integer> petColumn =
                new TableColumn<>("Pet ID");

        petColumn.setCellValueFactory(
                new PropertyValueFactory<>("petId")
        );

        TableColumn<MedicalRecord, Integer> vetColumn =
                new TableColumn<>("Vet ID");

        vetColumn.setCellValueFactory(
                new PropertyValueFactory<>("vetId")
        );

        TableColumn<MedicalRecord, String> dateColumn =
                new TableColumn<>("Visit Date");

        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("visitDate")
        );

        TableColumn<MedicalRecord, String> symptomsColumn =
                new TableColumn<>("Symptoms");

        symptomsColumn.setCellValueFactory(
                new PropertyValueFactory<>("symptoms")
        );

        TableColumn<MedicalRecord, String> diagnosisColumn =
                new TableColumn<>("Diagnosis");

        diagnosisColumn.setCellValueFactory(
                new PropertyValueFactory<>("diagnosis")
        );

        TableColumn<MedicalRecord, String> treatmentColumn =
                new TableColumn<>("Treatment");

        treatmentColumn.setCellValueFactory(
                new PropertyValueFactory<>("treatment")
        );

        TableColumn<MedicalRecord, String> notesColumn =
                new TableColumn<>("Notes");

        notesColumn.setCellValueFactory(
                new PropertyValueFactory<>("notes")
        );

        table.getColumns().addAll(
                idColumn,
                petColumn,
                vetColumn,
                dateColumn,
                symptomsColumn,
                diagnosisColumn,
                treatmentColumn,
                notesColumn
        );

        table.setPrefHeight(300);

        GridPane form = new GridPane();

        form.setHgap(10);
        form.setVgap(10);
        form.setAlignment(Pos.CENTER);

        form.add(new Label("Pet ID:"), 0, 0);
        form.add(petIdField, 1, 0);

        form.add(new Label("Vet ID:"), 2, 0);
        form.add(vetIdField, 3, 0);

        form.add(new Label("Visit Date:"), 0, 1);
        form.add(visitDateField, 1, 1);

        form.add(new Label("Symptoms:"), 2, 1);
        form.add(symptomsField, 3, 1);

        form.add(new Label("Diagnosis:"), 0, 2);
        form.add(diagnosisField, 1, 2);

        form.add(new Label("Treatment:"), 2, 2);
        form.add(treatmentField, 3, 2);

        form.add(new Label("Notes:"), 0, 3);
        form.add(notesField, 1, 3);

        form.add(addButton, 2, 3);
        form.add(clearButton, 3, 3);
        form.add(deleteButton, 4, 3);

        addButton.setOnAction(event -> {

            try {

                MedicalRecord record = new MedicalRecord(
                        0,
                        Integer.parseInt(petIdField.getText()),
                        Integer.parseInt(vetIdField.getText()),
                        visitDateField.getText(),
                        symptomsField.getText(),
                        diagnosisField.getText(),
                        treatmentField.getText(),
                        notesField.getText()
                );

                boolean success =
                        service.addMedicalRecord(record);

                if (success) {

                    table.setItems(
                            FXCollections.observableArrayList(
                                    service.getAllMedicalRecords()
                            )
                    );

                    petIdField.clear();
                    vetIdField.clear();
                    visitDateField.clear();
                    symptomsField.clear();
                    diagnosisField.clear();
                    treatmentField.clear();
                    notesField.clear();
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter valid numbers for Pet ID and Vet ID."
                );
            }
        });

        clearButton.setOnAction(event -> {

            petIdField.clear();
            vetIdField.clear();
            visitDateField.clear();
            symptomsField.clear();
            diagnosisField.clear();
            treatmentField.clear();
            notesField.clear();
        });
        deleteButton.setOnAction(event -> {

            MedicalRecord selectedRecord =
                    table.getSelectionModel().getSelectedItem();

            if (selectedRecord != null) {

                boolean success =
                        service.deleteMedicalRecord(
                                selectedRecord.getRecordId()
                        );

                if (success) {

                    table.setItems(
                            FXCollections.observableArrayList(
                                    service.getAllMedicalRecords()
                            )
                    );
                }
            }
        });

        table.setItems(
                FXCollections.observableArrayList(
                        service.getAllMedicalRecords()
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

        Scene scene = new Scene(root, 1200, 650);

        stage.setTitle("PetCare - Medical Record Management");
        stage.setScene(scene);
        stage.show();
    }
}