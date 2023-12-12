module com.vadim {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.vadim to javafx.fxml;
    opens com.vadim.fxml_controllers to javafx.fxml;
    opens com.vadim.tables to javafx.base;
    // opens com.vadim.tables to javafx.base;
    exports com.vadim;
    exports com.vadim.tables;
}
