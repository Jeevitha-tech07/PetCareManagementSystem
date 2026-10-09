package com.petcare.ui;

import com.petcare.model.Vaccination;
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

public class VaccinationView {

    private final PetCareService service = new PetCareService();

    public void show() {

        Stage stage = new Stage();

        Label title = new Label("VACCINATION MANAGEMENT");

        title.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;"
        );

        TextField petIdField = new TextField();
        petIdField.setPromptText("Pet ID");

        TextField vaccineNameField = new TextField();
        vaccineNameField.setPromptText("Vaccine Name");

        TextField vaccinationDateField = new TextField();
        vaccinationDateField.setPromptText("YYYY-MM-DD");

        TextField nextDueDateField = new TextField();
        nextDueDateField.setPromptText("YYYY-MM-DD");

        TextField notesField = new TextField();
        notesField.setPromptText("Notes");

        Button addButton = new Button("Add Vaccination");
        Button clearButton = new Button("Clear");
        Button deleteButton = new Button("Delete Vaccination");

        TableView<Vaccination> table = new TableView<>();

        TableColumn<Vaccination, Integer> idColumn =
                new TableColumn<>("ID");
        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("vaccinationId"));

        TableColumn<Vaccination, Integer> petColumn =
                new TableColumn<>("Pet ID");
        petColumn.setCellValueFactory(
                new PropertyValueFactory<>("petId"));

        TableColumn<Vaccination, String> vaccineColumn =
                new TableColumn<>("Vaccine Name");
        vaccineColumn.setCellValueFactory(
                new PropertyValueFactory<>("vaccineName"));

        TableColumn<Vaccination, String> vaccinationDateColumn =
                new TableColumn<>("Vaccination Date");
        vaccinationDateColumn.setCellValueFactory(
                new PropertyValueFactory<>("vaccinationDate"));

        TableColumn<Vaccination, String> nextDueDateColumn =
                new TableColumn<>("Next Due Date");
        nextDueDateColumn.setCellValueFactory(
                new PropertyValueFactory<>("nextDueDate"));

        TableColumn<Vaccination, String> notesColumn =
                new TableColumn<>("Notes");
        notesColumn.setCellValueFactory(
                new PropertyValueFactory<>("notes"));

        table.getColumns().addAll(
                idColumn,
                petColumn,
                vaccineColumn,
                vaccinationDateColumn,
                nextDueDateColumn,
                notesColumn
        );

        table.setPrefHeight(300);

        GridPane form = new GridPane();

        form.setHgap(10);
        form.setVgap(10);
        form.setAlignment(Pos.CENTER);

        form.add(new Label("Pet ID:"), 0, 0);
        form.add(petIdField, 1, 0);

        form.add(new Label("Vaccine Name:"), 2, 0);
        form.add(vaccineNameField, 3, 0);

        form.add(new Label("Vaccination Date:"), 0, 1);
        form.add(vaccinationDateField, 1, 1);

        form.add(new Label("Next Due Date:"), 2, 1);
        form.add(nextDueDateField, 3, 1);

        form.add(new Label("Notes:"), 0, 2);
        form.add(notesField, 1, 2);

        form.add(addButton, 2, 2);
        form.add(clearButton, 3, 2);
        form.add(deleteButton, 4, 2);

        addButton.setOnAction(event -> {

            try {

                Vaccination vaccination = new Vaccination(
                        0,
                        Integer.parseInt(petIdField.getText()),
                        vaccineNameField.getText(),
                        vaccinationDateField.getText(),
                        nextDueDateField.getText(),
                        notesField.getText()
                );

                boolean success =
                        service.addVaccination(vaccination);

                if (success) {

                    table.setItems(
                            FXCollections.observableArrayList(
                                    service.getAllVaccinations()
                            )
                    );

                    petIdField.clear();
                    vaccineNameField.clear();
                    vaccinationDateField.clear();
                    nextDueDateField.clear();
                    notesField.clear();
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number for Pet ID."
                );
            }
        });

        clearButton.setOnAction(event -> {

            petIdField.clear();
            vaccineNameField.clear();
            vaccinationDateField.clear();
            nextDueDateField.clear();
            notesField.clear();
        });
        deleteButton.setOnAction(event -> {

            Vaccination selectedVaccination =
                    table.getSelectionModel().getSelectedItem();

            if (selectedVaccination != null) {

                boolean success =
                        service.deleteVaccination(
                                selectedVaccination.getVaccinationId()
                        );

                if (success) {

                    table.setItems(
                            FXCollections.observableArrayList(
                                    service.getAllVaccinations()
                            )
                    );
                }
            }
        });

        table.setItems(
                FXCollections.observableArrayList(
                        service.getAllVaccinations()
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

        Scene scene = new Scene(root, 1050, 600);

        stage.setTitle("PetCare - Vaccination Management");
        stage.setScene(scene);
        stage.show();
    }
}