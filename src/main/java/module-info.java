module ies.thiar {
    requires java.xml.bind;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;
    requires java.sql;

    opens ies.vista to javafx.fxml;
    opens ies.modelo to java.xml.bind, com.opencsv;
    opens ies.controlador to java.xml.bind;
    
    exports ies.vista;
    exports ies.modelo to com.opencsv;
}
