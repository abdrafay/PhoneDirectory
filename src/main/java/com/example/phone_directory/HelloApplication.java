package com.example.phone_directory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        MongoClient client = MongoClients.create("mongodb+srv://zrdatastore:PYSs2Xuydz7maLG5@cluster0.qnire.mongodb.net");
        MongoDatabase database = client.getDatabase("ProShop");
        MongoCollection<Document> toys = database.getCollection("products");

        FindIterable<Document> ty = toys.find();
        MongoCursor<Document> it = ty.iterator();
        int i = 1;
        while(it.hasNext()) {
            var obj = it.next().get("name");
            System.out.println(obj);
        }
        stage.show();


    }

    public static void main(String[] args) {
        launch();

    }
}