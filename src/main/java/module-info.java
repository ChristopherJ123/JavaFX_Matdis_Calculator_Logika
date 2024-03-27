module com.example.javafx_matdis_calculator_logika {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx_matdis_calculator_logika to javafx.fxml;
    exports com.example.javafx_matdis_calculator_logika;
}