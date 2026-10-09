package com.petcare.ui;

import com.petcare.model.Appointment;
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

public class AppointmentView {

    private final PetCareService service = new PetCareService();

    public void show() {

        Stage stage = new Stage();

        Label title = new Label("APPOINTMENT MANAGEMENT");

        title.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;"
        );

        TextField petIdField = new TextField();
        petIdField.setPromptText("Pet ID");

        TextField vetIdField = new TextField();
        vetIdField.setPromptText("Veterinarian ID");

        TextField dateField = new TextField();
        dateField.setPromptText("YYYY-MM-DD");

        TextField timeField = new TextField();
        timeField.setPromptText("HH:MM:SS");

        TextField reasonField = new TextField();
        reasonField.setPromptText("Reason");

        TextField statusField = new TextField();
        statusField.setPromptText("Scheduled");

        Button addButton = new Button("Book Appointment");
        Button clearButton = new Button("Clear");
        Button deleteButton = new Button("Delete Appointment");

        TableView<Appointment> table = new TableView<>();

        TableColumn<Appointment, Integer> idColumn =
                new TableColumn<>("ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("appointmentId")
        );

        TableColumn<Appointment, Integer> petColumn =
                new TableColumn<>("Pet ID");

        petColumn.setCellValueFactory(
                new PropertyValueFactory<>("petId")
        );

        TableColumn<Appointment, Integer> vetColumn =
                new TableColumn<>("Vet ID");

        vetColumn.setCellValueFactory(
                new PropertyValueFactory<>("vetId")
        );

        TableColumn<Appointment, String> dateColumn =
                new TableColumn<>("Date");

        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("appointmentDate")
        );

        TableColumn<Appointment, String> timeColumn =
                new TableColumn<>("Time");

        timeColumn.setCellValueFactory(
                new PropertyValueFactory<>("appointmentTime")
        );

        TableColumn<Appointment, String> reasonColumn =
                new TableColumn<>("Reason");

        reasonColumn.setCellValueFactory(
                new PropertyValueFactory<>("reason")
        );

        TableColumn<Appointment, String> statusColumn =
                new TableColumn<>("Status");

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );

        table.getColumns().addAll(
                idColumn,
                petColumn,
                vetColumn,
                dateColumn,
                timeColumn,
                reasonColumn,
                statusColumn
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

        form.add(new Label("Date:"), 0, 1);
        form.add(dateField, 1, 1);

        form.add(new Label("Time:"), 2, 1);
        form.add(timeField, 3, 1);

        form.add(new Label("Reason:"), 0, 2);
        form.add(reasonField, 1, 2);

        form.add(new Label("Status:"), 2, 2);
        form.add(statusField, 3, 2);

        form.add(addButton, 1, 3);
        form.add(clearButton, 2, 3);
        form.add(deleteButton, 3, 3);

        addButton.setOnAction(event -> {

            try {

                Appointment appointment = new Appointment(
                        0,
                        Integer.parseInt(petIdField.getText()),
                        Integer.parseInt(vetIdField.getText()),
                        dateField.getText(),
                        timeField.getText(),
                        reasonField.getText(),
                        statusField.getText()
                );

                boolean success =
                        service.bookAppointment(appointment);

                if (success) {

                    table.setItems(
                            FXCollections.observableArrayList(
                                    service.getAllAppointments()
                            )
                    );

                    petIdField.clear();
                    vetIdField.clear();
                    dateField.clear();
                    timeField.clear();
                    reasonField.clear();
                    statusField.clear();
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
            dateField.clear();
            timeField.clear();
            reasonField.clear();
            statusField.clear();
        });
        deleteButton.setOnAction(event -> {

            Appointment selectedAppointment =
                    table.getSelectionModel().getSelectedItem();

            if (selectedAppointment != null) {

                boolean success =
                        service.deleteAppointment(
                                selectedAppointment.getAppointmentId()
                        );

                if (success) {

                    table.setItems(
                            FXCollections.observableArrayList(
                                    service.getAllAppointments()
                            )
                    );
                }
            }
        });

        table.setItems(
                FXCollections.observableArrayList(
                        service.getAllAppointments()
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

        stage.setTitle("PetCare - Appointment Management");
        stage.setScene(scene);
        stage.show();
    }
}