module PetCareManagementSystem {

    requires java.sql;
    requires javafx.controls;
    requires javafx.graphics;

    exports com.petcare;
    exports com.petcare.model;
    exports com.petcare.dao;
    exports com.petcare.service;
    exports com.petcare.util;
}