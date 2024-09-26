module ies.thiar {
    requires javafx.controls;
    requires javafx.fxml;

    opens ies.vista to javafx.fxml;
    exports ies.vista;
}
