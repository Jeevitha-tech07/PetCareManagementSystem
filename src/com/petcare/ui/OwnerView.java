package com.petcare.ui;

import com.petcare.model.PetOwner;
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

public class OwnerView {

    private final PetCareService service = new PetCareService();

    public void show() {

        Stage stage = new Stage();

        Label title = new Label("PET OWNER MANAGEMENT");

        title.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;"
        );

        TextField nameField = new TextField();
        nameField.setPromptText("Owner Name");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField addressField = new TextField();
        addressField.setPromptText("Address");

        Button addButton = new Button("Add Owner");
        Button clearButton = new Button("Clear");
        Button deleteButton = new Button("Delete Owner");
        
        TableView<PetOwner> table = new TableView<>();

        TableColumn<PetOwner, Integer> idColumn =
                new TableColumn<>("ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("ownerId")
        );

        TableColumn<PetOwner, String> nameColumn =
                new TableColumn<>("Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("ownerName")
        );

        TableColumn<PetOwner, String> phoneColumn =
                new TableColumn<>("Phone");

        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phone")
        );

        TableColumn<PetOwner, String> emailColumn =
                new TableColumn<>("Email");

        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        TableColumn<PetOwner, String> addressColumn =
                new TableColumn<>("Address");

        addressColumn.setCellValueFactory(
                new PropertyValueFactory<>("address")
        );

        table.getColumns().addAll(
                idColumn,
                nameColumn,
                phoneColumn,
                emailColumn,
                addressColumn
        );

        table.setPrefHeight(300);

        GridPane form = new GridPane();

        form.setHgap(10);
        form.setVgap(10);
        form.setAlignment(Pos.CENTER);

        form.add(new Label("Name:"), 0, 0);
        form.add(nameField, 1, 0);

        form.add(new Label("Phone:"), 2, 0);
        form.add(phoneField, 3, 0);

        form.add(new Label("Email:"), 0, 1);
        form.add(emailField, 1, 1);

        form.add(new Label("Address:"), 2, 1);
        form.add(addressField, 3, 1);

        form.add(addButton, 1, 2);
        form.add(clearButton, 2, 2);
        form.add(deleteButton, 3, 2);

        addButton.setOnAction(event -> {

            PetOwner owner = new PetOwner(
                    0,
                    nameField.getText(),
                    phoneField.getText(),
                    emailField.getText(),
                    addressField.getText()
            );

            boolean success = service.addOwner(owner);

            if (success) {

                table.setItems(
                        FXCollections.observableArrayList(
                                service.getAllOwners()
                        )
                );

                nameField.clear();
                phoneField.clear();
                emailField.clear();
                addressField.clear();
            }
        });

        clearButton.setOnAction(event -> {

            nameField.clear();
            phoneField.clear();
            emailField.clear();
            addressField.clear();
        });
        deleteButton.setOnAction(event -> {

            PetOwner selectedOwner = table.getSelectionModel().getSelectedItem();

            if (selectedOwner != null) {

                boolean success =
                        service.deleteOwner(selectedOwner.getOwnerId());

                if (success) {

                    table.setItems(
                            FXCollections.observableArrayList(
                                    service.getAllOwners()
                            )
                    );
                }
            }
        });

        table.setItems(
                FXCollections.observableArrayList(
                        service.getAllOwners()
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

        Scene scene = new Scene(root, 900, 600);

        stage.setTitle("PetCare - Pet Owner Management");
        stage.setScene(scene);
        stage.show();
    }
}