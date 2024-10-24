module com.maskarad.pgpwalkietalkie {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.maskarad.pgpwalkietalkie to javafx.fxml;
    exports com.maskarad.pgpwalkietalkie;
}