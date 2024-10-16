module ies.thiar {
    requires java.xml.bind;
    requires javafx.controls;
    requires javafx.fxml;

    opens ies.vista to javafx.fxml;
    exports ies.vista;
}
