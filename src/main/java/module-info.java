module com.maskarad.ratatoskr {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.maskarad.ratatoskr to javafx.fxml;
    exports com.maskarad.ratatoskr;
}