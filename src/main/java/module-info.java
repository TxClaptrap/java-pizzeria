module ies.thiar {
    requires java.xml.bind;
    requires javafx.controls;
    requires javafx.fxml;

    opens ies.vista to javafx.fxml;
    opens ies.modelo to java.xml.bind;
    exports ies.vista;
}
