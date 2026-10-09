package com.petcare.ui;

import com.petcare.model.Pet;
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

public class PetView {

    private final PetCareService service = new PetCareService();

    public void show() {

        Stage stage = new Stage();

        Label title = new Label("PET MANAGEMENT");

        title.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;"
        );

        TextField ownerIdField = new TextField();
        ownerIdField.setPromptText("Owner ID");

        TextField nameField = new TextField();
        nameField.setPromptText("Pet Name");

        TextField speciesField = new TextField();
        speciesField.setPromptText("Species");

        TextField breedField = new TextField();
        breedField.setPromptText("Breed");

        TextField genderField = new TextField();
        genderField.setPromptText("Gender");

        TextField ageField = new TextField();
        ageField.setPromptText("Age");

        TextField weightField = new TextField();
        weightField.setPromptText("Weight");

        Button addButton = new Button("Add Pet");
        Button clearButton = new Button("Clear");
        Button deleteButton = new Button("Delete Pet");

        TableView<Pet> table = new TableView<>();

        TableColumn<Pet, Integer> idColumn =
                new TableColumn<>("ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("petId")
        );

        TableColumn<Pet, Integer> ownerColumn =
                new TableColumn<>("Owner ID");

        ownerColumn.setCellValueFactory(
                new PropertyValueFactory<>("ownerId")
        );

        TableColumn<Pet, String> nameColumn =
                new TableColumn<>("Pet Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("petName")
        );

        TableColumn<Pet, String> speciesColumn =
                new TableColumn<>("Species");

        speciesColumn.setCellValueFactory(
                new PropertyValueFactory<>("species")
        );

        TableColumn<Pet, String> breedColumn =
                new TableColumn<>("Breed");

        breedColumn.setCellValueFactory(
                new PropertyValueFactory<>("breed")
        );

        TableColumn<Pet, String> genderColumn =
                new TableColumn<>("Gender");

        genderColumn.setCellValueFactory(
                new PropertyValueFactory<>("gender")
        );

        TableColumn<Pet, Integer> ageColumn =
                new TableColumn<>("Age");

        ageColumn.setCellValueFactory(
                new PropertyValueFactory<>("age")
        );

        TableColumn<Pet, Double> weightColumn =
                new TableColumn<>("Weight");

        weightColumn.setCellValueFactory(
                new PropertyValueFactory<>("weight")
        );

        table.getColumns().addAll(
                idColumn,
                ownerColumn,
                nameColumn,
                speciesColumn,
                breedColumn,
                genderColumn,
                ageColumn,
                weightColumn
        );

        table.setPrefHeight(300);

        GridPane form = new GridPane();

        form.setHgap(10);
        form.setVgap(10);
        form.setAlignment(Pos.CENTER);

        form.add(new Label("Owner ID:"), 0, 0);
        form.add(ownerIdField, 1, 0);

        form.add(new Label("Pet Name:"), 2, 0);
        form.add(nameField, 3, 0);

        form.add(new Label("Species:"), 0, 1);
        form.add(speciesField, 1, 1);

        form.add(new Label("Breed:"), 2, 1);
        form.add(breedField, 3, 1);

        form.add(new Label("Gender:"), 0, 2);
        form.add(genderField, 1, 2);

        form.add(new Label("Age:"), 2, 2);
        form.add(ageField, 3, 2);

        form.add(new Label("Weight:"), 0, 3);
        form.add(weightField, 1, 3);

        form.add(addButton, 2, 3);
        form.add(clearButton, 3, 3);
        form.add(deleteButton, 4, 3);

        addButton.setOnAction(event -> {

            try {

                Pet pet = new Pet(
                        0,
                        Integer.parseInt(ownerIdField.getText()),
                        nameField.getText(),
                        speciesField.getText(),
                        breedField.getText(),
                        genderField.getText(),
                        Integer.parseInt(ageField.getText()),
                        Double.parseDouble(weightField.getText())
                );

                boolean success = service.addPet(pet);

                if (success) {

                    table.setItems(
                            FXCollections.observableArrayList(
                                    service.getAllPets()
                            )
                    );

                    ownerIdField.clear();
                    nameField.clear();
                    speciesField.clear();
                    breedField.clear();
                    genderField.clear();
                    ageField.clear();
                    weightField.clear();
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter valid numbers for Owner ID, Age and Weight."
                );
            }
        });

        clearButton.setOnAction(event -> {

            ownerIdField.clear();
            nameField.clear();
            speciesField.clear();
            breedField.clear();
            genderField.clear();
            ageField.clear();
            weightField.clear();
        });
        deleteButton.setOnAction(event -> {

            Pet selectedPet = table.getSelectionModel().getSelectedItem();

            if (selectedPet != null) {

                boolean success =
                        service.deletePet(selectedPet.getPetId());

                if (success) {

                    table.setItems(
                            FXCollections.observableArrayList(
                                    service.getAllPets()
                            )
                    );
                }
            }
        });

        table.setItems(
                FXCollections.observableArrayList(
                        service.getAllPets()
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

        Scene scene = new Scene(root, 1100, 650);

        stage.setTitle("PetCare - Pet Management");
        stage.setScene(scene);
        stage.show();
    }
}