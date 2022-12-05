package com.example.phone_directory;

import javafx.application.Application;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    static List<Document> contacts;
    static DB db = new DB();

    @Override
    public void start(Stage stage) throws IOException {
        Main.contacts = db.getCollection("Contacts");
//        String name = (String) Main.contacts.get(0).get("first_name")+(String) Main.contacts.get(0).get("last_name");
//        System.out.println(name);
        NAVLTree t = new NAVLTree();
        for (int i = 0; i < contacts.size(); i++) {
            t.insert(contacts.get(i));
        }
        var d = t.search();
        System.out.println(d);
//        LinkedList l  =t.InOrder();
//        l.print();
//        var doc = contacts.get(0);
//        long x = Long.parseLong((String)doc.get("phone_no"));
//        System.out.println(x);
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Phone Directory");
//        stage.setScene(scene);
//
//        stage.show();


    }

    public static void main(String[] args) {
        launch();

    }
}