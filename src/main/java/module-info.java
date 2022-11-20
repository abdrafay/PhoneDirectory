module com.example.phone_directory {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
//    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires java.desktop;

    opens com.example.phone_directory to javafx.fxml;
    exports com.example.phone_directory;
}