package com.example.phone_directory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    static List<Document> contacts;
    static DB db = new DB();
    static FXMLLoader loadFXML(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
            return fxmlLoader;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        Main.contacts = db.getCollectionList("contacts");
        System.out.println("Hello");
        Scene scene = new Scene(loadFXML("main-view").load());
        stage.setTitle("Phone Directory");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

    }
}