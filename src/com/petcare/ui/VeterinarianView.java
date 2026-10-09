package com.petcare.ui;

import com.petcare.model.Veterinarian;
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

public class VeterinarianView {

    private final PetCareService service = new PetCareService();

    public void show() {

        Stage stage = new Stage();

        Label title = new Label("VETERINARIAN MANAGEMENT");

        title.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;"
        );

        TextField nameField = new TextField();
        nameField.setPromptText("Veterinarian Name");

        TextField specializationField = new TextField();
        specializationField.setPromptText("Specialization");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField experienceField = new TextField();
        experienceField.setPromptText("Experience Years");

        Button addButton = new Button("Add Veterinarian");
        Button clearButton = new Button("Clear");
        Button deleteButton = new Button("Delete Veterinarian");

        TableView<Veterinarian> table = new TableView<>();

        TableColumn<Veterinarian, Integer> idColumn =
                new TableColumn<>("ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("vetId")
        );

        TableColumn<Veterinarian, String> nameColumn =
                new TableColumn<>("Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("vetName")
        );

        TableColumn<Veterinarian, String> specializationColumn =
                new TableColumn<>("Specialization");

        specializationColumn.setCellValueFactory(
                new PropertyValueFactory<>("specialization")
        );

        TableColumn<Veterinarian, String> phoneColumn =
                new TableColumn<>("Phone");

        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phone")
        );

        TableColumn<Veterinarian, String> emailColumn =
                new TableColumn<>("Email");

        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        TableColumn<Veterinarian, Integer> experienceColumn =
                new TableColumn<>("Experience");

        experienceColumn.setCellValueFactory(
                new PropertyValueFactory<>("experienceYears")
        );

        table.getColumns().addAll(
                idColumn,
                nameColumn,
                specializationColumn,
                phoneColumn,
                emailColumn,
                experienceColumn
        );

        table.setPrefHeight(300);

        GridPane form = new GridPane();

        form.setHgap(10);
        form.setVgap(10);
        form.setAlignment(Pos.CENTER);

        form.add(new Label("Name:"), 0, 0);
        form.add(nameField, 1, 0);

        form.add(new Label("Specialization:"), 2, 0);
        form.add(specializationField, 3, 0);

        form.add(new Label("Phone:"), 0, 1);
        form.add(phoneField, 1, 1);

        form.add(new Label("Email:"), 2, 1);
        form.add(emailField, 3, 1);

        form.add(new Label("Experience:"), 0, 2);
        form.add(experienceField, 1, 2);

        form.add(addButton, 2, 2);
        form.add(clearButton, 3, 2);
        form.add(deleteButton, 4, 2);

        addButton.setOnAction(event -> {

            try {

                Veterinarian veterinarian = new Veterinarian(
                        0,
                        nameField.getText(),
                        specializationField.getText(),
                        phoneField.getText(),
                        emailField.getText(),
                        Integer.parseInt(experienceField.getText())
                );

                boolean success =
                        service.addVeterinarian(veterinarian);

                if (success) {

                    table.setItems(
                            FXCollections.observableArrayList(
                                    service.getAllVeterinarians()
                            )
                    );

                    nameField.clear();
                    specializationField.clear();
                    phoneField.clear();
                    emailField.clear();
                    experienceField.clear();
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number for experience."
                );
            }
        });

        clearButton.setOnAction(event -> {

            nameField.clear();
            specializationField.clear();
            phoneField.clear();
            emailField.clear();
            experienceField.clear();
        });
        deleteButton.setOnAction(event -> {

            Veterinarian selectedVeterinarian =
                    table.getSelectionModel().getSelectedItem();

            if (selectedVeterinarian != null) {

                boolean success =
                        service.deleteVeterinarian(
                                selectedVeterinarian.getVetId()
                        );

                if (success) {

                    table.setItems(
                            FXCollections.observableArrayList(
                                    service.getAllVeterinarians()
                            )
                    );
                }
            }
        });

        table.setItems(
                FXCollections.observableArrayList(
                        service.getAllVeterinarians()
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

        Scene scene = new Scene(root, 1000, 600);

        stage.setTitle("PetCare - Veterinarian Management");
        stage.setScene(scene);
        stage.show();
    }
}