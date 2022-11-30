package com.example.phone_directory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import org.bson.Document;

import java.util.List;

public class Main extends Application {
    static List<Document> contacts;
    static DB db;

    @Override
    public void start(Stage stage) throws IOException {
        db = new DB();
        List<Document> contacts = db.getCollection("Contacts");
        Main.contacts = contacts;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Phone Directory");
        stage.setScene(scene);


//        MongoCursor<Document> it = ty.iterator();

//        int i = 1;
//        while(it.hasNext()) {
//            Object obj = it.next().get("name");
//            System.out.println(obj);
//        }

        stage.show();


    }

    public static void main(String[] args) {
        launch();

    }
}