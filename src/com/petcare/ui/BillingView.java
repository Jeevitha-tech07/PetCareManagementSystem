package com.petcare.ui;

import com.petcare.model.Bill;
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

public class BillingView {

    private final PetCareService service = new PetCareService();

    public void show() {

        Stage stage = new Stage();

        Label title = new Label("BILLING MANAGEMENT");

        title.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;"
        );

        TextField appointmentIdField = new TextField();
        appointmentIdField.setPromptText("Appointment ID");

        TextField consultationFeeField = new TextField();
        consultationFeeField.setPromptText("Consultation Fee");

        TextField medicineFeeField = new TextField();
        medicineFeeField.setPromptText("Medicine Fee");

        TextField otherChargesField = new TextField();
        otherChargesField.setPromptText("Other Charges");

        TextField paymentStatusField = new TextField();
        paymentStatusField.setPromptText("Pending / Paid");

        TextField paymentDateField = new TextField();
        paymentDateField.setPromptText("YYYY-MM-DD");

        Button addButton = new Button("Add Bill");
        Button clearButton = new Button("Clear");
        Button deleteButton = new Button("Delete Bill");

        TableView<Bill> table = new TableView<>();

        TableColumn<Bill, Integer> idColumn =
                new TableColumn<>("Bill ID");
        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("billId"));

        TableColumn<Bill, Integer> appointmentColumn =
                new TableColumn<>("Appointment ID");
        appointmentColumn.setCellValueFactory(
                new PropertyValueFactory<>("appointmentId"));

        TableColumn<Bill, Double> consultationColumn =
                new TableColumn<>("Consultation Fee");
        consultationColumn.setCellValueFactory(
                new PropertyValueFactory<>("consultationFee"));

        TableColumn<Bill, Double> medicineColumn =
                new TableColumn<>("Medicine Fee");
        medicineColumn.setCellValueFactory(
                new PropertyValueFactory<>("medicineFee"));

        TableColumn<Bill, Double> otherColumn =
                new TableColumn<>("Other Charges");
        otherColumn.setCellValueFactory(
                new PropertyValueFactory<>("otherCharges"));

        TableColumn<Bill, Double> totalColumn =
                new TableColumn<>("Total Amount");
        totalColumn.setCellValueFactory(
                new PropertyValueFactory<>("totalAmount"));

        TableColumn<Bill, String> statusColumn =
                new TableColumn<>("Payment Status");
        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>("paymentStatus"));

        TableColumn<Bill, String> dateColumn =
                new TableColumn<>("Payment Date");
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("paymentDate"));

        table.getColumns().addAll(
                idColumn,
                appointmentColumn,
                consultationColumn,
                medicineColumn,
                otherColumn,
                totalColumn,
                statusColumn,
                dateColumn
        );

        table.setPrefHeight(300);

        GridPane form = new GridPane();

        form.setHgap(10);
        form.setVgap(10);
        form.setAlignment(Pos.CENTER);

        form.add(new Label("Appointment ID:"), 0, 0);
        form.add(appointmentIdField, 1, 0);

        form.add(new Label("Consultation Fee:"), 2, 0);
        form.add(consultationFeeField, 3, 0);

        form.add(new Label("Medicine Fee:"), 0, 1);
        form.add(medicineFeeField, 1, 1);

        form.add(new Label("Other Charges:"), 2, 1);
        form.add(otherChargesField, 3, 1);

        form.add(new Label("Payment Status:"), 0, 2);
        form.add(paymentStatusField, 1, 2);

        form.add(new Label("Payment Date:"), 2, 2);
        form.add(paymentDateField, 3, 2);

        form.add(addButton, 1, 3);
        form.add(clearButton, 2, 3);
        form.add(deleteButton, 3, 3);

        addButton.setOnAction(event -> {

            try {

                double consultationFee =
                        Double.parseDouble(
                                consultationFeeField.getText());

                double medicineFee =
                        Double.parseDouble(
                                medicineFeeField.getText());

                double otherCharges =
                        Double.parseDouble(
                                otherChargesField.getText());

                Bill bill = new Bill(
                        0,
                        Integer.parseInt(
                                appointmentIdField.getText()),
                        consultationFee,
                        medicineFee,
                        otherCharges,
                        0,
                        paymentStatusField.getText(),
                        paymentDateField.getText()
                );

                boolean success =
                        service.addBill(bill);

                if (success) {

                    table.setItems(
                            FXCollections.observableArrayList(
                                    service.getAllBills()
                            )
                    );

                    appointmentIdField.clear();
                    consultationFeeField.clear();
                    medicineFeeField.clear();
                    otherChargesField.clear();
                    paymentStatusField.clear();
                    paymentDateField.clear();
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter valid numbers for Appointment ID and fees."
                );
            }
        });

        clearButton.setOnAction(event -> {

            appointmentIdField.clear();
            consultationFeeField.clear();
            medicineFeeField.clear();
            otherChargesField.clear();
            paymentStatusField.clear();
            paymentDateField.clear();
        });
        deleteButton.setOnAction(event -> {

            Bill selectedBill =
                    table.getSelectionModel().getSelectedItem();

            if (selectedBill != null) {

                boolean success =
                        service.deleteBill(
                                selectedBill.getBillId()
                        );

                if (success) {

                    table.setItems(
                            FXCollections.observableArrayList(
                                    service.getAllBills()
                            )
                    );
                }
            }
        });

        table.setItems(
                FXCollections.observableArrayList(
                        service.getAllBills()
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

        stage.setTitle("PetCare - Billing Management");
        stage.setScene(scene);
        stage.show();
    }
}